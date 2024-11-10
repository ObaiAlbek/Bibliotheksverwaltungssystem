package domain.ExceptionsKlassen;

public class BenutzerNichtGefundenException extends Exception {
	
	public BenutzerNichtGefundenException(String error) {
		super(error);
	}

}
