package domain.AusleiheSystem;

import java.text.SimpleDateFormat;
import java.util.Date;

import domain.Medium.Medium;

public class MediumZumAusleihen {
	
	private SimpleDateFormat simpleFormatter;
	private Date ausleiheBeginn, ausleiheEnde;
	private Medium medium;
	private int wochenAnzahl;
	
	public MediumZumAusleihen(Medium medium,Date ausleiheBeginn, Date ausleiheEnde, int wochenAnzahl) {
		this.medium = medium;
		this.ausleiheBeginn = ausleiheBeginn;
		this.ausleiheEnde = ausleiheEnde;
		this.wochenAnzahl = wochenAnzahl;
		this.simpleFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	}

	public Date getAusleiheBeginn() {
		return ausleiheBeginn;
	}

	public void setAusleiheBeginn(Date ausleiheBeginn) {
		this.ausleiheBeginn = ausleiheBeginn;
	}

	public Date getAusleiheEnde() {
		return ausleiheEnde;
	}

	public void setAusleiheEnde(Date ausleiheEnde) {
		this.ausleiheEnde = ausleiheEnde;
	}

	public Medium getMedium() {
		return medium;
	}

	public void setMedium(Medium medium) {
		this.medium = medium;
	}

	public int getWochenAnzahl() {
		return wochenAnzahl;
	}

	public void setWochenAnzahl(int wochenAnzahl) {
		this.wochenAnzahl = wochenAnzahl;
	}

	@Override
	public String toString() {
		return "Ausgeliehene Medium: Ausleihe Beginn= " +simpleFormatter.format(ausleiheBeginn) + " ,Ausleihe Ende= " +  simpleFormatter.format(ausleiheEnde) + " ,Wochenanzahl zum Ausleihen= "+ this.wochenAnzahl+" ,Medium= " + medium.toString();
	}
}
