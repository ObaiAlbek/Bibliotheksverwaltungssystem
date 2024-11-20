package domain.medium;


public abstract class Medium {

	private String title;
	private int erscheinungsjahr;
	private String ID;

	public Medium(String ID,String title, int erscheinungsjahr) {
		super();
		this.title = title;
		this.erscheinungsjahr = erscheinungsjahr;
		this.ID = ID;
	}

	public String getTitle() {
		return title;
	}

	public int getErscheinungsjahr() {
		return erscheinungsjahr;
	}
	
	public String getID() {
		return ID;
	}

	@Override
	public String toString() {
		return  "ID= "+ ID + " ,title=" + title + ", erscheinungsjahr=" + erscheinungsjahr;
	}
	
}	
