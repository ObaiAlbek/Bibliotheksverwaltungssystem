package tui;

import java.util.ArrayList;
import java.util.Scanner;

import MedienHinzüfugen.MedienHinzufügen;
import domain.ExceptionsKlassen.*;
import domain.fassade.BibSystem;

public class Tui {
    private BibSystem fassade;
    private Scanner eingabe;
    private MedienHinzufügen mediumsHinzufügen;

    public Tui() {
        this.fassade = new BibSystem();
        this.eingabe = new Scanner(System.in);
        this.mediumsHinzufügen = new MedienHinzufügen(fassade);

        System.out.println("<< Willkommen in der Bibliothek >>");
        try {
            startBibProgramm();
        } catch (Exception e) {
            System.out.println("Ein unerwarteter Fehler ist aufgetreten: " + e.getMessage());
        }
    }

    private void startBibProgramm() {
        boolean programmIstAktiv = true;

        while (programmIstAktiv) {
            zeigeHauptmenü();
            System.out.print("> ");
            String aktion = eingabe.nextLine();

            try {
                switch (aktion) {
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
                    case "5":
                        mediumsRückgabeProzess();
                        break;
                    case "6":
                        zeigeAusgelieheneGegenstände();
                        break;
                    case "7":
                        mediumsVerlängernProzess();
                        break;
                    case "8":
                        verbucheGebührenProzess();
                        break;
                    case "0":
                        programmIstAktiv = false;
                        System.out.println("Programm beendet.");
                        break;
                    default:
                        System.out.println("Ungültige Eingabe. Bitte wählen Sie eine gültige Option.");
                }
            } catch (Exception e) {
                System.out.println("Fehler: " + e.getMessage());
            }
        }
    }

    private void zeigeHauptmenü() {
        System.out.println("\nHauptmenü:");
        System.out.println("1. Registrieren");
        System.out.println("2. Anmelden");
        System.out.println("3. Medien durchsuchen");
        System.out.println("4. Medium ausleihen");
        System.out.println("5. Medium zurückgeben");
        System.out.println("6. Ausgeliehene Gegenstände anzeigen");
        System.out.println("7. Leihfrist verlängern");
        System.out.println("8. Gebühren verbuchen (Admin)");
        System.out.println("0. Programm beenden");
    }

    private void registrierenProzess() {
        System.out.println("<< Registrierung >>");

        System.out.print("Name: ");
        String name = eingabe.nextLine();

        System.out.print("Alter: ");
        int alter = Integer.parseInt(eingabe.nextLine());

        System.out.print("Sind Sie Schüler oder Student? (Ja/Nein): ");
        String type = eingabe.nextLine();

        System.out.print("Sind Sie ein Admin? (Ja/Nein): ");
        String istAdmin = eingabe.nextLine();

        try {
            fassade.userRegistrieren(name, type, alter, istAdmin);
            System.out.println("Registrierung erfolgreich!");
        } catch (FalscheEingabeException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    private void anmeldenProzess() {
        System.out.println("<< Anmeldung >>");

        System.out.print("Kartennummer: ");
        String kartennummer = eingabe.nextLine();

        try {
            if (fassade.userAnmdelden(kartennummer)) {
                System.out.println("Erfolgreich angemeldet.");
            }
        } catch (BenutzerNichtGefundenException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    private void mediumDurchsuchenProzess() {
        System.out.println("<< Medien durchsuchen >>");
        System.out.print("Suchkriterium (z. B. Titel, Medienart): ");
        String auswahl = eingabe.nextLine();

        System.out.print("BibKartennummer: ");
        String bibKartennummer = eingabe.nextLine();

        try {
            fassade.mediumDurchsuchen(auswahl, bibKartennummer);
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    private void mediumAusleihenProzess() {
        System.out.println("<< Medium ausleihen >>");

        System.out.print("Kartennummer: ");
        String kartennummer = eingabe.nextLine();

        System.out.print("Eindeutige Kennung des Mediums: ");
        String eindeutigeKennung = eingabe.nextLine();

        try {
            System.out.println(fassade.mediumAusleihen(kartennummer, eindeutigeKennung));
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    private void mediumsRückgabeProzess() {
        System.out.println("<< Medium zurückgeben >>");

        System.out.print("Eindeutige Kennung des Mediums: ");
        String eindeutigeKennung = eingabe.nextLine();

        try {
            ArrayList<String> ausgelieheneMedien = fassade.medienRückgabe(eindeutigeKennung);
            System.out.println("Medium erfolgreich zurückgegeben.");

            if (ausgelieheneMedien.isEmpty()) 
                System.out.println("Sie haben keine weiteren ausgeliehenen Medien.");
            else {
                System.out.println("Ihre verbleibenden ausgeliehenen Medien:");
                ausgelieheneMedien.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    private void mediumsVerlängernProzess() {
        System.out.println("<< Leihfrist verlängern >>");

        System.out.print("BibKartennummer: ");
        String bibKartennummer = eingabe.nextLine();

        System.out.print("Eindeutige Kennung des Mediums: ");
        String eindeutigeKennung = eingabe.nextLine();

        try {
            fassade.medienVerlängern(eindeutigeKennung, bibKartennummer);
            System.out.println("Leihfrist erfolgreich verlängert.");
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    private void zeigeAusgelieheneGegenstände() {
        System.out.println("<< Ausgeliehene Gegenstände anzeigen >>");

        System.out.print("BibKartennummer: ");
        String bibKartennummer = eingabe.nextLine();

        try {
           // fassade.zeigeAusgelieheneGegenstände(bibKartennummer);
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    private void verbucheGebührenProzess() {
        System.out.println("<< Gebühren verbuchen >>");

        System.out.print("BibKartennummer des Nutzers: ");
        String bibKartennummer = eingabe.nextLine();

        try {
           // fassade.verbucheGebühren(bibKartennummer);
            System.out.println("Gebühren erfolgreich verbucht.");
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }
}

