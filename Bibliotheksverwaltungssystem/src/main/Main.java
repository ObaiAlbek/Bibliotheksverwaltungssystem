package main;


import java.time.LocalDate;

import domain.ExceptionsKlassen.BenutzerNichtAngemeldetException;
import domain.ExceptionsKlassen.FalscheEingabeException;
import domain.ExceptionsKlassen.MediumNichtGefundenException;
import tui.Tui;

public class Main {

	public static void main(String[] args) throws FalscheEingabeException, MediumNichtGefundenException, BenutzerNichtAngemeldetException {
		//new Tui();
		
		 // Aktuelles Datum
        LocalDate today = LocalDate.now();
        System.out.println("Heutiges Datum: " + today);
        
        // Datum nach einem Jahr
        LocalDate oneYearLater = today.plusYears(1);
        System.out.println("Datum nach einem Jahr: " + oneYearLater);
	}

}
