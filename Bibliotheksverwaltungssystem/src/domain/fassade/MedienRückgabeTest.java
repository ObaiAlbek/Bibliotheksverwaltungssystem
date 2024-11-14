package domain.fassade;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MedienRückgabeTest {

	private BibSystem bib;

	@BeforeEach
	void setUp() throws Exception {
		this.bib = new BibSystem();
	}

	@Test
	void test() throws Exception {
		bib.userRegistrieren("obai", "student", 15, "nein");
		bib.userAnmdelden("K1000");
		bib.mediumAusleihen("K1000", "B001");
		
		ArrayList<String> test = bib.medienRückgabe("B001");
		test.forEach(System.out::println);
	}

}
