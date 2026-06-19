# Event/Listener-Pattern, Kopplung & Kohäsion – Zusammenfassung

## 1. Motivation: Kopplung reduzieren

### 1.1 Zirkuläre Beziehungen
- Entstehen, wenn sich Klassen gegenseitig direkt kennen
- Folgen:
  - Sehr starke Kopplung
  - Schlechte Wiederverwendbarkeit
  - Keine unabhängige Weiterentwicklung
  - In modularen / verteilten Systemen verboten
- Beispiel:
  - Auto kennt Motor
  - Motor kennt Auto → Zyklus

### 1.2 Grundidee zur Lösung
- Abhängigkeiten über **Interfaces** abstrahieren
- Konkrete Klassen nicht direkt referenzieren
- → Lose Kopplung, keine Zyklen

---

## 2. Observer- / Event-Listener-Pattern

### 2.1 Grundkonzept
- Implementierung des **Observer-Patterns** in Java
- Ziel:
  - Lose Kopplung zwischen Objekten
  - Ereignisgesteuerte Kommunikation
- Rollen:
  - **Event-Quelle** (Subject)
  - **Event** (Datenobjekt)
  - **Listener** (Observer)

### 2.2 Analogie: Zeitungsabo
- Leser = Listener
- Verlag = Event-Quelle
- Zeitung = Event
- Verlag kennt Leser nur über den „Briefkasten“ (Interface)

---

## 3. Bestandteile des Event/Listener-Patterns

### 3.1 Listener-Interface
- Definiert die Callback-Methode
- Meist nur **eine Methode**
- Beispiel:
  - `PropertyChangeListener`
  - Methode: `propertyChange(PropertyChangeEvent e)`

### 3.2 Event-Klasse
- Enthält alle relevanten Informationen
- Typische Inhalte:
  - Quelle (`source`)
  - Event-spezifische Daten
- Eigene Events:
  - Erben immer von `EventObject`

### 3.3 Event-Quelle
- Verwaltet eine Liste von Listenern
- Benötigte Methoden:
  - `addXxxListener(...)`
  - `removeXxxListener(...)`
  - `fireXxxEvent(...)` (privat)

---

## 4. Beispiel: PropertyChangeListener

### 4.1 Event
- `PropertyChangeEvent`
- Informiert über Zustandsänderungen
- Enthält:
  - Quelle
  - Property-Name
  - Alten und neuen Wert

### 4.2 Event-Quelle
- Beispiel: Motor
- Speichert `List<PropertyChangeListener>`
- Löst Event bei Zustandsänderung aus

### 4.3 Event-Empfänger
- Beispiel: Car
- Implementiert `PropertyChangeListener`
- Registriert sich bei mehreren Quellen
- Unterscheidung der Events über `event.getSource()`

---

## 5. Probleme bei klassischem Event-Handling

- Ein Listener empfängt Events von mehreren Quellen
- Unterscheidung über `if (event.getSource() == ...)`
- Nachteile:
  - Verletzung des Single Responsibility Principle
  - Unübersichtlicher Code
  - Geringe Kohäsion

---

## 6. Innere Klassen (Inner Classes)

### 6.1 Idee
- Klassen können innerhalb anderer Klassen definiert werden
- Typische Nutzung:
  - Private Hilfsklassen
  - Stärkere Kohäsion
  - Weniger Sichtbarkeit nach aussen

### 6.2 Vorteil für Event-Handling
- Eigener Listener pro Quelle möglich
- Kein `if/else`-Chaos
- Bessere Struktur

### 6.3 Beispiel
- `MotorPropertyListener` als innere Klasse in `Car`
- Jede Quelle bekommt ihren eigenen Listener

---

## 7. Anonyme innere Klassen

### 7.1 Einsatz
- Wenn Listener nur **einmal** benötigt wird
- Kein Klassenname notwendig
- Direkt bei der Registrierung definiert

### 7.2 Eigenschaften
- Kein expliziter Klassenname
- Definition direkt im Methodenaufruf
- Kürzer, aber syntaktisch gewöhnungsbedürftig

---

## 8. Lambda-Ausdrucke (Ausblick)

### 8.1 Motivation
- Noch kompaktere Listener-Implementierung
- Seit Java 8

### 8.2 Voraussetzung
- Listener-Interface muss ein **Functional Interface** sein
  - Genau eine abstrakte Methode

### 8.3 Vorteil
- Sehr kurze, lesbare Event-Behandlung
- Ideal für einfache Listener

---

## 9. Kohäsion

### 9.1 Begriff
- Innerer Zusammenhalt einer Klasse oder Einheit
- Ziel:
  - Eine klar definierte Aufgabe
- Entspricht dem **Single Responsibility Principle (SRP)**

### 9.2 Hohe Kohäsion bedeutet
- Klasse ist klein und fokussiert
- Attribute und Methoden gehören logisch zusammen
- Klasse ist nicht sinnvoll weiter zerlegbar

### 9.3 Vorteile
- Bessere Verständlichkeit
- Leichtere Wartung
- Höhere Wiederverwendbarkeit
- Einfacheres Testen
- Weniger Änderungen bei Erweiterungen

---

## 10. Kopplung

### 10.1 Begriff
- Stärke und Art der Abhängigkeiten zwischen Klassen
- Kopplung ist notwendig, aber:
  - So **schwach wie möglich**
  - So **stark wie nötig**

### 10.2 Arten von Kopplung (von schwach zu stark)
1. Use / Depends
   - Lokale Variablen, Parameter, Rückgabewerte
2. Assoziation
   - Dauerhafte Beziehung (Attribut)
3. Aggregation
   - Teil-von-Beziehung (nicht existenziell)
4. Komposition
   - Existenzabhängige Teil-von-Beziehung
5. Implementation (`implements`)
6. Vererbung (`extends`)
   - Stärkste Kopplung

---

## 11. Gutes OO-Design

### 11.1 Zielkonflikt
- Hohe Kohäsion ↔ Lose Kopplung
- Beide Ziele müssen gleichzeitig verfolgt werden

### 11.2 Auswirkungen von gutem Design
- Bessere Softwarequalität
- Einfachere Erweiterbarkeit
- Schnellere Fehlersuche
- Mehr Parallelität in der Entwicklung
- Geringeres Fehlerrisiko

---

## 12. Empfehlungen

- SRP konsequent einhalten
- Lieber mehr, kleinere Klassen
- Abhängigkeiten über Interfaces
- Beziehungen möglichst schwach gestalten
- Event/Listener-Pattern zur Entkopplung nutzen
- Innere Klassen oder Lambdas für sauberes Event-Handling verwenden
- Hohe Kohäsion + lose Kopplung als Leitprinzip

---

## 13. Kernaussagen
- Event/Listener-Pattern ist zentrale Technik zur Entkopplung
- Verhindert zirkuläre Abhängigkeiten
- Unterstützt modularen, erweiterbaren Code
- Hohe Kohäsion und lose Kopplung sind Fundament guten OO-Designs
