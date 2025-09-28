package domain.fassade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.benutzer.Benutzer;

/**
 * Testet Jahresgebühren-Berechnung und Verbuchung durch den Admin.
 *
 * author Obai
 * since 1.0
 */
class GebührenTest {

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
     * Berechnet Gebühren bis 2026-11-16, prüft Admin-Login, Abfrage und Verbuchung.
     *
     * @throws Exception bei Fehlern im Ablauf
     */
    @Test
    @DisplayName("Jahresgebühren berechnen und verbuchen")
    void testGebuehrenFlow() throws Exception {
        fassade.userRegistrieren("obai", "schüler", 15, "nein");
        fassade.userAnmelden("K1001");

        Benutzer user = fassade.findeBenutzer("K1001");
        double gebuehren = fassade.jahresGebührenBerechnen("K1001", "2026-11-16");

        assertEquals(50.0, gebuehren, 1e-9);
        assertEquals(gebuehren, user.getGebühren(), 1e-9);

        assertTrue(fassade.adminAnmelden("A1000"));
        assertEquals(50.0, fassade.getgbührenBenutzer("K1001"), 1e-9);

        fassade.gebührenVerbuchen("K1001");
        assertEquals(0.0, user.getGebühren(), 1e-9);
    }
}
