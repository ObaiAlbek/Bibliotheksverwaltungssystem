package domain.Medium;

import java.util.Date;

public abstract class Medium {
	
	private String eindeutigeKennung;
	private String title;
	private int erscheinungsjahr;
	private boolean verlängerbar;
	
	public Medium(String eindeutigeKennung, String title, int erscheinungsjahr,boolean verlängerbar) {
		super();
		this.eindeutigeKennung = eindeutigeKennung;
		this.title = title;
		this.erscheinungsjahr = erscheinungsjahr;
		this.verlängerbar = verlängerbar;
	}

	public String getKennungNummer() {
		return eindeutigeKennung;
	}

	public void setKennungNummer(String kennungNummer) {
		eindeutigeKennung = kennungNummer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getErscheinungsjahr() {
		return erscheinungsjahr;
	}

	public void setErscheinungsjahr(int erscheinungsjahr) {
		this.erscheinungsjahr = erscheinungsjahr;
	}
	
	public String getEindeutigeKennung() {
		return eindeutigeKennung;
	}

	public void setEindeutigeKennung(String eindeutigeKennung) {
		this.eindeutigeKennung = eindeutigeKennung;
	}

	public boolean isVerlängerbar() {
		return verlängerbar;
	}

	public void setVerlängerbar(boolean verlängerbar) {
		this.verlängerbar = verlängerbar;
	}

	@Override
	public String toString() {
		return "eindeutigeKennung=" + eindeutigeKennung + ", title=" + title + ", erscheinungsjahr="
				+ erscheinungsjahr  + " ,Verlängerbar= " + verlängerbar ;
	}
	
	
	
	
	
}	
