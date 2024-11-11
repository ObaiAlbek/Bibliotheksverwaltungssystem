package domain.Medium;

import java.util.Date;

public class Videospiel extends Medium {
	
	private String plattform;
	
	public Videospiel(String kennungNummer, String title, int erscheinungsjahr, String plattform) {
		super(kennungNummer, title, erscheinungsjahr);
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
