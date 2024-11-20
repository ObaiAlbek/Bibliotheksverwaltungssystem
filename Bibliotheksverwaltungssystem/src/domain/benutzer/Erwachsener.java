package domain.benutzer;

public class Erwachsener extends Benutzer {

	public Erwachsener(Ausweis bibAusweis, String name, int alter, boolean istStudent) {
		super(bibAusweis, name, alter, istStudent);
	}

	@Override
	public double getJahresgebühren() {
		return 50.0;
	}

}
