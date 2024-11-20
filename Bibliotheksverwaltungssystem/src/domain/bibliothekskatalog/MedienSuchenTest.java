package domain.bibliothekskatalog;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import domain.benutzer.Benutzer;
import domain.exceptionsKlassen.BenutzerNichtAngemeldetException;
import domain.exceptionsKlassen.BenutzerNichtGefundenException;
import domain.exceptionsKlassen.FalscheEingabeException;
import domain.exceptionsKlassen.MediumNichtGefundenException;
import domain.fassade.BibSystem;
import domain.fassade.Registieren;

class MedienSuchenTest {

private BibSystem fassade;
	
	@BeforeEach
	void erstelleObjekt() throws FalscheEingabeException, BenutzerNichtGefundenException {
		this.fassade = new BibSystem();
		fassade.userRegistrieren("obai", "student", 15, "nein");
		fassade.userAnmelden("K1001");

	}
	
	@Disabled
	void testMedienSuchenNachTitle()throws MediumNichtGefundenException, FalscheEingabeException, BenutzerNichtAngemeldetException, BenutzerNichtGefundenException {
		ArrayList<String> nichtAusgeliehen = fassade.mediumDurchsuchen("Effektives Java Programmieren", "K1001");
		nichtAusgeliehen.forEach(System.out::println);

	}

	@Test
	void testMedienSuchenNachMedienart()throws MediumNichtGefundenException, FalscheEingabeException, BenutzerNichtAngemeldetException, BenutzerNichtGefundenException {
		ArrayList<String> nichtAusgeliehen = fassade.mediumDurchsuchen("Videospiele", "K1001");
		nichtAusgeliehen.forEach(System.out::println);

	}
	
	@Disabled
	void testMedienSuchenNachausgeliehen()throws MediumNichtGefundenException, FalscheEingabeException, BenutzerNichtAngemeldetException, BenutzerNichtGefundenException {
		ArrayList<String> nichtAusgeliehen = fassade.mediumDurchsuchen("ausgeliehen", "K1001");
		nichtAusgeliehen.forEach(System.out::println);

	}

}
