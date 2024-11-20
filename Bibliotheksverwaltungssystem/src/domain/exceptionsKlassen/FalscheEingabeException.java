package domain.exceptionsKlassen;

public class FalscheEingabeException extends Exception {
	
	public FalscheEingabeException(String error){
		super(error);
	}
}
