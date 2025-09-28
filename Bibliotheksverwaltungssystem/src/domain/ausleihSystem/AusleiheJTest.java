package domain.ausleihSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.fassade.BibSystem;

/**
 * Testet die Ausleihe eines Mediums und die initiale Gebührenhöhe.
 *
 * @author Obai
 * @since 1.0
 */
class AusleiheJTest {

    /** Fassade des Bibliothekssystems für End-to-End-Tests. */
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
     * Prüft, dass eine Ausleihe die Gebühren des Benutzers nicht erhöht,
     * wenn keine Überfälligkeit vorliegt.
     *
     * @throws Exception wenn der Ablauf fehlschlägt
     */
    @Test
    @DisplayName("Ausleihe ohne Überfälligkeit -> Gebühren = 0.0")
    void ausleiheOhneGebuehren() throws Exception {
        bib.userRegistrieren("obai", "student", 15, "nein");
        bib.userAnmelden("K1001");
        double userGebuehren = bib.mediumAusleihen("K1001", "B001");
        assertEquals(0.0, userGebuehren, 1e-9);
    }
}
