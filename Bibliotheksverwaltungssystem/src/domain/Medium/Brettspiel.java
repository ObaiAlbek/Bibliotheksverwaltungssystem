package domain.Medium;


public class Brettspiel extends Medium {
	private String Verlag;

	public Brettspiel(String eindeutigeKennung, String title, int erscheinungsjahr,boolean verlängerbar, String Verlag) {
		super(eindeutigeKennung, title, erscheinungsjahr, verlängerbar);
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
