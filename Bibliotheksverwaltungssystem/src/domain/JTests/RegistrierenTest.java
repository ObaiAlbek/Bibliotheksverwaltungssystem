package domain.JTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.BibSystem;
import domain.Benutzer.Ausweis;
import domain.Benutzer.Benutzer;
import domain.Benutzer.Kunde;
import domain.ExceptionsKlassen.FalscheEingabeException;

class RegistrierenTest {
	private BibSystem bib;
	
	@BeforeEach
	void erstelleObjekt() {
		this.bib = new BibSystem();
	}

	@Test
	void test() throws FalscheEingabeException {
		String name = "obai";
		String type = "schüler";
		String istAdmin = "nein";
		String test = bib.userRegistrieren(name, type, 15,istAdmin);
		
		assertTrue(test.contains(name));
		System.out.println(test);
	}

}
