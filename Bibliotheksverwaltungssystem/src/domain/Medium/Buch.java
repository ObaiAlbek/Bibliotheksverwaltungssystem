package domain.Medium;

import java.util.Date;

public class Buch extends Medium {
	
	private int anzahlVerlängerung;
	public Buch(String eindeutigeKennung, String title, int erscheinungsjahr, String autor) {
		super(eindeutigeKennung, title, erscheinungsjahr, autor);
	}
	
	public int getAnzahlVerlängerung() {
		return anzahlVerlängerung;
	}
	public void setAnzahlVerlängerung(int anzahlVerlängerung) {
		this.anzahlVerlängerung = anzahlVerlängerung;
	}
}
