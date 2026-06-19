# 🧮 DMATH-ALGO – Serie 05 Analyse von Algorithmen  
**Dozent:** Dr. Reto Berger  
**Hochschule Luzern – Informatik (HS 24)**  

---

## 🎯 Lernziele
- Worst-Case-Anzahl von Rechnungsschritten bestimmen  
- Asymptotisches Wachstum analysieren ( Big-O, Big-Ω, Big-Θ )  
- Wichtige Komplexitätsklassen kennen  

---

## 🔹 1 Warum werden Algorithmen analysiert?
Die Analyse betrachtet die **Wachstumsrate der Laufzeit oder des Speicherbedarfs**.  
Entscheidend ist das Verhalten für große Eingaben – Sonderfälle mit kleinen $n$ sind irrelevant.

**Beispielaufgabe:** Gegeben sei eine Funktion $f$ mit Eingabeliste der Länge $n$.  
Drei Algorithmen benötigen:

| Algorithmus | Rechnungsschritte |  
|:--|:--|  
| A | $n\log n$ |  
| B | $n^2$ |  
| C | $n!$ |  

Bei $10^9$ Schritten pro Sekunde und max. 60 Sekunden → $6\!\times\!10^{10}$ Schritte erlaubt.  
Damit:  
- A ≈ $n\log n ≤ 6·10^{10}$ → $n≈10^9$  
- B ≈ $n^2 ≤ 6·10^{10}$ → $n≈2.4·10^5$  
- C ≈ $n! ≤ 6·10^{10}$ → $n≈13$

---

## 🔹 2 Worst-Case Analyse
Ein Algorithmus kann je nach Eingabewerten mehr oder weniger Operationen ausführen.

**Definition:**  
Die Worst-Case-Analyse liefert die maximale Anzahl Rechnungsschritte in Abhängigkeit von $n$.

### Beispiel: Lineare Suche
```python
def linearSearch(L, element):
    for i in range(len(L)):
        if L[i] == element:
            return True
    return False
```
- Best Case: $1$ Vergleich  
- Worst Case: $n$ Vergleiche → $\mathcal O(n)$  

---

## 🔹 3 Sortieralgorithmen – Anzahl Rechnungsschritte

### Selection Sort
```python
def selectionSort(L):
    for i in range(len(L)):
        minIndex = i
        for j in range(i+1, len(L)):
            if L[j] < L[minIndex]:
                minIndex = j
        L[i], L[minIndex] = L[minIndex], L[i]
```
- Best Case ≈ $\tfrac{1}{2}n^2$ Operationen  
- Worst Case ≈ $\tfrac{1}{2}n^2$ → $\mathcal O(n^2)$  

---

### Insertion Sort
```python
def insertionSort(L):
    for i in range(1, len(L)):
        j = i
        while j > 0 and L[j] < L[j-1]:
            L[j], L[j-1] = L[j-1], L[j]
            j -= 1
```
- Best Case (sortierte Liste) → $\mathcal O(n)$  
- Worst Case (umgekehrt sortiert) → $\mathcal O(n^2)$  

---

### Bubble Sort
```python
def bubbleSort(L):
    for i in range(len(L)):
        for j in range(len(L)-1):
            if L[j] > L[j+1]:
                L[j], L[j+1] = L[j+1], L[j]
```
- Best Case (keine Vertauschung) → $\mathcal O(n)$  
- Worst Case → $\mathcal O(n^2)$  

---

### Binary Search
```python
def binarySearch(L, element):
    left, right = 0, len(L) - 1
    while left <= right:
        middle = (left + right)//2
        if L[middle] == element:
            return True
        elif L[middle] > element:
            right = middle - 1
        else:
            left = middle + 1
    return False
```
- Best Case → $1$ Vergleich ($\mathcal O(1)$)  
- Worst Case → $\lfloor\log_2 n\rfloor$ → $\mathcal O(\log n)$  

---

## 🔹 4 Asymptotische Analyse

**Warum?**  
Exakte Anzahl von Schritten ist selten wichtig – entscheidend ist das Wachstumsverhalten für große $n$.  

### Big-O, Big-Ω und Big-Θ
Seien $f$ und $g$ zwei Funktionen der Eingabegröße $x$:

- $f(x)\in \mathcal O(g(x))$ ⇔ $f(x)\le C·g(x)$ für $x≥x_0$  
 → **nicht schneller wachsend** als $g$  
- $f(x)\in \Omega(g(x))$ ⇔ $f(x)\ge c·g(x)$ für $x≥x_0$  
 → **nicht langsamer wachsend** als $g$  
- $f(x)\in \Theta(g(x))$ ⇔ $f(x)\in \mathcal O(g(x)) ∧ f(x)\in \Omega(g(x))$  
 → **asymptotisch gleich wachsend**

---

## 🔹 5 Beispiele für Asymptotik

a) $f(x)=4x^3+15x^2−10x−8 ∈ \mathcal O(x^3)$  
b) $f(x)=4x^3+15x^2−10x−8 ∈ \Omega(x^3)$  
c) $f(x)=5x^2−2x+8 ∈ \Theta(x^2)$  
d) $f(x)=5x\log_2(32x)+3x^2 ∈ \mathcal O(x^2\log x)$  

Begründung: Für hinreichend großes $x$ dominieren die höchsten Termen.

---

## 🔹 6 Wichtige Komplexitätsklassen

$$
\mathcal O(1) < \mathcal O(\log n) < \mathcal O(n) < \mathcal O(n\log n) < \mathcal O(n^2) < \mathcal O(n^3) < \ldots < \mathcal O(2^n)
$$

| Klasse | Beispiel | Wachstum |
|:--|:--|:--|
| $\mathcal O(1)$ | Direkter Speicherzugriff | konstant |
| $\mathcal O(\log n)$ | Binäre Suche | logarithmisch |
| $\mathcal O(n)$ | Lineare Suche | linear |
| $\mathcal O(n\log n)$ | Mergesort | quasi-linear |
| $\mathcal O(n^2)$ | Bubble-, Insertion-, Selection-Sort | quadratisch |
| $\mathcal O(n^3)$ | Matrixmultiplikation (naiv) | kubisch |
| $\mathcal O(2^n)$ | SAT-Problem | exponentiell |

**Interpretation:**  
Polynomielle Algorithmen → „praktisch lösbar“ ( real lösbar ).  
Exponentielle Algorithmen → „praktisch unlösbar“ für große $n$.  

---

## 🧾 Zusammenfassung

| Thema | Kerngedanke |
|:--|:--|
| Analyse von Algorithmen | Zählt Rechnungsschritte und bewertet Wachstum |
| Worst-Case | maximale Anzahl Operationen |
| Best-Case | minimale Anzahl Operationen |
| Asymptotik | Vergleich des Wachstums für großes $n$ |
| Big-O | obere Schranke |
| Big-Ω | untere Schranke |
| Big-Θ | exaktes asymptotisches Wachstum |
| Komplexitätsklassen | $\mathcal O(1)$ bis $\mathcal O(2^n)$ |
| Beispiele | Binary Search $\mathcal O(\log n)$, Mergesort $\mathcal O(n\log n)$ |

---

**Fazit:**  
Die Analyse von Algorithmen ermöglicht den Vergleich ihrer Effizienz unabhängig von Hardware und Implementierung.  
Sie liefert ein mathematisches Werkzeug, um die praktische Machbarkeit von Problemen in der Informatik einzuschätzen.
