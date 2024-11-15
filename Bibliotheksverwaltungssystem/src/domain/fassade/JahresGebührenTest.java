package domain.fassade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class JahresGebührenTest {
	BibSystem fassade;
	@BeforeEach
	void setUp() throws Exception {
		
		fassade = new BibSystem();
	}

	@Test
	void testFürKunde() throws Exception {
		fassade.userRegistrieren("obai", "schüler", 15, "nein");
		fassade.userAnmdelden("K1001");
		double gebühren = fassade.simuliereJahresGebührenBerechnen("K1001", "2020-11-15");
		assertEquals(100.0, gebühren);
		assertTrue(fassade.gebührenBezahlen(gebühren, "K1001"));

	}
	
	@Disabled
	void testFürMitarbeiter()  throws Exception {
		fassade.userRegistrieren("obai", "mitarbeiter", 15, "ja");
		fassade.userAnmdelden("A1000");
		double gebühren = fassade.simuliereJahresGebührenBerechnen("A1000","2020-11-15");
		assertEquals(200.0,gebühren);
		
	}

}
