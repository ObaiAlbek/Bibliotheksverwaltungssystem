package domain.benutzer;

public class Student extends Benutzer {

	public Student(Ausweis bibAusweis, String name, int alter, boolean istStudent) {
		super(bibAusweis, name, alter, istStudent);
	}

	@Override
	public double getJahresgebühren() {
		return 25.0;
	}

}
