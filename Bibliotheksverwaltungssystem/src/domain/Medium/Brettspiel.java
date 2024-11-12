package domain.Medium;


public class Brettspiel extends Medium {
	private String Verlag;

	public Brettspiel(String title, int erscheinungsjahr, String Verlag) {
		super(title, erscheinungsjahr);
		this.Verlag = Verlag;
	}

	public String getVerlag() {
		return Verlag;
	}

	public void setVerlag(String verlag) {
		Verlag = verlag;
	}
	
	@Override
	public String toString() {
		return "Brettspiel: " + super.toString() +  " ,Verlag=" + Verlag;
	}

}
