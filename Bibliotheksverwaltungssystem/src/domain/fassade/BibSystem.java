package domain.fassade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.AusleiheSystem.Ausleihe;
import domain.AusleiheSystem.AusleiheSystem;
import domain.Benutzer.*;
import domain.ExceptionsKlassen.*;
import domain.MedienDurchsuchen.Mediensuchen;
import domain.Medium.Brettspiel;
import domain.Medium.Buch;
import domain.Medium.Medium;
import domain.Medium.Mediumverwalter;
import domain.Medium.Videospiel;
import domain.UserRegistieren.Registieren;

public class BibSystem {
	private ArrayList<Benutzer> alleBibBenutzer;
	private HashMap<String,Mediumverwalter> medien;
	private ArrayList<Ausleihe> ausleihe;
	private AusleiheSystem ausleiheSystem;

	public BibSystem() {

		this.alleBibBenutzer = new ArrayList<>();
		this.medien = new HashMap<>();
		this.ausleiheSystem = new AusleiheSystem(medien);
		mediumsAufladen();
	}
	
	public ArrayList<String> mediumDurchsuchen(String auswahl, String bibKartenNummer) throws FalscheEingabeException, MediumNichtGefundenException, BenutzerNichtAngemeldetException {
		if (!checkIfUserImSystemAngemeldetIst(bibKartenNummer)) 
			throw new BenutzerNichtAngemeldetException("Sie müssen sich erst im System anmelden");		
		
		ArrayList<String> treffer = new ArrayList<>(); 
		Mediensuchen medienSuchen = new Mediensuchen();
		
		if(auswahl.equalsIgnoreCase("ausgeliehen"))
			treffer = medienSuchen.ausgeliehen(auswahl, medien);
		
		else if(auswahl.equalsIgnoreCase("nicht Ausgeliehen"))
			treffer = medienSuchen.nichtAusgeliehen(auswahl, medien);
	
		else if(auswahl.equalsIgnoreCase("Bücher") || auswahl.equalsIgnoreCase("Brettspiele") || auswahl.equalsIgnoreCase("Dvds") || auswahl.equalsIgnoreCase("Cds") || auswahl.equalsIgnoreCase("Videospiele") )
			treffer = medienSuchen.medienart(auswahl, medien);
		
		else
			treffer = medienSuchen.title(auswahl, medien);
			
		
		if (treffer.size() == 0)
			throw new MediumNichtGefundenException("Kein treffer");
		
		return treffer;

	}
	
	public void userRegistrieren(String name, String type, int alter,String istAdmin) throws FalscheEingabeException {
		Benutzer bibBenutzer = Registieren.userRegistrieren(name, type, alter, istAdmin);
		alleBibBenutzer.add(bibBenutzer);	
	}
	
	public boolean userAnmdelden(String bibKartenNummer) throws BenutzerNichtGefundenException {
		Benutzer bibBenutzer = findeBenutzer(bibKartenNummer);		
		bibBenutzer.setAngemeldet(true);
		return bibBenutzer.isAngemeldet();
	}
	
	public void mediumAusleihen(String bibKartenNummer, String eindeutigeKennung) throws Exception {
		Benutzer tempBenutzer = findeBenutzer(bibKartenNummer);
		
		if (tempBenutzer instanceof Mitarbeiter)
			throw new Exception("Mitarbeiter können keine Mediums ausleihen!");
		
		if (!checkIfUserImSystemAngemeldetIst(bibKartenNummer)) 
			throw new BenutzerNichtAngemeldetException("Sie müssen sich erst im System anmelden");
		
		Ausleihe neueAusleihe = ausleiheSystem.mediumAusleihen(tempBenutzer,eindeutigeKennung);
		ausleihe.add(neueAusleihe);
		
	}
	
	
	
	
	
	// Temporäre Test Methode
	private void mediumsAufladen() {
		Mediumverwalter buch = new Mediumverwalter(true, 10, 4, new Buch("Effektives Java Programmieren", 2018, "Joshua Bloch"));
		medien.put("B001",buch);
		
		Mediumverwalter buchIStAusgeliehen = new Mediumverwalter(true, 10, 4, new Buch("Effektives C++ Programmieren", 2018, "Joshua Bloch"));
		buchIStAusgeliehen.setIstAusgeliehen(true);
		medien.put("B00", buchIStAusgeliehen);
		
		Mediumverwalter buchIStNichtAusgeliehen = new Mediumverwalter(true, 10, 4, new Buch("Javascript lenren", 2018, "Joshua Bloch"));
		medien.put("BG001",buchIStNichtAusgeliehen);
		
		Mediumverwalter Videospiel = new Mediumverwalter(true,2,1,new Videospiel ("The Legend of Zelda: Breath of the Wild", 2017,"Nintendo Switch"));
		medien.put("BG00122",Videospiel);
	}

	private boolean checkIfUserImSystemAngemeldetIst(String bibKartenNummer) {
		return alleBibBenutzer.stream().anyMatch(user -> user.isAngemeldet());
	}

	private Benutzer findeBenutzer(String bibKartenNummer) throws BenutzerNichtGefundenException {
		return alleBibBenutzer.stream()
				.filter(k -> k.getBibAusweis().getKartenNummer().equalsIgnoreCase(bibKartenNummer)).findFirst()
				.orElseThrow(() -> new BenutzerNichtGefundenException("Benutzer mit Kartennummer " + bibKartenNummer + " nicht gefunden"));
	}

}
