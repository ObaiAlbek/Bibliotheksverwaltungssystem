package domain.Medium;


public class Dvd extends Medium {
	
	private String regisseur;
	public Dvd(String eindeutigeKennung, String title, int erscheinungsjahr, String regisseur) {
		super(eindeutigeKennung, title, erscheinungsjahr);
		this.regisseur = regisseur;
	}
	
	public String getRegisseur() {
		return regisseur;
	}
	public void setRegisseur(String regisseur) {
		this.regisseur = regisseur;
	}
	
	@Override
	public String toString() {
		return "DVD: " + super.toString() +  " ,Regisseur=" + regisseur;
	}

}
