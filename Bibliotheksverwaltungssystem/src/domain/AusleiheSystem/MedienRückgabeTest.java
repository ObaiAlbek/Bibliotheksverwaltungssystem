package domain.AusleiheSystem;


import static org.junit.jupiter.api.Assertions.assertEquals;

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
		bib.userAnmelden("K1001");
		bib.mediumAusleihen("K1001", "B001");
		
		double gebühren = bib.datumÄndern("B001", "2022-09-01", "2022-10-01", "2022-10-03");
		assertEquals(2.0,gebühren);
		
		
	}

	

}
