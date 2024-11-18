package domain.fassade;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Benutzer.Benutzer;
import domain.ExceptionsKlassen.BenutzerNichtGefundenException;
import domain.ExceptionsKlassen.FalscheEingabeException;

class GebührenTest {

	BibSystem fassade;

	@BeforeEach
	void setUp() throws Exception {
		fassade = new BibSystem();
	}

	@Test
	void testGebühren() throws Exception {
		fassade.userRegistrieren("obai", "schüler", 15, "nein");
		fassade.userAnmelden("K1001");
		Benutzer user = fassade.findeBenutzer("K1001");
		double gebühren = fassade.jahresGebührenBerechnen("K1001", "2026-11-16");
		assertEquals(50.0, gebühren);
		assertTrue (user.getGebühren() == gebühren);
		
		assertTrue(fassade.adminAnmelden("A1000"));
		assertTrue(fassade.getgbührenBenutzer("K1001") == 50.0);
		fassade.gebührenVerbuchen("K1001");
		assertTrue (user.getGebühren() == 0.0);
	}

}
