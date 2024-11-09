package domain.Benutzer;

import java.util.ArrayList;
import domain.Ausweis;
import domain.Medium.Medium;

public abstract class Benutzer {
	
	private Ausweis bibAusweis;
	private String name;
	private int alter;
	private boolean istStudent;
	private ArrayList<Medium> ausgeliehenenMedien;
	
	public Benutzer(Ausweis bibAusweis, String name, int alter, boolean istStudent) {
		super();
		this.bibAusweis = bibAusweis;
		this.name = name;
		this.alter = alter;
		this.istStudent = istStudent;
		this.ausgeliehenenMedien = new ArrayList<>();
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

	public ArrayList<Medium> getAusgeliehenenMedien() {
		return ausgeliehenenMedien;
	}
	
	public void addMedium(Medium medium) {
		this.ausgeliehenenMedien.add(medium);
	}
	
	public void removeMedium(Medium medium) {
		this.ausgeliehenenMedien.remove(medium);
	}

	@Override
	public String toString() {
		return "Benutzer [bibAusweisNummer= " + bibAusweis.getKartenNummer() + ", name=" + name + ", alter=" + alter + ", istStudent="
				+ istStudent + ", ausgeliehenenMedien=" + ausgeliehenenMedien + "]";
	}
	
	
}

