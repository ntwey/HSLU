# 🧮 DMATH-ALGO – Serie 08 Grundlegende Zähltechniken  
**Dozent:** Dr. Reto Berger • **HSLU Informatik (HS 24)**  

---

## 🎯 Lernziele
- Unterschied zwischen **Summen- und Produktregel** verstehen und anwenden.  
- **Zuordnungsregel** benutzen, um Zählprobleme zu vereinfachen.  
- **Sechs fundamentale Zählprobleme** kennen und lösen können.  

Zählen ist entscheidend in der Informatik (z. B. Brute-Force-Algorithmen und Rekursions-Entwurf).  

---

## 🔹 1 Grundlegende Zählregeln  

### Summenregel (Disjunkte Mengen)
Für disjunkte Mengen $A,B$ gilt 
$$|A∪B|=|A|+|B|.$$

Allgemein bei Partition $S=S_1∪S_2∪⋯∪S_n$ gilt 
$$|S|=|S_1|+|S_2|+⋯+|S_n|.$$

**Aufgabe 1** (8-Bit-Worte mit genau 2 Einsen)  
→ $\binom{8}{2}=28$

**Aufgabe 2** (64-Bit-Worte mit genau 2 Einsen)  
→ $\binom{64}{2}=2016$

---

### Produktregel (Kartesisches Produkt)
Für endliche Mengen $A,B$ gilt 
$$|A×B|=|A|·|B|.$$

Allgemein für $n$ Mengen $S_i$:  
$$|S_1×S_2×⋯×S_n|=|S_1|·|S_2|·⋯·|S_n|.$$
Sind alle gleich: $|S|^n$.  

**Aufgabe 3:** Wie viele 16-Bit-Worte? → $2^{16}=65536$

**Aufgabe 4:** URL-Shortener mit Ziffern (10), Groß- (26) und Kleinbuchstaben (26):  
$$62^7=3.5×10^{12}$$ mögliche URLs.  

**Aufgabe 5:** MAC-Adresse (6 Paare hex Ziffern 0–F) → $16^{12}=2^{48}≈2.8×10^{14}$  

---

### Zuordnungsregel
Für $f:A→B$ surjektiv und jedes $b∈B$ hat $k$ Urbild-Elemente:  
$$|A|=k·|B|.$$
Ist $f$ bijektiv ($k=1$): $|A|=|B|.$  

**Aufgabe 6:** Potenzmenge $P(A)$ hat $$|P(A)|=2^{|A|}.$$

**Aufgabe 7:** 1000 Rechner, je Handshake paarweise:  
$$\binom{1000}{2}=499 500.$$

---

## 🔹 2 Sechs fundamentale Zählprobleme  

### 1️⃣ Permutation (alle $n$ Objekte, geordnet)
- Ohne Wiederholung: $n!$
- Mit Wiederholungen: $\displaystyle\frac{n!}{s_1!s_2!⋯s_k!}$  

Beispiel $a,a,a,b,c$ → $\frac{5!}{3!}=20$ Permutationen.  

---

### 2️⃣ Variation (Teilmenge von $n$ Objekten, geordnet)
- Ohne Wdh.: $\displaystyle \frac{n!}{(n-k)!}$  
- Mit Wdh.: $n^k$

Beispiel $a,b,c,d$ → $V_{4,2}=4·3=12$ ohne Wdh., $4^2=16$ mit Wdh.

---

### 3️⃣ Kombination (Teilmenge von $n$ Objekten, ungeordnet)
- Ohne Wdh.: $\displaystyle\binom{n}{k}=\frac{n!}{k!(n-k)!}$  
- Mit Wdh.: $\displaystyle\binom{n+k-1}{k}=\frac{(n+k-1)!}{k!(n-1)!}$  

Beispiel $a,b,c,d,e$ → $\binom{5}{3}=10$ ohne Wdh., $\binom{7}{3}=35$ mit Wdh.

---

## 🔹 3 Anwendungsaufgaben  

**Aufgabe 8:** Passwörter (6–8 Zeichen, 62 Symbole)  
a) $62^6+62^7+62^8≈2.2×10^{14}$  
b) Mind. eine Ziffer → $62^n-52^n$ je Länge, aufsummieren.  

---

**Aufgabe 9:** Euro Millions (5 von 50 + 2 von 12)  
$$\binom{50}{5}·\binom{12}{2}=139 838 160.$$
29 Byte pro Tipp → ≈ 4 GB Datei.  

---

**Aufgabe 10:** 42 Threads auf 4 Prozessoren → Kombination mit Wdh.:  
$$\binom{42+4-1}{4-1}=\binom{45}{3}=14 190.$$  

---

**Aufgabe 11:** Passwort mit 1/I/l und 0/O unsicher → 6 Verwechslungsstellen → $2^6=64$ Varianten.  

---

**Aufgabe 12:** Router-Puffer mit A(5), B(9), C(5), D(3), E(7) Paketen  
a) $\displaystyle\frac{29!}{5!9!5!3!7!}$ gesamt.  
b) A nebeneinander → $\displaystyle\frac{25!}{9!5!3!7!}.$  
c) Alle gleichen nebeneinander → $5!$ Warteschlangen.  

---

**Aufgabe 13:** 32-Bit Worte mit höchstens 8 Einsen:  
$$\sum_{k=0}^{8}\binom{32}{k}=314 457 495.$$  

---

**Aufgabe 14:** Neuronales Netz 10-20-15-3  
a) $10·20+20·15+15·3=465$ Verbindungen.  
b) $10·20·15·3=9000$ Wege.  

---

**Aufgabe 15:** Ungerade 7-stellige Zahlen aus Ziffern 1,2,3,3,0,0,0  
→ $\displaystyle\frac{7!}{3!3!}=140$ Zahlen.  

---

**Aufgabe 16:** 6 unterschiedliche 64-Bit-Worte auf 9 Register:  
Variation ohne Wdh.: $\displaystyle\frac{9!}{(9-6)!}=60 480.$  

---

**Aufgabe 17:** Switch mit 50 Ports → unidirektionale Verbindungen $=50·49=2450.$  

---

**Aufgabe 18:** 5 von 8 Dateien mit versch. Passwörtern (8 verfügbar)  
Variation ohne Wdh.: $\displaystyle\frac{8!}{3!}=6720.$  

---

**Aufgabe 19:** 9 Programme, 3 Rechner  
a) $3^9=19 683$ (ohne Vorgabe)  
b) Auf jedem 3 → $\displaystyle\frac{9!}{(3!)^3}=1680.$  
c) Mind. eins → $3^9-3·2^9+3·1^9=19 170.$  

---

**Aufgabe 20:** 35 Router, Weg A→B (6 Ost, 4 Nord) → $\binom{10}{4}=210.$  
Über C → entsprechend Unterpfade multiplizieren.  

---

**Aufgabe 21:** Ringtopologie 10 Rechner → Kreis-Permutation  
$$\frac{10!}{10}=9! = 362 880.$$

---

**Aufgabe 22:** IPv4 / IPv6  
a) IPv4 = $2^{32}=4 294 967 296$ Adressen.  
b) /27 Netz → $2^{5}=32$ Adressen, −2 → 30 Hosts.  
c) IPv6 = $2^{128}≈3.4×10^{38}$ Adressen.  

---

## 🧾 Zusammenfassung  

| Regel / Typ     | Formel                              | Beispiel                  |     |            |     |             |     |                  |
| :-------------- | :---------------------------------- | :------------------------ | --- | ---------- | --- | ----------- | --- | ---------------- |
| Summenregel     | $                                   | A∪B                       | =   | A          | +   | B           | $   | Disjunkte Mengen |
| Produktregel    | $                                   | A×B                       | =   | A          | ·   | B           | $   | Bit-Wörter, MAC  |
| Zuordnungsregel | $                                   | A                         | =k· | B          | $   | Bijektionen |     |                  |
| Permutation     | $n!$                                | Reihenfolge aller Objekte |     |            |     |             |     |                  |
| Variation       | $V_{n,k}=\frac{n!}{(n-k)!}$         | Geordnete Auswahl         |     |            |     |             |     |                  |
| Kombination     | $\binom{n}{k}$ / $\binom{n+k-1}{k}$ | Ungeordnete Auswahl       |     |            |     |             |     |                  |
| Potenzmenge     | $2^{                                | A                         | }$  | Teilmengen |     |             |     |                  |
| Handshakes      | $\binom{n}{2}$                      | Rechnernetz               |     |            |     |             |     |                  |
| IPv4            | $2^{32}$                            | Adressraum                |     |            |     |             |     |                  |
| IPv6            | $2^{128}$                           | Adressraum                |     |            |     |             |     |                  |

---

**Fazit:**  
Zählen ist die Grundlage für Kombinatorik, Wahrscheinlichkeit und Komplexitätsanalyse.  
Mit Summen-, Produkt- und Zuordnungsregel lassen sich praktisch alle Zählprobleme formal lösen –  
von Bitfolgen über Passwörter bis zu IP-Adressen und Netzwerk-Topologien.
