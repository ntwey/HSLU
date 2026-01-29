# 🧮 DMATH-ALGO – Serie 09 Komplexere Zähltechniken  
**Dozent:** Dr. Reto Berger • **HSLU Informatik (HS 24)**  

---

## 🎯 Lernziele
- **Inklusions-Exklusions-Prinzip** anwenden.  
- **Rekursive Zählmethoden** verstehen und Rekursionsbeziehungen aufstellen.  
- **Erzeugende Funktionen** nutzen, um Zählprobleme algebraisch zu lösen.  

Auch scheinbar einfache Zählprobleme können komplex sein – z. B. das Zählen von Datenmustern oder Primzahlen.  
Diese Serie behandelt drei erweiterte Zähltechniken, die für Informatik relevant sind:  

1. **Inklusion und Exklusion** – Verallgemeinerung der Summenregel  
2. **Divide & Conquer** – Rekursive Zählmodelle  
3. **Erzeugende Funktionen** – Algebraische Modellierung von Zählproblemen  

---

## 🔹 1 Prinzip von Inklusion und Exklusion

Wenn $S = S_1 ∪ S_2 ∪ ⋯ ∪ S_n$, dann gilt die Summenregel **nur für disjunkte Mengen**.  
Bei Überlappungen muss man Überschneidungen berücksichtigen.

### Beispiel – Ungültige PINs
4-stelliger PIN, der **nicht** mit drei gleichen Ziffern **beginnt oder endet**.

$$
S_1 = \{x : \text{PIN beginnt mit drei gleichen Ziffern}\}\\
S_2 = \{x : \text{PIN endet mit drei gleichen Ziffern}\}
$$

Nach der Produktregel gilt:  
$|S_1| = 10·10 = 100$, $|S_2| = 100$  
Die 10 PINs {0000, 1111, …, 9999} sind doppelt gezählt.

$$
|S| = |S_1| + |S_2| - |S_1∩S_2| = 100 + 100 - 10 = 190
$$

---

### Allgemeines Inklusions-Exklusions-Prinzip
Für drei Mengen:
$$
|S_1∪S_2∪S_3| = |S_1|+|S_2|+|S_3| - |S_1∩S_2| - |S_1∩S_3| - |S_2∩S_3| + |S_1∩S_2∩S_3|
$$

Für $n$ Mengen:
$$
|⋃_{i=1}^n S_i| = \sum |S_i| - \sum |S_i∩S_j| + \sum |S_i∩S_j∩S_k| - ⋯
$$

---

### Algorithmisches Vorgehen
1. Bestimme $|S_i|$ aller Mengen.  
2. Addiere alle $|S_i|$ → **Summe**.  
3. Subtrahiere Überschneidungen einer geraden Anzahl Mengen → **Exklusion**.  
4. Addiere Überschneidungen einer ungeraden Anzahl Mengen → **Inklusion**.  
5. Ergebnis:  
$$|S| = \text{Summe} + \text{Inklusion} - \text{Exklusion}.$$

---

### Programmbeispiel
```python
n = 1
while n <= 1000:
    if n%2 == 0 or n%3 == 0 or n%5 == 0:
        doSomething()
    n += 1
```
Zähle Zahlen $\le 1000$, die durch 2, 3 oder 5 teilbar sind.  
Mit Inklusion-Exklusion:

$$
|S| = |S_2| + |S_3| + |S_5| - |S_2∩S_3| - |S_2∩S_5| - |S_3∩S_5| + |S_2∩S_3∩S_5|
$$

Ergebnis: **733** Zahlen.

---

### Beispiel: Zahlen bis 100000 teilbar durch 2,3,5,7

$$
|S| = 50000 + 33333 + 20000 + 14285 - 16666 - 10000 - 7142 - 4761 - 2857 - 2000 + 2380 + 1428 + 952 + 666 + 476 - 446 = 74257
$$

---

### Beispiel: Vertauschungen des Wortes „COMPUTER“  
Gesucht: Anzahl Permutationen, bei denen **mindestens ein Buchstabe am richtigen Platz** steht.  

Mit Inklusion-Exklusion:
$$
|S| = 8! - \binom{8}{1}7! + \binom{8}{2}6! - ⋯ + (-1)^8 0! = 25487
$$

---

### Beispiel: 7 Druckaufträge auf 5 Drucker, mind. ein Drucker frei
$$
|S| = 5^7 - \binom{5}{1}4^7 + \binom{5}{2}3^7 - \binom{5}{3}2^7 + \binom{5}{4}1^7 = 16807 - 5·16384 + 10·2187 - 10·128 + 5·1 = 1680
$$

---

## 🔹 2 Rekursive Zählverfahren (Divide & Conquer)

Zählprobleme lassen sich als Rekursionen modellieren:  
Definiere $s(n)$ als Anzahl der Elemente für Größe $n$.  

### Beispiel 1 – Binärworte ohne zwei aufeinanderfolgende 0
$$
s(n) = s(n-1) + s(n-2), \quad s(1)=2, s(2)=3
$$
→ Fibonacci-Folge: $s(n)=F_{n+2}$

---

### Beispiel 2 – Passwörter mit **gerader Anzahl Nullen**
$$
s(n) = 9·s(n-1) + 1·s(n-2)
$$
Ergebnis: $s(n)=0.5(10^n + 8^n)$ gültige Passwörter.

---

### Beispiel 3 – Binärworte ohne drei aufeinanderfolgende 0
$$
s(n)=s(n-1)+s(n-2)+s(n-3), \quad s(1)=2, s(2)=4, s(3)=7
$$

---

### Beispiel 4 – Binärworte, die das Muster 01 enthalten
$$
s(n)=2^n - s(n-1) - s(n-2)
$$

---

### Beispiel 5 – Treppensteigen (1 oder 2 Schritte)
$$
s(n)=s(n-1)+s(n-2), \quad s(1)=1, s(2)=2
$$
→ ebenfalls Fibonacci-Folge.

---

## 🔹 3 Zählen mit erzeugenden Funktionen

Komplexe Zählprobleme können in lineare Gleichungen überführt werden.  
Eine **erzeugende Funktion** beschreibt alle möglichen Kombinationen von Summen.

### Beispiel 1
Wie viele Lösungen hat  
$$
x_1+x_2+x_3=11,\\
x_1∈\{2,3,4\},\ x_2∈\{3,4\},\ x_3∈\{4,5\}
$$

Erzeugende Funktion:
$$
(x^2+x^3+x^4)(x^3+x^4)(x^4+x^5)
$$
Der Koeffizient vor $x^{11}$ = **4 Lösungen.**

---

### Vorgehen mit erzeugenden Funktionen
1. Beschreibe die Bedingung als lineare Gleichung.  
2. Schreibe für jede Variable eine erzeugende Teilfunktion.  
3. Multipliziere sie → Produktfunktion.  
4. Fasse gleiche Potenzen zusammen.  
5. Der **Koeffizient** des Potenzterms gibt die Anzahl der Lösungen an.

---

### Beispiel 2 – 4 verschiedenfarbige Würfel (Augensumme = 13)
Erzeugende Funktion:
$$
(x+x^2+x^3+x^4+x^5+x^6)^4
$$
Koeffizient von $x^{13}$ → **140 Kombinationen.**

---

### Beispiel 3 – 8 gleiche Jobs auf 3 Server (je 2–4 Jobs)
$$
(x^2+x^3+x^4)^3
$$
Koeffizient von $x^8$ → **6 Möglichkeiten.**

---

### Beispiel 4 – Geldautomat mit 1-, 2- und 5-Fr.-Münzen

**a) Betrag 15 Fr. mit beliebiger Reihenfolge**  
Erzeugende Funktion:
$$
(1+x+x^2+⋯)(1+x^2+x^4+⋯)(1+x^5+x^{10}+⋯)
$$
Koeffizient von $x^{15}$ → **26 Kombinationen.**

**b) Münzen nacheinander einwerfen**  
Permutation der gleichen Mengen → **48 Reihenfolgen.**

---

## 🧾 Zusammenfassung

| Methode | Prinzip | Beispiel |
|:--|:--|:--|
| Inklusion-Exklusion | Korrigiert doppelte Zählungen | Ungültige PINs, Druckerproblem |
| Rekursion | Divide & Conquer zur Zählung | Binärworte ohne 00 / 000 |
| Erzeugende Funktion | Algebraisches Modell für Summen | Würfel, Münzen, Server |

---

**Fazit:**  
Die drei erweiterten Zähltechniken – **Inklusion/Exklusion**, **Rekursion** und **Erzeugende Funktionen** –  
ermöglichen die Lösung von Zählproblemen, die über einfache Summen- oder Produktregeln hinausgehen.  
Sie sind zentrale Werkzeuge in der **Algorithmik**, **Kombinatorik** und **Wahrscheinlichkeitstheorie**.
