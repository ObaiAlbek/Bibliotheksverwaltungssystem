package domain.bibliothekskatalog;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import domain.exceptionsKlassen.BenutzerNichtAngemeldetException;
import domain.exceptionsKlassen.BenutzerNichtGefundenException;
import domain.exceptionsKlassen.FalscheEingabeException;
import domain.exceptionsKlassen.MediumNichtGefundenException;
import domain.fassade.BibSystem;

/**
 * Testet die Suchfunktionen im Bibliothekskatalog über die Fassade {@link BibSystem}.
 * Deckt Suchen nach Titel, Medienart und Ausleihstatus ab.
 *
 * @author Obai
 * @version 1.0
 * @since 1.0
 */
class MedienSuchenTest {

    /** Bibliothekssystem-Fassade für die Tests. */
    private BibSystem fassade;

    /**
     * Erstellt vor jedem Test ein neues Bibliothekssystem
     * und registriert sowie meldet einen Benutzer an.
     *
     * @throws FalscheEingabeException falls Registrierung fehlschlägt
     * @throws BenutzerNichtGefundenException falls Anmeldung fehlschlägt
     */
    @BeforeEach
    void erstelleObjekt() throws FalscheEingabeException, BenutzerNichtGefundenException {
        this.fassade = new BibSystem();
        fassade.userRegistrieren("obai", "student", 15, "nein");
        fassade.userAnmelden("K1001");
    }

    /**
     * Test für die Suche nach einem bestimmten Titel.
     * Aktuell deaktiviert.
     *
     * @throws MediumNichtGefundenException wenn Medium nicht existiert
     * @throws FalscheEingabeException wenn Eingabe ungültig
     * @throws BenutzerNichtAngemeldetException wenn Benutzer nicht angemeldet ist
     * @throws BenutzerNichtGefundenException wenn Benutzer unbekannt ist
     */
    @Disabled
    void testMedienSuchenNachTitle() throws MediumNichtGefundenException,
                                            FalscheEingabeException,
                                            BenutzerNichtAngemeldetException,
                                            BenutzerNichtGefundenException {
        ArrayList<String> nichtAusgeliehen = fassade.mediumDurchsuchen("Effektives Java Programmieren", "K1001");
        nichtAusgeliehen.forEach(System.out::println);
    }

    /**
     * Test für die Suche nach Medien anhand ihrer Medienart.
     *
     * @throws MediumNichtGefundenException wenn Medium nicht existiert
     * @throws FalscheEingabeException wenn Eingabe ungültig
     * @throws BenutzerNichtAngemeldetException wenn Benutzer nicht angemeldet ist
     * @throws BenutzerNichtGefundenException wenn Benutzer unbekannt ist
     */
    @Test
    void testMedienSuchenNachMedienart() throws MediumNichtGefundenException,
                                               FalscheEingabeException,
                                               BenutzerNichtAngemeldetException,
                                               BenutzerNichtGefundenException {
        ArrayList<String> nichtAusgeliehen = fassade.mediumDurchsuchen("Videospiele", "K1001");
        nichtAusgeliehen.forEach(System.out::println);
    }

    /**
     * Test für die Suche nach aktuell ausgeliehenen Medien.
     * Aktuell deaktiviert.
     *
     * @throws MediumNichtGefundenException wenn Medium nicht existiert
     * @throws FalscheEingabeException wenn Eingabe ungültig
     * @throws BenutzerNichtAngemeldetException wenn Benutzer nicht angemeldet ist
     * @throws BenutzerNichtGefundenException wenn Benutzer unbekannt ist
     */
    @Disabled
    void testMedienSuchenNachausgeliehen() throws MediumNichtGefundenException,
                                                  FalscheEingabeException,
                                                  BenutzerNichtAngemeldetException,
                                                  BenutzerNichtGefundenException {
        ArrayList<String> nichtAusgeliehen = fassade.mediumDurchsuchen("ausgeliehen", "K1001");
        nichtAusgeliehen.forEach(System.out::println);
    }
}
