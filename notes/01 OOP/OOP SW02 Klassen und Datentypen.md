# 🧠 O02 – Klassen

**Dozent:** Roland Gisler  
**Modul:** OOP – HS25  

---

## 📋 Inhalt
- Aufbau einer Klasse  
- Attribute  
- Methoden  
- Parameter  
- Rückgabewert  
- Lokale Variablen  
- Konstruktoren  

---

## 🎯 Lernziele
- Verständnis der zentralen Bestandteile einer Klasse: **Attribute**, **Konstruktoren**, **Methoden**
- Unterschied zwischen **Instanzvariablen**, **lokalen Variablen** und **Parametern**
- Unterschied zwischen **Implementierung** und **Aufruf** einer Methode
- Unterschied zwischen **formalen** und **aktuellen Parametern**
- Eine einfache Klasse schematisch implementieren können

---

## 🧱 Aufbau einer Klasse
```java
package ch.hslu.oop;

public final class Balloon {
   ...
}
```

**Struktur:**
1. Packagedeklaration  
2. JavaDoc-Kommentar (optional)  
3. Schlüsselwörter (`public`, `final`, etc.)  
4. Klassenname (CamelCase)  
5. Klassenrumpf (Attribute, Konstruktoren, Methoden)

**Namenskonvention:**
- Package: klein geschrieben, mit Punkten getrennt (`ch.hslu.oop.ex1`)
- Klasse: Grossbuchstaben am Anfang, keine Unterstriche
- Datei: `Person.java`, abgelegt unter `ch/hslu/oop/ex1/`

---

## 🧩 Klassenrumpf
```java
public final class Balloon {
    // 1. Attribute
    // 2. Konstruktoren
    // 3. Methoden
}
```

- **Attribute:** Zustand eines Objekts  
- **Konstruktoren:** Initialisierung  
- **Methoden:** Verhalten

---

## 🧾 Attribute
```java
private int distance;
private int altitude;
private int radius;
private String color;
```

**Merkmale:**
- Speichern den Zustand eines Objekts  
- Jedes Objekt hat eigene Attributwerte  
- Sichtbar in allen Methoden der Klasse  
- Existieren solange das Objekt existiert (auf dem Heap)  
- Sollte explizit initialisiert werden  

**Automatische Initialisierung:**
| Typ | Defaultwert |
|------|--------------|
| `int`, `short`, `byte` | 0 |
| `long` | 0L |
| `float` | 0.0f |
| `double` | 0.0d |
| `char` | '\u0000' |
| `boolean` | false |
| Referenzen | null |

---

## ⚙️ Methoden
- Fundamentales Konzept der Programmierung  
- Realisieren das **Verhalten von Objekten**
- Können **Parameter** und **Rückgabewerte** haben  
- Methode = Anfrage an ein Objekt → liefert Antwort (Rückgabewert)

```java
public void setLocation(int pos, int alt) {
   this.position = pos;
   this.altitude = alt;
}
```

**Methodenkopf:** beschreibt *was*  
**Methodenrumpf:** beschreibt *wie*  

---

## 📥 Parameter
- **Formale Parameter**: Variablen im Methodenkopf  
- **Aktuelle Parameter**: Werte beim Methodenaufruf  

```java
public void setLocation(final int pos, final int alt) {
   this.position = pos;
   this.altitude = alt;
}
balloon.setLocation(2, 10);
```

**Merkmale:**
- Übergabe erfolgt immer per **Wertkopie** (*call by value*)  
- Sichtbar nur innerhalb der Methode  
- Speicherort: **Stack**

---

## 🔁 Rückgabewert
```java
public int getDiameter() {
    int diameter = 2 * this.radius;
    return diameter;
}
```

- Methode mit Rückgabewert: gibt Wert mit `return` zurück  
- Methode ohne Rückgabewert: `void`, `return` optional  
- `return` beendet Methode sofort  

---

## 🧮 Lokale Variablen
```java
public final double getVolume() {
   final double kubik;
   kubik = Math.pow(this.radius, 3.0);
   return (4.0 / 3.0 * Math.PI * kubik);
}
```
- Nur innerhalb eines **Blocks** sichtbar  
- Müssen **explizit initialisiert** werden  
- Existieren nur während der Ausführung (auf dem Stack)

---

## 🏗️ Konstruktoren
```java
public Balloon(final String balloonColor) {
   this.distance = 0;
   this.altitude = 0;
   this.radius = 5;
   this.color = balloonColor;
}
```

**Eigenschaften:**
- Kein Rückgabewert  
- Name = Klassenname  
- Wird beim `new`-Operator automatisch aufgerufen  
- Initialisiert Attribute  
- Mehrere Konstruktoren möglich (mit unterschiedlicher Signatur)

```java
Balloon myBalloon = new Balloon("rot");
```

**Kein Destruktor in Java:**  
→ Speicherverwaltung durch Garbage Collector

---

## 🧾 Zusammenfassung
- Klassen bestehen aus **Attributen**, **Konstruktoren**, **Methoden**
- Methoden: mit/ohne Parameter, mit/ohne Rückgabewert  
- **Signatur** = Methodenkopf ohne Returntyp  
- **Formale vs. Aktuelle Parameter**
- **Lokale Variablen** im Methodenrumpf  
- **Zugriffsmodifizierer** (`private`, `public`) steuern Sichtbarkeit  
- **Finale Parameter** = unveränderbar

---

# 🧠 O03 – Datentypen, Operatoren & Typumwandlungen

**Dozent:** Roland Gisler  
**Modul:** OOP – HS25  

---

## 📋 Inhalt
- Elementare Datentypen  
- Operatoren  
- Typumwandlungen (Casting)  

---

## 🎯 Lernziele
- Die 8 **elementaren Datentypen** kennen  
- Unterschied zwischen **Wertebereich** und **Genauigkeit** verstehen  
- Wirkung von Operatoren kennen  
- **Implizite** und **explizite Typumwandlungen** anwenden  

---

## 🔢 Elementare Datentypen
- Beschreiben **Wertebereich**, **Genauigkeit** und mögliche **Operationen**  
- Enthalten **einen einzigen Wert** (keine Objekte)
- Effizient, platzsparend, keine Methoden

**Primitive Datentypen in Java:**
| Typ | Größe | Wertebereich |
|------|-------|---------------|
| `byte` | 8 Bit | -128 … +127 |
| `short` | 16 Bit | -32’768 … +32’767 |
| `int` | 32 Bit | -2’147’483’648 … +2’147’483’647 |
| `long` | 64 Bit | -9.22e18 … +9.22e18 |
| `float` | 32 Bit | ±3.4E+38 (~7 Stellen) |
| `double` | 64 Bit | ±1.8E+308 (~14 Stellen) |
| `boolean` | 8 Bit | true / false |
| `char` | 16 Bit | UTF-16 Zeichen |

---

## ⚖️ Wertebereich vs. Genauigkeit
- Ganzzahlen: Jeder Wert exakt abbildbar  
- Gleitkommazahlen: Nur begrenzte **relevante Stellen**  
- Beispiel: `2’000’000.05` → in `float` **nicht exakt darstellbar**  
- Für Geldbeträge: besser keine `float`/`double` verwenden!

---

## ➕ Einfache Operatoren
| Operator | Bedeutung |
|-----------|------------|
| `+` | Addition oder Vorzeichen |
| `-` | Subtraktion oder Vorzeichen |
| `*` | Multiplikation |
| `/` | Division |
| `=` | Zuweisung (von rechts nach links) |

**Beispiele:**
```java
int summe = 128 + 132;
String gericht = "Nude" + "lauf" + "lauf"; // Konkatenation
```

---

## 🔄 Operatoren sind polymorph
Der `+`-Operator hat je nach Kontext verschiedene Bedeutungen:
- `+100` → Vorzeichen  
- `100 + 200` → Addition  
- `"abc" + "def"` → String-Verkettung  

---

## 🔁 Typumwandlungen (Casting)
- Konvertierung eines Werts von einem Typ in einen anderen  
- **Implizit:** automatisch durch Java  
- **Explizit:** durch `(Typ)` angegeben

**Beispiele:**
```java
long wert = 100;           // implizit
long wert2 = (long) 100.7; // explizit
```

---

## 📐 Regeln für implizites Casting
| von | nach |
|------|------|
| byte | short, int, long, float, double |
| char, short | int, long, float, double |
| int | long, float, double |
| long | float, double |
| float | double |
| alle | String (bei Konkatenation) |

⚠️ Gefahr: **Genauigkeitsverlust** bei automatischen Castings

---

## ✖️ Beispiel: Ganzzahldivision
```java
int i1 = 5;
int i2 = 2;
float f = i1 / i2; // Ergebnis: 2.0f
```

**Erklärung:**
- Ganzzahldivision → 2  
- Danach implizit in `float` konvertiert  

**Korrekt:**
```java
float f = (float) i1 / i2;     // oder
float f = i1 / (float) i2;
```

---

## ⚠️ Empfehlungen
- Datentypen bewusst wählen  
- Implizite Castings: Genauigkeitsverlust prüfen  
- Explizite Castings: Resultate kritisch hinterfragen  
- Immer mit realistischen Wertebereichen testen

---

## 🧾 Zusammenfassung
- Primitive Datentypen speichern reine Werte (keine Objekte)
- Datentyp bestimmt:
  - Wertebereich
  - Genauigkeit
  - erlaubte Operationen
- Operatoren verhalten sich **polymorph**
- Typumwandlungen:
  - **implizit** = automatisch
  - **explizit** = `(Typ)`  
- Achtung bei Berechnungen – mögliche **Rundungs- und Genauigkeitsfehler**

---
