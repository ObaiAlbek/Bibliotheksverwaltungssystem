package tui;

import java.util.Scanner;

import domain.BibSystem;
import domain.ExceptionsKlassen.FalscheEingabeException;

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
			System.out.println("3.Medien Durchsuchen");
			System.out.println("4.Ausleihen eines Gegenstandes");
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
			}
		}
		
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
				fassade.addUser(name, type, alter, istAdmin);
				startBibProgramm();
			} catch (FalscheEingabeException e) {
				System.out.println(e.getMessage());
				continue;
			}

		}
	}
}
