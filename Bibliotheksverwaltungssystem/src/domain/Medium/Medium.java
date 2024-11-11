package domain.Medium;

import java.util.Date;

public abstract class Medium {
	
	private String eindeutigeKennung;
	private String title;
	private int erscheinungsjahr;
	
	public Medium(String eindeutigeKennung, String title, int erscheinungsjahr) {
		super();
		this.eindeutigeKennung = eindeutigeKennung;
		this.title = title;
		this.erscheinungsjahr = erscheinungsjahr;
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

	
	@Override
	public String toString() {
		return "eindeutigeKennung=" + eindeutigeKennung + ", title=" + title + ", erscheinungsjahr="
				+ erscheinungsjahr ;
	}
	
	
	
	
	
}	
