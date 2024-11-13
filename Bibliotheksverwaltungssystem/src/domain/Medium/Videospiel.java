package domain.Medium;

import java.util.Date;

public class Videospiel extends Medium {
	
	private String plattform;
	
	public Videospiel(String title, int erscheinungsjahr, String plattform) {
		super(title, erscheinungsjahr);
		
		if (plattform.isEmpty())
			this.plattform = "-";
		else
			this.plattform = plattform;
	}

	public String getPlattform() {
		return plattform;
	}

	public void setPlattform(String plattform) {
		this.plattform = plattform;
	}
	
	@Override
	public String toString() {
		return "Videospiel: " + super.toString() +  " ,Plattform=" + plattform;
	}

}
