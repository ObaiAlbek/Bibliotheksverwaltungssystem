package domain.fassade;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		double gebühren = fassade.jahresGebührenBerechnen("K1001", "2026-11-16");
		assertEquals(50.0, gebühren);
		

	}

}
