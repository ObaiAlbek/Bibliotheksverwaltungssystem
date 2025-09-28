package medienHinzüfugen;

import java.util.InputMismatchException;
import java.util.Scanner;

import domain.exceptionsKlassen.FalscheEingabeException;
import domain.fassade.BibSystem;

/**
 * Einfache Konsolen-Anwendung zum Hinzufügen neuer Medien ins Bibliothekssystem.
 * Bietet ein kleines Menü und verarbeitet Benutzereingaben.
 *
 * author Obai
 * version 1.0
 * since 1.0
 */
public class MedienHinzufügen {

    /** Scanner für Benutzereingaben. */
    private final Scanner scanner = new Scanner(System.in);

    /** Referenz auf die Fassade des Bibliothekssystems. */
    private final BibSystem fassade;

    /**
     * Konstruktor.
     *
     * @param fassade zentrale Fassade des Bibliothekssystems
     */
    public MedienHinzufügen(BibSystem fassade) {
        this.fassade = fassade;
    }

    /**
     * Startet das Konsolenmenü. Läuft in einer Schleife, bis der Benutzer das Programm beendet.
     */
    public void start() {
        System.out.println("📚 Willkommen im Bibliothekssystem!");
        boolean beenden = false;

        while (!beenden) {
            System.out.println("\nBitte wählen Sie eine Aktion aus:");
            System.out.println("1: Neues Medium hinzufügen");
            System.out.println("2: System beenden");
            System.out.print("Ihre Auswahl: ");

            try {
                int auswahl = scanner.nextInt();
                scanner.nextLine(); // Buffer leeren

                switch (auswahl) {
                    case 1 -> mediumHinzufügen();
                    case 2 -> {
                        beenden = true;
                        System.out.println("Programm beendet.");
                    }
                    default -> System.out.println("⚠️ Ungültige Auswahl. Bitte erneut versuchen.");
                }
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Bitte nur Zahlen eingeben!");
                scanner.nextLine(); // Buffer leeren
            }
        }
    }

    /**
     * Fragt alle Daten für ein neues Medium ab und legt es ins System.
     */
    private void mediumHinzufügen() {
        try {
            System.out.print("Typ des Mediums (Buch, Cd, Brettspiel, Dvd, Videospiel): ");
            String type = scanner.nextLine().trim();

            System.out.print("ID des Mediums: ");
            String id = scanner.nextLine().trim();

            System.out.print("Titel des Mediums: ");
            String title = scanner.nextLine().trim();

            System.out.print("Erscheinungsjahr: ");
            int erscheinungsjahr = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Autor/Regisseur/Entwickler: ");
            String ersteller = scanner.nextLine().trim();

            System.out.print("Verlängerbar? (ja/nein): ");
            String verlängerbar = scanner.nextLine().trim();

            System.out.print("Anzahl der Exemplare: ");
            int anzahl = scanner.nextInt();

            System.out.print("Leihdauer (Tage): ");
            int leihdauer = scanner.nextInt();
            scanner.nextLine();

            fassade.mediumsAufladen(type, id, title, erscheinungsjahr, ersteller, verlängerbar, anzahl, leihdauer);
            System.out.println("✅ Medium erfolgreich hinzugefügt!");

        } catch (InputMismatchException e) {
            System.out.println("⚠️ Fehler: Bitte gültige Zahlen eingeben!");
            scanner.nextLine(); // Buffer leeren
        } catch (FalscheEingabeException e) {
            System.out.println("⚠️ Fehler: " + e.getMessage());
        }
    }
}
