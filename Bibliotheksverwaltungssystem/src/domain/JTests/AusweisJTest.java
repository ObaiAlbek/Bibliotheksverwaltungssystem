package domain.JTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.BibSystem;
import domain.Benutzer.Benutzer;
import domain.ExceptionsKlassen.BenutzerNichtAngemeldetException;
import domain.ExceptionsKlassen.BenutzerNichtGefundenException;
import domain.ExceptionsKlassen.FalscheEingabeException;
import domain.ExceptionsKlassen.MediumNichtGefundenException;

class AusweisJTest {

	private BibSystem bib;

	@BeforeEach
	void setUp() throws Exception {
		this.bib = new BibSystem();
	}

	@Test
	void testKunde() throws FalscheEingabeException, BenutzerNichtAngemeldetException, BenutzerNichtGefundenException, MediumNichtGefundenException {
		// bibAusweisNummer= K1000
		System.out.println(bib.userRegistrieren("obai", "student", 15, "nein"));
		
	}
	
	@Test
	void testMitarbeiter() throws FalscheEingabeException, BenutzerNichtAngemeldetException, BenutzerNichtGefundenException, MediumNichtGefundenException {
		// bibAusweisNummer= A1001
		System.out.println(bib.userRegistrieren("Muster Müller", "Mitarbeiter", 25, "ja"));
		
	}

}
