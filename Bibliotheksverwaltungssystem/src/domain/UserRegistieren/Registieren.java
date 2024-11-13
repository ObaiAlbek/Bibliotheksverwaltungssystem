package domain.UserRegistieren;

import domain.Benutzer.*;
import domain.ExceptionsKlassen.FalscheEingabeException;

public class Registieren {
	
	public static Benutzer userRegistrieren(String name, String type, int alter,String istAdmin) throws FalscheEingabeException {
		Benutzer benutzer;
		Ausweis ausweis;
		
		if (!name.isEmpty() && (type.equalsIgnoreCase("schüler") || type.equalsIgnoreCase("student")) && istAdmin.equalsIgnoreCase("nein")) {
			ausweis = new Ausweis("K");
			benutzer = new Kunde(ausweis,name,alter,false);
		}
		else if (!name.isEmpty() && type.equalsIgnoreCase("mitarbeiter") && istAdmin.equalsIgnoreCase("ja")) {
			ausweis = new Ausweis("A");
			benutzer = new Mitarbeiter(ausweis,name,alter,true);
		}
		else
			throw new FalscheEingabeException("Falsche Eingabe");
		
		return benutzer;
		
	}
}
