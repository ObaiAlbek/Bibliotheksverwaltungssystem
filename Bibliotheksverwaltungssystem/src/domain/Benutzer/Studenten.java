package domain.Benutzer;

public class Studenten extends Benutzer {

	public Studenten(Ausweis bibAusweis, String name, int alter, boolean istStudent) {
		super(bibAusweis, name, alter, istStudent);
	}

	@Override
	public double getJahresgebühren() {
		return 25.0;
	}

}
