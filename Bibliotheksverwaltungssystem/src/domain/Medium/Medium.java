package domain.Medium;

import java.util.Date;

public abstract class Medium {

	private String title;
	private int erscheinungsjahr;

	public Medium(String title, int erscheinungsjahr) {
		super();
		this.title = title;
		this.erscheinungsjahr = erscheinungsjahr;
	}

	public String getTitle() {
		return title;
	}

	public int getErscheinungsjahr() {
		return erscheinungsjahr;
	}

	@Override
	public String toString() {
		return "title=" + title + ", erscheinungsjahr=" + erscheinungsjahr;
	}
	
}	
