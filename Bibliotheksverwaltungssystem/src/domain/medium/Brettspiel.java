package domain.medium;


public class Brettspiel extends Medium {
	
	private String Verlag;

	public Brettspiel(String ID,String title, int erscheinungsjahr, String Verlag) {
		super(ID,title, erscheinungsjahr);
		
		if (Verlag.isEmpty())
			this.Verlag = "-";
		else
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
