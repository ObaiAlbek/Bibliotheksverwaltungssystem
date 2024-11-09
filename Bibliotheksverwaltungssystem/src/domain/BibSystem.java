package domain;

import java.util.ArrayList;
import domain.Benutzer.*;
import domain.ExceptionsKlassen.*;

public class BibSystem {
	private ArrayList<Benutzer> benutzer;
	
	public BibSystem() {
		this.benutzer = new ArrayList<>();
	}
	
	public String addUser(String name, String type, int alter,String istAdmin) throws FalscheEingabeException {
		if (name.isEmpty() && type.isEmpty() && alter == 0 && istAdmin.isEmpty() )
			throw new FalscheEingabeException("Geben Sie alle Fielder ein");
		
		Benutzer benutzer;
		Ausweis ausweis = new Ausweis();
		boolean istStudentOderSchüler = (type.equalsIgnoreCase("schüler") || type.equalsIgnoreCase("student"))? true : false;	

		if (istAdmin.equalsIgnoreCase("nein")) 
			benutzer = new Mitarbeiter(ausweis,name,alter,istStudentOderSchüler);
		else
			benutzer = new Kunde(ausweis,name,alter,istStudentOderSchüler);
		
		return benutzer.toString();
		
	}
}
