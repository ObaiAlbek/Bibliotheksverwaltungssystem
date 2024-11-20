package domain.benutzer;

public class Ausweis {
	private String kartennummer;
	private static int generiereNummer = 1000;
	
	public Ausweis(String zeichen) {
		
		this.kartennummer =zeichen + generiereNummer++ ;
	}

	public String getKartenNummer() {
		return kartennummer;
	}
	
}
