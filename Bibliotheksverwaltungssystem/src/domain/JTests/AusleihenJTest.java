package domain.JTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.BibSystem;
import domain.ExceptionsKlassen.BenutzerNichtAngemeldetException;
import domain.ExceptionsKlassen.BenutzerNichtGefundenException;
import domain.ExceptionsKlassen.FalscheEingabeException;
import domain.ExceptionsKlassen.MediumNichtGefundenException;

class AusleihenJTest {

	private BibSystem bib;

	@BeforeEach
	void setUp() throws Exception {
		this.bib = new BibSystem();
	}

	@Test
	void test() throws FalscheEingabeException, BenutzerNichtAngemeldetException, BenutzerNichtGefundenException, MediumNichtGefundenException {
		
		bib.userRegistrieren("obai", "student", 15, "nein");
		bib.userAnmdelden(1000);
		bib.mediumAusleihen(1000,"B001" );
		
	}

}
