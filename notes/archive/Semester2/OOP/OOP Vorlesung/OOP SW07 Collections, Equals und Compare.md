# 🧬 O07 – Vererbung

## 🎯 Lernziele
- Konzept der **Vererbung** verstehen.  
- Unterschied **Klassenvererbung vs. Interfacevererbung**.  
- **Einfachvererbung vs. Mehrfachvererbung**.  
- **Gute und schlechte Vererbungen** erkennen.  
- Methoden überschreiben & Basisklassen wiederverwenden.

---

## 🧩 Grundlagen

**Definition:**  
Unterklassen erben Attribute und Methoden von Oberklassen.  
→ *„Ein Apfel ist ein Obst“*, *„Ein Student ist eine Person“*.

### Begriffe
| Begriff | Synonyme |
|----------|-----------|
| Oberklasse | Basisklasse, Superklasse, Generalisierung |
| Unterklasse | Subklasse, Spezialisierung, Ableitung |

---

## 🌳 Vererbungshierarchie
- Mehrstufige Vererbung möglich (Basisklasse → Subklasse → weitere Spezialisierung).  
- Tiefe Hierarchien = **komplizierter, schwer wartbar**.  
- **Weniger ist oft besser!**

---

## 🧱 Vererbung bei Klassen

### Eigenschaften
- Schlüsselwort: `extends`  
- Java unterstützt **nur Einfachvererbung**.  
- Ohne explizite Angabe → automatische Vererbung von `Object`.  
- Alle Klassen sind letztlich `Object`.  

### Beispiel
```java
public class Unterklasse extends Oberklasse {
    ...
}
```

### Vorteile
- Wiederverwendung von Code  
- Polymorphie (Subtyping)  
- Strukturierte Hierarchien  

---

## 🚫 Mehrfachvererbung in Java
- **Nicht erlaubt** (führt zu Mehrdeutigkeiten).  
- Wird durch **Interfaces** ersetzt.  

---

## 🔒 Datenkapselung und Sichtbarkeit
| Modifikator | Sichtbar für Unterklassen |
|--------------|--------------------------|
| private | ❌ nein |
| (default) | ✔️ im selben Package |
| protected | ✔️ in allen Subklassen |
| public | ✔️ überall |

---

## ⚙️ Konstruktoren und `super`
- Konstruktoren werden **nicht automatisch geerbt**.  
- `super()` ruft Konstruktor der Oberklasse auf (muss an erster Stelle stehen).  

```java
public class Demo extends Base {
    public Demo() {
        super(); // ruft Basiskonstruktor auf
        ...
    }
}
```

---

## 🧮 Beispiel: Vererbung
### Abstrakte Basisklasse
```java
public abstract class Shape {
    private int x, y;

    protected Shape(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public final void move(final int newX, final int newY) {
        this.x = newX;
        this.y = newY;
    }

    public abstract int getPerimeter();
}
```

### Spezialisierung
```java
public final class Rectangle extends Shape {
    private int width, height;

    public Rectangle(final int x, final int y, final int width, final int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public int getPerimeter() {
        return (2 * this.width) + (2 * this.height);
    }
}
```

---

## 🧭 Vererbung bei Interfaces
- Syntax: `extends`  
- Mehrfachvererbung möglich (da keine Implementierungen).  

```java
public interface SubInterface extends BaseInterface1, BaseInterface2 {
    ...
}
```

---

## 💡 Einsatz und Empfehlungen

### Gute Vererbung
- Nur wenn „**is-a**“-Beziehung stimmt.  
- Basisklasse ist abstrakt.  
- Methoden in Unterklassen spezialisieren Verhalten, nicht ersetzen.  

### Schlechte Vererbung
- Nur wegen ähnlicher Attribute/Methoden.  
- Beispiel: *Kreis ist keine Ellipse!*  
- Besser: **Komposition statt Vererbung**  
  → *Ein Auto hat einen Motor (has-a)* statt *Ein Auto ist ein Motor*.

---

## 🔐 Vererbung kontrollieren
- Unerwünschte Spezialisierung verhindern:
```java
public final class Klasse { ... }
```
- Oder einzelne Methoden schützen:
```java
public final void foo() { ... }
```

---

## 📋 Zusammenfassung O07
- Vererbung = Wiederverwendung & Spezialisierung.  
- Starke Kopplung → sparsam einsetzen!  
- Java: Einfachvererbung (Klassen), Mehrfachvererbung (Interfaces).  
- Prüfen, ob Beziehung wirklich „is-a“ ist.  
- Falls nicht: **Komposition** verwenden.  
- Gezielte Nutzung von `abstract` und `final`.  

---

# 🧠 Gesamtfazit
- **O06**: Interfaces und Abstraktion fördern lose Kopplung.  
- **O07**: Vererbung nur, wenn logisch sinnvoll („is-a“).  
- Beide Konzepte = zentrale Säulen objektorientierter Programmierung.  
- Immer dokumentieren (JavaDoc) und bewusst designen.
