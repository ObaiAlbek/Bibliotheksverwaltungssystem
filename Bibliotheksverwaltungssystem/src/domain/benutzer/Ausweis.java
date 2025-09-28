package domain.benutzer;

/**
 * Repräsentiert einen Bibliotheksausweis.
 * Jede Instanz erhält eine eindeutige Kartennummer mit Präfix.
 *
 * @author Obai
 * @version 1.0
 * @since 1.0
 */
public class Ausweis {

    /** Eindeutige Kartennummer des Ausweises. */
    private String kartennummer;

    /** Zähler zur Generierung neuer Kartennummern, beginnend bei 1000. */
    private static int generiereNummer = 1000;

    /**
     * Erstellt einen neuen Ausweis mit Präfix.
     * Die Kartennummer wird aus dem Präfix und einem automatisch
     * hochgezählten Wert generiert.
     *
     * @param zeichen Präfix für die Kartennummer (z. B. "K" für Kunde, "A" für Admin)
     */
    public Ausweis(String zeichen) {
        this.kartennummer = zeichen + generiereNummer++;
    }

    /**
     * Liefert die Kartennummer des Ausweises.
     *
     * @return Kartennummer als String
     */
    public String getKartenNummer() {
        return kartennummer;
    }
}
