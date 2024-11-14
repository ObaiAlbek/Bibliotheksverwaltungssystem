package domain.AusleiheSystem;

import java.text.SimpleDateFormat;
import java.util.Date;

import domain.Medium.Medium;
import domain.Medium.Mediumverwalter;

public class Ausleihe {
	
	private SimpleDateFormat simpleFormatter;
	private Date ausleiheBeginn, ausleiheEnde;
	private Mediumverwalter medium;
	private int verlängerungen;
	
	public Ausleihe(Mediumverwalter medium,Date ausleiheBeginn, Date ausleiheEnde) {
		this.medium = medium;
		this.ausleiheBeginn = ausleiheBeginn;
		this.ausleiheEnde = ausleiheEnde;
		this.simpleFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		this.verlängerungen = 0;
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

	public Mediumverwalter getMedium() {
		return medium;
	}

	@Override
	public String toString() {
		return "Ausgeliehene Mediums= "+ medium.toStringOhneAnzahl() + " Ausleihe Beginn= " +simpleFormatter.format(ausleiheBeginn) + " ,Ausleihe Ende= " +  simpleFormatter.format(ausleiheEnde) + " ,verlängerungen=  " + verlängerungen;
	}
}
