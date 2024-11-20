package domain.exceptionsKlassen;

public class BenutzerNichtAngemeldetException extends Exception {
	
	public BenutzerNichtAngemeldetException(String error) {
		super(error);
	}
}
