# 🧮 DMATH-ALGO – Serie 10 Relationen  
**Dozent:** Dr. Reto Berger • **HSLU Informatik (HS 24)**  

---

## 🎯 Lernziele
- Mit Relationen rechnen und ihre Eigenschaften bestimmen.  
- Reflexivität, Symmetrie und Transitivität analysieren.  
- Äquivalenzen und Ordnungen erkennen und Hüllen bilden.  
- Hasse-Diagramme konstruieren.

Relationen sind grundlegend für die Informatik – sie beschreiben Zusammenhänge zwischen Objekten, Daten oder Prozessen (z. B. Datenbanken, Graphen, Machine Learning, Routing, Zugriffsrechte, Synchronisation, Optimierung, Blockchain u. v. m.).

---

## 🔹 1 Was sind n-stellige Relationen?

Ein Prädikat $P(x,y)$ (z. B. „Der Monat x hat y Tage“) definiert, wann $x$ und $y$ in Relation stehen.  
Die Menge aller gültigen Paare $[x,y]$ heißt **Relation R**.

### Definition 1 (n-stellige Relation)
Seien $A_1,…,A_n$ Mengen. Eine Teilmenge $R\subseteq A_1×A_2×…×A_n$ heißt n-stellige Relation.  
Die Objekte $z_1,…,z_n$ stehen in Relation, wenn $[z_1,…,z_n]\in R$.  

Für 2-stellige Relationen (binär) nutzt man meist Infixnotation: $x R y$ statt $[x,y]\in R$.

**Beispiele**
- RGB-Farbraum → $R\subseteq$ *Namen × {0…255}³*  
- 3-stellige Relation „Summe = n“ auf {1…6}³  
- 2-stellige Relation „ist Teiler von“ auf $\mathbb N$  
- bekannte Relationen: = , < , ≤ , > , ≥  

---

### Neue Relationen aus gegebenen

**Definition 2**
- Komplementärrelation: $\bar R = A×B\setminus R$  
- Umkehrrelation: $R' = \{[y,x]\mid[x,y]\in R\}$  

**Beispielaufgabe**
Komplement und Umkehr von <, ≤ und „Teiler von“ auf {1,2,3,4}² → > bzw. ≥ bzw. „Vielfaches von“.

---

### Verkettung von Relationen

**Definition 3**  
Für $R\subseteq A×B$ und $S\subseteq B×C$ gilt:
$$
R ∘ S = \{[x,z]\in A×C \mid ∃y∈B:[x,y]\in R∧[y,z]\in S\}.
$$

**Beispiel**
$R=\{[1,2],[1,4],[2,5],[3,3]\}$,  
$S=\{[2,3],[4,5],[5,5]\}$ → $R∘S=\{[1,3],[1,5],[2,5],[3,3]\}$.

---

## 🔹 2 Wichtige Eigenschaften von Relationen  

### Definition 4 – Reflexivität
- reflexiv ⇔ ∀x [x,x]∈R  
- irreflexiv ⇔ ∀x [x,x]∉R  

### Definition 5 – Symmetrie
- symmetrisch ⇔ [x,y]∈R ⇒ [y,x]∈R  
- antisymmetrisch ⇔ [x,y]∈R ∧ [y,x]∈R ⇒ x=y  
- asymmetrisch ⇔ [x,y]∈R ⇒ [y,x]∉R  

### Definition 6 – Transitivität
- transitiv ⇔ [x,y],[y,z]∈R ⇒ [x,z]∈R  

**Beispiele**
| Relation | Reflexiv | Symmetrisch | Transitiv |
|:--|:--:|:--:|:--:|
| = | ✅ | ✅ | ✅ |
| < | ❌ | ❌ | ✅ |
| ≤ | ✅ | ❌ | ✅ |
| „Teiler von“ | ✅ | ❌ | ✅ |

---

### Übung – Eigenschaften auf kleinen Mengen
Alle Relationen auf {1,2} → 16 Möglichkeiten.  
Kombinationen von Reflexivität, Symmetrie, Transitivität ermitteln.

---

## 🔹 3 Hüllen von Relationen  

**Definition 7**
Sei $R⊆A×A$. Dann ist die kleinste Menge $H⊇R$ mit bestimmter Eigenschaft:  
- Reflexive Hülle $R_{ref}$ → alle [x,x] hinzufügen  
- Symmetrische Hülle $R_{sym}$ → alle [y,x], falls [x,y]∈R  
- Transitive Hülle $R_{trans}$ → alle [x,z], wenn [x,y],[y,z]∈R  

Berechnung iterativ (Transitivität nach jeder Erweiterung prüfen).  

**Beispiel**
$R=\{[a,d],[b,a],[c,a],[e,d],[d,c]\}$  
→ $R_{ref}=\{aa,bb,cc,dd,ee\}$ u. $a…$  
→ $R_{sym}$ = R ∪ Umkehrungen  
→ $R_{trans}$ = $R∪(R∘R)∪(R∘R∘R)…$  

---

## 🔹 4 Äquivalenzen und Ordnungen  

**Definition 8**
| Typ | Eigenschaften |
|:--|:--|
| Äquivalenz | reflexiv + symmetrisch + transitiv |
| Teilordnung | reflexiv + antisymmetrisch + transitiv |
| strenge Teilordnung | irreflexiv + antisymmetrisch + transitiv |
| Totalordnung | Teilordnung + jede Paarung vergleichbar |

**Beispiele**
- = auf {1,2,3} → Äquivalenz  
- ⊆ auf $P(\{1,2,3\})$ → Halbordnung  
- ≤ auf {1,2,3} → Totalordnung  
- < auf {1,2,3} → strikte Totalordnung  

---

### Satz 1 – Äquivalenzklassen
Jede Äquivalenzrelation zerlegt A in disjunkte Klassen.  

**Beispiel**
$x=y ⇔ x−y$ teilbar durch 3 auf ℤ:  
- [0] = {…, −6, −3, 0, 3, 6,…}  
- [1] = {…, −5, −2, 1, 4, 7,…}  
- [2] = {…, −4, −1, 2, 5, 8,…}  

---

### Satz 2 – Keine Kreise bei Ordnungen
Wenn $x<y$ und $y<x$ nie gleichzeitig gelten, kann kein Kreis entstehen.  
→ $x$ Vorgänger von $y$, $y$ Nachfolger von $x$.  

### Satz 3 – Minimale / maximale Elemente
Jede Ordnungsrelation auf endlicher Menge hat mind. ein minimales und ein maximales Element.

---

## 🔹 5 Hasse-Diagramme  

Zur Darstellung von Ordnungen auf endlichen Mengen:

1. Minimale Elemente unten anordnen.  
2. Direkte Nachfolger darüber setzen.  
3. Kanten nur zwischen direkten Beziehungen zeichnen.

**Beispiel:** Teilmenge ⊆ auf $P(\{1,2,3\})$

```
        {1,2,3}
      /    |    \
   {1,2} {1,3} {2,3}
     \   /  \   /
       {1} {2} {3}
          \ | /
           ∅
```

---

## 🔹 6 Anwendungsaufgaben (Auswahl)

| Nr | Thema | Kernidee |
|:--|:--|:--|
| 1–2 | Relationen definieren und verketteten | z. B. Monat–Tage, States–Senators |
| 3 | Eigenschaften Reflexiv/Symmetrisch/Transitiv prüfen | auf {1,…,4} |
| 4 | Hüllen berechnen | Reflexive, Symmetrische, Transitive |
| 5 | Äquivalenzrelation MonatTage ∘ MonatTage′ | → Monate mit gleicher Tagezahl |
| 6 | Hasse-Diagramme zeichnen | <, ⊆, „Teiler von“, alphabetisch |

---

## 🧾 Zusammenfassung

| Begriff | Bedeutung | Eigenschaften |
|:--|:--|:--|
| Relation | Menge von Tupeln $⊆ A×B$ | verknüpft Elemente zweier Mengen |
| Komplement, Umkehr, Verkettung | Operationen auf Relationen | Verknüpfen und invertieren |
| Reflexiv, Symmetrisch, Transitiv | Schlüsseleigenschaften | Grundlage für Struktur |
| Hülle | Erweiterung von R zu gewünschter Eigenschaft | Reflexiv, Symmetrisch, Transitiv |
| Äquivalenz | Reflexiv + Symmetrisch + Transitiv | Partitioniert Menge |
| Teilordnung | Reflexiv + Antisymmetrisch + Transitiv | Struktur vergleichbar |
| Totalordnung | alle Paare vergleichbar | z. B. ≤ auf ℕ |
| Hasse-Diagramm | Graph zur Ordnungsdarstellung | zeigt Vorgänger / Nachfolger |

---

**Fazit:**  
Relationen sind ein universelles Modell für Zusammenhänge in Daten und Algorithmen.  
Sie ermöglichen formale Darstellung von Abhängigkeiten, Klassifikationen und Ordnungen – von Datenbanken bis Graphentheorie und Künstlicher Intelligenz.
