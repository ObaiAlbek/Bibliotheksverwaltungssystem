package domain.Medium;

public class Mediumverwalter {
	
	private boolean verlängerbar;
	private int anzahl;
	private int leihdauer;
	private Medium medium;
	private boolean istAusgeliehen;
	
	public Mediumverwalter( Medium medium, boolean verlängerbar, int anzahl, int leihdauer) {
		super();
		this.verlängerbar = verlängerbar;
		this.anzahl = anzahl;
		this.leihdauer = leihdauer;
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

	public int  getLeihdauer(){
		return leihdauer;
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
		return   "Medium=" + medium.toString() + 
				" ,verlängerbar=" + verlängerbar + 
				", anzahl=" + anzahl + 
				",ist Ausgeliehen= " + istAusgeliehen +
				", Leihdauer=" + leihdauer ;
	}
	
	
	
}
