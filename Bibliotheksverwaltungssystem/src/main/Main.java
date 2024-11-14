package main;


import domain.ExceptionsKlassen.BenutzerNichtAngemeldetException;
import domain.ExceptionsKlassen.FalscheEingabeException;
import domain.ExceptionsKlassen.MediumNichtGefundenException;
import tui.Tui;

public class Main {

	public static void main(String[] args) throws FalscheEingabeException, MediumNichtGefundenException, BenutzerNichtAngemeldetException {
		new Tui();
	}

}
