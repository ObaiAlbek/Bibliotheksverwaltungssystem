package domain.AusleiheSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.fassade.BibSystem;

class AusleiheJTest {

	private BibSystem bib;

	@BeforeEach
	void setUp() throws Exception {
		this.bib = new BibSystem();
	}

	@Test
	void test() throws Exception {
		bib.userRegistrieren("obai", "student", 15, "nein");
		bib.userAnmelden("K1000");
		bib.mediumAusleihen("K1000", "B001");
	}

}
