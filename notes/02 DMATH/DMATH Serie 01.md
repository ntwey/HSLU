# 🧮 DMATH-ALGO – Serie 01 Elementare Datentypen
**Dozent:** Dr. Reto Berger  
**Hochschule Luzern – Informatik**

---

## 🎯 Lernziele
- Rechnen mit `boolean`, `integer`, `float`, `set`, `list`, `matrix`
- Umwandlung in Binärdarstellung  
- IEEE 754 kennen  
- Funktionsbegriff verstehen

---

## 🔹 1. Boolean
Ein boolescher Wert ist entweder `True` oder `False`.  
Nach George Boole (1815 – 1864): $1 ⇒ \text{True}$, $0 ⇒ \text{False}$.

| Operator | Bedeutung | Beispiel |
|:--|:--|:--|
| `not` | Negation | `not False = True` |
| `and` | Und | `True and False = False` |
| `or` | Oder | `True or False = True` |

**Priorität:** `not` → `and` → `or`

Beispiele:  
a) `False or True and False and True or False` → **True**  
b) `False and True or False and not True or False` → **False**

---

## 🔹 2. Integer
Ganze Zahlen: $\mathbb Z = \{…, -3, -2, -1, 0, 1, 2, 3,…\}$

| Symbol | Beschreibung | Beispiel |
|:--|:--|:--|
| `+` | Addition | $4+3=7$ |
| `-` | Subtraktion | $7-2=5$ |
| `*` | Multiplikation | $3*2=6$ |
| `//` | Ganzzahldivision | $25//7=3$ |
| `%` | Rest (Modulo) | $25\%7=4$ |

**Binärdarstellung (25)**  
$$
\begin{align*}
25&=12\cdot2+1\\
12&=6\cdot2+0\\
6&=3\cdot2+0\\
3&=1\cdot2+1\\
1&=0\cdot2+1
\end{align*}
$$  
Rückwärts → $(25)_2=11001$

---

## 🔹 3. Float
Fließkommazahlen haben endliche Nachkommastellen.  

Beispiele: $0.625 ⇒ (0.101)_2$, $0.3 ⇒ (0.01001\overline{0011})_2$

**IEEE 754-Standard** – Rundungsfehler:  
```python
0.7 + 0.1 == 0.9 - 0.1  # False
```  
Darstellung: $(-1)^0·1.001_2·2^{-3}$

---

## 🔹 4. Mengen · Listen · Matrizen

### Mengen
Ungeordnet und ohne Duplikate: $A=\{0,1,2,…,9\}$  

| Operation | Ergebnis |
|:--|:--|
| $\cup$ | Vereinigung |
| $\cap$ | Schnitt |
| $\setminus$ | Differenz |

Potenzmenge $\mathcal P(\{a,b,c,d\}) = 2^4 = 16$

### Listen
Geordnet, Wiederholungen möglich: $a=(5,3,8,8,1)$  
$(1,3,-1,0,1.5)+(1,2,2,1,0.5)=(2,5,1,1,2)$  
Broadcasting: $3-(2,5,4,-2)=(1,-2,-1,5)$

### Matrizen
$$
A=\begin{pmatrix}5&3&8&8&1\\0&0&9&2&9\end{pmatrix}
$$  
Dimension $2×5$ · Addition komponentenweise

---

## 🔹 5. Funktionen
$f:D→W$, $f(x)=y$

Beispiel:  
```python
def decToBin(x):
    y=''
    while x:
        y=('1' if x%2 else '0')+y
        x//=2
    return y
```

---

## 🧾 Zusammenfassung
| Typ | Beschreibung | Beispiel |
|:--|:--|:--|
| boolean | Wahr/Falsch | `True` |
| integer | Ganzzahl | `42` |
| float | Kommazahl | `3.14` |
| set | Menge | `{1,2,3}` |
| list | Liste | `[1,2,3]` |
| matrix | 2D-Liste | `[[1,2],[3,4]]` |
| function | Zuordnung | `f(x)=x²` |

---

# 🧩 DMATH-ALGO – Serie 02 Aussagenlogik
**Dozent:** Dr. Reto Berger · HSLU Informatik  

---

## 🎯 Lernziele
- Aussagenlogische Terme analysieren  
- Mit Gesetzen vereinfachen  
- *erfüllbar*, *allgemeingültig*, *unerfüllbar* kennen  
- SAT-Problem und NP-Komplexität verstehen  

---

## 🔹 1. Aussagen
Eine Aussage ist ein Satz mit Wahrheitswert. $1 ⇒ \text{wahr}$, $0 ⇒ \text{falsch}$  

---

## 🔹 2. Operatoren
| Symbol | Name | Bedeutung |
|:--|:--|:--|
| ¬A | Negation | nicht A |
| A∧B | Konjunktion | A und B |
| A∨B | Disjunktion | A oder B |
| A⊕B | exklusiv oder | entweder A oder B |
| A⇒B | Implikation | wenn A dann B |
| A⇔B | Äquivalenz | genau dann wenn B |

---

## 🔹 3. Wahrheitswerte
- **Erfüllbar:** mind. eine Belegung wahr  
- **Tautologie:** immer wahr  
- **Kontradiktion:** nie wahr  

---

## 🔹 4. Gesetze
| Gesetz | Formel |
|:--|:--|
| Doppelnegation | ¬(¬A)≡A |
| Idempotenz | A∨A≡A ; A∧A≡A |
| Dominanz | A∨T≡T ; A∧F≡F |
| Identität | A∨F≡A ; A∧T≡A |
| Invers | A∨¬A≡T ; A∧¬A≡F |
| Kommutativ | A∨B≡B∨A ; A∧B≡B∧A |
| Assoziativ | (A∨B)∨C≡A∨(B∨C) |
| Absorption | A∨(A∧B)≡A |
| Distributiv | A∨(B∧C)≡(A∨B)∧(A∨C) |
| De Morgan | ¬(A∨B)≡¬A∧¬B ; ¬(A∧B)≡¬A∨¬B |

---

## 🔹 5. Beispiel Vereinfachung
$$
((¬(P∨¬Q))∧(P⇒Q))⇒P
$$  
1. $P⇒Q≡¬P∨Q$ 
2. ¬(P∨¬Q)≡¬P∧Q  
3. $(¬P∧Q)∧(¬P∨Q)≡¬P∧Q$ 4. $(¬P∧Q)⇒P≡T$  
→ **Allgemeingültig**

---

## 🔹 6. SAT-Problem
$n$ Variablen → $2^n$ Zeilen.  
SAT = Prüfe Erfüllbarkeit eines Terms.  
NP-vollständig.

---

## 🧾 Zusammenfassung
| Begriff | Bedeutung |
|:--|:--|
| Aussage | wahr/falsch entscheidbar |
| Term | logischer Ausdruck |
| Interpretation | Belegung von Werten |
| Tautologie | immer wahr |
| Kontradiktion | immer falsch |
| Erfüllbar | mind. eine Belegung wahr |
| SAT | Erfüllbarkeitsproblem |

---

**Fazit:** Aussagenlogik bildet die Grundlage formaler Logik und Verifikation in der Informatik.
