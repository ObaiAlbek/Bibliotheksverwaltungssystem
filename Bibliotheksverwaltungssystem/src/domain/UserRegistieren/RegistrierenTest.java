package domain.UserRegistieren;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.BibSystem;
import domain.Benutzer.Ausweis;
import domain.Benutzer.Benutzer;
import domain.Benutzer.Kunde;
import domain.ExceptionsKlassen.FalscheEingabeException;
import domain.Medium.Buch;
import domain.Medium.Mediumverwalter;

class RegistrierenTest {
	private BibSystem bib;
	
	@BeforeEach
	void erstelleObjekt() {
		this.bib = new BibSystem();
	}

	@Test
	void test() throws FalscheEingabeException {
		
		
	}

}
