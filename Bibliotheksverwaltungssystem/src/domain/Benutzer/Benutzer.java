package domain.Benutzer;

import java.util.ArrayList;

import domain.MediumZumAusleihen;
import domain.Medium.Medium;

public abstract class Benutzer {
	
	private Ausweis bibAusweis;
	private String name;
	private int alter;
	private boolean istStudent;
	private ArrayList<MediumZumAusleihen> ausgeliehenenMedien;
	private boolean angemeldet;
	
	
	public Benutzer(Ausweis bibAusweis, String name, int alter, boolean istStudent) {
		super();
		this.bibAusweis = bibAusweis;
		this.name = name;
		this.alter = alter;
		this.istStudent = istStudent;
		this.ausgeliehenenMedien = new ArrayList<>();
		this.angemeldet = false;
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

	public ArrayList<MediumZumAusleihen> getAusgeliehenenMedien() {
		return ausgeliehenenMedien;
	}
	
	public void ausleihen(MediumZumAusleihen medium) {
		this.ausgeliehenenMedien.add(medium);
	}
	
	public void removeMedium(MediumZumAusleihen medium) {
		this.ausgeliehenenMedien.remove(medium);
	}
	
	public boolean isAngemeldet() {
		return angemeldet;
	}

	public void setAngemeldet(boolean angemeldet) {
		this.angemeldet = angemeldet;
	}

	@Override
	public String toString() {
		return "Benutzer [bibAusweisNummer= " + bibAusweis.getKartenNummer() + ", name=" + name + ", alter=" + alter + ", istStudent="
				+ istStudent + ", ist im System Online = " + angemeldet + ", ausgeliehenenMedien=" + ausgeliehenenMedien + "]";
	}
	
	
}

