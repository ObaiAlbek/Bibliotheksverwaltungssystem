package domain.medium;

/**
 * Medientyp DVD mit Regisseur.
 * Erbt Grunddaten von {@link Medium}.
 * Leerer Regisseur wird als "-" gespeichert.
 *
 * author Obai
 * version 1.0
 * since 1.0
 */
public class Dvd extends Medium {

    /** Regisseur der DVD. */
    private String regisseur;

    /**
     * Konstruktor.
     *
     * @param ID eindeutige ID
     * @param title Titel
     * @param erscheinungsjahr Erscheinungsjahr
     * @param regisseur Regisseur oder leer
     */
    public Dvd(String ID, String title, int erscheinungsjahr, String regisseur) {
        super(ID, title, erscheinungsjahr);
        if (regisseur.isEmpty())
            this.regisseur = "-";
        else
            this.regisseur = regisseur;
    }

    /**
     * Liefert den Regisseur.
     *
     * @return Regisseur
     */
    public String getRegisseur() {
        return regisseur;
    }

    /**
     * Setzt den Regisseur.
     *
     * @param regisseur neuer Regisseur
     */
    public void setRegisseur(String regisseur) {
        this.regisseur = regisseur;
    }

    /**
     * String-Repräsentation.
     *
     * @return Darstellung der DVD
     */
    @Override
    public String toString() {
        return "DVD: " + super.toString() + " ,Regisseur=" + regisseur;
    }
}
