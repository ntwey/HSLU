
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
