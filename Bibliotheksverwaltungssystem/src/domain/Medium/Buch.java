package domain.Medium;

public class Buch extends Medium {
	
	
	private String autor;

	public Buch(String title, int erscheinungsjahr, String autor) {
		super(title, erscheinungsjahr);
		
		if (autor.isEmpty())
			this.autor = "-";
		else
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
