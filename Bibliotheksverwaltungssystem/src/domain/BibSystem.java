package domain;

import java.util.ArrayList;
import java.util.stream.Stream;

import domain.AusleiheSystem.AusleiheSystem;
import domain.Benutzer.*;
import domain.ExceptionsKlassen.*;
import domain.Medium.Brettspiel;
import domain.Medium.Buch;
import domain.Medium.Medium;

public class BibSystem {
	private ArrayList<Benutzer> alleBibBenutzer;
	private ArrayList<Medium> alleMediums;
	private AusleiheSystem ausleiheSystem;
	
	public BibSystem() {
		
		this.alleBibBenutzer = new ArrayList<>();
		this.alleMediums = new ArrayList<>();
		this.ausleiheSystem = new AusleiheSystem(alleMediums);
		mediumAufladen();
	}
	
	public String userRegistrieren(String name, String type, int alter,String istAdmin) throws FalscheEingabeException {
		if (name.isEmpty() || type.isEmpty() || alter <= 0 || istAdmin.isEmpty())
		    throw new FalscheEingabeException("Geben Sie alle Felder korrekt ein");
		
		Benutzer benutzer;
		Ausweis ausweis = new Ausweis();
		boolean istStudentOderSchüler = (type.equalsIgnoreCase("schüler") || type.equalsIgnoreCase("student"))? true : false;	

		if (istAdmin.equalsIgnoreCase("nein")) 
			benutzer = new Mitarbeiter(ausweis,name,alter,istStudentOderSchüler);
		else
			benutzer = new Kunde(ausweis,name,alter,istStudentOderSchüler);
		
		alleBibBenutzer.add(benutzer);
		return benutzer.toString();
		
	}
	
	public boolean userAnmdelden(int kartennummer) throws BenutzerNichtGefundenException {
		Benutzer tempUser = findBenutzer(kartennummer);		
		tempUser.setAngemeldet(true);
		return tempUser.isAngemeldet();
	}
	
	public void mediumAusleihen(int kartennummer, String eindeutigeKennung) throws BenutzerNichtAngemeldetException, BenutzerNichtGefundenException, MediumNichtGefundenException {
		Benutzer tempBenutzer = findBenutzer(kartennummer);
		
		if (!checkIfUserImSystemAngemeldetIst(kartennummer)) 
			throw new BenutzerNichtAngemeldetException("Sie müssen sich erst im System anmelden");
		
		
		ausleiheSystem.mediumAusleihen(tempBenutzer,eindeutigeKennung);
		
	}
	
	private void mediumAufladen() {
		
		alleMediums.add(new Buch("B001","Effektives Java Programmieren",2018,"Joshua Bloch"));
		alleMediums.add(new Buch("B00","Effektives C++ Programmieren",2012,"XY Müller"));
		alleMediums.add(new Brettspiel("BG001","Die Siedler von Catan",2012,"XY Müller"));
	}
	
	
	
	
	
	
	
	
	
	
	private boolean checkIfUserImSystemAngemeldetIst(int kartennummer) {
		
		return alleBibBenutzer.stream()
				.anyMatch(user -> user.isAngemeldet());
				
	}
	
	private Benutzer findBenutzer(int kartennummer) throws BenutzerNichtGefundenException {
	    return alleBibBenutzer.stream()
	        .filter(k -> k.getBibAusweis().getKartenNummer() == kartennummer)
	        .findFirst() // nimmt das erste Element des Streams, das die Filterbedingung erfüllt.
	        .orElseThrow(() -> new  BenutzerNichtGefundenException ("Benutzer mit Kartennummer " + kartennummer + " nicht gefunden"));
	}

}
