package domain.medium;

/**
 * Verwalter für ein Medium mit Bestands- und Ausleihinformationen.
 * Enthält Verlängerbarkeit, Anzahl, Leihdauer, Referenz auf Medium und Ausleihstatus.
 *
 * @author Obai
 * @version 1.0
 * @since 1.0
 */
public class Mediumverwalter {

    /** Ob das Medium verlängerbar ist. */
    private boolean verlängerbar;

    /** Verfügbare Anzahl im Bestand. */
    private int anzahl;

    /** Leihdauer in Tagen. */
    private int leihdauer;

    /** Referenz auf das Medium. */
    private Medium medium;

    /** Ob aktuell ausgeliehen. */
    private boolean istAusgeliehen;

    /**
     * Konstruktor.
     *
     * @param medium Medium
     * @param verlängerbar true wenn verlängerbar
     * @param anzahl verfügbare Anzahl
     * @param leihdauer Leihdauer in Tagen
     */
    public Mediumverwalter(Medium medium, boolean verlängerbar, int anzahl, int leihdauer) {
        super();
        this.verlängerbar = verlängerbar;
        this.anzahl = anzahl;
        this.leihdauer = leihdauer;
        this.medium = medium;
    }

    /**
     * Prüft die Verlängerbarkeit.
     *
     * @return true wenn verlängerbar
     */
    public boolean isVerlängerbar() {
        return verlängerbar;
    }

    /**
     * Liefert die verfügbare Anzahl.
     *
     * @return Anzahl
     */
    public int getAnzahl() {
        return anzahl;
    }

    /**
     * Setzt die verfügbare Anzahl.
     *
     * @param anzahl neue Anzahl
     */
    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    /**
     * Liefert die Leihdauer in Tagen.
     *
     * @return Leihdauer
     */
    public int getLeihdauer() {
        return leihdauer;
    }

    /**
     * Liefert das Medium.
     *
     * @return Medium
     */
    public Medium getMedium() {
        return medium;
    }

    /**
     * Prüft, ob das Medium ausgeliehen ist.
     *
     * @return true wenn ausgeliehen
     */
    public boolean isIstAusgeliehen() {
        return istAusgeliehen;
    }

    /**
     * Setzt den Ausleihstatus.
     *
     * @param istAusgeliehen neuer Status
     */
    public void setIstAusgeliehen(boolean istAusgeliehen) {
        this.istAusgeliehen = istAusgeliehen;
    }

    /**
     * String-Repräsentation.
     *
     * @return Darstellung mit Medium, Verlängerbarkeit, Anzahl, Status und Leihdauer
     */
    @Override
    public String toString() {
        return "Medium=" + medium.toString() +
               " ,verlängerbar=" + verlängerbar +
               ", anzahl=" + anzahl +
               ",ist Ausgeliehen= " + istAusgeliehen +
               ", Leihdauer=" + leihdauer;
    }
}
