package domain.bibliothekskatalog;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import domain.ausleihSystem.Ausleihe;
import domain.medium.*;

public class Mediensuchen {
	
	public ArrayList<String> title(String title, HashMap<String,Mediumverwalter> medien) {
		return medien.entrySet().stream()
				.filter(t -> t.getValue().getMedium().getTitle().equalsIgnoreCase(title))
				.map(t -> "Eindutige Kennung= " + t.toString()).collect(Collectors.toCollection(ArrayList::new));
	}
	
	public ArrayList<String> ausgeliehen(String ausgeliehen,  HashMap<String,Mediumverwalter> medien) {
		return medien.entrySet().stream()
				.filter(t -> t.getValue().isIstAusgeliehen())
				.map(t -> "Eindutige Kennung= " + t.toString()).collect(Collectors.toCollection(ArrayList::new));
	}
	
	public ArrayList<String> nichtAusgeliehen(String ausgeliehen,  HashMap<String,Mediumverwalter> medien) {
		return medien.entrySet().stream()
				.filter(t -> !(t.getValue().isIstAusgeliehen()))
				.map(t -> "Eindutige Kennung= " + t.toString()).collect(Collectors.toCollection(ArrayList::new));
	}
	
	
	public ArrayList<String> medienart(String medienart, HashMap<String, Mediumverwalter> medien) {
	    // Map zur Zuordnung von medienart zu Klassen
	    Map<String, Class<?>> medienKlassen = Map.of(
	        "Bücher", Buch.class,
	        "Brettspiele", Brettspiel.class,
	        "Dvds", Dvd.class,
	        "Cds", Cd.class,
	        "Videospiele", Videospiel.class
	    );

	    // Ermitteln der entsprechenden Klasse für die angegebene Medienart
	    Class<?> klasse = medienKlassen.get(medienart);

	    if (klasse == null) {
	        return null; // Ungültige Medienart
	    }

	    // Stream für die Filterung und Konvertierung
	    return medien.entrySet().stream()
	            .filter(t -> klasse.isInstance(t.getValue().getMedium())) // Filtert nach der Klasse
	            .map(t -> klasse.cast(t.getValue().getMedium()))          // Castet das Medium zur richtigen Klasse
	            .map(t -> "Eindutige Kennung= " + t.toString())          // Konvertiert zu String
	            .collect(Collectors.toCollection(ArrayList::new));       // Sammeln als ArrayList
	}

	
	public ArrayList<String> baldVerfügbareMedien(ArrayList<Ausleihe> ausleihe) {
		LocalDate heutigesDatum = LocalDate.now();
		
		return ausleihe.stream()
		.filter(t -> t.getAusleiheEnde().until(heutigesDatum, ChronoUnit.DAYS) <= 3)
		.map(t -> t.getMediumverwalter())
		.map(t -> t.toString())
		.collect(Collectors.toCollection(ArrayList::new));
		
	}
	
}
