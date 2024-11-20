package medienHinzüfugen;

import java.util.Scanner;

import domain.exceptionsKlassen.FalscheEingabeException;
import domain.fassade.BibSystem;

public class MedienHinzufügen {
	private Scanner scanner = new Scanner(System.in);
	private BibSystem fassade;

	public MedienHinzufügen(BibSystem fassade) {
		this.fassade = fassade;
	}

	public void start() {
		System.out.println("Willkommen im Bibliothekssystem!");
		boolean beenden = false;

		while (!beenden) {
			System.out.println("\nBitte wählen Sie eine Aktion aus:");
			System.out.println("1: Neues Medium hinzufügen");
			System.out.println("2: System beenden");
			System.out.print("Ihre Auswahl: ");

			int auswahl = scanner.nextInt();
			scanner.nextLine();

			switch (auswahl) {
			case 1:
				mediumHinzufügen();
				break;
			case 2:
				beenden = true;
				System.out.println("Programm beendet.");
				break;
			default:
				System.out.println("Ungültige Auswahl. Bitte erneut versuchen.");
			}
		}
	}

	private void mediumHinzufügen() {
		try {
			System.out.println("Geben Sie den Typ des Mediums ein (Buch, Cd, Brettspiel, Dvd, Videospiel): ");
			String type = scanner.nextLine();

			System.out.println("Geben Sie die ID des Mediums ein: ");
			String id = scanner.nextLine();

			System.out.println("Geben Sie den Titel des Mediums ein: ");
			String title = scanner.nextLine();

			System.out.println("Geben Sie das Erscheinungsjahr ein: ");
			int erscheinungsjahr = scanner.nextInt();
			scanner.nextLine();

			System.out.println("Geben Sie den Macher (Autor/Regisseur/Entwickler) ein: ");
			String ersteller = scanner.nextLine();

			System.out.println("Ist das Medium verlängerbar? (ja/nein): ");
			String verlängerbar = scanner.nextLine();

			System.out.println("Geben Sie die Anzahl der verfügbaren Exemplare ein: ");
			int anzahl = scanner.nextInt();

			System.out.println("Geben Sie die maximale Leihdauer (in Tagen) ein: ");
			int leihdauer = scanner.nextInt();
			scanner.nextLine();

			fassade.mediumsAufladen(type, id, title, erscheinungsjahr, ersteller, verlängerbar, anzahl, leihdauer);
			System.out.println("Medium erfolgreich hinzugefügt!");

		} catch (FalscheEingabeException e) {
			System.out.println("Fehler: " + e.getMessage());
		} 
	}
}
