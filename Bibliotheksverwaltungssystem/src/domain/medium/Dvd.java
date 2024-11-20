package domain.medium;


public class Dvd extends Medium {
	
	private String regisseur;
	public Dvd(String ID,String title, int erscheinungsjahr,String regisseur) {
		super(ID,title, erscheinungsjahr);
		
		if (regisseur.isEmpty())
			this.regisseur = "-";
		else
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
