package domain.AusleiheSystem;

import java.util.*;

import domain.Benutzer.Benutzer;
import domain.ExceptionsKlassen.MediumNichtGefundenException;
import domain.Medium.*;

public class AusleiheSystem {
	
	private  HashMap<String,Mediumverwalter> medien;
	private Date ausleiheBeginn;
	private Date ausleiheEnde;
	private Calendar calendar;

	
	public AusleiheSystem( HashMap<String,Mediumverwalter> medien) {
		this.medien = medien;
	}
	
	public Ausleihe mediumAusleihen(Benutzer benutzer, String eindutigenummer) throws MediumNichtGefundenException {
		Mediumverwalter mediumAusleihen = findMedium(eindutigenummer);
		if (mediumAusleihen.isIstAusgeliehen())
			throw new MediumNichtGefundenException("Das Medium ist ausgeliehen");
		
		mediumAusleihen.setIstAusgeliehen(true);
		mediumAusleihen.setAnzahl(mediumAusleihen.getAnzahl() - 1);
		this.ausleiheBeginn = new Date();
		this.calendar = Calendar.getInstance();
		calendar.setTime(ausleiheBeginn);

		calendar.add(Calendar.WEEK_OF_YEAR, mediumAusleihen.getLeihdauer());
		this.ausleiheEnde = calendar.getTime();
		Ausleihe neueAusleihe = new Ausleihe(mediumAusleihen,ausleiheBeginn,ausleiheEnde);
		benutzer.ausleihen(neueAusleihe);
		
		return neueAusleihe;
		
	}
	
	private Mediumverwalter findMedium(String eindeutigeKennung) throws MediumNichtGefundenException {
	    if (medien.containsKey(eindeutigeKennung)) 
	        return medien.get(eindeutigeKennung); 
	    else 
	        throw new MediumNichtGefundenException("Das ausgewählte Medium ist nicht verfügbar");
	    
	}

	 

}
