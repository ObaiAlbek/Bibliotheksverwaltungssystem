package domain.Medium;

import java.util.Date;

public class Cd extends Medium {
	
	private String Künstler;
	public Cd(String eindeutigeKennung, String title, int erscheinungsjahr,boolean verlängerbar, String Künstler) {
		super(eindeutigeKennung, title, erscheinungsjahr,verlängerbar);
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
