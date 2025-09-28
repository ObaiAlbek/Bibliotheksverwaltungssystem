package domain.medium;

/**
 * Basisklasse für alle Medientypen.
 * Enthält ID, Titel und Erscheinungsjahr.
 *
 * @author Obai
 * @version 1.0
 * @since 1.0
 */
public abstract class Medium {

    /** Titel des Mediums. */
    private String title;

    /** Erscheinungsjahr des Mediums. */
    private int erscheinungsjahr;

    /** Eindeutige ID des Mediums. */
    private String ID;

    /**
     * Konstruktor.
     *
     * @param ID eindeutige Kennung
     * @param title Titel des Mediums
     * @param erscheinungsjahr Erscheinungsjahr
     */
    public Medium(String ID, String title, int erscheinungsjahr) {
        super();
        this.title = title;
        this.erscheinungsjahr = erscheinungsjahr;
        this.ID = ID;
    }

    /**
     * Liefert den Titel.
     *
     * @return Titel
     */
    public String getTitle() {
        return title;
    }

    /**
     * Liefert das Erscheinungsjahr.
     *
     * @return Erscheinungsjahr
     */
    public int getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    /**
     * Liefert die ID.
     *
     * @return ID
     */
    public String getID() {
        return ID;
    }

    /**
     * String-Repräsentation.
     *
     * @return Darstellung mit ID, Titel und Erscheinungsjahr
     */
    @Override
    public String toString() {
        return "ID= " + ID + " ,title=" + title + ", erscheinungsjahr=" + erscheinungsjahr;
    }
}
