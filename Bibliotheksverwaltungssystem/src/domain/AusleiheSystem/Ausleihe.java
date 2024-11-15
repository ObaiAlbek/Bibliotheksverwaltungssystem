package domain.AusleiheSystem;

import java.time.LocalDate;
import java.util.Date;

import domain.Benutzer.Benutzer;
import domain.Medium.Mediumverwalter;

public class Ausleihe {
	
	private LocalDate ausleiheBeginn, ausleiheEnde;
	private Mediumverwalter medium;
	private Benutzer benutzer;
	private int verlängerungen;
	
	public Ausleihe(Mediumverwalter medium, Benutzer benutzer,LocalDate ausleiheBeginn, LocalDate ausleiheEnde) {
		this.medium = medium;
		this.ausleiheBeginn = ausleiheBeginn;
		this.ausleiheEnde = ausleiheEnde;
		this.verlängerungen = 0;
		this.benutzer = benutzer;
	}

	public Mediumverwalter getMediumverwalter() {
		return medium;
	}

	public Benutzer getBenutzer() {
		return benutzer;
	}

	public LocalDate getAusleiheBeginn() {
		return ausleiheBeginn;
	}

	public void setAusleiheBeginn(LocalDate ausleiheBeginn) {
		this.ausleiheBeginn = ausleiheBeginn;
	}

	public LocalDate getAusleiheEnde() {
		return ausleiheEnde;
	}

	public void setAusleiheEnde(LocalDate ausleiheEnde) {
		this.ausleiheEnde = ausleiheEnde;
	}
	
	public int getVerlängerungen() {
		return verlängerungen;
	}

	public void setVerlängerungen(int verlängerungen) {
		this.verlängerungen = verlängerungen;
	}
	

	@Override
	public String toString() {
		return "Ausgeliehene Mediums= "+ medium.toString()+ 
				" Ausleihe Beginn= " +ausleiheBeginn + 
				" ,Ausleihe Ende= " + ausleiheEnde + 
				" ,verlängerungen=  " + verlängerungen + 
				" ,inhaber= " + benutzer.toString() ;
	}
}
