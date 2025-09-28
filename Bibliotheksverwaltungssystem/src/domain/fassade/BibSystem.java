package domain.fassade;

import java.util.ArrayList;
import java.util.HashMap;

import domain.ausleihSystem.Ausleihe;
import domain.ausleihSystem.AusleiheSystem;
import domain.benutzer.Ausweis;
import domain.benutzer.Benutzer;
import domain.benutzer.Mitarbeiter;
import domain.bibliothekskatalog.Mediensuchen;
import domain.exceptionsKlassen.BenutzerNichtAngemeldetException;
import domain.exceptionsKlassen.BenutzerNichtGefundenException;
import domain.exceptionsKlassen.FalscheEingabeException;
import domain.exceptionsKlassen.MediumNichtGefundenException;
import domain.medium.Buch;
import domain.medium.Cd;
import domain.medium.Dvd;
import domain.medium.Mediumverwalter;
import domain.medium.Videospiel;

/**
 * Fassade des Bibliothekssystems.
 * Stellt Registrierung, Anmeldung, Medien-Suche, Ausleihe, Rückgabe,
 * Verlängerung, Gebührenfunktionen und Admin-Operationen bereit.
 *
 * @author Obai
 * @version 1.0
 * @since 1.0
 */
public class BibSystem {

    /** Alle registrierten Bibliotheksbenutzer. */
    private ArrayList<Benutzer> alleBibBenutzer;

    /** Medienbestand: Kennung -> Mediumverwalter. */
    private HashMap<String, Mediumverwalter> medien;

    /** Laufende Ausleihen. */
    private ArrayList<Ausleihe> ausleihe;

    /** Ausleihsystem für Fachlogik der Ausleihe. */
    private AusleiheSystem ausleiheSystem;

    /** Interner Admin (Mitarbeiter) für Admin-Operationen. */
    private Benutzer bibAdmin;

    /**
     * Erstellt ein neues Bibliothekssystem mit Demo-Daten.
     */
    public BibSystem() {
        this.alleBibBenutzer = new ArrayList<>();
        this.medien = new HashMap<>();
        this.ausleiheSystem = new AusleiheSystem(medien);
        this.ausleihe = new ArrayList<>();
        this.bibAdmin = new Mitarbeiter(new Ausweis("A"), "Xy Müller", 20, false);
        mediumsAufladen();
    }

    /**
     * Registriert einen neuen Benutzer.
     *
     * @param name Name
     * @param type Benutzertyp (z. B. "student", "erwachsener", "mitarbeiter")
     * @param alter Alter
     * @param istAdmin "ja" für Admin, sonst "nein"
     * @throws FalscheEingabeException wenn Eingaben ungültig sind
     */
    public void userRegistrieren(String name, String type, int alter, String istAdmin) throws FalscheEingabeException {
        Benutzer bibBenutzer = Registieren.userRegistrieren(name, type, alter, istAdmin);
        alleBibBenutzer.add(bibBenutzer);
    }

    /**
     * Meldet einen Benutzer an und liefert aktuelle Gebühren.
     *
     * @param bibKartenNummer Kartennummer des Benutzers
     * @return aktuelle Gebühren
     * @throws BenutzerNichtGefundenException wenn Benutzer nicht existiert
     */
    public double userAnmelden(String bibKartenNummer) throws BenutzerNichtGefundenException {
        Benutzer bibBenutzer = findeBenutzer(bibKartenNummer);
        bibBenutzer.anmelden();
        return bibBenutzer.getGebühren();
    }

    /**
     * Durchsucht Medien nach Auswahl.
     * Erlaubt Werte: "ausgeliehen", "nicht ausgeliehen", Medienarten ("Bücher", "Brettspiele", "Dvds", "Cds", "Videospiele"),
     * "ja" für bald verfügbare Medien, oder ein Titelstring für Titelsuche.
     *
     * @param auswahl Suchauswahl
     * @param bibKartenNummer Kartennummer des Benutzers
     * @return Trefferliste als Strings
     * @throws FalscheEingabeException wenn Auswahl ungültig
     * @throws MediumNichtGefundenException wenn keine Treffer
     * @throws BenutzerNichtAngemeldetException wenn Benutzer nicht angemeldet ist
     * @throws BenutzerNichtGefundenException wenn Benutzer nicht existiert
     */
    public ArrayList<String> mediumDurchsuchen(String auswahl, String bibKartenNummer)
            throws FalscheEingabeException, MediumNichtGefundenException, BenutzerNichtAngemeldetException,
                   BenutzerNichtGefundenException {
        Benutzer benutzer = findeBenutzer(bibKartenNummer);

        if (!benutzer.isAngemeldet()) {
            throw new BenutzerNichtAngemeldetException("Sie müssen sich erst im System anmelden");
        }

        ArrayList<String> treffer;
        Mediensuchen medienSuchen = new Mediensuchen();

        if (auswahl.equalsIgnoreCase("ausgeliehen")) {
            treffer = medienSuchen.ausgeliehen(medien);
        } else if (auswahl.equalsIgnoreCase("nicht Ausgeliehen") || auswahl.equalsIgnoreCase("nicht ausgeliehen")) {
            treffer = medienSuchen.nichtAusgeliehen(medien);
        } else if (auswahl.equalsIgnoreCase("Bücher") || auswahl.equalsIgnoreCase("Brettspiele")
                || auswahl.equalsIgnoreCase("Dvds") || auswahl.equalsIgnoreCase("Cds")
                || auswahl.equalsIgnoreCase("Videospiele")) {
            treffer = medienSuchen.medienart(auswahl, medien);
        } else if (auswahl.equalsIgnoreCase("ja")) {
            treffer = medienSuchen.baldVerfügbareMedien(ausleihe);
        } else {
            treffer = medienSuchen.title(auswahl, medien);
        }

        if (treffer.size() == 0) {
            throw new MediumNichtGefundenException("Kein Treffer");
        }
        return treffer;
    }

    /**
     * Leiht ein Medium aus.
     *
     * @param bibKartenNummer Kartennummer
     * @param eindeutigeKennung Medienkennung
     * @return aktuelle Gebühren des Benutzers
     * @throws Exception wenn Mitarbeiter ausleihen wollen oder Benutzer nicht angemeldet ist
     * @throws BenutzerNichtGefundenException wenn Benutzer nicht existiert
     * @throws MediumNichtGefundenException wenn Medium nicht existiert/ausgeliehen
     */
    public double mediumAusleihen(String bibKartenNummer, String eindeutigeKennung) throws Exception {
        Benutzer bibBenutzer = findeBenutzer(bibKartenNummer);

        if (bibBenutzer instanceof Mitarbeiter) {
            throw new Exception("Mitarbeiter können keine Medien ausleihen!");
        }
        if (!bibBenutzer.isAngemeldet()) {
            throw new BenutzerNichtAngemeldetException("Sie müssen sich erst im System anmelden");
        }

        Ausleihe neueAusleihe = ausleiheSystem.mediumAusleihen(bibBenutzer, eindeutigeKennung);
        ausleihe.add(neueAusleihe);
        bibBenutzer.ausleihen(neueAusleihe);
        return bibBenutzer.getGebühren();
    }

    /**
     * Verlängert eine bestehende Ausleihe.
     *
     * @param eindeutigeKennung Medienkennung
     * @param bibKartennummer Kartennummer des Benutzers
     * @return true bei Erfolg, sonst false
     * @throws BenutzerNichtGefundenException wenn Benutzer nicht existiert
     * @throws MediumNichtGefundenException wenn Medium/Ausleihe nicht gefunden
     */
    public boolean medienVerlängern(String eindeutigeKennung, String bibKartennummer)
            throws BenutzerNichtGefundenException, MediumNichtGefundenException {
        Benutzer benutzer = findeBenutzer(bibKartennummer);
        return ausleiheSystem.medienVerlängern(benutzer, eindeutigeKennung);
    }

    /**
     * Gibt ein Medium zurück.
     *
     * @param eindeutigeKennung Medienkennung
     * @return Liste verbleibender Ausleihen des Benutzers als Strings
     */
    public ArrayList<String> medienRückgabe(String eindeutigeKennung) {
        return ausleiheSystem.mediumRückgabe(ausleihe, eindeutigeKennung);
    }

    /**
     * Meldet den Admin an.
     *
     * @param bibKartennummerAdmin Kartennummer des Admins
     * @return true wenn angemeldet
     */
    public boolean adminAnmelden(String bibKartennummerAdmin) {
        this.bibAdmin.anmelden();
        return this.bibAdmin.isAngemeldet();
    }

    /**
     * Liefert die aktuellen Gebühren eines Benutzers.
     *
     * @param bibKartennummer Kartennummer
     * @return Gebührenbetrag
     * @throws BenutzerNichtGefundenException wenn Benutzer nicht existiert
     */
    public double getgbührenBenutzer(String bibKartennummer) throws BenutzerNichtGefundenException {
        Benutzer benutzer = findeBenutzer(bibKartennummer);
        return benutzer.getGebühren();
    }

    /**
     * Liefert die aktuell ausgeliehenen Medien eines Benutzers.
     *
     * @param bibKartennummer Kartennummer
     * @return Liste der Ausleihen als Strings
     * @throws BenutzerNichtGefundenException wenn Benutzer nicht existiert
     */
    public ArrayList<String> ausgeliehenGegenstände(String bibKartennummer) throws BenutzerNichtGefundenException {
        Benutzer bibUser = findeBenutzer(bibKartennummer);
        ArrayList<String> treffer = new ArrayList<>();
        for (Ausleihe a : bibUser.getAusgeliehenenMedien()) {
            treffer.add(a.toString());
        }
        return treffer;
    }

    /**
     * Verbucht alle Gebühren eines Benutzers auf 0.0 über den Admin.
     *
     * @param bibKartennummer Kartennummer
     * @return true wenn Gebühren danach 0.0 sind
     * @throws BenutzerNichtGefundenException wenn Benutzer nicht existiert
     */
    public boolean gebührenVerbuchen(String bibKartennummer) throws BenutzerNichtGefundenException {
        Benutzer bibUser = findeBenutzer(bibKartennummer);
        ((Mitarbeiter) this.bibAdmin).gebührVerbuchen(bibUser);
        return bibUser.getGebühren() == 0.0;
    }

    /**
     * Simuliert ein Rückgabedatum und berechnet Gebühren.
     *
     * @param eindeutigeKennung Medienkennung
     * @param ausleiheBeginn Beginn-ISO-Datum
     * @param ausleiheEnde Ende-ISO-Datum
     * @param datum Rückgabedatum ISO
     * @return Gebührenstand des Benutzers
     * @throws MediumNichtGefundenException wenn Medium/Ausleihe nicht gefunden
     */
    public double datumÄndern(String eindeutigeKennung, String ausleiheBeginn, String ausleiheEnde, String datum)
            throws MediumNichtGefundenException {
        return ausleiheSystem.SimulieremediumRückgabe(ausleihe, eindeutigeKennung, ausleiheBeginn, ausleiheEnde, datum);
    }

    /**
     * Berechnet Jahresgebühren für einen Benutzer bis zu einem optionalen Datum.
     *
     * @param bibKartennummer Kartennummer
     * @param datum optionales Testdatum (yyyy-MM-dd) oder leer
     * @return neuer Gebührenstand
     * @throws BenutzerNichtGefundenException wenn Benutzer nicht existiert
     */
    public double jahresGebührenBerechnen(String bibKartennummer, String datum) throws BenutzerNichtGefundenException {
        Benutzer benutzer = findeBenutzer(bibKartennummer);
        return benutzer.jahresgebühren(datum);
    }

    /**
     * Legt ein neues Medium im Bestand an.
     *
     * @param type "Buch", "Cd", "Brettspiel", "Dvd", "Videospiel"
     * @param ID Kennung
     * @param title Titel
     * @param erscheinungsjahr Erscheinungsjahr
     * @param ersteller Autor/Verlag/Künstler/Regisseur/Plattform
     * @param verlängbar "ja" oder "nein"
     * @param anzahl verfügbare Anzahl
     * @param leihdauer Leihdauer in Tagen
     * @throws FalscheEingabeException wenn Typ ungültig
     */
    public void mediumsAufladen(String type, String ID, String title, int erscheinungsjahr, String ersteller,
                                String verlängbar, int anzahl, int leihdauer) throws FalscheEingabeException {
        Mediumverwalter medium;
        boolean verlängerung = verlängbar.equalsIgnoreCase("ja");
        switch (type) {
            case "Buch":
                medium = new Mediumverwalter(new Buch(ID, title, erscheinungsjahr, ersteller), verlängerung, anzahl, leihdauer);
                break;
            case "Cd":
                medium = new Mediumverwalter(new Cd(ID, title, erscheinungsjahr, ersteller), verlängerung, anzahl, leihdauer);
                break;
            case "Brettspiel":
                medium = new Mediumverwalter(new domain.medium.Brettspiel(ID, title, erscheinungsjahr, ersteller), verlängerung, anzahl, leihdauer);
                break;
            case "Dvd":
                medium = new Mediumverwalter(new Dvd(ID, title, erscheinungsjahr, ersteller), verlängerung, anzahl, leihdauer);
                break;
            case "Videospiel":
                medium = new Mediumverwalter(new Videospiel(ID, title, erscheinungsjahr, ersteller), verlängerung, anzahl, leihdauer);
                break;
            default:
                throw new FalscheEingabeException("Falsche Eingabe");
        }
        medien.put(ID, medium);
    }

    /**
     * Lädt Demo-Medien in den Bestand.
     */
    private void mediumsAufladen() {
        Mediumverwalter buch = new Mediumverwalter(
                new Buch("B001", "Effektives Java Programmieren", 2018, "Joshua Bloch"),
                true, 10, 28);
        medien.put(buch.getMedium().getID(), buch);

        Mediumverwalter buchIstAusgeliehen = new Mediumverwalter(
                new Buch("B00", "Effektives C++ Programmieren", 2018, "Joshua Bloch"),
                true, 10, 28);
        buchIstAusgeliehen.setIstAusgeliehen(true);
        medien.put(buchIstAusgeliehen.getMedium().getID(), buchIstAusgeliehen);

        Mediumverwalter buchIstNichtAusgeliehen = new Mediumverwalter(
                new Buch("BG001", "Javascript lenren", 2018, "Joshua Bloch"),
                true, 10, 28);
        medien.put(buchIstNichtAusgeliehen.getMedium().getID(), buchIstNichtAusgeliehen);

        Mediumverwalter videospiel = new Mediumverwalter(
                new Videospiel("BG00122", "The Legend of Zelda: Breath of the Wild", 2017, "Nintendo Switch"),
                false, 2, 28);
        medien.put(videospiel.getMedium().getID(), videospiel);
    }

    /**
     * Sucht einen Benutzer anhand Kartennummer.
     *
     * @param bibKartenNummer Kartennummer
     * @return gefundener Benutzer
     * @throws BenutzerNichtGefundenException wenn Benutzer nicht existiert
     */
    public Benutzer findeBenutzer(String bibKartenNummer) throws BenutzerNichtGefundenException {
        return alleBibBenutzer.stream()
                .filter(k -> k.getBibAusweis().getKartenNummer().equalsIgnoreCase(bibKartenNummer))
                .findFirst()
                .orElseThrow(() ->
                        new BenutzerNichtGefundenException(
                                "Benutzer mit Kartennummer " + bibKartenNummer + " nicht gefunden"));
    }
}
