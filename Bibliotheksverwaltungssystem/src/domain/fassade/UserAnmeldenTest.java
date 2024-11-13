package domain.fassade;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.ExceptionsKlassen.BenutzerNichtGefundenException;
import domain.ExceptionsKlassen.FalscheEingabeException;

class UserAnmeldenTest {
	
	BibSystem fassade;
	@BeforeEach
	void setUp() throws Exception {
		fassade = new BibSystem();
	}

	@Test
	void testAnmelden() throws FalscheEingabeException, BenutzerNichtGefundenException {
		
		// True => User ist Angemeldet
		fassade.userRegistrieren("obai", "Student", 15, "nein");
		assertTrue(fassade.userAnmdelden("K1000"));
	}
	
	@Test
	void testBenutzerIstNichtImSystem() throws FalscheEingabeException, BenutzerNichtGefundenException {
		
		// True, da Benutzer nicht im System ist
		assertThrows( BenutzerNichtGefundenException.class, () -> fassade.userAnmdelden("K1000") );
	}

}
