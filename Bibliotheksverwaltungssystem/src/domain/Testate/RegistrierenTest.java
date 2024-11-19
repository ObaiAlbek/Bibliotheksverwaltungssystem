package domain.Testate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.Benutzer.*;
import domain.ExceptionsKlassen.FalscheEingabeException;
import domain.UserRegistieren.Registieren;

class RegistrierenTest {

	@Test
	void testKunde() throws FalscheEingabeException {

		Benutzer student = Registieren.userRegistrieren("Müller", "student", 15, "nein");
		// True
		assertTrue(student instanceof Studenten);

		Benutzer erwachsener = Registieren.userRegistrieren("Schneider", "erwachsener", 15, "nein");
		// True
		assertTrue(erwachsener instanceof Erwachsener);

		Benutzer mitarbeiter = Registieren.userRegistrieren("Schuchmacher", "mitarbeiter", 15, "ja");
		assertTrue(mitarbeiter instanceof Mitarbeiter);

	}

}
