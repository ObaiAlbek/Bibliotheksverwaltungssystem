package domain.Testate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import domain.fassade.BibSystem;

class AusleihenTest {

	private BibSystem bib;

	@BeforeEach
	void setUp() throws Exception {
		this.bib = new BibSystem();
	}

	@Disabled
	void testAusleiheFris() throws Exception {
		
		bib.userRegistrieren("obai", "student", 15, "nein");
		bib.userAnmelden("K1001");
		double gerbühren = bib.mediumAusleihen("K1001", "B001");
		assertEquals(0.0,gerbühren);
		
		// das ist ein Test für das Testate:
		String ausleihBeginn = "2024-11-01";
		String ausleihEnde = "2024-12-01";
		String heutigesDatum = "2024-12-05";
		double überfälligeGebühren = bib.datumÄndern("B001", ausleihBeginn, ausleihEnde, heutigesDatum);
		assertEquals(4.0,überfälligeGebühren);
		
		// Gebühren bezahlen (Admin)
		assertTrue(bib.adminAnmelden("A1000"));
		assertEquals(4.0,bib.getgbührenBenutzer("K1001"));
		assertTrue(bib.gebührenVerbuchen("K1001"));
	}
	
	@Test
	void testMitJahresGebühren() throws Exception {
		
		bib.userRegistrieren("obai", "student", 15, "nein");
		bib.userAnmelden("K1001");
		double gerbühren = bib.mediumAusleihen("K1001", "B001");
		assertEquals(0.0,gerbühren);
		
		// das ist eine Test für das Testate:
		String ausleihBeginn = "2024-11-01";
		String ausleihEnde = "2024-12-01";
		String heutigesDatum = "2024-12-05";
		double überfälligeGebühren = bib.datumÄndern("B001", ausleihBeginn, ausleihEnde, heutigesDatum);
		assertEquals(4.0,überfälligeGebühren);
		
		// setze noch jahresGebühren dazu
		double jahreGebühren = bib.jahresGebührenBerechnen("K1001", "2025-11-19");
		assertEquals(29.0,jahreGebühren);
		
		// Gebühren bezahlen (Admin)
		assertTrue(bib.adminAnmelden("A1000"));
		assertEquals(29.0,bib.getgbührenBenutzer("K1001"));
		assertTrue(bib.gebührenVerbuchen("K1001"));
	}

}
