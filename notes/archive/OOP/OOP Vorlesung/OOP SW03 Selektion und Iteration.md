# 🧠  Selektion (Kontrollfluss in Java)

**Dozent:** Roland Gisler  
**Modul:** OOP – HS25  

---

## 📋 Inhalt
- if-then-else  
- Bedingungen & bool’sche Operatoren  
- Logische Operatoren (UND, ODER, NICHT, XOR)  
- Empfehlungen zu if  
- else-if & verschachtelte Bedingungen  
- switch-Statement & switch-Expressions  
- Zusammenfassung  

---

## 🎯 Lernziele
- Bedingungen und boolesche Ausdrücke formulieren  
- if-, else-if- und switch-Statements kennen  
- switch-Expressions (seit Java 14) verstehen  
- Geeignete Selektionsform für jede Situation wählen können  

---

## ⚙️ Grundlagen
- **Selektion (Auswahl):** Steuerung des Programmflusses durch Bedingungen  
- Beispiel:
```java
if (divisor != 0) {
   quotient = dividend / divisor;
} else {
   System.out.println("Fehler: Division durch Null!");
}
```

- Bedingung → `boolean` Ergebnis (`true` oder `false`)
- else-Block ist **optional**, aber geschweifte Klammern `{}` immer empfohlen  

---

## 🔢 Vergleichsoperatoren
| Operator | Bedeutung |
|-----------|------------|
| `<` | kleiner als |
| `<=` | kleiner oder gleich |
| `==` | gleich |
| `!=` | ungleich |
| `>` | grösser als |
| `>=` | grösser oder gleich |

---

## 🧠 Logische Operatoren
| Operator | Bedeutung | Beschreibung |
|-----------|------------|---------------|
| `&&` | UND | Beide Bedingungen müssen wahr sein |
| `||` | ODER | Mindestens eine Bedingung wahr |
| `^` | XOR | Genau eine Bedingung wahr |
| `!` | NICHT | Negiert das Ergebnis |

**Optimierung (Short-Circuit Evaluation):**  
- `&&`: zweiter Ausdruck wird **nicht** geprüft, wenn der erste `false` ist.  
- `||`: zweiter Ausdruck wird **nicht** geprüft, wenn der erste `true` ist.

---

## 🧩 Gesetz von De Morgan
Mathematische Umformungen für logische Ausdrücke:

```java
!(a && b) == (!a || !b)
!(a || b) == (!a && !b)
```

**Ziel:** Bedingungen vereinfachen & besser lesbar machen.

**Beispiel:**
```java
boolean valid = (value >= 1) && (value <= 6);
```
statt:
```java
boolean valid = !((value < 1) || (value > 6));
```

---

## ✅ Empfehlungen zu if-Statements
1. Immer `{}` verwenden, auch bei einem Statement.  
2. Bedingungen klar & lesbar formulieren.  
3. Häufigen Fall im **then-Block**, seltenen im **else-Block**.  
4. Leere Blöcke vermeiden.  
5. Boolean-Werte direkt prüfen:
```java
if (flag) {...}   // statt if (flag == true)
```
6. Ausdrücke direkt zurückgeben:
```java
return (x < 3);
```
statt
```java
if (x < 3) return true; else return false;
```

---

## ⚠️ Verschachtelte ifs vermeiden
Schlecht lesbarer Code:
```java
if (x > 5) {
  doA();
} else {
  if (x > 4) {
    doB();
  } else {
    if (x > 3) {
      doC();
    }
  }
}
```

Besser:
- in eigene Methode auslagern oder
- **else-if-Kette** verwenden:
```java
if (x > 5) {
  doA();
} else if (x > 4) {
  doB();
} else if (x > 3) {
  doC();
}
```

---

## 🔀 switch-Statement
- Für **einfache Fallunterscheidungen** (nur auf konkrete Werte)
- Unterstützte Typen:
  - `byte`, `short`, `char`, `int`
  - `String`, `enum`

**Beispiel:**
```java
switch (value) {
  case 1: day = "Montag"; break;
  case 2: day = "Dienstag"; break;
  default: day = "Unerlaubte Zahl";
}
```

⚠️ `break` nicht vergessen, sonst „fall through“!

---

## ⚙️ switch mit fall-through
```java
switch (value) {
  case 1, 2, 3, 4, 5:
    type = "Arbeitstag";
    break;
  case 6, 7:
    type = "Wochenende";
    break;
  default:
    type = "Unerlaubte Tagnummer";
}
```

---

## 🆕 switch-Expressions (Java 14+)
- Kürzere Syntax, kein `break` nötig  
- Direktes Ergebnis (Expression mit Rückgabewert)

**Beispiel:**
```java
String type = switch (value) {
  case 1, 2, 3, 4, 5 -> "Arbeitstag";
  case 6, 7          -> "Wochenende";
  default            -> "Unerlaubte Tagnummer";
};
```

Mit Codeblock + `yield`:
```java
String type = switch (value) {
  case 1, 2, 3, 4, 5 -> {
     String name = "Arbeitstag(" + value + ")";
     yield name;
  }
  case 6, 7 -> "Wochenende";
  default   -> "Unerlaubte Tagnummer";
};
```

---

## 💡 Empfehlungen
- if/else: flexibel, aber auf Lesbarkeit achten  
- else-if: kompakt, aber nur bei unabhängigen Bedingungen  
- switch: sparsam einsetzen → oft besser durch Objektorientierung (Design Patterns) ersetzen  
- switch-Expressions: moderne, klare Alternative  

---

## 🧾 Zusammenfassung
- **if:** einfachste Selektion, else optional  
- **else-if:** mehrere Alternativen, weniger Einrückung  
- **switch:** mehrere Werte, aber eingeschränkt  
- **switch-Expression:** moderne, sichere Alternative  
- Bedingungen einfach & lesbar halten  

---

# 🔁 Iteration (Schleifen in Java)

**Dozent:** Roland Gisler  
**Modul:** OOP – HS25  

---

## 📋 Inhalt
- while-, do-while-, for- und foreach-Schleifen  
- Schleifenbedingungen  
- Abbruchkriterien  
- Empfehlungen & Best Practices  

---

## 🎯 Lernziele
- Verschiedene Schleifentypen kennen  
- Passenden Typ auswählen und korrekt implementieren  
- Sichere und robuste Abbruchbedingungen formulieren  

---

## 🧩 Einführung
- Wiederholte Ausführung von Anweisungen → **Iteration**
- Typische Anwendungsfälle:
  - Daten einlesen / bearbeiten
  - Wiederkehrende Aufgaben
- Schleifen können:
  - Eintrittstest haben (`while`, `for`)
  - Austrittstest haben (`do-while`)

---

## 🔁 while-Schleife
**Syntax:**
```java
while (<expression>) {
   <statements>
}
```
- Eintrittstest: Bedingung wird **vor** dem ersten Durchlauf geprüft.  
- Wird Bedingung sofort `false` → kein Durchlauf.  

**Beispiel:**
```java
int input = scan.nextInt();
int sum = 0;
while (input != 0) {
   sum += input;
   input = scan.nextInt();
}
```

---

## 🔄 do-while-Schleife
**Syntax:**
```java
do {
   <statements>
} while (<expression>);
```
- Austrittstest: Bedingung wird **nach** Ausführung geprüft.  
- Schleifenkörper läuft **mindestens einmal**.

**Beispiel:**
```java
int dice1, dice2, dice3, tries = 0;
do {
   dice1 = (int)(Math.random() * 6) + 1;
   dice2 = (int)(Math.random() * 6) + 1;
   dice3 = (int)(Math.random() * 6) + 1;
   tries++;
} while ((dice1 != 6) || (dice2 != 6) || (dice3 != 6));
```

---

## 🔢 for-Schleife
**Syntax:**
```java
for (<initialisierung>; <bedingung>; <änderung>) {
   <statements>
}
```

**Beispiel:**
```java
for (int i = 1; i <= 10; i++) {
   System.out.println("Wert: " + i);
}
```
- Ideal für **zählende Schleifen**
- Variable `i` existiert nur innerhalb der Schleife

---

## 🔂 foreach-Schleife
**Kurzform für Sammlungen & Arrays:**
```java
for (final Temperatur t : list) {
   t.doSomething();
}
```
- Seit Java 5  
- Nur **syntaktische Vereinfachung**, kein eigener Typ  

---

## ⚙️ Schleifenbedingungen
- Robust formulieren!  
- Endlosschleifen vermeiden  
- Keine absoluten Werte prüfen (`x == 10`), besser Bereiche (`x >= 10`)  
- Bei Gleitkommazahlen auf Rundungsfehler achten  

---

## ⚠️ break & continue
- `break`: beendet Schleife sofort  
- `continue`: springt an Schleifenende → prüft Bedingung erneut  
- Meist vermeidbar mit `if`-Strukturen  
→ sparsamer Einsatz, da ähnlich wie `goto`

---

## ✅ Empfehlungen
- Alle Schleifen sind **gleich mächtig** → Auswahl nach Lesbarkeit  
- Immer `{}` verwenden, auch bei einem Statement  
- Abbruchbedingungen **robust** formulieren  
- Methodenaufrufe im Schleifenkörper → Laufzeit beachten  
- Keine „if-Schleife“ – das gibt’s nicht 😉

---

## 🧾 Zusammenfassung
| Schleife | Typ | Einsatz |
|-----------|------|----------|
| `while` | Eintrittstest | Unbekannte Iterationszahl |
| `do-while` | Austrittstest | Mind. ein Durchlauf |
| `for` | Eintrittstest | Zählende Schleifen |
| `foreach` | syntaktische Variante | Arrays, Collections |

**Zusätzlich:**  
- Schleifen robust und effizient schreiben  
- `break` / `continue` vermeiden, wenn möglich  

---
