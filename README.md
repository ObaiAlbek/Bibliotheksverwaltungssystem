# 📚 Bibliotheksverwaltungssystem

Ein Java-basiertes **Bibliothekssystem** mit Benutzerverwaltung, Medienverwaltung und Ausleihsystem.  
Das Projekt wurde im Rahmen einer Studienleistung entwickelt und simuliert zentrale Abläufe einer Bibliothek wie **Registrierung, Ausleihe, Rückgabe, Verlängerung, Gebührenberechnung** sowie **Admin-Funktionen**.

---

## 🚀 Features

- 👤 **Benutzerverwaltung**
  - Registrierung (Student, Schüler, Erwachsener, Mitarbeiter)
  - Anmeldung / Abmeldung
  - Gebührenverwaltung (inkl. Jahresgebühren)

- 📦 **Medienverwaltung**
  - Medienarten: Bücher, CDs, DVDs, Brettspiele, Videospiele
  - Hinzufügen neuer Medien
  - Suchen nach Titel, Medienart oder Verfügbarkeit
  - Auflisten bald verfügbarer Medien

- 📖 **Ausleihsystem**
  - Medien ausleihen
  - Rückgabe (inkl. Berechnung von Verzugsgebühren)
  - Verlängerung von Leihfristen (falls erlaubt)
  - Simulation von Ausleihfristen & Gebühren über Datum-Änderung

- 🛠 **Admin-Funktionen**
  - Anmeldung als Admin
  - Anzeigen von Benutzergebühren
  - Verbuchen von Gebühren (Zurücksetzen auf 0)
  - Anzeigen aktuell ausgeliehener Medien

- 🧪 **Unit Tests**
  - Implementiert mit **JUnit 5**
  - Tests für Registrierung, Anmeldung, Ausleihe, Rückgabe, Verlängerung und Gebühren

---

## 🏗 Architektur

Das Projekt folgt dem **Domain-Driven Design Ansatz** mit klar getrennten Paketen:

src/
├── domain/
│ ├── ausleihSystem/ # Klassen für Ausleihe, Rückgabe, Verlängerung
│ ├── benutzer/ # Benutzerklassen (Student, Erwachsener, Mitarbeiter, Ausweis)
│ ├── bibliothekskatalog # Suchfunktionen für Medien
│ ├── exceptionsKlassen # Eigene Exceptions
│ ├── fassade/ # Fassade (BibSystem) und Registrierung
│ └── medium/ # Medienklassen (Buch, CD, DVD, etc.)
├── tui/ # Text-UI (Kommandozeilenoberfläche)
├── medienHinzüfugen/ # Menü zum Hinzufügen neuer Medien
└── main/ # Einstiegspunkt (Main.java)


- **BibSystem** dient als zentrale Fassade zwischen UI und Logik.  
- **AusleiheSystem** kümmert sich um Ausleihen, Rückgabe und Verlängerungen.  
- **Benutzer-Hierarchie** (`Student`, `Erwachsener`, `Mitarbeiter`) verwaltet Rechte und Gebühren.  
- **Mediumverwalter** bindet Medien mit Leihdauer, Anzahl und Ausleihstatus.  

---

## ⚙️ Installation & Ausführung

### Voraussetzungen
- **Java 17+**
- **JUnit 5** (für Tests)
- IDE wie IntelliJ IDEA, Eclipse oder VS Code mit Java Extension Pack

### Schritte
1. Repository klonen:
   ```bash
   git clone <REPO_URL>
   cd bibliotheksverwaltung
   javac -d bin src/**/*.java
   java -cp bin main.Main
   
---

## 📋 Nutzung

Nach dem Start erscheint das Hauptmenü:

<< Willkommen in der Bibliothek >>

Hauptmenü:
1. Registrieren
2. Anmelden
3. Medien durchsuchen
4. Medium ausleihen
5. Medium zurückgeben
6. Ausgeliehene Gegenstände anzeigen
7. Leihfrist verlängern
8. Gebühren verbuchen (Admin)
9. Datum ändern (Simulation)
10. Neues Medium hinzufügen
0. Programm beenden
   
👉 Die Navigation erfolgt über Eingabe der Zahl (z. B. 1 für Registrierung).
👉 Für die Ausleihe wird eine BibKartennummer benötigt, die bei der Registrierung automatisch vergeben wird (z. B. K1001).

---

##  🧪 Tests

Alle Kernfunktionen sind mit JUnit 5 getestet:
- RegistrierenTest
- UserAnmeldenTest
- MedienSuchenTest
- AusleiheJTest
- MedienRückgabeTest
- MediumVerlängernTest
- GebührenTest

Tests starten (z. B. in IntelliJ oder Maven/Gradle Integration).
Bei manueller Ausführung mit junit-platform-console-standalone.jar:
- java -jar junit-platform-console-standalone.jar -cp bin --scan-classpath

## 👤 Autor

- Name: Obai Albek
- Hochschule: Teschniche Hochschule Mannheim
- Fachbereich: Informatik (Bachelor)
