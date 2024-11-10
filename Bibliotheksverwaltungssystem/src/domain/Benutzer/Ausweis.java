package domain.Benutzer;

public class Ausweis {
	private int kartenNummer;
	private static int generiereNummer = 1000;
	
	public Ausweis() {
		this.kartenNummer = generiereNummer++;
	}

	public int getKartenNummer() {
		return kartenNummer;
	}
	
}
