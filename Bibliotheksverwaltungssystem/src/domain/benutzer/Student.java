package domain.benutzer;

/**
 * Repräsentiert einen Studenten als Bibliotheksbenutzer.
 * Jahresgebühr beträgt 25 EUR.
 *
 * @author Obai
 * @version 1.0
 * @since 1.0
 */
public class Student extends Benutzer {

    /**
     * Erstellt einen neuen Studenten-Benutzer.
     *
     * @param bibAusweis Bibliotheksausweis
     * @param name Name des Studenten
     * @param alter Alter des Studenten
     * @param istStudent Student-Status (normalerweise true)
     */
    public Student(Ausweis bibAusweis, String name, int alter, boolean istStudent) {
        super(bibAusweis, name, alter, istStudent);
    }

    /**
     * Liefert die Jahresgebühren für Studenten.
     *
     * @return 25.0 EUR
     */
    @Override
    public double getJahresgebühren() {
        return 25.0;
    }
}
