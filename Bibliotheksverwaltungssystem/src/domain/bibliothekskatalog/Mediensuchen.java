package domain.bibliothekskatalog;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import domain.ausleihSystem.Ausleihe;
import domain.medium.*;

/**
 * Bietet Suchfunktionen über den Medienbestand.
 * Unterstützt Suche nach Titel, Ausleihstatus, Medienart sowie bald verfügbare Medien.
 *
 * @author Obai
 * @version 1.0
 * @since 1.0
 */
public class Mediensuchen {

    /**
     * Sucht Medien nach exaktem Titel (case-insensitive).
     *
     * @param title gesuchter Titel
     * @param medien Map von Kennung zu Mediumverwalter
     * @return Liste von Strings mit "Eindeutige Kennung= <ID>"
     */
    public ArrayList<String> title(String title, HashMap<String, Mediumverwalter> medien) {
        return medien.entrySet().stream()
                .filter(e -> e.getValue().getMedium().getTitle().equalsIgnoreCase(title))
                .map(e -> "Eindeutige Kennung= " + e.getKey())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Listet alle aktuell ausgeliehenen Medien.
     *
     * @param medien Map von Kennung zu Mediumverwalter
     * @return Liste von Strings mit "Eindeutige Kennung= <ID>"
     */
    public ArrayList<String> ausgeliehen(HashMap<String, Mediumverwalter> medien) {
        return medien.entrySet().stream()
                .filter(e -> e.getValue().isIstAusgeliehen())
                .map(e -> "Eindeutige Kennung= " + e.getKey())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Listet alle aktuell nicht ausgeliehenen Medien.
     *
     * @param medien Map von Kennung zu Mediumverwalter
     * @return Liste von Strings mit "Eindeutige Kennung= <ID>"
     */
    public ArrayList<String> nichtAusgeliehen(HashMap<String, Mediumverwalter> medien) {
        return medien.entrySet().stream()
                .filter(e -> !e.getValue().isIstAusgeliehen())
                .map(e -> "Eindeutige Kennung= " + e.getKey())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Sucht Medien anhand der Medienart (z. B. Bücher, Brettspiele, Dvds, Cds, Videospiele).
     *
     * @param medienart Medienart-Name
     * @param medien Map von Kennung zu Mediumverwalter
     * @return Liste von Strings mit "Eindeutige Kennung= <ID>", oder leere Liste bei unbekannter Art
     */
    public ArrayList<String> medienart(String medienart, HashMap<String, Mediumverwalter> medien) {
        Map<String, Class<?>> medienKlassen = Map.of(
                "Bücher", Buch.class,
                "Brettspiele", Brettspiel.class,
                "Dvds", Dvd.class,
                "Cds", Cd.class,
                "Videospiele", Videospiel.class
        );

        Class<?> klasse = medienKlassen.get(medienart);
        if (klasse == null) {
            return new ArrayList<>();
        }

        return medien.entrySet().stream()
                .filter(e -> klasse.isInstance(e.getValue().getMedium()))
                .map(e -> "Eindeutige Kennung= " + e.getKey())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Liefert Medien, die in den nächsten 3 Tagen zurückgegeben werden sollten.
     *
     * @param ausleihe Liste laufender Ausleihen
     * @return Liste der Mediumverwalter-Strings der bald verfügbaren Medien
     */
    public ArrayList<String> baldVerfügbareMedien(ArrayList<Ausleihe> ausleihe) {
        LocalDate heute = LocalDate.now();

        return ausleihe.stream()
                // Tage bis Ende >= 0 (noch nicht überfällig) und <= 3
                .filter(a -> {
                    long tageBisEnde = ChronoUnit.DAYS.between(heute, a.getAusleiheEnde());
                    return tageBisEnde >= 0 && tageBisEnde <= 3;
                })
                .map(Ausleihe::getMediumverwalter)
                .map(Object::toString)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
