package domain.ausleihSystem;

import java.time.LocalDate;
import domain.benutzer.Benutzer;
import domain.medium.Mediumverwalter;

/**
 * Repräsentiert eine Ausleihe in der Bibliothe.
 *
 * Enthält Informationen über das ausgeliehene Medium, den Benutzer, 
 * den Beginn und das Ende der Ausleihe sowie die Anzahl der Verlängerungen.
 *
 */
public class Ausleihe {
    
    /** Startdatum der Ausleihe. */
    private LocalDate ausleiheBeginn;

    /** Enddatum der Ausleihe. */
    private LocalDate ausleiheEnde;

    /** Das ausgeliehene Medium. */
    private Mediumverwalter medium;

    /** Der Benutzer, der das Medium ausgeliehen hat. */
    private Benutzer benutzer;

    /** Anzahl der bereits durchgeführten Verlängerungen. */
    private int verlängerungen;

    /**
     * Erstellt eine neue Ausleihe für ein Medium und einen Benutzer.
     *
     * @param medium das ausgeliehene Medium
     * @param benutzer der Benutzer, der das Medium ausleiht
     * @param ausleiheBeginn das Startdatum der Ausleihe
     * @param ausleiheEnde das Enddatum der Ausleihe
     */
    public Ausleihe(Mediumverwalter medium, Benutzer benutzer, LocalDate ausleiheBeginn, LocalDate ausleiheEnde) {
        this.medium = medium;
        this.ausleiheBeginn = ausleiheBeginn;
        this.ausleiheEnde = ausleiheEnde;
        this.verlängerungen = 0;
        this.benutzer = benutzer;
    }

    /**
     * Liefert das ausgeliehene Medium.
     *
     * @return das Medium
     */
    public Mediumverwalter getMediumverwalter() {
        return medium;
    }

    /**
     * Liefert den Benutzer, der das Medium ausgeliehen hat.
     *
     * @return der Benutzer
     */
    public Benutzer getBenutzer() {
        return benutzer;
    }

    /**
     * Liefert das Startdatum der Ausleihe.
     *
     * @return Beginn der Ausleihe
     */
    public LocalDate getAusleiheBeginn() {
        return ausleiheBeginn;
    }

    /**
     * Setzt das Startdatum der Ausleihe.
     *
     * @param ausleiheBeginn neues Startdatum
     */
    public void setAusleiheBeginn(LocalDate ausleiheBeginn) {
        this.ausleiheBeginn = ausleiheBeginn;
    }

    /**
     * Liefert das Enddatum der Ausleihe.
     *
     * @return Ende der Ausleihe
     */
    public LocalDate getAusleiheEnde() {
        return ausleiheEnde;
    }

    /**
     * Setzt das Enddatum der Ausleihe.
     *
     * @param ausleiheEnde neues Enddatum
     */
    public void setAusleiheEnde(LocalDate ausleiheEnde) {
        this.ausleiheEnde = ausleiheEnde;
    }

    /**
     * Liefert die Anzahl der Verlängerungen.
     *
     * @return Anzahl der Verlängerungen
     */
    public int getVerlängerungen() {
        return verlängerungen;
    }

    /**
     * Setzt die Anzahl der Verlängerungen.
     *
     * @param verlängerungen neue Anzahl der Verlängerungen
     */
    public void setVerlängerungen(int verlängerungen) {
        this.verlängerungen = verlängerungen;
    }

    /**
     * Gibt eine String-Darstellung der Ausleihe zurück.
     *
     * @return String mit Informationen über Medium, Benutzer, Zeitraum und Verlängerungen
     */
    @Override
    public String toString() {
        return "Ausgeliehenes Medium= " + medium.toString() + 
               " | Ausleihe Beginn= " + ausleiheBeginn + 
               " | Ausleihe Ende= " + ausleiheEnde + 
               " | Verlängerungen= " + verlängerungen + 
               " | Inhaber= " + benutzer.toString();
    }
}
