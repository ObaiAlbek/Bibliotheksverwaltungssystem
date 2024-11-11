package domain.AusleiheSystem;

import java.util.ArrayList;

import domain.Benutzer.Benutzer;
import domain.ExceptionsKlassen.MediumNichtGefundenException;
import domain.Medium.Brettspiel;
import domain.Medium.Medium;

public class AusleiheSystem {
	
	private ArrayList<Medium> mediums; 
	private Benutzer benutzer;
	
	public AusleiheSystem(ArrayList<Medium> mediums) {
		this.mediums = mediums;
	}
	
	public void mediumAusleihen(Benutzer benutzer, String eindutigenummer) throws MediumNichtGefundenException {
		this.benutzer = benutzer;
		Medium mediumAusleihen = findMedium(eindutigenummer);
		MediumZumAusleihen medium;
		if (mediumAusleihen instanceof Brettspiel)
			
			medium = new MediumZumAusleihen(false,mediumAusleihen);
		else 
			medium = new MediumZumAusleihen(true,mediumAusleihen);
			
		benutzer.ausleihen(medium);
		benutzer.getAusgeliehenenMedien().stream()
		.forEach(System.out::println);
		
		
	}
	
	private Medium findMedium(String eindeutigeKennung) throws MediumNichtGefundenException {
		return mediums.stream()
		.filter(m -> m.getKennungNummer().equalsIgnoreCase(eindeutigeKennung))
		.findFirst()
		.orElseThrow(() -> new MediumNichtGefundenException("Das ausgewählte Medium ist nicht verfügbar"));
	}
}
