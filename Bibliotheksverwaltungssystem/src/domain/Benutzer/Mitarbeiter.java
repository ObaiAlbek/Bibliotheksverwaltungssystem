package domain.Benutzer;

public class Mitarbeiter extends Benutzer {

	public Mitarbeiter(Ausweis bibAusweis, String name, int alter, boolean istStudent) {
		super(bibAusweis, name, alter, istStudent);
	}

	@Override
	public double getJahresgebühren() {
	
		return 0.0;
	}
	
	 public void gebührVerbuchen(Benutzer benutzer) {
	        benutzer.setGebühren(0.0); 
	 }
}
