package domain.ausleihSystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import domain.benutzer.Benutzer;
import domain.exceptionsKlassen.MediumNichtGefundenException;
import domain.medium.*;

public class AusleiheSystem {

	private HashMap<String, Mediumverwalter> medien;
	private LocalDate ausleiheBeginn, ausleiheEnde;
	
	public AusleiheSystem(HashMap<String, Mediumverwalter> medien) {
		this.medien = medien;
	}
	
	
	public Ausleihe mediumAusleihen(Benutzer benutzer, String eindeutigenummer) throws MediumNichtGefundenException {
		Mediumverwalter mediumAusleihen = findMedium(eindeutigenummer);

		if (mediumAusleihen.isIstAusgeliehen())
			throw new MediumNichtGefundenException("Das Medium ist ausgeliehen");

		mediumAusleihen.setIstAusgeliehen(true);
		mediumAusleihen.setAnzahl(mediumAusleihen.getAnzahl() - 1);

		this.ausleiheBeginn = LocalDate.now();

		this.ausleiheEnde = ausleiheBeginn.plusDays(mediumAusleihen.getLeihdauer());

		Ausleihe neueAusleihe = new Ausleihe(mediumAusleihen, benutzer, ausleiheBeginn, ausleiheEnde);

		return neueAusleihe;
	}

	public ArrayList<String> mediumRückgabe(ArrayList<Ausleihe> ausleihe, String eindeutigeKennung) {
		Ausleihe ausgelieheneMedium = ausleihe.stream()
				.filter(k -> k.getMediumverwalter().getMedium().getID().equalsIgnoreCase(eindeutigeKennung))
				.findFirst()
				.orElse(null);

		ArrayList<String> ausgeliehenMedien = new ArrayList<>();
		LocalDate heutigesDatum;
		Benutzer bibBenutzer = ausgelieheneMedium.getBenutzer();
		
		if (ausgelieheneMedium != null) {
			heutigesDatum = LocalDate.now();
			if (heutigesDatum.isAfter(ausgelieheneMedium.getAusleiheEnde())) {
				long überfälligeTage = ausgelieheneMedium.getAusleiheEnde().until(heutigesDatum, ChronoUnit.DAYS);
				double gebühren = 0.0;
				if (überfälligeTage <= 7)
					gebühren = überfälligeTage * 1.0;

				else {
					gebühren = (7 * 1.0);
					gebühren += ((überfälligeTage - 7) * 2.0);

				}

				bibBenutzer.setGebühren(bibBenutzer.getGebühren() + gebühren);

			}
			ausgelieheneMedium.getBenutzer().mediumZurückgeben(ausgelieheneMedium);
			ausleihe.remove(ausgelieheneMedium);
			ausgelieheneMedium.getMediumverwalter().setIstAusgeliehen(false);
			ausgelieheneMedium.getMediumverwalter().setAnzahl(ausgelieheneMedium.getMediumverwalter().getAnzahl() + 1);
			for (Ausleihe a : ausgelieheneMedium.getBenutzer().getAusgeliehenenMedien())
				ausgeliehenMedien.add(a.toString());

		}

		return ausgeliehenMedien;
	}

	public double SimulieremediumRückgabe(ArrayList<Ausleihe> ausleihe, String eindeutigeKennung, String ausleiheBeginn, String ausleiheEnde,String datum) throws MediumNichtGefundenException {
		Ausleihe ausgelieheneMedium = ausleihe.stream()
				.filter(k -> k.getMediumverwalter().getMedium().getID().equalsIgnoreCase(eindeutigeKennung)).findFirst()
				.orElse(null);
		
		LocalDate beginn = LocalDate.parse(ausleiheBeginn);
		LocalDate ende = LocalDate.parse(ausleiheEnde);
		LocalDate heutigesDatum = LocalDate.parse(datum);
		Benutzer bibBenutzer = ausgelieheneMedium.getBenutzer();
		
		ausgelieheneMedium.setAusleiheBeginn(beginn);
		ausgelieheneMedium.setAusleiheEnde(ende);
		
		if (heutigesDatum.isAfter(ausgelieheneMedium.getAusleiheEnde())) {
			long überfälligeTage = ausgelieheneMedium.getAusleiheEnde().until(heutigesDatum, ChronoUnit.DAYS);
			double gebühren = 0.0;
			if (überfälligeTage <= 7)
				gebühren = überfälligeTage * 1.0;

			else {
				gebühren = (7 * 1.0);
				gebühren += ((überfälligeTage - 7) * 2.0);

			}

			bibBenutzer.setGebühren(bibBenutzer.getGebühren() + gebühren);
		}
		
		return bibBenutzer.getGebühren();
	}

	public boolean medienVerlängern(Benutzer benutzer, String eindeutigeKennung) throws MediumNichtGefundenException {
		Ausleihe medium = benutzer.getAusgeliehenenMedien().stream()
				.filter(m -> m.getMediumverwalter().getMedium().getID().equalsIgnoreCase(eindeutigeKennung)).findFirst()
				.orElse(null);

		if (medium == null)
			return false;

		LocalDate heutigesDatum = LocalDate.now();
		if (heutigesDatum.isAfter(medium.getAusleiheEnde()))
			return false;

		if (!medium.getMediumverwalter().isVerlängerbar())
			return false;

		if (medium.getVerlängerungen() == 3)
			return false;

		medium.setVerlängerungen(medium.getVerlängerungen() + 1);
		medium.setAusleiheBeginn(LocalDate.now());
		medium.setAusleiheEnde(LocalDate.now().plusDays(medium.getMediumverwalter().getLeihdauer()));
		
		return true;
	}

	
	
	private Mediumverwalter findMedium(String eindeutigeKennung) throws MediumNichtGefundenException {
		if (medien.containsKey(eindeutigeKennung))
			return medien.get(eindeutigeKennung);
		else
			throw new MediumNichtGefundenException("Das ausgewählte Medium ist nicht verfügbar");

	}

}
