package tui;

import java.util.Scanner;

import domain.BibSystem;
import domain.ExceptionsKlassen.BenutzerNichtAngemeldetException;
import domain.ExceptionsKlassen.BenutzerNichtGefundenException;
import domain.ExceptionsKlassen.FalscheEingabeException;
import domain.ExceptionsKlassen.MediumNichtGefundenException;

public class Tui {
	private BibSystem fassade;
	private Scanner eingabe = new Scanner(System.in);
	
	public Tui() {
		this.fassade = new BibSystem();
		startBibProgramm();
	}
	
	private void startBibProgramm() {
		String aktion;
		System.out.println("<< Willkommen in der Bibliothek >>");
		
		boolean programmIstAktiv = true;
		while(programmIstAktiv) {
			System.out.println("1.Registrieren");
			System.out.println("2.Anmelden");
			System.out.println("4.Medien Durchsuchen");
			System.out.println("3.Ausleihen eines Gegenstandes");
			System.out.println("5.Rückgabe eines Gegenstandes");
			System.out.println("6.Ausgeliehene Gegenstände, Fälligkeitsdaten und aufgelaufene Gebühren anzeigen");
			System.out.println("7.Verlängern der Leihfrist");
			System.out.println("8.Verbuchen von überfälligen Gebühren (Admin)");
			System.out.println("Wählen Sie bitte eine Aktion aus: ");
			System.out.print(">");
			aktion = eingabe.nextLine();
			
			switch(aktion) {
				case "1":
					registrierenProzess();
					break;
					
				case "2":
					anmeldenProzess();
					break;
				
				case "3":
					mediumDurchsuchenProzess();
					break;
					
				case "4":
					mediumAusleihenProzess();
					break;
			}
		}
		
	}
	
	private void mediumAusleihenProzess(){
		int kartennummer;
		String eindutigeKennung;
		boolean mediumAusleihenProzess = true;
		
		System.out.println("Geben Sie bitte Ihre kartennummer");
		System.out.print(">");
		kartennummer = eingabe.nextInt();
		while(mediumAusleihenProzess) {
			System.out.println("Geben Sie bitte die eindutige Kennung des Mediums");
			System.out.print(">");
			eindutigeKennung = eingabe.nextLine();
			
			try {
				fassade.mediumAusleihen(kartennummer, eindutigeKennung);
				
			} catch (BenutzerNichtAngemeldetException | BenutzerNichtGefundenException | MediumNichtGefundenException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	
	private void mediumDurchsuchenProzess() {
		
	}
	
	private void anmeldenProzess() {
		int kartennummer;
		System.out.println("Geben Sie bitte die Kartennummer Ihres Bibliotheksausweises an: ");
		System.out.print(">");
		kartennummer = eingabe.nextInt();
		
		try {
			if (fassade.userAnmdelden(kartennummer))
				System.out.println("Sie sind nun im System Angemeldet");
		} catch (BenutzerNichtGefundenException e) {
			System.out.println(e.getMessage());
		}
		startBibProgramm();
	}
	
	
	
	private void registrierenProzess() {
		boolean registrierenProzess = true;
		String name;
		int alter;
		String type;
		String istAdmin;

		while (registrierenProzess) {
			System.out.println("Name: ");
			System.out.print(">");
			name = eingabe.nextLine();
			System.out.println("Alter: ");
			System.out.print(">");
			alter = eingabe.nextInt();
			System.out.println("sind Sie Schüler oder Student: Ja/Nein");
			System.out.print(">");
			type = eingabe.nextLine();
			System.out.println("Admin: Ja/Nein");
			System.out.print(">");
			istAdmin = eingabe.nextLine();
			try {
				fassade.userRegistrieren(name, type, alter, istAdmin);
				registrierenProzess = false;
				startBibProgramm();
			} catch (FalscheEingabeException e) {
				System.out.println(e.getMessage());
				continue;
			}

		}

	}
}
