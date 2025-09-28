package domain.ausleihSystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

import domain.benutzer.Benutzer;
import domain.exceptionsKlassen.MediumNichtGefundenException;
import domain.medium.Ausleihe;
import domain.medium.Mediumverwalter;

/**
 * Verwaltung der Ausleihen in der Bibliothek.
 * Beinhaltet Ausleihe, Rückgabe, Simulation und Verlängerung von Medien.
 *
 * @author Obai
 * @version 1.0
 * @since 1.0
 */
public class AusleiheSystem {

    /** Medienbestand, gemappt über eindeutige Kennung. */
    private HashMap<String, Mediumverwalter> medien;

    /** Letzter Ausleihbeginn im System-Kontext. */
    private LocalDate ausleiheBeginn;

    /** Letztes Ausleihende im System-Kontext. */
    private LocalDate ausleiheEnde;

    /**
     * Konstruktor für das Ausleihsystem.
     *
     * @param medien Medienbestand
     */
    public AusleiheSystem(HashMap<String, Mediumverwalter> medien) {
        this.medien = medien;
    }

    /**
     * Leiht ein Medium an einen Benutzer aus.
     *
     * @param benutzer Benutzer der das Medium ausleiht
     * @param eindeutigenummer Kennung des Mediums
     * @return erzeugte Ausleihe
     * @throws MediumNichtGefundenException wenn Medium nicht gefunden oder bereits ausgeliehen
     */
    public Ausleihe mediumAusleihen(Benutzer benutzer, String eindeutigenummer) throws MediumNichtGefundenException {
        Mediumverwalter mediumAusleihen = findMedium(eindeutigenummer);

        if (mediumAusleihen.isIstAusgeliehen()) {
            throw new MediumNichtGefundenException("Das Medium ist ausgeliehen");
        }

        mediumAusleihen.setIstAusgeliehen(true);
        mediumAusleihen.setAnzahl(mediumAusleihen.getAnzahl() - 1);

        this.ausleiheBeginn = LocalDate.now();
        this.ausleiheEnde = ausleiheBeginn.plusDays(mediumAusleihen.getLeihdauer());

        return new Ausleihe(mediumAusleihen, benutzer, ausleiheBeginn, ausleiheEnde);
    }

    /**
     * Gibt ein Medium zurück und berechnet Gebühren falls überfällig.
     *
     * @param ausleihe Liste der Ausleihen
     * @param eindeutigeKennung Kennung des Mediums
     * @return Liste der verbleibenden Ausleihen als String
     */
    public ArrayList<String> mediumRückgabe(ArrayList<Ausleihe> ausleihe, String eindeutigeKennung) {
        Ausleihe ausgelieheneMedium = ausleihe.stream()
                .filter(k -> k.getMediumverwalter().getMedium().getID().equalsIgnoreCase(eindeutigeKennung))
                .findFirst()
                .orElse(null);

        ArrayList<String> ausgeliehenMedien = new ArrayList<>();

        if (ausgelieheneMedium != null) {
            LocalDate heutigesDatum = LocalDate.now();
            Benutzer bibBenutzer = ausgelieheneMedium.getBenutzer();

            if (heutigesDatum.isAfter(ausgelieheneMedium.getAusleiheEnde())) {
                long überfälligeTage = ausgelieheneMedium.getAusleiheEnde().until(heutigesDatum, ChronoUnit.DAYS);
                double gebühren = 0.0;

                if (überfälligeTage <= 7) {
                    gebühren = überfälligeTage * 1.0;
                } else {
                    gebühren = 7.0 + ((überfälligeTage - 7) * 2.0);
                }

                bibBenutzer.setGebühren(bibBenutzer.getGebühren() + gebühren);
            }

            ausgelieheneMedium.getBenutzer().mediumZurückgeben(ausgelieheneMedium);
            ausleihe.remove(ausgelieheneMedium);

            ausgelieheneMedium.getMediumverwalter().setIstAusgeliehen(false);
            ausgelieheneMedium.getMediumverwalter().setAnzahl(
                    ausgelieheneMedium.getMediumverwalter().getAnzahl() + 1
            );

            for (Ausleihe a : ausgelieheneMedium.getBenutzer().getAusgeliehenenMedien()) {
                ausgeliehenMedien.add(a.toString());
            }
        }

        return ausgeliehenMedien;
    }

    /**
     * Simuliert Rückgabe eines Mediums und berechnet Gebühren.
     *
     * @param ausleihe Liste der Ausleihen
     * @param eindeutigeKennung Kennung des Mediums
     * @param ausleiheBeginn Beginn der Ausleihe als String
     * @param ausleiheEnde Ende der Ausleihe als String
     * @param datum simuliertes Rückgabedatum
     * @return Gebührenstand des Benutzers
     * @throws MediumNichtGefundenException wenn Medium nicht gefunden
     */
    public double SimulieremediumRückgabe(ArrayList<Ausleihe> ausleihe,
                                          String eindeutigeKennung,
                                          String ausleiheBeginn,
                                          String ausleiheEnde,
                                          String datum) throws MediumNichtGefundenException {
        Ausleihe ausgelieheneMedium = ausleihe.stream()
                .filter(k -> k.getMediumverwalter().getMedium().getID().equalsIgnoreCase(eindeutigeKennung))
                .findFirst()
                .orElse(null);

        if (ausgelieheneMedium == null) {
            throw new MediumNichtGefundenException("Ausleihe nicht gefunden");
        }

        LocalDate beginn = LocalDate.parse(ausleiheBeginn);
        LocalDate ende = LocalDate.parse(ausleiheEnde);
        LocalDate heutigesDatum = LocalDate.parse(datum);
        Benutzer bibBenutzer = ausgelieheneMedium.getBenutzer();

        ausgelieheneMedium.setAusleiheBeginn(beginn);
        ausgelieheneMedium.setAusleiheEnde(ende);

        if (heutigesDatum.isAfter(ende)) {
            long überfälligeTage = ende.until(heutigesDatum, ChronoUnit.DAYS);
            double gebühren = 0.0;

            if (überfälligeTage <= 7) {
                gebühren = überfälligeTage * 1.0;
            } else {
                gebühren = 7.0 + ((überfälligeTage - 7) * 2.0);
            }

            bibBenutzer.setGebühren(bibBenutzer.getGebühren() + gebühren);
        }

        return bibBenutzer.getGebühren();
    }

    /**
     * Verlängert eine bestehende Ausleihe falls möglich.
     *
     * @param benutzer Benutzer mit Ausleihe
     * @param eindeutigeKennung Kennung des Mediums
     * @return true wenn Verlängerung erfolgreich, sonst false
     * @throws MediumNichtGefundenException wenn Medium nicht gefunden
     */
    public boolean medienVerlängern(Benutzer benutzer, String eindeutigeKennung) throws MediumNichtGefundenException {
        Ausleihe medium = benutzer.getAusgeliehenenMedien().stream()
                .filter(m -> m.getMediumverwalter().getMedium().getID().equalsIgnoreCase(eindeutigeKennung))
                .findFirst()
                .orElse(null);

        if (medium == null) {
            return false;
        }

        LocalDate heutigesDatum = LocalDate.now();
        if (heutigesDatum.isAfter(medium.getAusleiheEnde())) {
            return false;
        }

        if (!medium.getMediumverwalter().isVerlängerbar()) {
            return false;
        }

        if (medium.getVerlängerungen() == 3) {
            return false;
        }

        medium.setVerlängerungen(medium.getVerlängerungen() + 1);
        medium.setAusleiheBeginn(LocalDate.now());
        medium.setAusleiheEnde(LocalDate.now().plusDays(medium.getMediumverwalter().getLeihdauer()));

        return true;
    }

    /**
     * Sucht ein Medium anhand Kennung.
     *
     * @param eindeutigeKennung Kennung des Mediums
     * @return Mediumverwalter falls vorhanden
     * @throws MediumNichtGefundenException wenn Medium nicht gefunden
     */
    private Mediumverwalter findMedium(String eindeutigeKennung) throws MediumNichtGefundenException {
        if (medien.containsKey(eindeutigeKennung)) {
            return medien.get(eindeutigeKennung);
        } else {
            throw new MediumNichtGefundenException("Das ausgewählte Medium ist nicht verfügbar");
        }
    }
}
