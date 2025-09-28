package domain.fassade;

import domain.benutzer.*;
import domain.exceptionsKlassen.FalscheEingabeException;

/**
 * Statische Fabrik zur Registrierung von Bibliotheksbenutzern.
 * Erzeugt je nach Typ die passende {@link Benutzer}-Unterklasse und weist eine Kartennummer zu.
 * Erlaubte Typen: "schüler", "student", "erwachsener", "mitarbeiter".
 *
 * author Obai
 * version 1.0
 * since 1.0
 */
public class Registieren {

    /**
     * Registriert einen Benutzer und liefert die erzeugte Instanz zurück.
     *
     * @param name Name des Benutzers
     * @param type Benutzertyp: "schüler", "student", "erwachsener", "mitarbeiter"
     * @param alter Alter des Benutzers
     * @param istAdmin "ja" oder "nein" (bestimmt nur das Präfix/Kartentyp beim Mitarbeiter/Benutzer)
     * @return erzeugter Benutzer
     * @throws FalscheEingabeException wenn Name leer oder Typ ungültig ist
     */
    public static Benutzer userRegistrieren(String name, String type, int alter, String istAdmin)
            throws FalscheEingabeException {

        if (name == null || name.trim().isEmpty() || type == null) {
            throw new FalscheEingabeException("Falsche Eingabe");
        }

        final String t = type.trim();
        final String ia = istAdmin == null ? "nein" : istAdmin.trim();
        final boolean admin = ia.equalsIgnoreCase("ja");

        Ausweis ausweis;
        Benutzer benutzer;

        if (t.equalsIgnoreCase("schüler") || t.equalsIgnoreCase("student")) {
            ausweis = new Ausweis("K");
            benutzer = new Student(ausweis, name, alter, admin);
        } else if (t.equalsIgnoreCase("erwachsener")) {
            ausweis = new Ausweis("K");
            benutzer = new Erwachsener(ausweis, name, alter, admin);
        } else if (t.equalsIgnoreCase("mitarbeiter")) {
            ausweis = new Ausweis("A");
            benutzer = new Mitarbeiter(ausweis, name, alter, admin);
        } else {
            throw new FalscheEingabeException("Falsche Eingabe");
        }

        return benutzer;
    }
}
