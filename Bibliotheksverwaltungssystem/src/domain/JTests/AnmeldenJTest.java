package domain.JTests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.BibSystem;
import domain.ExceptionsKlassen.BenutzerNichtGefundenException;
import domain.ExceptionsKlassen.FalscheEingabeException;

class AnmeldenJTest {
	
	private BibSystem bib;
	
	@BeforeEach
	void setUp() throws Exception {
		this.bib = new BibSystem();
	}

	@Test
	void testUSerIstNichtAngemeldet() {
		// Status => True
		assertThrows(BenutzerNichtGefundenException.class, () -> bib.userAnmdelden("1110"));
		
	}
	
	@Test
	void testUSerIstAngemeldet() throws FalscheEingabeException, BenutzerNichtGefundenException {
		
		bib.userRegistrieren("obai", "student", 16, "nein");
		
		
	}

}
