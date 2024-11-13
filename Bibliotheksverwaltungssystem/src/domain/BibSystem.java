package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.Stream;

import domain.AusleiheSystem.Ausleihe;
import domain.AusleiheSystem.AusleiheSystem;
import domain.Benutzer.*;
import domain.ExceptionsKlassen.*;
import domain.Medium.Brettspiel;
import domain.Medium.Buch;
import domain.Medium.Medium;
import domain.Medium.Mediumverwalter;
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
	
	public void userRegistrieren(String name, String type, int alter,String istAdmin) throws FalscheEingabeException {
		Benutzer bibbenutzer = Registieren.userRegistrieren(name, type, alter, istAdmin);
		alleBibBenutzer.add(bibbenutzer);	
	}
	
	public boolean userAnmdelden(String kartennummer) throws BenutzerNichtGefundenException {
		Benutzer tempUser = findBenutzer(kartennummer);		
		tempUser.setAngemeldet(true);
		return tempUser.isAngemeldet();
	}
	
	public void mediumAusleihen(String kartennummer, String eindeutigeKennung) throws Exception {
		Benutzer tempBenutzer = findBenutzer(kartennummer);
		
		if (tempBenutzer instanceof Mitarbeiter)
			throw new Exception("Mitarbeiter können keine Mediums ausleihen!");
		
		if (!checkIfUserImSystemAngemeldetIst(kartennummer)) 
			throw new BenutzerNichtAngemeldetException("Sie müssen sich erst im System anmelden");
		
		Ausleihe neueAusleihe = ausleiheSystem.mediumAusleihen(tempBenutzer,eindeutigeKennung);
		ausleihe.add(neueAusleihe);
		
	}
	
	private void mediumsAufladen() {
		medien.put("B001",new Mediumverwalter (true,10,4, new Buch("Effektives Java Programmieren",2018,"Joshua Bloch")));
		medien.put("B00", new Mediumverwalter (true,10,4, new Buch("Effektives Java Programmieren",2018,"Joshua Bloch")));
		medien.put("BG001", new Mediumverwalter (false,10,4, new Brettspiel("Die Siedler von Catan",2012,"XY Müller")));
		
	}
	
		
	private boolean checkIfUserImSystemAngemeldetIst(String kartennummer) {
		
		return alleBibBenutzer.stream()
				.anyMatch(user -> user.isAngemeldet());
				
	}
	
	private Benutzer findBenutzer(String kartennummer) throws BenutzerNichtGefundenException {
	    return alleBibBenutzer.stream()
	        .filter(k -> k.getBibAusweis().getKartenNummer().equalsIgnoreCase(kartennummer))
	        .findFirst() // nimmt das erste Element des Streams, das die Filterbedingung erfüllt.
	        .orElseThrow(() -> new  BenutzerNichtGefundenException ("Benutzer mit Kartennummer " + kartennummer + " nicht gefunden"));
	}

}
