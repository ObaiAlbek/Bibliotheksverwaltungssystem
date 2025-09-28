package domain.fassade;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.exceptionsKlassen.BenutzerNichtGefundenException;
import domain.exceptionsKlassen.FalscheEingabeException;

/**
 * Testet die Benutzeranmeldung.
 *
 * author Obai
 * since 1.0
 */
class UserAnmeldenTest {

    /** Fassade des Systems. */
    private BibSystem fassade;

    /**
     * Frischer Systemzustand vor jedem Test.
     *
     * @throws Exception wenn Initialisierung fehlschlägt
     */
    @BeforeEach
    void setUp() throws Exception {
        fassade = new BibSystem();
    }

    /**
     * Anmeldung eines registrierten Benutzers.
     *
     * @throws FalscheEingabeException bei ungültiger Registrierung
     * @throws BenutzerNichtGefundenException wenn Benutzer nicht existiert
     */
    @Test
    @DisplayName("Anmeldung: registrierter Benutzer kann sich anmelden")
    void testAnmelden() throws FalscheEingabeException, BenutzerNichtGefundenException {
        fassade.userRegistrieren("obai", "Student", 15, "nein");
        fassade.userAnmelden("K1001");
    }

    /**
     * Anmeldung schlägt fehl, wenn Benutzer nicht existiert.
     */
    @Test
    @DisplayName("Anmeldung: Benutzer nicht im System -> Exception")
    void testBenutzerIstNichtImSystem() {
        assertThrows(BenutzerNichtGefundenException.class, () -> fassade.userAnmelden("K1001"));
    }
}
