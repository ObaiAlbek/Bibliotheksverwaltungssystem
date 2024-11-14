package domain.AusleiheSystem;

import java.text.SimpleDateFormat;
import java.util.Date;

import domain.Benutzer.Ausweis;
import domain.Benutzer.Benutzer;
import domain.Benutzer.Kunde;
import domain.Medium.Buch;
import domain.Medium.Medium;
import domain.Medium.Mediumverwalter;

public class Ausleihe {
	
	private SimpleDateFormat simpleFormatter;
	private Date ausleiheBeginn, ausleiheEnde;
	
	private Mediumverwalter medium;
	private Benutzer benutzer;
	private int verlängerungen;
	
	public Ausleihe(Mediumverwalter medium, Benutzer benutzer,Date ausleiheBeginn, Date ausleiheEnde) {
		this.medium = medium;
		this.ausleiheBeginn = ausleiheBeginn;
		this.ausleiheEnde = ausleiheEnde;
		this.simpleFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		this.verlängerungen = 0;
		this.benutzer = benutzer;
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

	public Mediumverwalter getMediumverwalter() {
		return medium;
	}
	
	public Benutzer getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	@Override
	public String toString() {
		return "Ausgeliehene Mediums= "+ medium.toString()+ 
				" Ausleihe Beginn= " +simpleFormatter.format(ausleiheBeginn) + 
				" ,Ausleihe Ende= " +  simpleFormatter.format(ausleiheEnde) + 
				" ,verlängerungen=  " + verlängerungen + 
				" ,inhaber= " + benutzer.toString() ;
	}
}
