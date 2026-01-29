# Exceptionhandling in Java – Zusammenfassung

## 1. Grundverständnis von Fehlern

### 1.1 Was ist ein Fehler?
- Gleiche Fehlermeldung kann viele Ursachen haben
- Beurteilung ist **kontextabhängig**
- Beispiele:
  - Division durch 0 → Programm- oder Eingabefehler?
  - NullPointerException → Aufrufer oder Aufgerufener schuld?

### 1.2 Fehlerkategorien
1. **Nicht behebbare Fehler**
   - z.B. OutOfMemoryError
   - Programmabbruch
2. **Durch Wiederholung behebbare Fehler**
   - z.B. temporäre Netzwerkfehler
3. **Durch Korrektur behebbare Fehler**
   - z.B. falscher Hostname

---

## 2. Fehlersignalisation

### 2.1 Rückgabewerte (problematisch)
- Fehlerstatus über Rückgabewert
- Führt zu:
  - Unübersichtlichem Code
  - Vermischung von Fachlogik und Fehlerbehandlung
- Für komplexe Abläufe ungeeignet

### 2.2 Ausnahmebehandlung (Exceptions)
- Fehler werden als **Objekte** signalisiert
- Vorteile:
  - Klarere Struktur
  - Trennung von Fachlogik und Fehlerbehandlung
- Exception wird:
  - erzeugt (`new`)
  - geworfen (`throw`)
  - gefangen (`catch`)
  - weitergereicht (`throws`)

---

## 3. Exceptionhandling in Java

### 3.1 Zentrale Schlüsselwörter
- `throw` – Exception auslösen
- `throws` – Exception weiterreichen
- `try` – geschützter Block
- `catch` – Behandlung
- `finally` – wird immer ausgeführt (z.B. Cleanup)

### 3.2 Exception-Hierarchie
- `Error`
  - Schwerwiegende, nicht behebbare Fehler
  - Unchecked
- `RuntimeException`
  - Behebbare, aber unerwartete Fehler
  - Unchecked
- `Exception`
  - Erwartete, behebbare Fehler
  - Checked (Behandlung Pflicht)

**Eigene Exceptions**
- Typisch: von `Exception` oder `RuntimeException`
- `Throwable` niemals direkt erweitern

---

## 4. Werfen und Behandeln von Exceptions

### 4.1 Werfen
- Methode wird beim `throw` sofort abgebrochen
- Code danach wird nicht mehr ausgeführt

### 4.2 Checked vs. Unchecked
- Checked:
  - Müssen gefangen oder mit `throws` deklariert werden
- Unchecked:
  - Behandlung optional

### 4.3 Behandeln
- Behandlung erfolgt meist **nicht an der Quelle**
- Sonderfall: finally-Block
  - Wird immer ausgeführt
  - Typisch für Aufräumarbeiten

### 4.4 Wichtige Regeln
- Mehrere catch-Blöcke erlaubt
- Reihenfolge: spezifisch → allgemein
- Keine leeren catch-Blöcke!
- Keine tief verschachtelten try/catch-Strukturen

---

## 5. Exceptions weiterleiten
- Unchecked Exceptions:
  - Automatisch weitergeleitet
- Checked Exceptions:
  - Müssen explizit mit `throws` weitergegeben werden
- Unbehandelt:
  - JVM gibt Stacktrace aus
  - Programmabbruch

---

## 6. Stacktrace
- Zeigt:
  - Klasse
  - Methode
  - Datei
  - Zeilennummer
- Analyse:
  - Von oben nach unten
  - Erste eigene Klasse ist meist relevant

---

## 7. Testen von Exceptionhandling

### 7.1 Warum testen?
- Fehlerfälle treten selten auf
- Fehler in der Fehlerbehandlung sind besonders kritisch
- Ziel: korrekte Signalisierung und Behandlung sicherstellen

### 7.2 JUnit-Techniken
- JUnit 3:
  - try/catch + fail()
  - veraltet
- JUnit 4:
  - @Test(expected=…)
  - ExpectedException Rule
  - eingeschränkt / veraltet
- **JUnit 5 (empfohlen)**:
  - `assertThrows()`
  - Zugriff auf Exception-Objekt möglich

---

## 8. AssertJ (optional)

### 8.1 Vorteile
- Fluent API (lesbarer Code)
- Typisierte, aussagekräftige Assertions
- Gute IDE-Unterstützung

### 8.2 Exception-Testing
- `assertThatThrownBy(...)`
- Prüfung von Typ und Message
- Auch mit JUnit 4 nutzbar

---

## 9. Logging

### 9.1 Warum kein System.out.println?
- Konsole oft unsichtbar
- Begrenzter Puffer
- Ausgaben gehen verloren
- In Tests nutzlos

### 9.2 Logging-Frameworks
- Log4J 2
- LogBack
- java.util.logging (selten)
- Logging-Fassaden:
  - SLF4J
  - Apache Commons Logging

---

## 10. SLF4J – Simple Logging Facade for Java

### 10.1 Idee
- Einheitliche Logging-Schnittstelle
- Austauschbares Backend
- Geringe Kopplung

### 10.2 Nutzung
- Pro Klasse ein Logger
- Logging-Level:
  - ERROR
  - WARN
  - INFO
  - DEBUG
  - TRACE

### 10.3 Konfiguration
- Über Konfigurationsdateien
- Ohne Codeänderung anpassbar
- Unterschiedliche Profile für Test & Produktion

---

## 11. Logging & Exceptionhandling

- Jede Exception mindestens loggen
- Typischer Level:
  - ERROR oder WARN
- Produktion:
  - Meist nur ERROR/WARN aktiv
- Debugging:
  - Temporär Level erhöhen
- Logging ist oft die **minimale Behandlung**

---

## 12. Empfehlungen für gutes Exceptionhandling

- Fehlerbehandlung ernst nehmen
- Exceptions dokumentieren (JavaDoc)
- Exceptions nur für echte Ausnahmen
- Nicht für Programmsteuerung missbrauchen
- Eigene Exceptions sparsam einsetzen
- Keine leeren catch-Blöcke
- Exceptionhandling immer testen
- Logging konsequent einsetzen

---

## 13. Kernaussagen
- Gute Fehlerbehandlung ist anspruchsvoll
- Exceptions trennen Fachlogik und Fehlerbehandlung
- Checked vs. Unchecked bewusst wählen
- Testen und Logging sind zentrale Bestandteile
- Sauberes Exceptionhandling erhöht Robustheit massiv
