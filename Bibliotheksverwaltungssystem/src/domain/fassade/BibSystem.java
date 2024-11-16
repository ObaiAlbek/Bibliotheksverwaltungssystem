package domain.fassade;

import java.util.*;
import domain.AusleiheSystem.*;
import domain.Benutzer.*;
import domain.Bibliothekskatalog.Mediensuchen;
import domain.ExceptionsKlassen.*;
import domain.Medium.*;
import domain.UserRegistieren.Registieren;

public class BibSystem {
	private ArrayList<Benutzer> alleBibBenutzer;
	private HashMap<String, Mediumverwalter> medien;
	private ArrayList<Ausleihe> ausleihe;
	private AusleiheSystem ausleiheSystem;
	private Benutzer bibAdmin;

	public BibSystem() {

		this.alleBibBenutzer = new ArrayList<>();
		this.medien = new HashMap<>();
		this.ausleiheSystem = new AusleiheSystem(medien);
		this.ausleihe = new ArrayList<>();
		bibAdmin = new Mitarbeiter(new Ausweis("A"),"XY Müller",20,false);
		mediumsAufladen();
	}
	
	public boolean gebührenBezahlen(double betrag, String bibKartennummer) throws BenutzerNichtGefundenException {
		Benutzer benutzer = findeBenutzer(bibKartennummer);
		double userBetrag = benutzer.getGebühren();
		if (betrag == userBetrag ) {
			((Mitarbeiter)bibAdmin).gebührVerbuchen(benutzer);
			return true;
		}
		return false;
	}
	
	public double jahresGebührenBerechnen(String bibKartennummer) throws BenutzerNichtGefundenException {
		Benutzer benutzer = findeBenutzer(bibKartennummer);
		return benutzer.jahresgebühren();
	}
	
	public double simuliereJahresGebührenBerechnen(String bibKartennummer, String datum) throws BenutzerNichtGefundenException {
		Benutzer benutzer = findeBenutzer(bibKartennummer);
		return benutzer.simuliereJahresGebühren(datum);
	}
	
	public ArrayList<String> medienRückgabe(String eindeutigeKennung) {
		return ausleiheSystem.mediumRückgabe(ausleihe, eindeutigeKennung);
	}

	public double simuliereMedienRückgabe(String eindeutigeKennung, String datum) throws MediumNichtGefundenException {
		return ausleiheSystem.SimulieremediumRückgabe(ausleihe, eindeutigeKennung, datum);
	}

	public boolean medienVerlängern(String eindeutigeKennung, String bibKartennummer) throws BenutzerNichtGefundenException, MediumNichtGefundenException {
		Benutzer benutzer = findeBenutzer(bibKartennummer);
		return ausleiheSystem.medienVerlängern(benutzer,eindeutigeKennung);
	}

	public ArrayList<String> mediumDurchsuchen(String auswahl, String bibKartenNummer)
			throws FalscheEingabeException, MediumNichtGefundenException, BenutzerNichtAngemeldetException {
		if (!checkIfUserImSystemAngemeldetIst(bibKartenNummer))
			throw new BenutzerNichtAngemeldetException("Sie müssen sich erst im System anmelden");

		ArrayList<String> treffer = new ArrayList<>();
		Mediensuchen medienSuchen = new Mediensuchen();

		if (auswahl.equalsIgnoreCase("ausgeliehen"))
			treffer = medienSuchen.ausgeliehen(auswahl, medien);

		else if (auswahl.equalsIgnoreCase("nicht Ausgeliehen"))
			treffer = medienSuchen.nichtAusgeliehen(auswahl, medien);

		else if (auswahl.equalsIgnoreCase("Bücher") || auswahl.equalsIgnoreCase("Brettspiele")
				|| auswahl.equalsIgnoreCase("Dvds") || auswahl.equalsIgnoreCase("Cds")
				|| auswahl.equalsIgnoreCase("Videospiele"))
			treffer = medienSuchen.medienart(auswahl, medien);

		else
			treffer = medienSuchen.title(auswahl, medien);

		if (treffer.size() == 0)
			throw new MediumNichtGefundenException("Kein treffer");

		return treffer;
	}

	public void userRegistrieren(String name, String type, int alter, String istAdmin) throws FalscheEingabeException {
		Benutzer bibBenutzer = Registieren.userRegistrieren(name, type, alter, istAdmin);
		alleBibBenutzer.add(bibBenutzer);
	}

	public boolean userAnmdelden(String bibKartenNummer) throws BenutzerNichtGefundenException {
		Benutzer bibBenutzer = findeBenutzer(bibKartenNummer);
		bibBenutzer.anmelden();
		return bibBenutzer.isAngemeldet();
	}

	public String mediumAusleihen(String bibKartenNummer, String eindeutigeKennung) throws Exception {
		Benutzer bibBenutzer = findeBenutzer(bibKartenNummer);

		if (bibBenutzer instanceof Mitarbeiter)
			throw new Exception("Mitarbeiter können keine Mediums ausleihen!");

		if (!checkIfUserImSystemAngemeldetIst(bibKartenNummer))
			throw new BenutzerNichtAngemeldetException("Sie müssen sich erst im System anmelden");

		Ausleihe neueAusleihe = ausleiheSystem.mediumAusleihen(bibBenutzer, eindeutigeKennung);
		ausleihe.add(neueAusleihe);
		bibBenutzer.ausleihen(neueAusleihe);
		return "Das Medium wurde erfolgreich ausgeliehen";
	}
	
	public void mediumsAufladen(String type, String ID, String title, int erscheinungsjahr,String ersteller,String verlängbar,int anzahl,int leihdauer) throws FalscheEingabeException {
		Mediumverwalter medium;
		boolean verlängerung = (verlängbar.equalsIgnoreCase("ja")) ? true: false;
		switch (type) {
			case "Buch":
				medium = new Mediumverwalter(new Buch(ID,title,erscheinungsjahr,ersteller),verlängerung,anzahl,leihdauer);
				break;
			
			case "Cd":
				medium = new Mediumverwalter(new Cd(ID,title,erscheinungsjahr,ersteller),verlängerung,anzahl,leihdauer);
				break;
				
			case "Brettspiel":
				medium = new Mediumverwalter(new Brettspiel(ID,title,erscheinungsjahr,ersteller),verlängerung,anzahl,leihdauer);
				break;
				
			case "Dvd":
				medium = new Mediumverwalter(new Dvd(ID,title,erscheinungsjahr,ersteller),verlängerung,anzahl,leihdauer);
				break;
				
			case "Videospiel":
				medium = new Mediumverwalter(new Videospiel(ID,title,erscheinungsjahr,ersteller),verlängerung,anzahl,leihdauer);
				break;
				
			default:
				throw new FalscheEingabeException("Falsch Eingabe");
		}
		
		String getMediumID = medium.getMedium().getID();
		medien.put(getMediumID, medium);
		

	}
	
	// Test Methode
	private void mediumsAufladen() {
		Mediumverwalter buch = new Mediumverwalter(new Buch("B001", "Effektives Java Programmieren", 2018, "Joshua Bloch"),true, 10, 28);
		medien.put(buch.getMedium().getID(), buch);

		Mediumverwalter buchIStAusgeliehen = new Mediumverwalter(new Buch("B00", "Effektives C++ Programmieren", 2018, "Joshua Bloch"),true, 10, 28);
		buchIStAusgeliehen.setIstAusgeliehen(true);
		medien.put(buchIStAusgeliehen.getMedium().getID(), buchIStAusgeliehen);

		Mediumverwalter buchIStNichtAusgeliehen = new Mediumverwalter(new Buch("BG001", "Javascript lenren", 2018, "Joshua Bloch"),true, 10, 28);
		medien.put(buchIStNichtAusgeliehen.getMedium().getID(), buchIStNichtAusgeliehen);

		Mediumverwalter Videospiel = new Mediumverwalter(new Videospiel("BG00122", "The Legend of Zelda: Breath of the Wild", 2017, "Nintendo Switch"),true, 2, 28);
		medien.put(Videospiel.getMedium().getID(), Videospiel);
	}

	private boolean checkIfUserImSystemAngemeldetIst(String bibKartenNummer) {
		return alleBibBenutzer.stream().anyMatch(user -> user.isAngemeldet());
	}

	private Benutzer findeBenutzer(String bibKartenNummer) throws BenutzerNichtGefundenException {
		return alleBibBenutzer.stream()
				.filter(k -> k.getBibAusweis().getKartenNummer().equalsIgnoreCase(bibKartenNummer)).findFirst()
				.orElseThrow(() -> new BenutzerNichtGefundenException(
						"Benutzer mit Kartennummer " + bibKartenNummer + " nicht gefunden"));
	}

}
