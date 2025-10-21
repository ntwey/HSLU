# 🧮 DMATH-ALGO – Serie 01

**Fach:** Algorithmen & Datenstrukturen  
**Dozent:** (HSLU Informatik)  
**Semester:** HS25  
**Thema:** Grundlagen zu Algorithmen, Laufzeiten und mathematischer Notation  

---

## 📋 Inhaltsübersicht
1. Grundbegriffe der Algorithmenanalyse  
2. Wachstumsverhalten & Big-O-Notation  
3. Laufzeitfunktionen & Ordnungen  
4. Konstanten, Dominante Terme & Vereinfachungen  
5. Vergleich typischer Komplexitäten  
6. Praktische Überlegungen & Beispiele  

---

# 1️⃣ Grundbegriffe

## 📘 Definition „Algorithmus“
> Ein **Algorithmus** ist eine endliche, eindeutige und ausführbare Vorschrift  
> zur Lösung eines Problems oder zur Berechnung einer Funktion.

**Eigenschaften:**
- **Finitheit:** endlich viele Schritte  
- **Determinismus:** eindeutige Handlungsvorschriften  
- **Terminierung:** endet nach endlicher Zeit  
- **Effektivität:** jeder Schritt ist eindeutig ausführbar  
- **Allgemeingültigkeit:** funktioniert für eine ganze Klasse von Eingaben  

---

# 2️⃣ Wachstumsverhalten & Big-O-Notation

## 🧩 Motivation
Nicht alle Algorithmen sind gleich effizient.  
Wir vergleichen ihre **Asymptotik**, d. h. das Verhalten für grosse Eingaben (n → ∞).

---

## ⚙️ Definition O(g(n))
> Eine Funktion f(n) ist **O(g(n))**, wenn es Konstanten c > 0 und n₀ ≥ 0 gibt, sodass gilt:
>  
> ∀ n ≥ n₀ : 0 ≤ f(n) ≤ c · g(n)

**Beispiel:**
```
3n² + 5n + 2 = O(n²)
```
weil für genügend grosses n der Term `n²` dominiert.

---

## 🧮 Weitere Notationen
| Symbol | Bedeutung | Beispiel |
|---------|------------|-----------|
| **O(g(n))** | obere Schranke („höchstens so schnell wächst“) | f(n) ≤ c · g(n) |
| **Ω(g(n))** | untere Schranke („mindestens so schnell wächst“) | f(n) ≥ c · g(n) |
| **Θ(g(n))** | asymptotisch gleich | c₁ · g(n) ≤ f(n) ≤ c₂ · g(n) |

---

# 3️⃣ Laufzeitfunktionen & Vereinfachungen

## 🔹 Regeln der Vereinfachung

1. **Dominanter Term zählt**  
   - `5n³ + 6n² + 3n + 10` → O(n³)
2. **Konstanten werden ignoriert**  
   - `100n²` → O(n²)
3. **Niedrigere Terme entfallen**  
   - `n² + n log n` → O(n²)
4. **Multiplikative Konstanten irrelevant**  
   - `7 · n log n` → O(n log n)

---

## 🔹 Beispielanalyse

### Beispiel 1
```python
for i in range(0, n):
    print(i)
```
➡️ Laufzeit: O(n)

### Beispiel 2
```python
for i in range(0, n):
    for j in range(0, n):
        print(i, j)
```
➡️ Laufzeit: O(n²)

### Beispiel 3
```python
i = n
while i > 1:
    i = i / 2
```
➡️ Laufzeit: O(log n)

---

# 4️⃣ Vergleich typischer Komplexitäten

| Komplexität | Name | Beispiel |
|--------------|------|-----------|
| **O(1)** | konstant | Zugriff auf Array-Element |
| **O(log n)** | logarithmisch | binäre Suche |
| **O(n)** | linear | einfaches Durchlaufen einer Liste |
| **O(n log n)** | log-linear | Mergesort, Heapsort |
| **O(n²)** | quadratisch | doppelte Schleife |
| **O(2ⁿ)** | exponentiell | rekursive Fibonacci-Berechnung |
| **O(n!)** | faktoriell | Permutationssuche |

---

## 🔍 Visualisierung (Wachstumskurven)

```
O(1) < O(log n) < O(n) < O(n log n) < O(n²) < O(2ⁿ) < O(n!)
```

➡️ **Exponentielle** und **faktoriell** wachsende Algorithmen werden sehr schnell unpraktisch.

---

# 5️⃣ Praktische Überlegungen

## 🧩 Konstanten & reale Performance
- Asymptotik zeigt **Tendenzen**, nicht exakte Laufzeiten  
- Konstantenfaktoren können bei kleinen n dominieren  
- Beispiel: Ein O(n log n)-Algorithmus kann für kleine n langsamer sein als ein O(n²)

---

## 🧠 Speicherkomplexität
Neben Laufzeit ist auch **Speicherbedarf** wichtig:
- O(1): fester Speicherbedarf  
- O(n): linear mit Eingabegrösse  
- O(n²): Matrixoperationen  

---

# 6️⃣ Übungsbeispiele (Serie 01)

### Aufgabe 1 – Schleifenanalyse
Analysiere die Laufzeit:
```python
for i in range(1, n):
    for j in range(1, i):
        print(i, j)
```
➡️ Anzahl Iterationen ≈ n · (n – 1)/2 → **O(n²)**

---

### Aufgabe 2 – Logarithmische Schleife
```python
x = 1
while x < n:
    x = x * 2
```
➡️ Anzahl Iterationen ≈ log₂ n → **O(log n)**

---

### Aufgabe 3 – Verschachtelte Schleifen
```python
for i in range(0, n):
    for j in range(0, n*n):
        print(i, j)
```
➡️ Laufzeit: **O(n³)**

---

### Aufgabe 4 – Mehrere unabhängige Schleifen
```python
for i in range(0, n):        # O(n)
for j in range(0, n*n):      # O(n²)
for k in range(0, n*n*n):    # O(n³)
```
➡️ Gesamt: O(n³) (dominiert durch höchste Ordnung)

---

# 🧾 Zusammenfassung

| Thema | Kernaussage |
|--------|--------------|
| **Algorithmus** | endliche, deterministische Vorschrift zur Problemlösung |
| **Big-O-Notation** | beschreibt das Wachstumsverhalten für grosse n |
| **Vereinfachungsregeln** | nur dominanter Term zählt |
| **Typische Komplexitäten** | von konstant bis exponentiell |
| **Praxisbezug** | kleine n → Konstanten relevant, grosse n → O-Typ dominiert |

---

## 💡 Takeaways
- Komplexität ist ein theoretisches Werkzeug, kein Performance-Messer.  
- Big-O hilft beim Vergleichen von Algorithmen unabhängig von Hardware.  
- Reale Laufzeiten hängen zusätzlich von Implementierung & Ressourcen ab.  
- Immer testen, nicht nur theoretisch abschätzen.

---

**Kontakt:**  
👨‍🏫 Fachbereich Mathematik & Algorithmen – HSLU Informatik  
📧 [info@hslu.ch](mailto:info@hslu.ch)  

---
