package domain.Medium;

import java.util.Date;

public class Cd extends Medium {
	
	private String Künstler;
	public Cd(String title, int erscheinungsjahr,String Künstler) {
		super(title, erscheinungsjahr);
		
		if (Künstler.isEmpty())
			this.Künstler = "-";
		else
			this.Künstler = Künstler;
	}
	
	public String getKünstler() {
		return Künstler;
	}
	public void setKünstler(String künstler) {
		Künstler = künstler;
	}
	
	@Override
	public String toString() {
		return "CD: " + super.toString() +  " ,Künstler=" + Künstler;
	}

}
