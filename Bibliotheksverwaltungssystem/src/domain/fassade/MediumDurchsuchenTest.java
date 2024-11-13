package domain.fassade;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import domain.Benutzer.Benutzer;
import domain.ExceptionsKlassen.BenutzerNichtAngemeldetException;
import domain.ExceptionsKlassen.BenutzerNichtGefundenException;
import domain.ExceptionsKlassen.FalscheEingabeException;
import domain.ExceptionsKlassen.MediumNichtGefundenException;
import domain.UserRegistieren.Registieren;

class MediumDurchsuchenTest {
	
	BibSystem fassade;
	@BeforeEach
	void setUp() throws Exception {
		fassade = new BibSystem();
	}

	@Disabled
	void testMediumsuchen() throws FalscheEingabeException, BenutzerNichtGefundenException, MediumNichtGefundenException, BenutzerNichtAngemeldetException {
		fassade.userRegistrieren("obai", "student", 15, "nein");
		assertTrue(fassade.userAnmdelden("K1000"));
		ArrayList<String> treffer = (ArrayList<String>) fassade.mediumDurchsuchen("Effektives Java Programmieren", "B001");
		treffer.forEach(System.out::println);
	}
	
	@Test
	void testMediumNichtGefunden() throws FalscheEingabeException, BenutzerNichtGefundenException, MediumNichtGefundenException, BenutzerNichtAngemeldetException {
		fassade.userRegistrieren("obai", "student", 15, "nein");
		assertTrue(fassade.userAnmdelden("K1000"));
		assertThrows(MediumNichtGefundenException.class, () -> fassade.mediumDurchsuchen("C++ Programmieren", "B001"));
		
	}


}
