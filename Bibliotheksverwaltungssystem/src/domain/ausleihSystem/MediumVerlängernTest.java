package domain.ausleihSystem;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.fassade.BibSystem;

/**
 * Testet die Verlängerung einer bestehenden Ausleihe.
 * 
 * author Obai
 * since 1.0
 */
class MediumVerlängernTest {

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
     * Prüft, dass eine Verlängerung unter gültigen Bedingungen erfolgreich ist.
     *
     * @throws Exception wenn der Ablauf fehlschlägt
     */
    @Test
    @DisplayName("Verlängerung einer aktiven, verlängerbaren Ausleihe")
    void verlaengerungErfolgreich() throws Exception {
        bib.userRegistrieren("obai", "student", 15, "nein");
        bib.userAnmelden("K1001");
        bib.mediumAusleihen("K1001", "B001");

        assertTrue(bib.medienVerlängern("B001", "K1001"));
    }
}
