package domain.Benutzer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import domain.AusleiheSystem.Ausleihe;
import domain.Medium.Medium;

public abstract class Benutzer {
	
	private Ausweis bibAusweis;
	private String name;
	private int alter;
	private boolean istStudent;
	private ArrayList<Ausleihe> ausgeliehenenMedien;
	private boolean angemeldet;
	private double gebühren;
	private Date anmeldebeginn;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	
	public Benutzer(Ausweis bibAusweis, String name, int alter, boolean istStudent) {
		super();
		this.bibAusweis = bibAusweis;
		this.name = name;
		this.alter = alter;
		this.istStudent = istStudent;
		this.ausgeliehenenMedien = new ArrayList<>();
		this.angemeldet = false;
		this.gebühren = 0;
		this.anmeldebeginn = new Date();
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
	
	public void removeMedium(Ausleihe medium) {
		this.ausgeliehenenMedien.remove(medium);
	}
	
	public boolean isAngemeldet() {
		return angemeldet;
	}

	public void setAngemeldet(boolean angemeldet) {
		this.angemeldet = angemeldet;
	}
	
	
	public double getGebühren() {
		return gebühren;
	}

	public void setGebühren(double gebühren) {
		this.gebühren = gebühren;
	}
	
	
	
	public Date getAnmeldebeginn() {
		return anmeldebeginn;
	}

	public void setAnmeldebeginn(String anmeldebeginn) throws ParseException {
		this.anmeldebeginn = formatter.parse(anmeldebeginn);
	}

//	public Date getAnmeldeEnde() {
//		return anmeldeEnde;
//	}
//
//	public void setAnmeldeEnde(String anmeldeEnde) throws ParseException {
//		this.anmeldeEnde= formatter.parse(anmeldeEnde);
//	}

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

