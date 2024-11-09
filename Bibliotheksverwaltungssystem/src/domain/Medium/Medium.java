package domain.Medium;

import java.util.Date;

public abstract class Medium {
	
	private int KennungNummer;
	private String title;
	private Date erscheinungsjahr;
	private String autor;
	
	public Medium(int kennungNummer, String title, Date erscheinungsjahr, String autor) {
		super();
		KennungNummer = kennungNummer;
		this.title = title;
		this.erscheinungsjahr = erscheinungsjahr;
		this.autor = autor;
	}

	public int getKennungNummer() {
		return KennungNummer;
	}

	public void setKennungNummer(int kennungNummer) {
		KennungNummer = kennungNummer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getErscheinungsjahr() {
		return erscheinungsjahr;
	}

	public void setErscheinungsjahr(Date erscheinungsjahr) {
		this.erscheinungsjahr = erscheinungsjahr;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	
}	
