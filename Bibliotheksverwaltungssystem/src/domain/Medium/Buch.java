package domain.Medium;

import java.util.Date;

public class Buch extends Medium {
	
	private String autor;
	public Buch(String eindeutigeKennung, String title, int erscheinungsjahr, String autor) {
		super(eindeutigeKennung, title, erscheinungsjahr);
		this.autor = autor;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	@Override
	public String toString() {
		return "Buch: " + super.toString() +  " ,autor=" + autor;
	}
	
	
	
	

}
