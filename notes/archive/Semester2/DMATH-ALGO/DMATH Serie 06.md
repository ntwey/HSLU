# 🧮 DMATH-ALGO – Serie 06 Rekursive Algorithmen  
**Dozent:** Dr. Reto Berger • **HSLU Informatik (HS 24)**  

---

## 🎯 Lernziele
- Funktionsweise rekursiver Algorithmen verstehen und **Rekursionsbäume** zeichnen.  
- **Divide-and-Conquer-Algorithmen** mit Big-O abschätzen.  
- **Lineare Rekursionsbeziehungen** mit konstanten Koeffizienten auflösen.  

---

## 🔹 1 Was ist ein rekursiver Algorithmus?
Ein Algorithmus, der sich **selbst aufruft**, um kleinere Instanzen eines Problems zu lösen.

**Eigenschaften von divide-and-conquer Algorithmen**
1. Kleine Inputs ($n=1$) → direkt lösbar.  
2. Große Inputs → in Teile zerlegen.  
3. Teillösungen kombinieren → Gesamtergebnis.

**Beispiele (Serie 6 Aufgabe 1):**

```python
def summe(n):
    if n == 1:
        return 1
    else:
        return n + summe(n - 1)

def fakultaet(n):
    if n == 1:
        return 1
    else:
        return n * fakultaet(n - 1)
```

**Rekursionsbaum Beispiel für $n=5$**

```
summe(5)
 ├─5+summe(4)
 │   ├─4+summe(3)
 │   │   ├─3+summe(2)
 │   │   │   ├─2+summe(1)=1
 │   │   └─…
 →15
```

---

## 🔹 2 Beispiel: mergeSort (John von Neumann, 1945)

```python
def mergeSort(A):
    if len(A) == 1:
        return A
    mid = len(A)//2
    L = mergeSort(A[:mid])
    R = mergeSort(A[mid:])
    return merge(L, R)
```

**Prinzip:** Liste teilen → Teil sortieren → zusammenführen.  
Rekursionsbaum für $n=8$ zeigt Teilungen bis zur Einzelliste.  

---

## 🔹 3 Türme von Hanoi (Edouard Lucas, 1883)
**Ziel:** Alle Scheiben vom linken auf rechten Pfosten verschieben.  
Regeln:
1. Nur eine Scheibe pro Zug.  
2. Keine größere Scheibe auf kleinerer.  

**Algorithmus (Pseudocode):**
```python
def hanoi(n, start, ziel, hilf):
    if n == 1:
        print(f"Bewege Scheibe von {start} nach {ziel}")
    else:
        hanoi(n-1, start, hilf, ziel)
        print(f"Bewege Scheibe von {start} nach {ziel}")
        hanoi(n-1, hilf, ziel, start)
```

---

## 🔹 4 Analyse rekursiver Algorithmen
Laufzeit $T(n)$ ergibt sich meist selbst rekursiv.  

**Beispiel Summe:**
$$
T(n)=T(n-1)+1,\quad T(1)=1
$$
$$
\Rightarrow T(n)=n \implies \mathcal{O}(n)
$$

**Beispiel Fakultät:**
$$
T(n)=T(n-1)+1,\quad T(1)=1 \Rightarrow \mathcal{O}(n)
$$

---

## 🔹 5 Komplexitätsabschätzungen (verschiedene Formen)
Typische rekursive Beziehungen:

| Beziehung | Lösung (Big-O) |
|:--|:--|
| $T(n)=2T(\tfrac{n}{2})+n$ | $\mathcal{O}(n\log n)$ |
| $T(n)=2T(\tfrac{n}{2})+1$ | $\mathcal{O}(n)$ |
| $T(n)=2T(\tfrac{n}{2})+n^2$ | $\mathcal{O}(n^2)$ |

Zur Bestimmung wird ein **Rekursionsbaum** oder das **Master-Theorem** verwendet.

---

## 🔹 6 Rekursionsbaum-Beispiel für mergeSort
$$
T(n)=2T\!\left(\frac{n}{2}\right)+n
$$
Ebene 1: $n$ Schritte für Merge  
Ebene 2: $2\times \frac{n}{2}=n$  
…  
$\log_2(n)$ Ebenen → $T(n)=n\log n$

→ **mergeSort ist $\mathcal{O}(n\log n)$**

---

## 🔹 7 Master-Theorem
Allgemein:
$$
T(n)=a\,T\!\left(\frac{n}{b}\right)+c\,n^k,\quad T(1)=c_0
$$

| Bedingung | Resultat |
|:--|:--|
| $b^k<a$ | $\mathcal{O}(n^{\log_b a})$ |
| $b^k=a$ | $\mathcal{O}(n^k\log n)$ |
| $b^k>a$ | $\mathcal{O}(n^k)$ |

**Beispiele**
1. $T(n)=4T(\tfrac{n}{3})+n^2$ → $b^k=3^2=9>a=4$ ⇒ $\mathcal{O}(n^2)$  
2. $T(n)=2T(\tfrac{n}{3})+n$ → $b^k=3^1=3>a=2$ ⇒ $\mathcal{O}(n)$  
3. $T(n)=4T(\tfrac{n}{2})+1$ → $b^k=2^0=1<a=4$ ⇒ $\mathcal{O}(n^{\log_2 4})=\mathcal{O}(n^2)$  

---

## 🔹 8 Lineare Rekursionsbeziehungen
**Definition**
$$
T(n)=c_1T(n-1)+c_2T(n-2)+\dots+c_kT(n-k)+F(n)
$$

- **homogen**, wenn $F(n)=0$  
- **inhomogen**, wenn $F(n)\neq0$

**Satz 1 (Homogen):**
- Grad 1: $T(n)=a\,r^n$  
- Grad 2: Finde $r_1,r_2$ aus $r^2=c_1r+c_2$

$$
T(n)=
\begin{cases}
a r_1^n+b r_2^n,& r_1\neq r_2\\[3pt]
(a+bn)r^n,& r_1=r_2
\end{cases}
$$

**Satz 2 (Inhomogen):**
$$
T(n)=T_h(n)+T_p(n)
$$
mit $T_h$ = homogene Lösung, $T_p$ = partikuläre Lösung.

---

## 🔹 9 Beispiele (Serie 6 Aufgabe 8)

| Nr | Rekursionsbeziehung | Lösung |
|:--|:--|:--|
| a | $T(n)=5T(n-1),\,T(0)=4$ | $T(n)=4·5^n$ |
| b | $T(n)=6T(n-1)-9T(n-2)$ | $T(n)=a·3^n+b·3^n=(a+bn)3^n$ |
| c | $T(n)=T(n-1)+2T(n-2)$ | $r^2=r+2→r=2,-1⇒T(n)=a·2^n+b·(-1)^n$ |
| d | $T(n)=3T(n-1)+2n$ | $T_h(n)=a·3^n,\ T_p(n)=-n-½$ → $T(n)=a·3^n-n-½$ |
| e | $T(n)=5T(n-1)-6T(n-2)$ | $r^2-5r+6=0→r_1=2,r_2=3⇒T(n)=a·2^n+b·3^n$ |

---

## 🧾 Zusammenfassung
| Thema | Kernpunkt |
|:--|:--|
| Rekursive Algorithmen | rufen sich selbst auf → Teilprobleme lösen |
| Divide & Conquer | Zerlege Problem → kombiniere Teillösungen |
| Rekursionsbaum | visualisiert rekursive Aufrufe |
| Master-Theorem | bestimmt asymptotische Komplexität |
| Homogene Rekursion | nur abhängig von früheren Werten |
| Inhomogene Rekursion | enthält Zusatzterm $F(n)$ |
| mergeSort | $\mathcal{O}(n\log n)$ |
| Summe/Fakultät | $\mathcal{O}(n)$ |

---

**Fazit:**  
Rekursive Algorithmen sind mächtig und klar strukturiert.  
Ihre Laufzeit lässt sich über Rekursionsbäume und das Master-Theorem systematisch abschätzen.  
Lineare Rekursionsbeziehungen bilden eine wichtige Brücke zwischen Algorithmus- und Mathematikanalyse.
