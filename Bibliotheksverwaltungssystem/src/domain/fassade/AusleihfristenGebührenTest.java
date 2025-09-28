package domain.fassade;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * End-to-End-Test: Ausleihe starten ohne Gebührenerhöhung bei nicht überfälliger Frist.
 *
 * author Obai
 * since 1.0
 */
class AusleihfristenGebührenTest {

    /** Bibliothekssystem-Fassade. */
    private BibSystem bib;

    /**
     * Initialisiert ein frisches System vor jedem Test.
     *
     * @throws Exception wenn Initialisierung fehlschlägt
     */
    @BeforeEach
    void setUp() throws Exception {
        this.bib = new BibSystem();
    }

    /**
     * Startet eine Ausleihe für einen registrierten und angemeldeten Benutzer.
     *
     * @throws Exception bei Fehlern im Ablauf
     */
    @Test
    @DisplayName("Ausleihe starten (ohne Überfälligkeit) läuft fehlerfrei")
    void ausleiheStartOhneGebuehren() throws Exception {
        bib.userRegistrieren("obai", "student", 15, "nein");
        bib.userAnmelden("K1001");
        assertDoesNotThrow(() -> bib.mediumAusleihen("K1001", "B001"));
    }
}
