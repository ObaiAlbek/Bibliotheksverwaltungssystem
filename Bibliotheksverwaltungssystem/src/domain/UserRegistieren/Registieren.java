package domain.UserRegistieren;

import domain.Benutzer.*;
import domain.ExceptionsKlassen.FalscheEingabeException;

public class Registieren {
	
	public static Benutzer userRegistrieren(String name, String type, int alter,String istAdmin) throws FalscheEingabeException {
		if (name.isEmpty() || type.isEmpty() || alter <= 0 || istAdmin.isEmpty())
		    throw new FalscheEingabeException("Geben Sie alle Felder korrekt ein");
		
		Benutzer benutzer;
		Ausweis ausweis;
		boolean istStudentOderSchüler = (type.equalsIgnoreCase("schüler") || type.equalsIgnoreCase("student"))? true : false;	

		if (istAdmin.equalsIgnoreCase("nein")) {
			ausweis = new Ausweis("K");
			benutzer = new Mitarbeiter(ausweis,name,alter,istStudentOderSchüler);
		}
		
		else {
			ausweis = new Ausweis("A");
			benutzer = new Kunde(ausweis,name,alter,istStudentOderSchüler);
		}
		
		return benutzer;
		
	}
}
