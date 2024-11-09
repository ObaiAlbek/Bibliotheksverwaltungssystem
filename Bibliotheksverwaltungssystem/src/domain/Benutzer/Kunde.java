package domain.Benutzer;

import domain.Ausweis;

public class Kunde extends Benutzer {

	public Kunde(Ausweis bibAusweis, String name, int alter, boolean istStudent) {
		super(bibAusweis, name, alter, istStudent);
	}

}
