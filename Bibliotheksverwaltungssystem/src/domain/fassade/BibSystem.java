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
		this.bibAdmin = new Mitarbeiter(new Ausweis("A"),"Xy Müller",20,false);
		mediumsAufladen();
	}
	
	// Registrierung
	public void userRegistrieren(String name, String type, int alter, String istAdmin) throws FalscheEingabeException {
		Benutzer bibBenutzer = Registieren.userRegistrieren(name, type, alter, istAdmin);
		alleBibBenutzer.add(bibBenutzer);
	}
	
	// Anmeldung
	public double userAnmelden(String bibKartenNummer) throws BenutzerNichtGefundenException {
		Benutzer bibBenutzer = findeBenutzer(bibKartenNummer);
		bibBenutzer.anmelden();
		return bibBenutzer.getGebühren();
	}
	
	
	
	// Mediums Durchsuchen
	public ArrayList<String> mediumDurchsuchen(String auswahl, String bibKartenNummer) throws FalscheEingabeException, MediumNichtGefundenException, BenutzerNichtAngemeldetException, BenutzerNichtGefundenException {
		Benutzer benutzer = findeBenutzer(bibKartenNummer);
		
		if (benutzer.abmelden())
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
		
		else if (auswahl.equalsIgnoreCase("ja")) {
			treffer = baldVerfügbareMedien(ausleihe);
		}
			
		else
			treffer = medienSuchen.title(auswahl, medien);

		if (treffer.size() == 0)
			throw new MediumNichtGefundenException("Kein treffer");

		return treffer;
	}
	
	
	/*
	 * 	Aufgaben des Leihsystems:
	 * 		1. Mediums ausleihen lassen
	 * 		2. Mediums verlängern
	 * 		3. Mediums Rückgabe
	 */
	
	// Mediums Ausleihen
	public double mediumAusleihen(String bibKartenNummer, String eindeutigeKennung) throws Exception {
		Benutzer bibBenutzer = findeBenutzer(bibKartenNummer);

		if (bibBenutzer instanceof Mitarbeiter)
			throw new Exception("Mitarbeiter können keine Mediums ausleihen!");

		if (bibBenutzer.abmelden())
			throw new BenutzerNichtAngemeldetException("Sie müssen sich erst im System anmelden");

		Ausleihe neueAusleihe = ausleiheSystem.mediumAusleihen(bibBenutzer, eindeutigeKennung);
		ausleihe.add(neueAusleihe);
		bibBenutzer.ausleihen(neueAusleihe);
		return bibBenutzer.getGebühren();
	}
	
	// Mediums verlängern
	public boolean medienVerlängern(String eindeutigeKennung, String bibKartennummer) throws BenutzerNichtGefundenException, MediumNichtGefundenException {
		Benutzer benutzer = findeBenutzer(bibKartennummer);
		return ausleiheSystem.medienVerlängern(benutzer,eindeutigeKennung);
	}
		
	// Admin meldet sich an
	public boolean adminAnmelden(String bibKartennummerAdmin){
		this.bibAdmin.anmelden();		
		return this.bibAdmin.isAngemeldet();
	}
	
	
	// aktuelle Gebühren der Benutzer
	public double getgbührenBenutzer(String bibKartennummer) throws BenutzerNichtGefundenException {
		Benutzer benutzer = findeBenutzer(bibKartennummer);
		return benutzer.getGebühren();
	}
	
	// akteulle ausgelihene Mediums der Benutzer
	public ArrayList<String> ausgeliehenGegenstände(String bibKartennummer) throws BenutzerNichtGefundenException {
		Benutzer bibUser = findeBenutzer(bibKartennummer);
		ArrayList<String> treffer = new ArrayList<>();
		for (Ausleihe ausleihe : bibUser.getAusgeliehenenMedien())
			treffer.add(ausleihe.toString());

		return treffer;

	}
	
	public double gebührenVerbuchen(String bibKartennummer) throws BenutzerNichtGefundenException {
		Benutzer bibUser = findeBenutzer(bibKartennummer);
		((Mitarbeiter)this.bibAdmin).gebührVerbuchen(bibUser);
		return bibUser.getGebühren();
	}
	
	// Mediums Rückgabe 
	public ArrayList<String> medienRückgabe(String eindeutigeKennung) {
		return ausleiheSystem.mediumRückgabe(ausleihe, eindeutigeKennung);
	}
	
	// Simuliere Datum
	public double datumÄndern(String eindeutigeKennung,String ausleiheBeginn, String ausleiheEnde,String datum) throws MediumNichtGefundenException {
		return ausleiheSystem.SimulieremediumRückgabe(ausleihe, eindeutigeKennung, ausleiheBeginn, ausleiheEnde, datum);
	}
	
	// jahresGebühren berechnen
	public double jahresGebührenBerechnen(String bibKartennummer, String datum) throws BenutzerNichtGefundenException {
		Benutzer benutzer = findeBenutzer(bibKartennummer);
		return benutzer.jahresgebühren(datum);
	}
	
	
	// Mediums Aufladen 
	public void mediumsAufladen(String type, String ID, String title, int erscheinungsjahr, String ersteller,
			String verlängbar, int anzahl, int leihdauer) throws FalscheEingabeException {
		Mediumverwalter medium;
		boolean verlängerung = (verlängbar.equalsIgnoreCase("ja")) ? true : false;
		switch (type) {
		case "Buch":
			medium = new Mediumverwalter(new Buch(ID, title, erscheinungsjahr, ersteller), verlängerung, anzahl,
					leihdauer);
			break;

		case "Cd":
			medium = new Mediumverwalter(new Cd(ID, title, erscheinungsjahr, ersteller), verlängerung, anzahl,
					leihdauer);
			break;

		case "Brettspiel":
			medium = new Mediumverwalter(new Brettspiel(ID, title, erscheinungsjahr, ersteller), verlängerung, anzahl,
					leihdauer);
			break;

		case "Dvd":
			medium = new Mediumverwalter(new Dvd(ID, title, erscheinungsjahr, ersteller), verlängerung, anzahl,
					leihdauer);
			break;

		case "Videospiel":
			medium = new Mediumverwalter(new Videospiel(ID, title, erscheinungsjahr, ersteller), verlängerung, anzahl,
					leihdauer);
			break;

		default:
			throw new FalscheEingabeException("Falsch Eingabe");
		}

		medien.put(ID, medium);

	}
	
	/*
	 *  => Hilfsmethoden
	 */
	
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

	

	public Benutzer findeBenutzer(String bibKartenNummer) throws BenutzerNichtGefundenException {
		return alleBibBenutzer.stream()
				.filter(k -> k.getBibAusweis().getKartenNummer().equalsIgnoreCase(bibKartenNummer)).findFirst()
				.orElseThrow(() -> new BenutzerNichtGefundenException(
						"Benutzer mit Kartennummer " + bibKartenNummer + " nicht gefunden"));
	}

}
