package domain.fassade;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.benutzer.Benutzer;
import domain.benutzer.Erwachsener;
import domain.benutzer.Student;
import domain.exceptionsKlassen.FalscheEingabeException;

/**
 * Testet die Benutzerregistrierung und korrekte Typzuordnung.
 *
 * author Obai
 * since 1.0
 */
class RegistrierenTest {

    /** Bibliothekssystem-Fassade. */
    private BibSystem bib;

    /**
     * Frischer Systemzustand vor jedem Test.
     */
    @BeforeEach
    void erstelleObjekt() {
        this.bib = new BibSystem();
    }

    /**
     * Prüft, dass "student" -> {@link Student} und "erwachsener" -> {@link Erwachsener}.
     *
     * @throws FalscheEingabeException bei ungültigen Eingaben
     */
    @Test
    @DisplayName("Registrierung: Student und Erwachsener")
    void testKunde() throws FalscheEingabeException {
        Benutzer benutzer = Registieren.userRegistrieren("obai", "student", 15, "nein");
        assertTrue(benutzer instanceof Student);
        String bibKartenNummer = "K1000";
        assertTrue(benutzer.getBibAusweis().getKartenNummer().equalsIgnoreCase(bibKartenNummer));

        Benutzer erwachsener = Registieren.userRegistrieren("obai", "erwachsener", 15, "nein");
        assertTrue(erwachsener instanceof Erwachsener);
    }
}
