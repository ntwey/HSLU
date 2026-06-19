# ⚙️ E01 – Entwicklungsumgebung (IDE)

**Dozent:** Roland Gisler  
**Modul:** OOP – HS25  

---

## 📋 Inhalt
- Was ist eine IDE?  
- Geschichte und Entwicklung  
- Aktuelle IDEs für Java  
- Einsatz in OOP  
- IDE vs. BlueJ  
- Empfehlungen  

---

## 🎯 Lernziele
- Wissen, was eine **IDE (Integrated Development Environment)** ist  
- Wichtige Aufgaben und Komponenten einer IDE verstehen  
- Grundlegende Programmiertasks mit einer IDE durchführen  
- Projekt- & Verzeichnisstruktur für Java-Projekte kennen  

---

## 💡 Was ist eine IDE?
> Eine **IDE** ist eine integrierte Umgebung, die alle Werkzeuge für die Softwareentwicklung bündelt.

**Hauptfunktionen:**
- Quellcode-Editor & Navigation  
- Refactoring-Unterstützung  
- Projektorganisation & Konfiguration  
- Kompilierung, Ausführung, Debugging  
- Versionsverwaltung (z. B. Git)  
- Artefakt-Management & Buildsystem-Integration  
- Codegenerierung & Analyse  

---

## 🏗️ Entstehung & Entwicklung
- Früher: Editieren mit Editor → Kompilieren per Hand → Linken → Testen  
- Tools wurden später **integriert** → Geburt der IDE  
- Beispiel: *Borland Turbo Pascal* (Ende 1980er) – Editor + Compiler + Debugger  

---

## 🧰 Aktuelle IDEs für Java

| IDE | Typ | Hinweise |
|------|------|-----------|
| **Apache NetBeans** | Open Source | Einfach, klar, Maven-Integration, ideal für Einsteiger |
| **Eclipse JDT** | Open Source | Workspaces, stark erweiterbar, viele Plugins |
| **IntelliJ IDEA** | Kommerziell / Community | Effizient, Shortcut-orientiert, für erfahrene Nutzer |
| **Visual Studio Code** | Open Source | Multi-Language, pluginbasiert, universell einsetzbar |

---

### 🧱 Apache NetBeans
- Einfaches GUI, zentral gespeicherte Einstellungen  
- Perfekte Integration mit **Maven**  
- Empfohlen für **Einsteiger:innen**

### 🌒 Eclipse JDT
- Klassisches Menüsystem, Workspace-Konzept  
- Unterstützt Maven, viele Erweiterungen  
- Empfohlen für Nutzer:innen mit IDE-Erfahrung  

### ⚡ IntelliJ IDEA
- Sehr effizient, stark auf Tastaturbedienung optimiert  
- Konfiguration pro Projekt  
- Zielgruppe: Fortgeschrittene Entwickler:innen  

### 💻 Visual Studio Code
- Ursprünglich für C#, per Plugin auch für Java  
- Sehr aktives Plugin-Ökosystem  
- Gut integrierbar, aber weniger spezialisiert für Java  

---

## 🧩 Arbeiten mit der IDE im OOP-Modul
- Bereitgestelltes **Template-Projekt** mit Maven  
- Einheitliche **Projektstruktur:**

```
./
 ├── src/
 │   ├── main/java
 │   ├── main/resources
 │   ├── test/java
 │   └── test/resources
 ├── target/  (Build-Artefakte)
 └── .mvn/    (Maven-Konfiguration)
```

- Maven führt automatisierte Prozesse aus:
  - Kompilierung
  - Testausführung
  - Javadoc-Generierung
  - Codeanalyse & Coverage

---

## 🧱 Anwendung des Templates
1. ZIP entpacken & umbenennen  
2. In IDE importieren / öffnen  
3. Beispielcode entfernen & eigene Klassen hinzufügen  
4. Dateikodierung auf **UTF-8** setzen  
5. Details: *OOP_JavaDevelopmentManual_jdk21.pdf (ILIAS)*  

---

## ⚖️ IDE vs. BlueJ
| BlueJ | Entwicklungsumgebung (z. B. NetBeans, Eclipse) |
|--------|-----------------------------------------------|
| Lernwerkzeug | Professionelle Umgebung |
| Interaktiv & einfach | Automatisiert & strukturiert |
| Kleine Projekte | Für grosse, reale Softwareprojekte |
| Kein Maven | Maven-basiert |
| Einzelne Dateien | Definierte Projektstruktur |

➡️ Kein Ersatz, sondern **unterschiedliche Zielgruppen**  

---

## 🧠 Empfehlungen zu IDEs
- IDE **bewusst erlernen**, Shortcuts nutzen  
- **Nicht abhängig** von einer IDE machen  
- **Plugins** sparsam einsetzen  
- **Dark Mode** ≠ Auswahlkriterium 😉  
- KI-Funktionen vorerst **deaktivieren** – Lernprozess wichtiger!  
- IDE nach persönlicher Arbeitsweise auswählen  

---

## 🧩 Empfehlungen für Übungen
- Ein einziges Projekt für das Modul: `oop_exercises`
- Gliederung über Packages:
  - `ch.hslu.oop.sw01`, `ch.hslu.oop.sw02`, …
- Vorteile:
  - Übersichtlicher Code
  - Einfacher Vergleich zwischen Übungen
  - Weniger Overhead

---

## 🧾 Zusammenfassung
- IDE = Zentrale Arbeitsplattform für Entwicklung  
- Integriert Editieren, Kompilieren, Testen, Verwalten  
- Wichtige Java-IDEs: NetBeans, Eclipse, IntelliJ, VS Code  
- Effizienzsteigerung durch Maven & Shortcuts  
- OOP-Projekte → Maven-basiert mit Template  

---

# 🧬 O07 – Vererbung

**Dozent:** Roland Gisler  
**Modul:** OOP – HS25  

---

## 📋 Inhalt
- Einführung in Vererbung  
- Begriffe und Hierarchien  
- Vererbung bei Klassen und Interfaces  
- Konstruktoren & Sichtbarkeit  
- Empfehlungen & Best Practices  

---

## 🎯 Lernziele
- Konzept der **Vererbung** verstehen  
- Zwischen **Klassen-** und **Interface-Vererbung** unterscheiden  
- **Einfach- vs. Mehrfachvererbung** kennen  
- Gute von schlechter Vererbung unterscheiden  
- Methoden korrekt **überschreiben und wiederverwenden** können  

---

## 💡 Grundlagen
> Vererbung = Spezialisierung + Wiederverwendung  

- Eine **Unterklasse** erbt von einer **Oberklasse**  
- Unterklasse **erweitert** die Funktionalität der Oberklasse  
- Ziel: **Gemeinsame Eigenschaften** zentral implementieren  

**Synonyme:**
| Oberklasse | Unterklasse |
|-------------|-------------|
| Basisklasse / Superklasse | Subklasse / Spezialisierung |

---

## 🧱 Prinzipien der Vererbung
- Unterklasse erbt:
  - **Attribute**
  - **Methoden**
  - (indirekt) Konstruktoren  
- Unterklasse ist gleichzeitig **vom Typ der Oberklasse** (*Subtyping, Polymorphie*)  

**Beispiel:**  
- „Ein Apfel ist ein Kernobst.“  
- „Ein Student ist eine Person.“  

→ **is-a-Beziehung**: Unterklasse kann an Stelle der Oberklasse verwendet werden.  

---

## 🧩 Syntax (Java)
```java
public class Unterklasse extends Oberklasse {
   ...
}
```

- Schlüsselwort: `extends`  
- Nur **eine Oberklasse** möglich (Einfachvererbung)  
- Jede Klasse erbt **implizit von `Object`**  

---

## ⚙️ Sichtbarkeit in der Vererbung
| Modifizierer | Sichtbar in Unterklasse? |
|---------------|--------------------------|
| `private` | ❌ nein |
| *(default)* | ✅ nur im selben Package |
| `protected` | ✅ überall in Unterklassen |
| `public` | ✅ überall |

**Tipp:**  
Auch in Vererbung **Attribute privat halten**, bei Bedarf **protected-Getter** nutzen.  

---

## 🏗️ Konstruktoren
- Konstruktoren werden **nicht automatisch vererbt**
- Unterklassen können Konstruktoren der Oberklasse über `super()` aufrufen  

```java
public Rectangle(final int x, final int y, int width, int height) {
    super(x, y);
    this.width = width;
    this.height = height;
}
```
⚠️ `super()` **muss als erstes Statement** stehen!  

---

## 🧮 Beispiel: Shape-Hierarchie
```java
public abstract class Shape {
    private int x, y;

    protected Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract int getPerimeter();
}

public final class Rectangle extends Shape {
    private int width, height;

    public Rectangle(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public int getPerimeter() {
        return (2 * width) + (2 * height);
    }
}
```

---

## 🔗 Vererbung bei Interfaces
- Syntax ebenfalls mit `extends`  
- Ein Interface kann von **mehreren Interfaces** erben  
- Keine Implementationen → keine Mehrdeutigkeit  
- Signaturen müssen eindeutig bleiben  

```java
public interface SubInterface extends BaseInterface1, BaseInterface2 { }
```

---

## ⚠️ Mehrfachvererbung
- Java erlaubt **keine Mehrfachvererbung bei Klassen**
- Aber: **Mehrfachvererbung bei Interfaces** möglich  
- Vorteile:
  - Kein Konflikt durch Mehrdeutigkeit  
  - Vereinigt mehrere Rollen  

---

## 💡 Gute Vererbung
- Nur verwenden, wenn der Satz „X **ist ein** Y“ **sinnvoll** ist  
  - ✅ Student ist eine Person  
  - ❌ Kreis ist eine Ellipse  
- Im Zweifel: **Komposition bevorzugen**
  - → *Favor Composition over Inheritance (FCoI)*  
  - Beispiel: Ein Auto **hat** einen Motor (nicht „ist“ einer)  

---

## 🚫 Vererbung einschränken
- Klassen mit `final` → nicht weiter vererbbar  
- Methoden mit `final` → nicht überschreibbar  

```java
public final class ClassName { ... }

public final void foo() { ... }
```

---

## ✅ Empfehlungen
**Gute Vererbung:**
- Unterklasse kann **überall** Oberklasse ersetzen  
- Oberklasse ist abstrakt  
- Methoden der Unterklasse überschreiben **abstrakte oder leere Methoden**  

**Schlechte Vererbung:**
- Unterklasse **blockiert** Basismethoden  
- Unterklasse hängt vom **Laufzeittyp** ab  
- Unterklasse **verändert Verhalten** unvorhersehbar  

---

## 🧾 Zusammenfassung
| Konzept | Beschreibung |
|----------|---------------|
| **Vererbung** | Wiederverwendung & Spezialisierung von Klassen |
| **Einfachvererbung** | Eine Oberklasse pro Klasse |
| **Mehrfachvererbung** | Nur bei Interfaces erlaubt |
| **is-a** | Gültigkeitskriterium für Vererbung |
| **Composition over Inheritance** | Besser lose gekoppelte Designs |
| **final** | Vererbung explizit verhindern |

---

