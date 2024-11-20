package domain.fassade;

import domain.benutzer.*;
import domain.exceptionsKlassen.FalscheEingabeException;

public class Registieren {
	
	public static Benutzer userRegistrieren(String name, String type, int alter,String istAdmin) throws FalscheEingabeException {
		
		Benutzer benutzer;
		Ausweis ausweis;
		
		if (!name.isEmpty()) {
			boolean admin = (istAdmin.equalsIgnoreCase("Ja"))? true : false;	
			if ((type.equalsIgnoreCase("schüler") || type.equalsIgnoreCase("student"))) {
				ausweis = new Ausweis("K");
				benutzer = new Student(ausweis,name,alter,admin);
			}
			else if (type.equalsIgnoreCase("erwachsener")) {
				ausweis = new Ausweis("K");
				benutzer = new Erwachsener(ausweis,name,alter,admin);
			}
			else if (type.equalsIgnoreCase("mitarbeiter")) {
				ausweis = new Ausweis("A");
				benutzer = new Mitarbeiter(ausweis,name,alter,admin);
			}
			else
				throw new FalscheEingabeException("Falsche Eingabe");

		}
		else
			throw new FalscheEingabeException("Falsche Eingabe");
		
		return benutzer;
	}
}
