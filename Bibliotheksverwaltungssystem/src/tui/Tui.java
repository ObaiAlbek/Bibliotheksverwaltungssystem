package tui;

import java.util.ArrayList;
import java.util.Scanner;

import domain.exceptionsKlassen.*;
import domain.fassade.BibSystem;
import medienHinzüfugen.MedienHinzufügen;

public class Tui {
    private BibSystem fassade;
    private Scanner eingabe;
    private MedienHinzufügen mediumsHinzufügen;

    public Tui() {
        this.fassade = new BibSystem();
        this.eingabe = new Scanner(System.in);
        this.mediumsHinzufügen = new MedienHinzufügen(fassade);

        System.out.println("<< Willkommen in der Bibliothek >>");
        try {
            startBibProgramm();
        } catch (Exception e) {
            System.out.println("Ein unerwarteter Fehler ist aufgetreten: " + e.getMessage());
        }
    }

    private void startBibProgramm() {
        boolean programmIstAktiv = true;

        while (programmIstAktiv) {
            zeigeHauptmenü();
            System.out.print("> ");
            String aktion = eingabe.nextLine();

            try {
                switch (aktion) {
                    case "1":
                        registrierenProzess();
                        break;
                    case "2":
                        anmeldenProzess();
                        break;
                    case "3":
                        mediumDurchsuchenProzess();
                        break;
                    case "4":
                        mediumAusleihenProzess();
                        break;
                    case "5":
                        mediumsRückgabeProzess();
                        break;
                    case "6":
                        zeigeAusgelieheneGegenstände();
                        break;
                    case "7":
                        mediumsVerlängernProzess();
                        break;
                    case "8":
                        verbucheGebührenProzess();
                        break;
                    case "9":
                    	datumÄndern();
                    	break;
                    case "0":
                        programmIstAktiv = false;
                        System.out.println("Programm beendet.");
                        break;
                    default:
                        System.out.println("Ungültige Eingabe. Bitte wählen Sie eine gültige Option.");
                }
            } catch (Exception e) {
                System.out.println("Fehler: " + e.getMessage());
            }
        }
    }

    private void zeigeHauptmenü() {
        System.out.println("\nHauptmenü:");
        System.out.println("1. Registrieren");
        System.out.println("2. Anmelden");
        System.out.println("3. Medien durchsuchen");
        System.out.println("4. Medium ausleihen");
        System.out.println("5. Medium zurückgeben");
        System.out.println("6. Ausgeliehene Gegenstände anzeigen");
        System.out.println("7. Leihfrist verlängern");
        System.out.println("8. Gebühren verbuchen (Admin)");
        System.out.println("9. Datum ändern");
        System.out.println("0. Programm beenden");
    }
    
    
    // Aktion: 1
    private void registrierenProzess() {
        System.out.println("<< Registrierung >>");

        System.out.print("Name: ");
        String name = eingabe.nextLine();

        System.out.print("Alter: ");
        int alter = Integer.parseInt(eingabe.nextLine());

        System.out.print("Sind Sie Schüler oder Student? (Ja/Nein): ");
        String type = eingabe.nextLine();

        System.out.print("Sind Sie ein Admin? (Ja/Nein): ");
        String istAdmin = eingabe.nextLine();

        try {
            fassade.userRegistrieren(name, type, alter, istAdmin);
            System.out.println("Registrierung erfolgreich!");
        } catch (FalscheEingabeException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }
    
    // Aktion: 2
    private void anmeldenProzess() {
        System.out.println("<< Anmeldung >>");

        System.out.print("Kartennummer: ");
        String kartennummer = eingabe.nextLine();

        try {
           {
        	   double gebühren = fassade.userAnmelden(kartennummer);
               System.out.println("Erfolgreich angemeldet.\n" + "Gebühren= " + gebühren);
            }
        } catch (BenutzerNichtGefundenException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }
    
    // Aktion: 3
    private void mediumDurchsuchenProzess() {
        System.out.println("<< Medien durchsuchen >>");
        System.out.print("Suchkriterium (z. B. Titel, Medienart, Ausgeliehen, nicht Ausgeliehen,Medien die bald wieder verügbar sind (ja/nein)): ");
        String auswahl = eingabe.nextLine();

        System.out.print("BibKartennummer: ");
        String bibKartennummer = eingabe.nextLine();

        try {
        	if (auswahl.equalsIgnoreCase("Medienart")) {
        		System.out.println("Bücher, Brettspiele, Dvds, Cds, Videospiele");
        		auswahl = eingabe.nextLine();
        	}
        		
           ArrayList<String> treffer =  fassade.mediumDurchsuchen(auswahl, bibKartennummer);
           treffer.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }
    
    // Aktion: 4
    private void mediumAusleihenProzess() {
        System.out.println("<< Medium ausleihen >>");

        System.out.print("Kartennummer: ");
        String kartennummer = eingabe.nextLine();

        System.out.print("Eindeutige Kennung des Mediums: ");
        String eindeutigeKennung = eingabe.nextLine();

        try {
        	double gebühren = fassade.mediumAusleihen(kartennummer, eindeutigeKennung);
            System.out.println("Akteulle Gebühren= " + gebühren);
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }
    
    // Aktion: 5
    private void mediumsRückgabeProzess() {
        System.out.println("<< Medium zurückgeben >>");

        System.out.print("Eindeutige Kennung des Mediums: ");
        String eindeutigeKennung = eingabe.nextLine();

        try {
            ArrayList<String> ausgelieheneMedien = fassade.medienRückgabe(eindeutigeKennung);
            System.out.println("Medium erfolgreich zurückgegeben.");  
            if (ausgelieheneMedien.isEmpty()) 
                System.out.println("Sie haben keine weiteren ausgeliehenen Medien.");
            else {
                System.out.println("Ihre verbleibenden ausgeliehenen Medien:");
                ausgelieheneMedien.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }
    
    // Aktion: 6
    private void zeigeAusgelieheneGegenstände() {
        System.out.println("<< Ausgeliehene Gegenstände anzeigen >>");

        System.out.print("BibKartennummer: ");
        String bibKartennummer = eingabe.nextLine();

        try {
          ArrayList<String> treffer = fassade.ausgeliehenGegenstände(bibKartennummer);
          if (treffer.size() == 0)
        	  System.out.println("Sie haben keine ausgeliehen Medien");
          else
        	  treffer.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }
    
    // Aktion: 7
    private void mediumsVerlängernProzess() {
        System.out.println("<< Leihfrist verlängern >>");

        System.out.print("BibKartennummer: ");
        String bibKartennummer = eingabe.nextLine();

        System.out.print("Eindeutige Kennung des Mediums: ");
        String eindeutigeKennung = eingabe.nextLine();

        try {
            fassade.medienVerlängern(eindeutigeKennung, bibKartennummer);
            System.out.println("Leihfrist erfolgreich verlängert.");
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    //Aktion: 8
    private void verbucheGebührenProzess() {
        System.out.println("<< Gebühren verbuchen >>");

        System.out.print("BibKartennummer des Admins: ");
        String bibKartennummer = eingabe.nextLine();
        
        try {
            if (fassade.adminAnmelden(bibKartennummer)) {
            	System.out.println("Erfolgreich Angemeldt");
            	System.out.println("Welche Aktion: ");
            	System.out.println("1.Betrag verbuchen");
            	System.out.println("2.ausgeliehene Mediums anzeigen");
            	System.out.println("3.aktuelles Betrag anzeigen");
            	System.out.print(">");
            	String auswahl = eingabe.nextLine();
            	String userID = eingabe.nextLine();
            	
            	switch (auswahl) {
            		case "1":
            			System.out.println(fassade.gebührenVerbuchen(userID));
            			break;
            		
            		case "2":
            			ArrayList<String> treffer = fassade.ausgeliehenGegenstände(userID);
            			treffer.forEach(System.out::println);
            			break;
            		
            		case "3":
            			System.out.println(fassade.getgbührenBenutzer(userID));
            			break;
            		default:
            			System.out.println("Falsche Eingabe");
            	}
            }
            	
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }
    
    // Aktion 9
    private void datumÄndern() {
    	System.out.println("1.Jahresgebühren");
    	System.out.println("2.Ausleihefristen");
    	String auswahl = eingabe.nextLine();
    	
    	switch (auswahl) {
    		case "1":
				try {
					System.out.println("Bibkartennummer:");
	    	    	String bibkartennummer = eingabe.nextLine();
	    	    	System.out.println("Datum: ");
	    	    	String datum = eingabe.nextLine();
					fassade.jahresGebührenBerechnen(bibkartennummer, datum);
				} catch (BenutzerNichtGefundenException e) {
					e.printStackTrace();
				}
				break;
    		case "2":
    			
			try {
				System.out.println("Bibkartennummer:");
    	    	String bibkartennummer = eingabe.nextLine();    	
    	    	System.out.println("AusleihBeginn: ");
    	    	String ausleihbeginn = eingabe.nextLine();
    	    	System.out.println("AusleihEnde: ");
    	    	String ausleihEnde = eingabe.nextLine();
    	    	System.out.println("Datum vom heute: ");
    	    	String heutigesDatum = eingabe.nextLine();
    	    	System.out.println("Medium ID: ");
    	    	String mediumID = eingabe.nextLine();
				fassade.datumÄndern(mediumID, ausleihEnde, ausleihEnde, heutigesDatum);
			} catch (MediumNichtGefundenException e) {
				System.out.println("Fehler: " + e.getMessage());
			}
    	    	
    	}
    }
}

