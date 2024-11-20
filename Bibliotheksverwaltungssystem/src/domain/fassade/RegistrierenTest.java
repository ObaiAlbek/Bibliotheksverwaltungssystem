package domain.fassade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import domain.benutzer.Benutzer;
import domain.benutzer.Erwachsener;
import domain.benutzer.Mitarbeiter;
import domain.benutzer.Student;
import domain.exceptionsKlassen.FalscheEingabeException;

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
		assertTrue(benutzer instanceof Student);
		String bibKartenNummer = "K1000";
		assertTrue(benutzer.getBibAusweis().getKartenNummer().equalsIgnoreCase(bibKartenNummer));
	
		
		Benutzer erwachsener = Registieren.userRegistrieren("obai", "erwachsener", 15, "nein");
		// True 
		assertTrue(erwachsener instanceof Erwachsener);
	}
	
	


}
