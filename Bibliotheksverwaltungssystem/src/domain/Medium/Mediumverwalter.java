package domain.Medium;

public class Mediumverwalter {
	
	private boolean verlängerbar;
	private int anzahl;
	private int wocheAnzahlZumAusleihen;
	private Medium medium;
	private boolean istAusgeliehen;
	
	public Mediumverwalter(boolean verlängerbar, int anzahl, int wocheAnzahlZumAusleihen, Medium medium) {
		super();
		this.verlängerbar = verlängerbar;
		this.anzahl = anzahl;
		this.wocheAnzahlZumAusleihen = wocheAnzahlZumAusleihen;
		this.medium = medium;
	}
	

	public boolean isVerlängerbar() {
		return verlängerbar;
	}

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public int getWocheAnzahlZumAusleihen() {
		return wocheAnzahlZumAusleihen;
	}

	public Medium getMedium() {
		return medium;
	}
	public boolean isIstAusgeliehen() {
		return istAusgeliehen;
	}


	public void setIstAusgeliehen(boolean istAusgeliehen) {
		this.istAusgeliehen = istAusgeliehen;
	}


	@Override
	public String toString() {
		return   "Medium=" + medium.toString() + " ,verlängerbar=" + verlängerbar + ", anzahl=" + anzahl + ",ist Ausgeliehen= " + istAusgeliehen + ", wocheAnzahlZumAusleihen="
				+ wocheAnzahlZumAusleihen ;
	}
	
	
	public String toStringOhneAnzahl() {
		return   "Medium=" + medium.toString() + " ,verlängerbar=" + verlängerbar + ", Anzahl der Wochen zum Ausleihen="
				+ wocheAnzahlZumAusleihen ;
	}
}
