package domain.Bibliothekskatalog;

import java.util.*;
import java.util.stream.Collectors;
import domain.Medium.*;

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
	
	
	public ArrayList<String> medienart(String medienart, HashMap<String,Mediumverwalter> medien) {
		switch(medienart) {
			case "Bücher":
				return medien.entrySet().stream()
						.filter(t -> t.getValue().getMedium() instanceof Buch)
						.map(t -> t.getValue().getMedium())
						.map(t -> ((Buch)t))
						.map(t -> "Eindutige Kennung= " + t.toString()).collect(Collectors.toCollection(ArrayList::new));
				
			case "Brettspiele":
				return medien.entrySet().stream()
						.filter(t -> t.getValue().getMedium() instanceof Brettspiel)
						.map(t -> t.getValue().getMedium())
						.map(t -> ((Brettspiel)t))
						.map(t -> "Eindutige Kennung= " + t.toString()).collect(Collectors.toCollection(ArrayList::new));
				
			case "Dvds":
				return medien.entrySet().stream()
						.filter(t -> t.getValue().getMedium() instanceof Dvd)
						.map(t -> t.getValue().getMedium())
						.map(t -> ((Dvd)t))
						.map(t -> "Eindutige Kennung= " + t.toString()).collect(Collectors.toCollection(ArrayList::new));
				
			case "Cds":
				return medien.entrySet().stream()
						.filter(t -> t.getValue().getMedium() instanceof Cd)
						.map(t -> t.getValue().getMedium())
						.map(t -> ((Cd)t))
						.map(t -> "Eindutige Kennung= " + t.toString()).collect(Collectors.toCollection(ArrayList::new));
				
			case "Videospiele":
				return medien.entrySet().stream()
						.filter(t -> t.getValue().getMedium() instanceof Videospiel)
						.map(t -> t.getValue().getMedium())
						.map(t -> ((Videospiel)t))
						.map(t -> "Eindutige Kennung= " + t.toString()).collect(Collectors.toCollection(ArrayList::new));
			
			default:
				return null;
				
		}
		
		
		
	}
}
