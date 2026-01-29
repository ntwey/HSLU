# 🧠 O01 – Objektorientierung (OOP)

**Dozent:** Roland Gisler  
**Modul:** OOP – HS25  
**Version:** 1.6.0  

---

## 📋 Inhalt
- Was ist Objektorientierung?
- Objekte und Klassen
- Klassen: Zustand und Verhalten
- Quellcode in Java
- Was ist ein Programm?
- Kurze Live-Demo
- Übung
- Zusammenfassung

---

## 🎯 Lernziele
- Erstes Verständnis von *Objektorientierung*  
- Wissen, was **Klassen** und **Objekte** sind  
- Objekte identifizieren und einfache Klassen entwerfen  
- Zwischen **Zustand** und **Verhalten** unterscheiden können  
- Verschiedene **Abstraktionsebenen** verstehen  
- Wissen, was **Quellcode** und ein **Programm** ist  

---

## 💡 Objektorientierung (OO)
- Entstanden **Ende der 1960er Jahre**
- Unterschied zur **prozeduralen Programmierung**:  
  → Daten und Funktionen werden in **Objekten** zusammengefasst  
- Frühe OO-Sprache: **Smalltalk**  
- Populär seit **1985** durch **C++**, später **Java (1996)**  
- Zentrale Elemente:
  - **Objekte**
  - **Klassen**
- Weiterführend: Modul *PCP – Programming Concepts & Paradigms*

---

## 🧱 Objekte und Klassen

### In der Realität
- **Objekte:** Repräsentieren reale oder abstrakte Dinge  
  - Beispiel konkret: *„Der rote Wagen im Parkhaus“*  
  - Beispiel abstrakt: *„Das Telefongespräch mit Frau Müller am 24.08.2024“*  
- **Klassen:** Baupläne für Objekte  
  - Beispiel: *„Bauplan für einen Wagen“*  
  - Beispiel: *„Kontaktprotokoll-Formular“*  

---

### Interaktion
- In der realen Welt: Objekte interagieren durch Kommunikation (Rauch, Licht, Schall etc.)
- In der OOP: Objekte interagieren durch **Methodenaufrufe**
  - verändern ihren **Zustand**
  - beeinflussen sich gegenseitig

---

### Modellierung
- Reale Objekte → als **Software-Objekte** modelliert (vereinfacht, abstrahiert)
- Vorteile:
  - Durchgängige objektorientierte Sichtweise
- Nachteile:
  - Vereinfachungen und Abstraktionen können sehr stark sein

---

### Programmierung
- Entwickler:innen programmieren **Klassen**, nicht einzelne Objekte  
- Aus einer Klasse können viele Objekte erzeugt werden  
- Objekte entstehen **zur Laufzeit**

---

### Beispiel: Kuchen backen
- **Klasse** = Rezept  
- **Objekte** = Kuchen  
- Aus einem Rezept (Klasse) können viele Kuchen (Objekte) gebacken werden  

---

### Zusammengefasst
- Wir **programmieren Klassen**
- Objekte werden **zur Laufzeit** erzeugt  
- Objekte interagieren miteinander und lösen Aufgaben (Algorithmen)

---

## 🧩 Klassen

- Eine **Klasse** kann mehrere **Instanzen (Objekte)** erzeugen  
- Definiert:
  - **Attribute** (Zustand)
  - **Methoden** (Verhalten)
- Jedes Objekt hat eigene Attributwerte  
- Herausforderung: sinnvolle Kombination von Attributen & Methoden → **Kapselung**

---

## ⚙️ Attribute, Methoden & Parameter
- **Attribute** speichern den Zustand (haben Datentypen)
- **Methoden** definieren das Verhalten
  - Können **Parameter** annehmen
  - Können **Rückgabewerte** liefern

---

## 🔵 Zustand eines Objekts (State)
- Beispiel: *Circle-Objekt* in BlueJ  
- Attribute repräsentieren Position, Durchmesser, Farbe etc.  
- Jedes Objekt hat seinen individuellen Zustand  

---

## 🧭 Verhalten eines Objekts (Behavior)
- Methoden verändern den Zustand
  - Beispiel: `moveUp()` verschiebt den Kreis nach oben  
- Methodenaufrufe = Interaktion

---

## 💻 Quellcode in Java

### Grundlagen
- Jede Klasse hat eine eigene **.java-Datei**  
- Beispiel: `Circle.java`  
- Enthält:
  - **Attribute** (Zustand)
  - **Methoden** (Verhalten)
- Dokumentation im **JavaDoc-Format** möglich  
- Kompiliert zu **Bytecode (.class)**, der von der **JVM** ausgeführt wird  

---

### Beispielcode `Circle.java` (Ausschnitt)
```java
public class Circle {
    private int diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

    public Circle() {
        diameter = 68;
        ...
    }

    public void makeVisible() {
        isVisible = true;
        draw();
    }

    public void makeInvisible() {
        erase();
        isVisible = false;
    }
}
```

---

## 🧠 Was ist ein Programm?
- Eine Folge von **Anweisungen**, die einem Computer sagen, was zu tun ist  
- Programme setzen **Algorithmen** um  
- Geschrieben in einer **formalen Sprache** (z. B. Java)  
  - Präzise für Maschinen  
  - Verständlich für Menschen  
- Ziel: **Plattformunabhängigkeit**

---

## ⚙️ Kompilation von Java-Programmen
- `.java` → **Kompiliert** mit `javac` → `.class`  
- Compiler überprüft Syntax  
- `.class` enthält **Bytecode** für die **Java Virtual Machine (JVM)**  

---

## 🚀 Ausführung von Java-Programmen
- JVM interpretiert und führt Bytecode aus  
- **Just-in-time (JIT) Compiler** übersetzt Bytecode in Maschinencode zur Laufzeit  
- JVM ist plattformunabhängig  
- Bytecode kann aus verschiedenen Sprachen stammen (z. B. Java, Kotlin, Scala, Groovy)

---

## 🧪 Live Demo
- Praktische Demonstration der OOP-Konzepte in Java / BlueJ

---

## 🧱 Übung: Parkhaus
- Aufgabe:
  - Finden Sie Objekte und identifizieren Sie passende Klassen  
  - Ziel: Programm zur Verwaltung von Parkplätzen in einem Parkhaus  

---

## 🧾 Zusammenfassung
- **Klassen** = Abstraktionen, Baupläne  
  - Beispiel: Rezept, Bauplan  
- **Objekte** = konkrete Instanzen  
  - Beispiel: Kuchen, Haus, Kreis  
- Klassen enthalten:
  - **Attribute** → Zustand  
  - **Methoden** → Verhalten  
- **Quellcode** beschreibt Klassen in einer Programmiersprache (z. B. Java)

---

## ❓ Fragen?
*(Ende der Präsentation)*
