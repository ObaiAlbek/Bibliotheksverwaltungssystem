package domain.Benutzer;

import java.time.LocalDate;

public class Kunde extends Benutzer {

	public Kunde(Ausweis bibAusweis, String name, int alter, boolean istStudent) {
		super(bibAusweis, name, alter, istStudent);
	}

	@Override
	public double getJahresgebühr() {
		return 25.0;
	}

}
