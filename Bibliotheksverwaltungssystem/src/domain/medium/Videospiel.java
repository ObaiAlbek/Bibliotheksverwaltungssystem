package domain.medium;

/**
 * Medientyp Videospiel mit Plattform.
 * Erbt Grunddaten von {@link Medium}.
 * Leere Plattform wird als "-" gespeichert.
 *
 * author Obai
 * version 1.0
 * since 1.0
 */
public class Videospiel extends Medium {

    /** Zielplattform des Videospiels. */
    private String plattform;

    /**
     * Konstruktor.
     *
     * @param ID eindeutige ID
     * @param title Titel
     * @param erscheinungsjahr Erscheinungsjahr
     * @param plattform Plattform oder leer
     */
    public Videospiel(String ID, String title, int erscheinungsjahr, String plattform) {
        super(ID, title, erscheinungsjahr);
        if (plattform.isEmpty())
            this.plattform = "-";
        else
            this.plattform = plattform;
    }

    /**
     * Liefert die Plattform.
     *
     * @return Plattform
     */
    public String getPlattform() {
        return plattform;
    }

    /**
     * Setzt die Plattform.
     *
     * @param plattform neue Plattform
     */
    public void setPlattform(String plattform) {
        this.plattform = plattform;
    }

    /**
     * String-Repräsentation.
     *
     * @return Darstellung des Videospiels
     */
    @Override
    public String toString() {
        return "Videospiel: " + super.toString() + " ,Plattform=" + plattform;
    }
}
