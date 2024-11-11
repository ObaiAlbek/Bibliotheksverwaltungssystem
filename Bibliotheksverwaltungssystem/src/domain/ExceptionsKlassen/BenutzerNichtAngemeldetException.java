package domain.ExceptionsKlassen;

public class BenutzerNichtAngemeldetException extends Exception {
	
	public BenutzerNichtAngemeldetException(String error) {
		super(error);
	}
}
