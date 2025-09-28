package domain.ausleihSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.benutzer.Benutzer;
import domain.fassade.BibSystem;

/**
 * Testet Rückgabe mit Überfälligkeit, Gebührenverbuchung und Admin-Workflow.
 * Enthält eine Datumssimulation zur Reproduktion der Gebührenlogik.
 * 
 * author Obai
 * since 1.0
 */
class MedienRückgabeTest {

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
     * Simuliert eine überfällige Rückgabe, prüft Gebührenhöhe, Admin-Login
     * und die Verbuchung der Gebühren auf 0.0.
     *
     * @throws Exception wenn der Ablauf fehlschlägt
     */
    @Test
    @DisplayName("Rückgabe mit Überfälligkeit -> 9.0 EUR Gebühren, danach Verbuchung auf 0.0")
    void rueckgabeMitGebuehrenUndVerbuchung() throws Exception {
        bib.userRegistrieren("obai", "student", 15, "nein");
        bib.userAnmelden("K1001");
        bib.mediumAusleihen("K1001", "B001");

        Benutzer user = bib.findeBenutzer("K1001");

        double gebuehren = bib.datumÄndern("B001", "2022-09-01", "2022-10-01", "2022-10-09");
        assertEquals(9.0, gebuehren, 1e-9);

        assertTrue(bib.adminAnmelden("A1000"));
        assertEquals(9.0, bib.getgbührenBenutzer("K1001"), 1e-9);

        bib.gebührenVerbuchen("K1001");
        assertEquals(0.0, user.getGebühren(), 1e-9);
    }
}
