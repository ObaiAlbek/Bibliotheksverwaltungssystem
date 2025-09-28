package domain.benutzer;

import java.time.LocalDate;
import java.util.ArrayList;

import domain.ausleihSystem.Ausleihe;

/**
 * Abstrakte Basisklasse für alle Bibliotheksbenutzer.
 * Enthält gemeinsame Eigenschaften wie Name, Alter, Student-Status,
 * Ausweis, Gebühren und ausgeliehene Medien.
 *
 * @author Obai
 * @version 1.0
 * @since 1.0
 */
public abstract class Benutzer {
    
    /** Bibliotheksausweis des Benutzers. */
    private Ausweis bibAusweis;

    /** Name des Benutzers. */
    private String name;

    /** Alter des Benutzers. */
    private int alter;

    /** Ob der Benutzer Student ist. */
    private boolean istStudent;

    /** Liste der aktuell ausgeliehenen Medien. */
    private ArrayList<Ausleihe> ausgeliehenenMedien;

    /** Ob der Benutzer aktuell im System angemeldet ist. */
    private boolean angemeldet;

    /** Aktuell offene Gebühren des Benutzers. */
    private double gebühren;

    /** Beginn der Anmeldung, dient für Berechnung der Jahresgebühren. */
    private LocalDate anmeldebeginn;

    /**
     * Konstruktor für einen neuen Benutzer.
     *
     * @param bibAusweis Ausweis des Benutzers
     * @param name Name des Benutzers
     * @param alter Alter des Benutzers
     * @param istStudent true falls Student, sonst false
     */
    public Benutzer(Ausweis bibAusweis, String name, int alter, boolean istStudent) {
        this.bibAusweis = bibAusweis;
        this.name = name;
        this.alter = alter;
        this.istStudent = istStudent;
        this.ausgeliehenenMedien = new ArrayList<>();
        this.angemeldet = false;
        this.gebühren = 0;
        this.anmeldebeginn = LocalDate.now();
    }

    /**
     * Liefert den Ausweis.
     *
     * @return Bibliotheksausweis
     */
    public Ausweis getBibAusweis() {
        return bibAusweis;
    }

    /**
     * Setzt den Ausweis.
     *
     * @param bibAusweis neuer Ausweis
     */
    public void setBibAusweis(Ausweis bibAusweis) {
        this.bibAusweis = bibAusweis;
    }

    /**
     * Liefert den Namen.
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen.
     *
     * @param name neuer Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Liefert das Alter.
     *
     * @return Alter
     */
    public int getAlter() {
        return alter;
    }

    /**
     * Setzt das Alter.
     *
     * @param alter neues Alter
     */
    public void setAlter(int alter) {
        this.alter = alter;
    }

    /**
     * Prüft, ob Benutzer Student ist.
     *
     * @return true wenn Student
     */
    public boolean isIstStudent() {
        return istStudent;
    }

    /**
     * Setzt den Student-Status.
     *
     * @param istStudent true falls Student
     */
    public void setIstStudent(boolean istStudent) {
        this.istStudent = istStudent;
    }

    /**
     * Liefert alle ausgeliehenen Medien.
     *
     * @return Liste ausgeliehener Medien
     */
    public ArrayList<Ausleihe> getAusgeliehenenMedien() {
        return ausgeliehenenMedien;
    }

    /**
     * Fügt eine Ausleihe hinzu.
     *
     * @param medium ausgeliehenes Medium
     */
    public void ausleihen(Ausleihe medium) {
        this.ausgeliehenenMedien.add(medium);
    }

    /**
     * Entfernt eine Ausleihe.
     *
     * @param medium zurückgegebenes Medium
     */
    public void mediumZurückgeben(Ausleihe medium) {
        this.ausgeliehenenMedien.remove(medium);
    }

    /**
     * Prüft, ob Benutzer angemeldet ist.
     *
     * @return true wenn angemeldet
     */
    public boolean isAngemeldet() {
        return angemeldet;
    }

    /**
     * Meldet den Benutzer an.
     *
     * @return true wenn erfolgreich
     */
    public boolean anmelden() {
        this.angemeldet = true;
        return this.angemeldet;
    }

    /**
     * Meldet den Benutzer ab.
     *
     * @return false nach Abmeldung
     */
    public boolean abmelden() {
        this.angemeldet = false;
        return this.angemeldet;
    }

    /**
     * Liefert die Gebühren.
     *
     * @return Gebührenbetrag
     */
    public double getGebühren() {
        return gebühren;
    }

    /**
     * Setzt die Gebühren.
     *
     * @param gebühren neuer Gebührenbetrag
     */
    public void setGebühren(double gebühren) {
        this.gebühren = gebühren;
    }

    /**
     * Liefert den Beginn der Anmeldung.
     *
     * @return Anmeldedatum
     */
    public LocalDate getAnmeldebeginn() {
        return anmeldebeginn;
    }

    /**
     * Setzt den Beginn der Anmeldung.
     *
     * @param anmeldebeginn neues Datum
     */
    public void setAnmeldebeginn(LocalDate anmeldebeginn) {
        this.anmeldebeginn = anmeldebeginn;
    }

    /**
     * Berechnet und addiert Jahresgebühren.
     *
     * @param datum optionales Testdatum im Format yyyy-MM-dd
     * @return neue Gesamthöhe der Gebühren
     */
    public double jahresgebühren(String datum) {
        if (!datum.isEmpty()) {
            LocalDate aktuellesDatum = LocalDate.parse(datum);
            while (this.anmeldebeginn.isBefore(aktuellesDatum)) {
                this.gebühren += getJahresgebühren();
                this.anmeldebeginn = this.anmeldebeginn.plusYears(1);
            }
            this.anmeldebeginn = aktuellesDatum;
        } else {
            LocalDate nachEinemJahr = this.anmeldebeginn.plusYears(1);
            while (LocalDate.now().isAfter(nachEinemJahr)) {
                this.gebühren += getJahresgebühren();
                this.anmeldebeginn = nachEinemJahr;
                nachEinemJahr = this.anmeldebeginn.plusYears(1);
            }
        }
        return this.gebühren;
    }

    /**
     * Liefert die spezifischen Jahresgebühren des Benutzertyps.
     *
     * @return Jahresgebühren
     */
    public abstract double getJahresgebühren();

    /**
     * Gibt eine String-Darstellung des Benutzers zurück.
     *
     * @return String mit allen Benutzerdetails
     */
    @Override
    public String toString() {
        return "Benutzer [bibAusweisNummer= " + bibAusweis.getKartenNummer() +
                ", name=" + name +
                ", alter=" + alter +
                ", istStudent=" + istStudent +
                ", ist im System Online = " + angemeldet +
                " ,Gebühren= " + gebühren + "]";
    }

    /**
     * Gibt eine String-Darstellung aller ausgeliehenen Medien zurück.
     *
     * @return String mit ausgeliehenen Medien
     */
    public String zeigeAusgeliehenMediums() {
        return "Ausgeliehene Mediums= " + ausgeliehenenMedien;
    }
}
