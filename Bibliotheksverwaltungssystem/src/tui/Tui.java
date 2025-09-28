package tui;

import java.util.ArrayList;
import java.util.Scanner;

import domain.exceptionsKlassen.*;
import domain.fassade.BibSystem;
import medienHinzüfugen.MedienHinzufügen;

/**
 * Einfache Text-UI (TUI) für das Bibliothekssystem.
 * Bietet Registrierung, Anmeldung, Suche, Ausleihe, Rückgabe, Verlängerung, Gebührenfunktionen und Datums-Simulation.
 *
 * author Obai
 * version 1.0
 * since 1.0
 */
public class Tui {

    /** Fassade des Bibliothekssystems. */
    private final BibSystem fassade;

    /** Scanner für Benutzereingaben. */
    private final Scanner eingabe;

    /** Submenü zum Hinzufügen neuer Medien. */
    private final MedienHinzufügen mediumsHinzufügen;

    /**
     * Startet die TUI und initialisiert ein neues System.
     */
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

    /**
     * Haupt-Loop des Programms.
     */
    private void startBibProgramm() {
        boolean programmIstAktiv = true;

        while (programmIstAktiv) {
            zeigeHauptmenü();
            System.out.print("> ");
            String aktion = eingabe.nextLine().trim();

            try {
                switch (aktion) {
                    case "1" -> registrierenProzess();
                    case "2" -> anmeldenProzess();
                    case "3" -> mediumDurchsuchenProzess();
                    case "4" -> mediumAusleihenProzess();
                    case "5" -> mediumsRückgabeProzess();
                    case "6" -> zeigeAusgelieheneGegenstände();
                    case "7" -> mediumsVerlängernProzess();
                    case "8" -> verbucheGebührenProzess();
                    case "9" -> datumÄndern();
                    case "10" -> mediumsHinzufügen.start(); // optionales Menü zum Medien-Hinzufügen
                    case "0" -> {
                        programmIstAktiv = false;
                        System.out.println("Programm beendet.");
                    }
                    default -> System.out.println("Ungültige Eingabe. Bitte wählen Sie eine gültige Option.");
                }
            } catch (Exception e) {
                System.out.println("Fehler: " + e.getMessage());
            }
        }
    }

    /**
     * Zeigt das Hauptmenü.
     */
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
        System.out.println("9. Datum ändern (Simulation)");
        System.out.println("10. Neues Medium hinzufügen");
        System.out.println("0. Programm beenden");
    }

    /**
     * Registrierung eines Benutzers.
     * Erfordert: name, alter, type (schüler/student/erwachsener/mitarbeiter), istAdmin (ja/nein).
     */
    private void registrierenProzess() {
        System.out.println("<< Registrierung >>");

        System.out.print("Name: ");
        String name = eingabe.nextLine().trim();

        System.out.print("Alter: ");
        String alterStr = eingabe.nextLine().trim();
        int alter = Integer.parseInt(alterStr);

        System.out.print("Benutzertyp (schüler / student / erwachsener / mitarbeiter): ");
        String type = eingabe.nextLine().trim();

        System.out.print("Sind Sie ein Admin? (ja/nein): ");
        String istAdmin = eingabe.nextLine().trim();

        try {
            fassade.userRegistrieren(name, type, alter, istAdmin);
            System.out.println("Registrierung erfolgreich!");
        } catch (FalscheEingabeException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    /**
     * Anmeldung eines Benutzers per Kartennummer.
     */
    private void anmeldenProzess() {
        System.out.println("<< Anmeldung >>");
        System.out.print("Kartennummer: ");
        String kartennummer = eingabe.nextLine().trim();

        try {
            double gebühren = fassade.userAnmelden(kartennummer);
            System.out.println("Erfolgreich angemeldet. Gebühren= " + gebühren);
        } catch (BenutzerNichtGefundenException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    /**
     * Medien durchsuchen (Titel, Medienart, ausgeliehen/nicht ausgeliehen, bald verfügbar).
     */
    private void mediumDurchsuchenProzess() {
        System.out.println("<< Medien durchsuchen >>");
        System.out.print("Suchkriterium (Titel ODER: ausgeliehen / nicht ausgeliehen / Medienart / bald verfügbar (ja)): ");
        String auswahl = eingabe.nextLine().trim();

        if (auswahl.equalsIgnoreCase("Medienart")) {
            System.out.println("Geben Sie eine Medienart ein: Bücher, Brettspiele, Dvds, Cds, Videospiele");
            auswahl = eingabe.nextLine().trim();
        }

        System.out.print("BibKartennummer: ");
        String bibKartennummer = eingabe.nextLine().trim();

        try {
            ArrayList<String> treffer = fassade.mediumDurchsuchen(auswahl, bibKartennummer);
            treffer.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    /**
     * Medium ausleihen.
     */
    private void mediumAusleihenProzess() {
        System.out.println("<< Medium ausleihen >>");

        System.out.print("Kartennummer: ");
        String kartennummer = eingabe.nextLine().trim();

        System.out.print("Eindeutige Kennung des Mediums: ");
        String eindeutigeKennung = eingabe.nextLine().trim();

        try {
            double gebühren = fassade.mediumAusleihen(kartennummer, eindeutigeKennung);
            System.out.println("Aktuelle Gebühren= " + gebühren);
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    /**
     * Medium zurückgeben.
     */
    private void mediumsRückgabeProzess() {
        System.out.println("<< Medium zurückgeben >>");
        System.out.print("Eindeutige Kennung des Mediums: ");
        String eindeutigeKennung = eingabe.nextLine().trim();

        try {
            ArrayList<String> ausgelieheneMedien = fassade.medienRückgabe(eindeutigeKennung);
            System.out.println("Medium erfolgreich zurückgegeben.");
            if (ausgelieheneMedien.isEmpty()) {
                System.out.println("Sie haben keine weiteren ausgeliehenen Medien.");
            } else {
                System.out.println("Ihre verbleibenden ausgeliehenen Medien:");
                ausgelieheneMedien.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    /**
     * Ausgeliehene Gegenstände eines Benutzers anzeigen.
     */
    private void zeigeAusgelieheneGegenstände() {
        System.out.println("<< Ausgeliehene Gegenstände anzeigen >>");
        System.out.print("BibKartennummer: ");
        String bibKartennummer = eingabe.nextLine().trim();

        try {
            ArrayList<String> treffer = fassade.ausgeliehenGegenstände(bibKartennummer);
            if (treffer.isEmpty()) {
                System.out.println("Sie haben keine ausgeliehenen Medien.");
            } else {
                treffer.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    /**
     * Leihfrist verlängern.
     */
    private void mediumsVerlängernProzess() {
        System.out.println("<< Leihfrist verlängern >>");

        System.out.print("BibKartennummer: ");
        String bibKartennummer = eingabe.nextLine().trim();

        System.out.print("Eindeutige Kennung des Mediums: ");
        String eindeutigeKennung = eingabe.nextLine().trim();

        try {
            boolean ok = fassade.medienVerlängern(eindeutigeKennung, bibKartennummer);
            System.out.println(ok ? "Leihfrist erfolgreich verlängert." : "Verlängerung nicht möglich.");
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    /**
     * Admin: Gebühren verbuchen / anzeigen / ausgeliehene Medien auflisten.
     */
    private void verbucheGebührenProzess() {
        System.out.println("<< Gebühren verbuchen (Admin) >>");

        System.out.print("BibKartennummer des Admins: ");
        String bibKartennummerAdmin = eingabe.nextLine().trim();

        try {
            if (fassade.adminAnmelden(bibKartennummerAdmin)) {
                System.out.println("Erfolgreich angemeldet.");
                System.out.println("Welche Aktion: ");
                System.out.println("1. Betrag verbuchen");
                System.out.println("2. Ausgeliehene Medien anzeigen");
                System.out.println("3. Aktuellen Betrag anzeigen");
                System.out.print("> ");
                String auswahl = eingabe.nextLine().trim();

                System.out.print("BibKartennummer des Benutzers: ");
                String userID = eingabe.nextLine().trim();

                switch (auswahl) {
                    case "1" -> System.out.println(
                            fassade.gebührenVerbuchen(userID) ? "Gebühren verbucht (0.0)." : "Verbuchen fehlgeschlagen."
                    );
                    case "2" -> {
                        ArrayList<String> treffer = fassade.ausgeliehenGegenstände(userID);
                        if (treffer.isEmpty()) System.out.println("Keine Ausleihen.");
                        else treffer.forEach(System.out::println);
                    }
                    case "3" -> System.out.println("Gebühren: " + fassade.getgbührenBenutzer(userID));
                    default -> System.out.println("Falsche Eingabe.");
                }
            }
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    /**
     * Datumssimulation (Jahresgebühren oder Ausleihfristen).
     */
    private void datumÄndern() {
        System.out.println("<< Datum ändern (Simulation) >>");
        System.out.println("1. Jahresgebühren simulieren");
        System.out.println("2. Ausleihefristen simulieren");
        System.out.print("> ");
        String auswahl = eingabe.nextLine().trim();

        switch (auswahl) {
            case "1" -> {
                try {
                    System.out.print("BibKartennummer: ");
                    String bibkartennummer = eingabe.nextLine().trim();
                    System.out.print("Datum (yyyy-MM-dd): ");
                    String datum = eingabe.nextLine().trim();
                    double betrag = fassade.jahresGebührenBerechnen(bibkartennummer, datum);
                    System.out.println("Neuer Gebührenstand: " + betrag);
                } catch (BenutzerNichtGefundenException e) {
                    System.out.println("Fehler: " + e.getMessage());
                }
            }
            case "2" -> {
                try {
                    System.out.print("AusleihBeginn (yyyy-MM-dd): ");
                    String ausleihbeginn = eingabe.nextLine().trim();
                    System.out.print("AusleihEnde (yyyy-MM-dd): ");
                    String ausleihEnde = eingabe.nextLine().trim();
                    System.out.print("Heutiges Datum (yyyy-MM-dd): ");
                    String heutigesDatum = eingabe.nextLine().trim();
                    System.out.print("Medium ID: ");
                    String mediumID = eingabe.nextLine().trim();

                    // FIX: Reihenfolge & Werte korrekt übergeben (Beginn, Ende)
                    double geb = fassade.datumÄndern(mediumID, ausleihbeginn, ausleihEnde, heutigesDatum);
                    System.out.println("Simulierter Gebührenstand: " + geb);
                } catch (MediumNichtGefundenException e) {
                    System.out.println("Fehler: " + e.getMessage());
                }
            }
            default -> System.out.println("Ungültige Eingabe.");
        }
    }
}
