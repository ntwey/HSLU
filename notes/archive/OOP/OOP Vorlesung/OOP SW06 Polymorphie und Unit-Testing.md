# 💻 Objektorientierte Programmierung – O06 
## Hochschule Luzern – HS25 – Roland Gisler

---

# 🧩 O06 – Schnittstellen (Interfaces)

## 🎯 Lernziele
- Verständnis von **Abstraktion** und **Modularisierung**.  
- **Abstrakte Klassen** entwerfen und nutzen.  
- **Interfaces** definieren und implementieren.  
- **JavaDoc** schreiben.

---

## 🔍 Abstraktion
**Definition:** Vereinfachung bzw. Reduktion der Realität auf das im jeweiligen Kontext Notwendige.

- *Innenperspektive:* Fokus auf Attribute (Zustand) & Methoden (Verhalten).  
- *Außenperspektive:* Fokus auf Schnittstellen (Nutzendensicht).  

Abstraktion trennt **WAS** (Schnittstelle) vom **WIE** (Implementierung).

---

## ⚙️ Modularisierung
**Definition:** Zerlegung einer Gesamtaufgabe in unabhängige, wiederverwendbare Teilaufgaben.  
→ „Divide et impera“ – Teile und herrsche.

**Ziele:**
- Komplexität reduzieren  
- Klar definierte Schnittstellen  
- Unabhängige, wiederverwendbare Module  
- Prinzipien: *Separation of Concerns (SoC)*, *Single Responsibility Principle (SRP)*  

**Manifestation:**
- Klassen (niedrige Ebene)  
- Module, Libraries, Komponenten (mittlere Ebene)  
- Services / Microservices (hohe Ebene)

---

## 🏗️ Abstrakte Klassen

### Eigenschaften
- Schlüsselwort: `abstract`  
- Können **nicht instanziiert** werden.  
- Definieren Methoden (WAS), aber keine Implementierung (WIE).  
- Abstrakte Methoden machen die Klasse automatisch abstrakt.  
- Mischung aus abstrakten und konkreten Methoden möglich.

### Varianten
1. Mindestens eine abstrakte Methode → Klasse wird implizit abstrakt.  
2. Klasse explizit abstrakt → gemischte Methoden erlaubt.

### Beispiel 1 – Vollständig abstrakt
```java
package ch.hslu.oop.oop06;

/**
 * Abstrakte Klasse für einen Schalter.
 */
public abstract class AbstractSwitch {
    public abstract void switchOn();
    public abstract void switchOff();
    public abstract boolean isSwitchedOn();
    public abstract boolean isSwitchedOff();
}
```

### Beispiel 2 – Teilweise implementiert
```java
public abstract class AbstractSwitchVariant {
    private boolean switchedOn = false;

    public abstract void switchOn();
    public abstract void switchOff();

    public final boolean isSwitchedOn() {
        return this.switchedOn;
    }

    public final boolean isSwitchedOff() {
        return !this.switchedOn;
    }

    protected final void setSwitchedOn(final boolean switchedOn) {
        this.switchedOn = switchedOn;
    }
}
```

### Empfehlungen
- Vorsichtiger Einsatz – nur bei klarer Notwendigkeit.  
- Wenn vollständig abstrakt → besser **Interface** nutzen.  
- Verhindern der Instanziierung ohne Vererbung → `final` + privater Konstruktor.  

---

## 🔗 Interfaces in Java

### Eigenschaften
- Schlüsselwort: `interface`  
- Vollständig abstrakt (keine Instanziierung).  
- Enthalten nur Methodenköpfe (implizit `public abstract`).  
- Kein Konstruktor.  
- Definieren Typen (Rollen).  
- Klassen implementieren Interfaces mit `implements`.

### Beispiel – Interface
```java
/**
 * Schnittstellen NIE ohne Dokumentation!
 */
public interface Switchable {
    void switchOn();
    void switchOff();
    boolean isSwitchedOn();
    boolean isSwitchedOff();
}
```

### Beispiel – Implementierung
```java
/**
 * Modell einer Lampe mit Lichtstrom.
 */
public final class Lampe implements Switchable {
    private int lumen = 0;
    private static final int MAX_LUMEN = 800;

    @Override
    public void switchOn() {
        if (this.isSwitchedOff()) {
            this.lumen = MAX_LUMEN;
        }
    }

    @Override
    public void switchOff() {
        this.lumen = 0;
    }

    @Override
    public boolean isSwitchedOn() {
        return this.lumen > 0;
    }

    @Override
    public boolean isSwitchedOff() {
        return !this.isSwitchedOn();
    }
}
```

### Vorteile
- **Lose Kopplung**
- **Polymorphie** – verschiedene Klassen können dieselbe Rolle erfüllen.  
- Mehrfachimplementierung möglich (1..n Interfaces).  
- Trennung von *Spezifikation (WAS)* und *Implementierung (WIE)*.  

### Namenskonventionen
- `-able` (z. B. `Iterable`, `Serializable`)  
- `Interface`-Suffix (`PrintInterface`)  
- Reiner Name (`Iterator`, `Order`)  
- `I`-Prefix (z. B. `IShape`, C#-Stil)  

### Empfehlung
- Lieber **ein Interface zu viel** als zu wenig.  
- Ermöglicht flexible Austauschbarkeit von Implementierungen.  
- Konzept: *Design by Interfaces*.

---

## 📝 JavaDoc

### Zweck
- Offizielle API-Dokumentation (z. B. [docs.oracle.com](https://docs.oracle.com/en/java/javase/21/docs/api/index.html)).  
- Dokumentiert Packages, Interfaces, Klassen, Methoden, Attribute.  
- Besonders wichtig für öffentliche Elemente & Interfaces.

### Beispiel
```java
/**
 * Returns a string representation of the object.
 * @return a string representation of the object.
 */
public String toString() {
    return getClass().getName() + "@" + Integer.toHexString(hashCode());
}
```

### Regeln
- Kurze, prägnante Kommentare.  
- „**Erster Satz mit Punkt**“-Regel.  
- Kein unnötiges HTML.  
- Dokumentieren, was Nutzer verstehen müssen.  

---

## 🧠 Zusammenfassung O06
- Abstraktion & Modularisierung = Grundpfeiler der OO-Entwicklung.  
- Abstrakte Klassen → verstärkte Abstraktion (teilweise implementiert).  
- Interfaces → reine Rollenbeschreibung.  
- Implementation via `implements`.  
- JavaDoc = essentielle Dokumentation.  

---