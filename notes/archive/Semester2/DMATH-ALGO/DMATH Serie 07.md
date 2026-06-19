# 🧮 DMATH-ALGO – Serie 07 Mathematische Induktion  
**Dozent:** Dr. Reto Berger • **HSLU Informatik (HS 24)**  

---

## 🎯 Lernziele
- Prinzip der **mathematischen Induktion** verstehen.  
- Unterschied zwischen **schwacher** und **starker Induktion** kennen.  
- Behauptungen und Algorithmen **induktiv beweisen** können.  

Die mathematische Induktion ist eine Beweistechnik, die analog zur Rekursion arbeitet:  
Ein Beweis für $n$ baut auf dem Beweis für $n-1$ auf.  
In der Informatik wird sie oft zur Analyse und Korrektheitsprüfung von Algorithmen verwendet.  

---

## 🔹 1 Schwache Induktion
Ziel: Für ein Prädikat $P(n)$ mit $n∈\mathbb N$ zeigen, dass $∀n≥n_0:\,P(n)$ wahr ist.  

### Vorgehen
1. **Induktionsverankerung**:  
   Zeige $P(n_0)$ ist wahr.  
2. **Induktionsschritt**:  
   Zeige $∀n≥n_0:\,P(n)\Rightarrow P(n+1)$ ist wahr.  

Dann gilt $P(n)$ für alle $n≥n_0$.  

---

## 🔹 2 Beispiele mit Induktionsbeweis

### Aufgabe 1 – Arithmetische Summe  
$$s+(s+d)+(s+2d)+⋯+(s+(n-1)d)$$  
Algorithmus:
```python
def summe(s, n, d):
    return n*s + n*(n-1)/2*d
```
**Induktionsverankerung:** $n=1$ → $P(1)$ gilt.  
**Induktionsschritt:** Zeige $P(n)\Rightarrow P(n+1)$ durch Einsetzen und algebraisches Umformen.  

---

### Aufgabe 2 – Geometrische Summe  
$$s + s q + s q^2 + ⋯ + s q^{n-1} = s \frac{q^n - 1}{q - 1}$$  
Algorithmus:
```python
def summe(s, n, q):
    return s*(q**n - 1)/(q - 1)
```
Beweis wie oben → Formel gilt für alle $n≥1$.  

---

### Aufgabe 3 – Summe der Quadratzahlen  
$$1^2 + 2^2 + 3^2 + ⋯ + n^2 = \frac{n(n+1)(2n+1)}{6}$$  
Algorithmus:
```python
def summQuad(n):
    return n*(n+1)*(2*n+1)/6
```
Induktiv über $n$ → Formel bleibt erfüllt.  

---

### Aufgabe 4 – Summe ungerader Zahlen  
$$1 + 3 + 5 + ⋯ + (2n - 1) = n^2$$  
Algorithmus:
```python
def summe(n):
    return n**2
```
Induktiv über $n$:  
$n^2 + (2n+1) = (n+1)^2$ ⇒ Behauptung gilt für alle $n$.  

---

### Aufgabe 5 – Nicht terminierende while-Schleife  
```python
n = 0
while (8**n - 3**n) % 5 == 0:
    print("Hallo Welt!")
    n += 1
```
**Behauptung:** Schleife terminiert nicht.  
**Beweis (Induktion):**  
Für $n=0$ gilt $(8^0-3^0)%5=0$.  
Annahme für $n$ → gilt auch für $n+1$ wegen Kongruenzeigenschaft $(8^{n+1}-3^{n+1})\equiv0 (mod 5)$.  

---

### Aufgabe 6 – Zehnerreste von $9^n$  
```python
n = 0
while 9**n % 10 == 1 or 9**n % 10 == 9:
    print("Hallo Welt!")
    n += 1
```
Reste wechseln zwischen 1 und 9 ⇒ Bedingung bleibt immer wahr ⇒ nicht terminierend.  

---

### Aufgabe 7 – Zehnerreste von $2^n \bmod 47$  
```python
n = 0
while 2**n % 47 in [1, 2, 4]:
    print("Hallo Welt!")
    n += 1
```
Da $2^n \bmod 47$ zyklisch die Werte {1, 2, 4, 8, 16, 32,…} liefert und 1 wieder auftaucht → unendliche Schleife.  

---

### Aufgabe 8 – Parkettierung mit L-Fliesen  
Ein Algorithmus `findeUeberdeckung(n)` soll eine Parkettierung für ein $2^n×2^n$-Brett finden,  
bei der genau ein Feld frei bleibt.  

**Beweis (skizziert):**  
- Für $n=1$ existiert eine L-Fliese.  
- Für $n+1$: Teile das Brett in 4 Teile vom Typ $2^n×2^n$, setze eine L-Fliese in die Mitte,  
 jede Teilfläche hat jetzt ein freies Feld → Induktionsannahme anwenden.  

---

### Aufgabe 9 (optional) – Springerweg  
Algorithmus `findeWeg(n)` findet einen Springerweg für $n≥5$.  
**Beweisidee:** Für $n=5$ existiert eine Lösung (bekannt). Füge Ränder hinzu und nutze den Pfad rekursiv weiter.  

---

## 🔹 3 Starke Induktion
Beim Induktionsschritt darf man alle bisher bewiesenen Fälle verwenden:  
$$
∀n≥n_0:\,[P(n_0)∧P(n_0+1)∧…∧P(n)]⇒P(n+1)
$$

### Aufgabe 10 – Münzwechselproblem  
Für $n>4$ Franken gibt es immer $a$ Zweifränkler und $b$ Fünffränkler ($a,b≥0$) mit $2a+5b=n$.  
**Beweis (stark induktiv):**  
- Basis: $n=4,5,6,7,8,9$ direkt lösbar.  
- Schritt: Aus Lösungen für $n−1,…,n−5$ konstruiere eine für $n$.  

---

### Aufgabe 11 – Korrektheit von QuickSort  
```python
import random as rd
def quickSort(A):
    if len(A) <= 1:
        return A
    pivot = rd.randint(0, len(A)-1)
    L, R = [], []
    for i in range(len(A)):
        if i != pivot:
            if A[i] < A[pivot]:
                L.append(A[i])
            else:
                R.append(A[i])
    L = quickSort(L)
    R = quickSort(R)
    return L + [A[pivot]] + R
```
**Induktionsbeweis (stark):**
- **IA:** Listen der Länge 0 oder 1 sind sortiert.  
- **IS:** Angenommen alle Teillisten mit $<n$ Elementen werden korrekt sortiert.  
 Dann werden L und R rekursiv korrekt sortiert → Konkatenation liefert sortierte Gesamtliste.  

---

## 🧾 Zusammenfassung
| Thema | Aussage |
|:--|:--|
| Schwache Induktion | $P(n_0)$ gilt und $P(n)\Rightarrow P(n+1)$ ⇒ $∀n≥n_0:\,P(n)$ |
| Starke Induktion | Nutzt alle bisher bewiesenen Fälle |
| Arithm./Geom. Summen | Formeln durch Induktion bewiesen |
| Nicht terminierende Schleifen | Induktiv gezeigt, dass Bedingung immer wahr bleibt |
| L-Parkettierung | Konstruktion rekursiv erweiterbar |
| QuickSort | Korrektheit durch starke Induktion |
| Anwendung | Beweisprinzip für Algorithmen und mathematische Formeln |

---

**Fazit:**  
Die mathematische Induktion ist das wichtigste Werkzeug zum Nachweis der Korrektheit rekursiver Algorithmen.  
Sie verbindet formale Beweise mit algorithmischem Denken – jede Rekursion hat eine analoge Induktion.
