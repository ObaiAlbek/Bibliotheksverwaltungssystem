package domain.exceptionsKlassen;

public class BenutzerNichtGefundenException extends Exception {
	
	public BenutzerNichtGefundenException(String error) {
		super(error);
	}

}
