package main;

import domain.exceptionsKlassen.*;
import tui.Tui;

/**
 * Einstiegspunkt für das Bibliotheksverwaltungssystem.
 * Startet die Text-User-Interface (TUI) Anwendung.
 *
 * author Obai
 * version 1.0
 */
public class Main {

    /**
     * Hauptmethode: Startet das Bibliothekssystem über die TUI.
     *
     * @param args Kommandozeilenargumente (werden nicht verwendet)
     */
    public static void main(String[] args) {
        try {
            new Tui(); // Start der TUI
        } catch (FalscheEingabeException | MediumNichtGefundenException | BenutzerNichtAngemeldetException e) {
            System.err.println("❌ Fehler beim Starten des Systems: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
