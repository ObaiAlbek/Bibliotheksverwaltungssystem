package domain.Benutzer;

import java.time.LocalDate;
import java.util.ArrayList;
import domain.AusleiheSystem.Ausleihe;

public abstract class Benutzer {
	
	private Ausweis bibAusweis;
	private String name;
	private int alter;
	private boolean istStudent;
	private ArrayList<Ausleihe> ausgeliehenenMedien;
	private boolean angemeldet;
	private double gebühren;
	private LocalDate anmeldebeginn;
	
	public Benutzer(Ausweis bibAusweis, String name, int alter, boolean istStudent) {
		super();
		this.bibAusweis = bibAusweis;
		this.name = name;
		this.alter = alter;
		this.istStudent = istStudent;
		this.ausgeliehenenMedien = new ArrayList<>();
		this.angemeldet = false;
		this.gebühren = 0;
		this.anmeldebeginn = LocalDate.now();
	}

	public Ausweis getBibAusweis() {
		return bibAusweis;
	}

	public void setBibAusweis(Ausweis bibAusweis) {
		this.bibAusweis = bibAusweis;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAlter() {
		return alter;
	}

	public void setAlter(int alter) {
		this.alter = alter;
	}

	public boolean isIstStudent() {
		return istStudent;
	}

	public void setIstStudent(boolean istStudent) {
		this.istStudent = istStudent;
	}

	public ArrayList<Ausleihe> getAusgeliehenenMedien() {
		return ausgeliehenenMedien;
	}
	
	public void ausleihen(Ausleihe medium) {
		this.ausgeliehenenMedien.add(medium);
	}
	
	public void mediumZurückgeben(Ausleihe medium) {
		this.ausgeliehenenMedien.remove(medium);
	}
	
	public boolean isAngemeldet() {
		return angemeldet;
	}

	public void anmelden(boolean angemeldet) {
		this.angemeldet = angemeldet;
	}
	
	public void abmelden(boolean angemeldet) {
		this.angemeldet = angemeldet;
	}
	
	
	public double getGebühren() {
		return gebühren;
	}

	public void setGebühren(double gebühren) {
		this.gebühren = gebühren;
	}
	
	public LocalDate getAnmeldebeginn() {
		return anmeldebeginn;
	}

	public void setAnmeldebeginn(LocalDate anmeldebeginn) {
		this.anmeldebeginn = anmeldebeginn;
	}
	
	public double jahresgebühren() {
		LocalDate nacheinemJahr = anmeldebeginn.plusYears(1);
		if (LocalDate.now().isAfter(nacheinemJahr)) {
            this.gebühren += getJahresgebühr();
            anmeldebeginn = nacheinemJahr; 
            return this.gebühren;
        }
		return 0.0;
	}
	
	public double simuliereJahresGebühren(String anmeldeBeginn) {
	    LocalDate aktuellesDatum = LocalDate.now();
	    LocalDate test = LocalDate.parse(anmeldeBeginn);

	    while (test.isBefore(aktuellesDatum)) {
	    	this.gebühren += getJahresgebühr();
	        test = test.plusYears(1);
	    }

	    anmeldebeginn = test;
	    return this.gebühren;
	}
	

	public abstract double getJahresgebühr();

	@Override
	public String toString() {
		return "Benutzer [bibAusweisNummer= " + bibAusweis.getKartenNummer() + 
				", name=" + name + 
				", alter=" + alter + 
				", istStudent=" + istStudent + 
				", ist im System Online = " + angemeldet + 
				" ,Gebühren= "+ gebühren + "]";
	}
	
	public String zeigeAusgeliehenMediums() {
		return "Ausgeliehene Mediums= " + ausgeliehenenMedien;
	}
}

