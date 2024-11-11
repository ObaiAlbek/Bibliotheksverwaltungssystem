package domain.Medium;

import java.util.Date;

public class Videospiel extends Medium {
	
	private int anzahlVerlängerung;
	public Videospiel(String kennungNummer, String title, int erscheinungsjahr, String autor) {
		super(kennungNummer, title, erscheinungsjahr, autor);
	}
	
	public int getAnzahlVerlängerung() {
		return anzahlVerlängerung;
	}
	public void setAnzahlVerlängerung(int anzahlVerlängerung) {
		this.anzahlVerlängerung = anzahlVerlängerung;
	}

}
