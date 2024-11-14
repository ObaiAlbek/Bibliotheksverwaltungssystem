package tui;

import java.util.Scanner;

import domain.ExceptionsKlassen.BenutzerNichtAngemeldetException;
import domain.ExceptionsKlassen.BenutzerNichtGefundenException;
import domain.ExceptionsKlassen.FalscheEingabeException;
import domain.ExceptionsKlassen.MediumNichtGefundenException;
import domain.fassade.BibSystem;

public class Tui {
	private BibSystem fassade;
	private Scanner eingabe = new Scanner(System.in);
	
	public Tui() {
		this.fassade = new BibSystem();
		startBibProgramm();
	}
	
	private void startBibProgramm() throws FalscheEingabeException, MediumNichtGefundenException, BenutzerNichtAngemeldetException {
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
			System.out.println("9.Gebühren berechnen");
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
		String kartennummer;
		String eindutigeKennung;
		boolean mediumAusleihenProzess = true;
		
		System.out.println("Geben Sie bitte Ihre kartennummer");
		System.out.print(">");
		kartennummer = eingabe.nextLine();
		while(mediumAusleihenProzess) {
			System.out.println("Geben Sie bitte die eindutige Kennung des Mediums");
			System.out.print(">");
			eindutigeKennung = eingabe.nextLine();
			
			System.out.println("Geben Sie bitte den Datum ein");
			System.out.println("Geben Sie bitte die Ausleihebeginn");

		
		
			try {
				fassade.mediumAusleihen(kartennummer, eindutigeKennung);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} 
		}
		
	}
	
	
	private void mediumDurchsuchenProzess() throws FalscheEingabeException, MediumNichtGefundenException, BenutzerNichtAngemeldetException {
		String auswahl;
		String bibKartennummer;
		boolean mediumDurchsuchenProzess = true;
		System.out.println("Wählen Sie bitte aus, wonache Sie suchen möchten");
		while (mediumDurchsuchenProzess) {
			System.out.println("Katalog der Bibliothek: ");
			System.out.println("Title");
			System.out.println("Ausgeliehene Medien");
			System.out.println("Nicht Ausgeliehene Medien");
			System.out.println("Medienart (Bücher,Brettspiele,Dvds,Cds oder Videospiele");
			System.out.println("zurück");
			auswahl = eingabe.nextLine();
			if (auswahl.equalsIgnoreCase("zurück")){
				mediumDurchsuchenProzess = false;
				startBibProgramm();
			}
			System.out.println("gebe Sie nun Ihre BibKartenummer");
			bibKartennummer = eingabe.nextLine();
					
			fassade.mediumDurchsuchen(auswahl, bibKartennummer);
		}

	}
	
	private void anmeldenProzess() {
		String kartennummer;
		System.out.println("Geben Sie bitte die Kartennummer Ihres Bibliotheksausweises an: ");
		System.out.print(">");
		kartennummer = eingabe.nextLine();
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
