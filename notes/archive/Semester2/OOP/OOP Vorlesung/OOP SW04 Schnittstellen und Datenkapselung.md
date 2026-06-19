# 🧠 Schnittstellen (Interfaces, Abstraktion & Modularisierung)

**Dozent:** Roland Gisler  
**Modul:** OOP – HS25  

---

## 📋 Inhalt
- Abstraktion  
- Modularisierung  
- Abstrakte Klassen  
- Interfaces  
- JavaDoc  

---

## 🎯 Lernziele
- Vorgang der **Abstraktion** verstehen  
- Prinzip der **Modularisierung** verstehen  
- **Abstrakte Klassen** entwerfen  
- **Schnittstellen (Interfaces)** definieren und implementieren  
- **JavaDoc** für Klassen, Methoden und Interfaces erstellen  

---

## 🧠 Abstraktion
- Vereinfachung der Realität auf das **im Kontext Wesentliche**  
- Fokus auf das **WAS**, nicht auf das **WIE**
- Zwei Perspektiven:
  - **Innensicht:** Attribute & interne Methoden  
  - **Aussensicht:** Nur öffentliche Methoden (Nutzendensicht)

**Beispiel:**  
Ein Bankautomat abstrahiert den komplexen Geldbezugsvorgang zu einer einfachen **„Auszahlungstaste“**.

---

## ⚙️ Modularisierung
> „Divide et impera“ – teile und herrsche  

**Definition:**  
Zerlegung einer Gesamtaufgabe in klar abgegrenzte Teilmodule mit wohldefinierten Schnittstellen.

**Ziele:**  
- Komplexität reduzieren  
- Wiederverwendbarkeit erhöhen  
- Unabhängige Entwicklung ermöglichen  

**Prinzipien:**  
- **Separation of Concerns (SoC)**  
- **Single Responsibility Principle (SRP)**  

**Ebenen der Modularisierung:**  
| Ebene | Beispiel |
|--------|-----------|
| Tief | Klassen, Datenkapselung |
| Mittel | Module, Libraries |
| Hoch | Microservices, Architekturkomponenten |

---

## 🧩 Abstrakte Klassen
- Definiert mit `abstract`  
- Können **nicht instanziiert** werden  
- Enthalten **abstrakte Methoden** (ohne Body) und ggf. **konkrete Methoden**
- Dienen zur **Definition des WAS**, nicht des WIE  

```java
public abstract class AbstractSwitch {
    public abstract void switchOn();
    public abstract void switchOff();
    public abstract boolean isSwitchedOn();
}
```

**Eigenschaften:**
- Enthält mindestens eine abstrakte Methode  
- Kann teilweise implementiert sein  
- Kann als Basisklasse für Spezialisierungen dienen  

**Namenskonvention:**  
`Abstract`-Prefix → z. B. `AbstractPerson`, `AbstractConnection`  

**Empfehlung:**  
Wenn **alle Methoden abstrakt** sind → besser ein **Interface** verwenden.  

---

## 🔗 Interfaces (Schnittstellen)
- Vollständig **abstrakt**, keine Instanziierung möglich  
- Definiert **Verhalten** (Methodenköpfe), nicht Implementation  
- Schlüsselwort: `interface`  
- Methoden sind implizit `public abstract`  
- Keine Attribute oder Konstruktoren (Ausnahmen in Spezialfällen)

**Beispiel:**
```java
public interface Switchable {
    void switchOn();
    void switchOff();
    boolean isSwitchedOn();
    boolean isSwitchedOff();
}
```

**Verwendung:**
```java
public final class Lamp implements Switchable {
    @Override
    public void switchOn() {
        // Implementation
    }
}
```

**Vorteile:**
- Ermöglicht **Polymorphie**
- Fördert **Entkopplung**
- Eignet sich für **Design by Interfaces**

**Namenskonventionen:**
| Variante | Beispiel |
|-----------|-----------|
| „able“-Postfix | `Runnable`, `Serializable` |
| „Interface“-Postfix | `PrintInterface` |
| „I“-Prefix (C#-Stil) | `IPrinter` |
| Fachname direkt | `Iterator` |

**Empfehlung:**  
Lieber **ein Interface zu viel als zu wenig** → erleichtert Austausch und Erweiterung.

---

## 📘 JavaDoc
- Dokumentationstool für Java-Quellcode  
- Dokumentiert:
  - Packages, Klassen, Interfaces, Methoden, Attribute  

**Beispiel:**
```java
/**
 * Schaltet das Gerät ein.
 * @return true wenn erfolgreich eingeschaltet.
 */
public boolean switchOn();
```

**Regeln:**
1. Dokumentieren, was für andere relevant ist  
2. Kurze, prägnante Sätze (keine Romane)  
3. „Erster-Satz-und-Punkt“-Regel  
4. HTML-Fragmente vermeiden  
5. Pflicht bei **Interfaces** und **öffentlichen Methoden**

---

## 🧾 Zusammenfassung
- **Abstraktion:** Reduktion auf das Wesentliche (WAS vs. WIE)  
- **Modularisierung:** Zerlegung komplexer Systeme  
- **Abstrakte Klassen:** Teilweise implementiert, nicht instanziierbar  
- **Interfaces:** Verträge zwischen Komponenten  
- **JavaDoc:** Verständliche, automatisierte Dokumentation  

---

# 🧱 Datenkapselung & Information Hiding

**Dozent:** Roland Gisler  
**Modul:** OOP – HS25  

---

## 📋 Inhalt
- Datenkapselung  
- Zugriffsmodifizierer  
- Setter- und Getter-Methoden  
- Information Hiding  

---

## 🎯 Lernziele
- Konzept der **Datenkapselung** verstehen und anwenden  
- Wirkung von **Zugriffsmodifizierern** kennen  
- **Setter** und **Getter** korrekt einsetzen  
- **Information Hiding** im Designkontext verstehen  

---

## 🔒 Datenkapselung
- Kombination von **Daten (Attributen)** und **Verhalten (Methoden)** in einer Klasse  
- Ziel: **Lose Kopplung** und **hohe Kohäsion**  
- Zugriff auf Daten erfolgt **nur über Methoden**, nicht direkt  

**Problem:**  
Starke Kopplung = Klassen sind voneinander abhängig → schwer wartbar  

**Lösung:**  
Zugriffe kontrollieren mit **Zugriffsmodifizierern**  

---

## 🧩 Zugriffsmodifizierer
| Modifizierer | Sichtbarkeit | UML-Symbol | Beschreibung |
|---------------|---------------|-------------|---------------|
| `private` | nur in derselben Klasse | `-` | maximale Kapselung |
| *default* | innerhalb des Pakets | `~` | keine Angabe nötig |
| `protected` | Paket + Unterklassen | `#` | Vererbungssichtbar |
| `public` | überall | `+` | öffentlich |

---

## ⚙️ Setter & Getter
- Methoden für **kontrollierten Zugriff** auf Attribute  
- Getter → lesender Zugriff  
- Setter → schreibender Zugriff  

```java
public final class Person {
    private String name;
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
}
```

**Namenskonvention:**
- `getAttribut()` → Rückgabe des Werts  
- `setAttribut(...)` → Änderung des Werts  

**Best Practice:**
- Attribute `private` halten  
- Getter/Setter nur bei Bedarf  
- IDE zum Generieren verwenden  
- Methoden nach Möglichkeit `final` deklarieren  

---

## ⚠️ Hinweise zum Design
- Nur `private` + Getter/Setter ≠ gutes Design!  
- Gute Objektorientierung verlangt **bewusste Kapselung**
- Überlege, **welche Methoden nötig** sind und **welche nicht**  

---

## 🧠 Information Hiding
- Weiterentwicklung der Datenkapselung  
- Ziel: **Interne Implementierung verbergen**  
- Fokus auf **öffentliche Schnittstelle (Interface)**  

**Vorteile:**
- Lose Kopplung  
- Flexible interne Änderungen  
- Klare Trennung von „Innen“ und „Aussen“  

**Beispiel:**
- Interne Speicherung in `Kelvin`  
- Externe Schnittstelle arbeitet mit `Celsius` → intern bleibt verborgen  

---

## 💡 Empfehlungen
- Attribute möglichst `private`  
- Öffentliche Schnittstelle **schmal halten**  
- Parameter und Rückgabewerte so wählen, dass sie **keine internen Details preisgeben**  
- Falls nötig: Objekte **kopieren**, um interne Daten zu schützen  
- „Immutable Objects“ fördern Datensicherheit  

---

## 🧾 Zusammenfassung
| Konzept | Beschreibung |
|----------|---------------|
| **Datenkapselung** | Bündelung von Daten & Verhalten in einer Klasse |
| **Zugriffsmodifizierer** | Steuern Sichtbarkeit von Attributen und Methoden |
| **Setter/Getter** | Ermöglichen kontrollierte Zugriffe |
| **Information Hiding** | Entkopplung von interner Repräsentation und Schnittstelle |

**Ziel:**  
➡️ **Starke Kapselung, lose Kopplung, sauberes Design**

---
