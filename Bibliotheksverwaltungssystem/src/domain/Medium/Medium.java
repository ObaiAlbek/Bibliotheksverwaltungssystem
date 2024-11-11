package domain.Medium;

import java.util.Date;

public abstract class Medium {
	
	private String eindeutigeKennung;
	private String title;
	private int erscheinungsjahr;
	private String autor;
	private int anzahlWochen;
	
	public Medium(String eindeutigeKennung, String title, int erscheinungsjahr, String autor) {
		super();
		this.eindeutigeKennung = eindeutigeKennung;
		this.title = title;
		this.erscheinungsjahr = erscheinungsjahr;
		this.autor = autor;
		this.anzahlWochen = 0;
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAnzahlWochen() {
		return anzahlWochen;
	}

	public void setAnzahlWochen(int anzahlWochen) {
		this.anzahlWochen = anzahlWochen;
	}

	@Override
	public String toString() {
		return "Medium [eindeutigeKennung=" + eindeutigeKennung + ", title=" + title + ", erscheinungsjahr="
				+ erscheinungsjahr + ", autor=" + autor + ", anzahlWochen=" + anzahlWochen + "]";
	}
	
	
	
	
	
}	
