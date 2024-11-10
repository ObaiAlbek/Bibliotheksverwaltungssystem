package domain;

import java.util.ArrayList;
import java.util.stream.Stream;

import domain.Benutzer.*;
import domain.ExceptionsKlassen.*;

public class BibSystem {
	private ArrayList<Benutzer> alleBibBenutzer;
	
	public BibSystem() {
		this.alleBibBenutzer = new ArrayList<>();
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
		if (tempUser == null)
			throw new  BenutzerNichtGefundenException("Benutzer mit Kartennummer " + kartennummer + " nicht gefunden");
		
		tempUser.setAngemeldet(true);
		return tempUser.isAngemeldet();
	}
	
	private Benutzer findBenutzer(int kartennummer) {
	    return alleBibBenutzer.stream()
	        .filter(k -> k.getBibAusweis().getKartenNummer() == kartennummer)
	        .findFirst() // nimmt das erste Element des Streams, das die Filterbedingung erfüllt.
	        .orElse(null);
	}

}
