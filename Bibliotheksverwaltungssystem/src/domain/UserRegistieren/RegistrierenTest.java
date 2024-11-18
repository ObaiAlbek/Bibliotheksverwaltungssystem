package domain.UserRegistieren;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import domain.Benutzer.Benutzer;
import domain.Benutzer.Erwachsener;
import domain.Benutzer.Mitarbeiter;
import domain.Benutzer.Studenten;
import domain.ExceptionsKlassen.FalscheEingabeException;
import domain.fassade.BibSystem;

class RegistrierenTest {
	private BibSystem bib;
	
	@BeforeEach
	void erstelleObjekt() {
		this.bib = new BibSystem();
	}

	@Test
	void testKunde() throws FalscheEingabeException {
		
		Benutzer benutzer = Registieren.userRegistrieren("obai", "student", 15, "nein");
		// True ist
		assertTrue(benutzer instanceof Studenten);
		String bibKartenNummer = "K1000";
		assertTrue(benutzer.getBibAusweis().getKartenNummer().equalsIgnoreCase(bibKartenNummer));
	
		
		Benutzer erwachsener = Registieren.userRegistrieren("obai", "erwachsener", 15, "nein");
		// True 
		assertTrue(erwachsener instanceof Erwachsener);
	}
	
	@Disabled
	void testMitarbeiter() throws FalscheEingabeException {
		
		Benutzer benutzer = Registieren.userRegistrieren("obai", "mitarbeiter", 15, "ja");
		// True, da Mitarbeiter ist
		assertTrue(benutzer instanceof Mitarbeiter);
		String bibKartenNummer = "A1001";
		assertTrue(benutzer.getBibAusweis().getKartenNummer().equalsIgnoreCase(bibKartenNummer));
	}
	


}
