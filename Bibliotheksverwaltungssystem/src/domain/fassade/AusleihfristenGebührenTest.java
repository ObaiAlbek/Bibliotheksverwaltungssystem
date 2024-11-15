package domain.fassade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AusleihfristenGebührenTest {

	private BibSystem bib;

	@BeforeEach
	void setUp() throws Exception {
		this.bib = new BibSystem();
	}

	@Test
	void test() throws Exception {
		bib.userRegistrieren("obai", "student", 15, "nein");
		bib.userAnmdelden("K1001");
		bib.mediumAusleihen("K1001", "B001");
		
		assertEquals(2.0,bib.simuliereMedienRückgabe("B001", "2024-11-13"));
	}

}
