# 🧮 DMATH-ALGO – Serie 03: Prädikatenlogik
**Dozent:** Dr. Reto Berger  
**Hochschule Luzern – Informatik (HS 24)**  

---

## 🎯 Lernziele
- Wahrheitswerte von Prädikaten bestimmen  
- All- und Existenzquantor verstehen  
- Aussagen mit verschachtelten Quantoren richtig auswerten  
- Negationen korrekt hinter Quantoren umschreiben  

Die Aussagenlogik (Serie 2) beschreibt boolesche Ausdrücke.  
Die **Prädikatenlogik** erweitert sie durch Variablen, um genauere Aussagen zu formulieren.  

---

## 🔹 1. Was ist ein Prädikat?
Eine **Aussage** ist immer wahr oder falsch.  
Ein **Prädikat** enthält Variablen → es wird erst nach Ersatz durch konkrete Werte zur Aussage.

Beispiele:
- `sumEqualToFive(x, y)` = „Die Summe von x und y ist fünf.“
- `Pal(s)` = „Die Zeichenkette s ist ein Palindrom.“

**Definition 1:**  
Ein Prädikat ist ein Satz mit Variablen.  
Werden diese Variablen mit Werten aus einem Universum (U) ersetzt, entsteht eine Aussage.

Beispiele:  
- `sumEqualToFive(1, 3)` → falsch (0)  
- `Pal(REITTIER)` → wahr (1)

**Aufgabe 1:** Für $a,b,c,n∈\mathbb N$  
$$Fermat(a,b,c,n): a^n+b^n=c^n$$  
Finde 3 wahre und 3 falsche Aussagen.

---

## 🔹 2. Quantoren
Ein Prädikat P(x) wird durch Quantoren zu einer Aussage.

**Definition 2:**  
Für $x∈U$ gilt:
- **Allquantor ∀:** $∀x∈U:\,P(x)$ ist *wahr*, wenn P(x) für alle x wahr ist.  
- **Existenzquantor ∃:** $∃x∈U:\,P(x)$ ist *wahr*, wenn P(x) für mindestens ein x wahr ist.  

**Beispiele (Algorithmus-Sicht):**

a) $∀x∈U:\,P(x)$  
```python
for x in U:
    if not P(x):
        return False
return True
```

b) $∃x∈U:\,P(x)$  
```python
for x in U:
    if P(x):
        return True
return False
```

---

## 🔹 3. Beispiele zu Quantoren
a) $∀x∈\mathbb N:$ „x ist gerade ⇒ x+1 ist ungerade“ ✅ wahr  
b) $∃x∈\mathbb N:$ „x ist Primzahl ∧ x>100 ∧ x ist gerade“ ❌ falsch  
c) $∃x∈\mathbb Z:$ „2x < x“ ❌ falsch  
d) $∃x∈\mathbb R:$ „2x < x“ ✅ wahr  
e) $∀x∈\mathbb N:$ „(2x ist gerade ∧ x ist gerade)“ ✅ wahr  

---

## 🔹 4. Verschachtelte Quantoren
Die Reihenfolge von Quantoren ist entscheidend!  

Beispiel:  
**„Everybody loves somebody.“**

1. $∀x∈U:\,∃y∈U:\,x loves y$ → Jeder liebt jemanden.  
2. $∃y∈U:\,∀x∈U:\,x loves y$ → Es gibt jemanden, den alle lieben.  

---

## 🔹 5. Beispiel mit Matrizen
Prädikat $P(M,i,j): m_{ij}=1$  
Finde Matrizen A–F, für die die Aussagen wahr sind:

a) $∀i∈\{1…6\}:\,∀j∈\{1…6\}: P(M,i,j)$ → M=A  
b) $∀j∀i:P(M,i,j)$ → M=A  
c) $∃i∀j:P(M,i,j)$ → M=A,B,C,D,E,F (wahr)  
d) $∀j∃i:P(M,i,j)$ → M=A,B,C,F (wahr)  
e) $∀i∃j:P(M,i,j)$ → M=A,D,F  
f) $∀j∃i:P(M,i,j)$ → M=A,C,E  
g) $∃i∀j:P(M,i,j)$ → M=A,E  
h) $∃j∀i:P(M,i,j)$ → M=A,F  

---

## 🔹 6. Negation von Quantoren
Zur Widerlegung einer Aussage kann die Negation von Quantoren verwendet werden:

**Regeln:**
$$
\neg(∀x∈U:P(x)) ≡ ∃x∈U:¬P(x)
$$
$$
\neg(∃x∈U:P(x)) ≡ ∀x∈U:¬P(x)
$$

**Beispiele:**

> Behauptung: „Der Algorithmus liefert für jeden Input den korrekten Output.“  
Negation: „Es gibt einen Input, für den die Implementierung nicht korrekt arbeitet.“  
→ $¬(∀x P(x)) ≡ ∃x¬P(x)$  

> Behauptung: „Der Algorithmus findet für mindestens ein Konto das richtige Passwort.“  
Negation: „Für alle Konten liefert er kein richtiges Passwort.“  
→ $¬(∃x P(x)) ≡ ∀x¬P(x)$  

---

## 🔹 7. Negation nach innen ziehen
Bringe die Negation hinter alle Quantoren:

a) $¬(∃y∈\mathbb R:∀x∈\mathbb R: x<y)$  
→ $∀y∈\mathbb R:∃x∈\mathbb R: x≥y$

b) $¬(∀x∈\mathbb Z:∃y∈\mathbb Z: x=y+1)$  
→ $∃x∈\mathbb Z:∀y∈\mathbb Z: x≠y+1$

c) $¬(∃x∈U:∀y∈U:∃z∈U:P(x,y,z))$  
→ $∀x∈U:∃y∈U:∀z∈U:¬P(x,y,z)$  

---

## 🔹 8. Übersetzung und Negation eines Satzes
> „Für jeden iPhone-Benutzer gibt es eine App, die alle seine iPhone-benutzenden Freunde heruntergeladen haben.“

Mit U = Menge der User, A = Menge der Apps, $P(x,y)$ = „User x hat App y“.  

Formulierung:  
$$
∀x∈U:∃a∈A:∀y∈U:\,(friend(x,y)⇒P(y,a))
$$

Negation:  
$$
∃x∈U:∀a∈A:∃y∈U:\,(friend(x,y)∧¬P(y,a))
$$

---

## 🧾 Zusammenfassung
| Symbol | Bedeutung |
|:--|:--|
| $P(x)$ | Prädikat mit Variable x |
| $∀x$ | „für alle x“ – Allquantor |
| $∃x$ | „es gibt x“ – Existenzquantor |
| $¬$ | Negation |
| Reihenfolge | entscheidend bei verschachtelten Quantoren |
| $¬∀x P(x)$ | ≡ $∃x ¬P(x)$ |
| $¬∃x P(x)$ | ≡ $∀x ¬P(x)$ |

---

**Fazit:**  
Die Prädikatenlogik erweitert die Aussagenlogik um Quantoren und Variablen.  
Sie ist die mathematische Grundlage für formale Verifikation, KI und logische Programmierung (Prolog, SQL, Z3 Solver usw.).
