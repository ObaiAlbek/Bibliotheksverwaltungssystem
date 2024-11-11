package domain;

import java.util.ArrayList;

import domain.Benutzer.Benutzer;
import domain.ExceptionsKlassen.MediumNichtGefundenException;
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
		MediumZumAusleihen medium = new MediumZumAusleihen();
		medium.getMedium(mediumAusleihen);
		benutzer.getAusgeliehenenMedien().add(medium);
		System.out.println(benutzer.getAusgeliehenenMedien().toString());
	}
	
	private Medium findMedium(String eindeutigeKennung) throws MediumNichtGefundenException {
		return mediums.stream()
		.filter(m -> m.getKennungNummer().equalsIgnoreCase(eindeutigeKennung))
		.findFirst()
		.orElseThrow(() -> new MediumNichtGefundenException("Das ausgewählte Medium ist nicht verfügbar"));
	}
}
