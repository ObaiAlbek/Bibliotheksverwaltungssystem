package domain.UserRegistieren;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.BibSystem;
import domain.Benutzer.Ausweis;
import domain.Benutzer.Benutzer;
import domain.Benutzer.Kunde;
import domain.Benutzer.Mitarbeiter;
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
	void testKunde() throws FalscheEingabeException {
		
		Benutzer benutzer = Registieren.userRegistrieren("obai", "student", 15, "nein");
		// True, da Kunde ist
		assertTrue(benutzer instanceof Kunde);
		String bibKartenNummer = "K1000";
		assertTrue(benutzer.getBibAusweis().getKartenNummer().equalsIgnoreCase(bibKartenNummer));
	}
	
	@Test
	void testMitarbeiter() throws FalscheEingabeException {
		
		Benutzer benutzer = Registieren.userRegistrieren("obai", "mitarbeiter", 15, "ja");
		// True, da Mitarbeiter ist
		assertTrue(benutzer instanceof Mitarbeiter);
		String bibKartenNummer = "A1001";
		assertTrue(benutzer.getBibAusweis().getKartenNummer().equalsIgnoreCase(bibKartenNummer));
	}
	


}
