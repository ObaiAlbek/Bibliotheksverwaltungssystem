package domain.AusleiheSystem;

import java.util.*;

import domain.Benutzer.Benutzer;
import domain.ExceptionsKlassen.MediumNichtGefundenException;
import domain.Medium.*;

public class AusleiheSystem {
	
	private ArrayList<Medium> mediums; 
	private Date ausleiheBeginn;
	private Date ausleiheEnde;
	private Calendar calendar;
	private int wocheAnzahlZuAusleihen;

	
	public AusleiheSystem(ArrayList<Medium> mediums) {
		this.mediums = mediums;
	}
	
	public void mediumAusleihen(Benutzer benutzer, String eindutigenummer) throws MediumNichtGefundenException {
		Medium mediumAusleihen = findMedium(eindutigenummer);
		this.ausleiheBeginn = new Date();
		this.calendar = Calendar.getInstance();
		calendar.setTime(ausleiheBeginn);
		if (mediumAusleihen instanceof Buch || mediumAusleihen instanceof Videospiel)
			wocheAnzahlZuAusleihen = 4;

		else if (mediumAusleihen instanceof Dvd)
			wocheAnzahlZuAusleihen = 1;

		else if (mediumAusleihen instanceof Cd || mediumAusleihen instanceof Brettspiel)
			wocheAnzahlZuAusleihen = 2;

		calendar.add(Calendar.WEEK_OF_YEAR, wocheAnzahlZuAusleihen);
		this.ausleiheEnde = calendar.getTime();
		benutzer.ausleihen(new MediumZumAusleihen(mediumAusleihen,ausleiheBeginn,ausleiheEnde,wocheAnzahlZuAusleihen));
		

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
