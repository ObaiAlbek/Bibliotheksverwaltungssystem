package domain.medium;

/**
 * Medientyp Buch mit Autor.
 * Erbt Grunddaten von {@link Medium}.
 * Leerer Autor wird als "-" gespeichert.
 *
 * author Obai
 * version 1.0
 * since 1.0
 */
public class Buch extends Medium {

    /** Autor des Buches. */
    private String autor;

    /**
     * Konstruktor.
     *
     * @param ID eindeutige ID
     * @param title Titel
     * @param erscheinungsjahr Erscheinungsjahr
     * @param autor Autor oder leer
     */
    public Buch(String ID, String title, int erscheinungsjahr, String autor) {
        super(ID, title, erscheinungsjahr);
        if (autor.isEmpty())
            this.autor = "-";
        else
            this.autor = autor;
    }

    /**
     * Liefert den Autor.
     *
     * @return Autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Setzt den Autor.
     *
     * @param autor neuer Autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * String-Repräsentation.
     *
     * @return Darstellung des Buchs
     */
    @Override
    public String toString() {
        return "Buch: " + super.toString() + " ,autor=" + autor;
    }
}
