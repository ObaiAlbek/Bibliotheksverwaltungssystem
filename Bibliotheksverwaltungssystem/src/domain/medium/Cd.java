package domain.medium;

/**
 * Medientyp CD mit Künstler.
 * Erbt Grunddaten von {@link Medium}.
 * Leerer Künstler wird als "-" gespeichert.
 *
 * author Obai
 * version 1.0
 * since 1.0
 */
public class Cd extends Medium {

    /** Künstler der CD. */
    private String Künstler;

    /**
     * Konstruktor.
     *
     * @param ID eindeutige ID
     * @param title Titel
     * @param erscheinungsjahr Erscheinungsjahr
     * @param Künstler Künstler oder leer
     */
    public Cd(String ID, String title, int erscheinungsjahr, String Künstler) {
        super(ID, title, erscheinungsjahr);
        if (Künstler.isEmpty())
            this.Künstler = "-";
        else
            this.Künstler = Künstler;
    }

    /**
     * Liefert den Künstler.
     *
     * @return Künstler
     */
    public String getKünstler() {
        return Künstler;
    }

    /**
     * Setzt den Künstler.
     *
     * @param künstler neuer Künstler
     */
    public void setKünstler(String künstler) {
        Künstler = künstler;
    }

    /**
     * String-Repräsentation.
     *
     * @return Darstellung der CD
     */
    @Override
    public String toString() {
        return "CD: " + super.toString() + " ,Künstler=" + Künstler;
    }
}
