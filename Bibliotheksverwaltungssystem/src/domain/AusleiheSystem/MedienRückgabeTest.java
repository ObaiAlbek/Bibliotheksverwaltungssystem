package domain.AusleiheSystem;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.fassade.BibSystem;

class MedienRückgabeTest {

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
		bib.mediumAusleihen("K1001", "BG001");
		
		ArrayList<String> test = bib.medienRückgabe("B001");
		if (test.size() == 0)
			System.out.println("kein Mehr");
		test.forEach(System.out::println);
	}

}
