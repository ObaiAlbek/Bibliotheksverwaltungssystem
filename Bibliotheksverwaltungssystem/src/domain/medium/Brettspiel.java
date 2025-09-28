package domain.medium;

/**
 * Medientyp Brettspiel mit Verlag.
 * Erbt Grunddaten von {@link Medium}.
 * Leerer Verlag wird als "-" gespeichert.
 * 
 * author Obai
 * version 1.0
 * since 1.0
 */
public class Brettspiel extends Medium {

    /** Verlag des Brettspiels. */
    private String Verlag;

    /**
     * Konstruktor.
     *
     * @param ID eindeutige ID
     * @param title Titel
     * @param erscheinungsjahr Erscheinungsjahr
     * @param Verlag Verlag oder leer
     */
    public Brettspiel(String ID, String title, int erscheinungsjahr, String Verlag) {
        super(ID, title, erscheinungsjahr);
        if (Verlag.isEmpty())
            this.Verlag = "-";
        else
            this.Verlag = Verlag;
    }

    /**
     * Liefert den Verlag.
     *
     * @return Verlag
     */
    public String getVerlag() {
        return Verlag;
    }

    /**
     * Setzt den Verlag.
     *
     * @param verlag neuer Verlag
     */
    public void setVerlag(String verlag) {
        Verlag = verlag;
    }

    /**
     * String-Repräsentation.
     *
     * @return Darstellung des Brettspiels
     */
    @Override
    public String toString() {
        return "Brettspiel: " + super.toString() + " ,Verlag=" + Verlag;
    }
}
