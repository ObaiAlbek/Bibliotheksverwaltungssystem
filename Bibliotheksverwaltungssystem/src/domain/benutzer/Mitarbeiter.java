package domain.benutzer;

/**
 * Repräsentiert einen Mitarbeiter der Bibliothek.
 * Mitarbeiter zahlen keine Jahresgebühr.
 * Zusätzlich können Mitarbeiter Gebühren für andere Benutzer verbuchen.
 *
 * @author Obai
 * @version 1.0
 * @since 1.0
 */
public class Mitarbeiter extends Benutzer {

    /**
     * Erstellt einen neuen Mitarbeiter.
     *
     * @param bibAusweis Bibliotheksausweis
     * @param name Name des Mitarbeiters
     * @param alter Alter des Mitarbeiters
     * @param istStudent Student-Status (meist irrelevant für Mitarbeiter)
     */
    public Mitarbeiter(Ausweis bibAusweis, String name, int alter, boolean istStudent) {
        super(bibAusweis, name, alter, istStudent);
    }

    /**
     * Liefert die Jahresgebühren für Mitarbeiter.
     *
     * @return 0.0 EUR
     */
    @Override
    public double getJahresgebühren() {
        return 0.0;
    }

    /**
     * Verbucht alle offenen Gebühren eines Benutzers auf 0.0.
     *
     * @param benutzer Benutzer, dessen Gebühren zurückgesetzt werden sollen
     */
    public void gebührVerbuchen(Benutzer benutzer) {
        benutzer.setGebühren(0.0);
    }
}
