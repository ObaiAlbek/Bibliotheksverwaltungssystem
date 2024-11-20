package domain.fassade;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.exceptionsKlassen.BenutzerNichtGefundenException;
import domain.exceptionsKlassen.FalscheEingabeException;

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
		fassade.userAnmelden("K1001");
	}
	
	@Test
	void testBenutzerIstNichtImSystem() throws FalscheEingabeException, BenutzerNichtGefundenException {
		
		// True, da Benutzer nicht im System ist
		assertThrows( BenutzerNichtGefundenException.class, () -> fassade.userAnmelden("K1001") );
	}

}
