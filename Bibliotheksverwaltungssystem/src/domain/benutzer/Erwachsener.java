package domain.benutzer;

/**
 * Repräsentiert einen erwachsenen Bibliotheksbenutzer.
 * Jahresgebühr beträgt 50 EUR.
 *
 * @author Obai
 * @version 1.0
 * @since 1.0
 */
public class Erwachsener extends Benutzer {

    /**
     * Erstellt einen neuen Erwachsenen-Benutzer.
     *
     * @param bibAusweis Bibliotheksausweis
     * @param name Name des Benutzers
     * @param alter Alter des Benutzers
     * @param istStudent Student-Status (normalerweise false für Erwachsene)
     */
    public Erwachsener(Ausweis bibAusweis, String name, int alter, boolean istStudent) {
        super(bibAusweis, name, alter, istStudent);
    }

    /**
     * Liefert die Jahresgebühren für Erwachsene.
     *
     * @return 50.0 EUR
     */
    @Override
    public double getJahresgebühren() {
        return 50.0;
    }
}
