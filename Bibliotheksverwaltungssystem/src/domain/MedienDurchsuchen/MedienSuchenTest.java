package domain.MedienDurchsuchen;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import domain.Benutzer.Benutzer;
import domain.ExceptionsKlassen.BenutzerNichtAngemeldetException;
import domain.ExceptionsKlassen.BenutzerNichtGefundenException;
import domain.ExceptionsKlassen.FalscheEingabeException;
import domain.ExceptionsKlassen.MediumNichtGefundenException;
import domain.UserRegistieren.Registieren;
import domain.fassade.BibSystem;

class MedienSuchenTest {

private BibSystem fassade;
	
	@BeforeEach
	void erstelleObjekt() throws FalscheEingabeException, BenutzerNichtGefundenException {
		this.fassade = new BibSystem();
		fassade.userRegistrieren("obai", "student", 15, "nein");
		fassade.userAnmdelden("K1000");

	}
	
	@Disabled
	void testMedienSuchenNachTitle()throws MediumNichtGefundenException, FalscheEingabeException, BenutzerNichtAngemeldetException {
		ArrayList<String> nichtAusgeliehen = fassade.mediumDurchsuchen("Effektives Java Programmieren", "K1000");
		nichtAusgeliehen.forEach(System.out::println);

	}

	@Disabled
	void testMedienSuchenNachMedienart()throws MediumNichtGefundenException, FalscheEingabeException, BenutzerNichtAngemeldetException {
		ArrayList<String> nichtAusgeliehen = fassade.mediumDurchsuchen("Videospiele", "K1000");
		nichtAusgeliehen.forEach(System.out::println);

	}
	
	@Test
	void testMedienSuchenNachausgeliehen()throws MediumNichtGefundenException, FalscheEingabeException, BenutzerNichtAngemeldetException {
		ArrayList<String> nichtAusgeliehen = fassade.mediumDurchsuchen("ausgeliehen", "K1000");
		nichtAusgeliehen.forEach(System.out::println);

	}

}
