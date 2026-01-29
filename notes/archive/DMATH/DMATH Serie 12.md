# 🧮 DMATH-ALGO – Serie 12 Spezielle Graphen  
**Dozent:** Dr. Reto Berger • **HSLU Informatik (HS 24)**  

---

## 🎯 Lernziele
- Verständnis von **k-regulären**, **vollständigen**, **bipartiten** und **planaren** Graphen.  
- **Cliquen** und **Matchings** in Graphen erkennen.  
- Das **Euler-Theorem** anwenden.  
- **Bäume** und ihre Eigenschaften beschreiben.  

Diese Serie erweitert die Grundlagen aus Serie 11 um wichtige Unterarten von Graphen, die häufig in praktischen Anwendungen vorkommen (z. B. Netzwerke, Hierarchien, Planungen).

---

## 🔹 1 k-reguläre und vollständige Graphen

Ein **einfacher ungerichteter Graph** mit $n$ Knoten heißt *k-regulär*,  
wenn jeder Knoten genau $k$ Nachbarn hat.  
Er heißt **vollständig**, wenn er $(n−1)$-regulär ist.

### Eigenschaften
- Jeder Knoten hat denselben Grad $k$.  
- Der vollständige Graph mit $n$ Knoten wird mit $K_n$ bezeichnet.

### Formeln
1. **Kantenanzahl eines k-regulären Graphen:**  
   $$
   |E| = \frac{n·k}{2}
   $$

2. **Kantenanzahl eines vollständigen Graphen:**  
   $$
   |E| = \frac{n·(n−1)}{2}
   $$

---

## 🔹 2 Cliquen und Cliquenzahl

Eine **Clique** ist eine Teilmenge von Knoten, die alle paarweise durch Kanten verbunden sind.  
Die **Cliquenzahl** $\omega(G)$ ist die Anzahl der Knoten in der größten Clique.

**Beispiel:**  
In einem Kollaborationsnetz bilden alle Personen eines gemeinsamen Teams eine Clique.

**Aufgabe 2:** Bestimme $\omega(G)$ für verschiedene Beispielgraphen.  

**Aufgabe 3:** Turnierplan – 10 Teams, jedes spielt gegen 4 andere.  
→ Modellierung als Graph: Teams = Knoten, Spiele = Kanten.  
→ Jeder Knoten hat Grad 4 → 4-regulärer Graph.

---

## 🔹 3 Matching und perfektes Matching

Eine **Teilmenge von Kanten** $M⊆E$ heißt **perfektes Matching**,  
wenn der Graph mit nur diesen Kanten *1-regulär* ist,  
also **jede Person / jeder Knoten genau einem anderen zugeordnet** ist.

**Beispiel:** Zuweisung von Personen zu Aufgaben  
→ bipartiter Graph: linke Menge = Personen, rechte Menge = Aufgaben.  
Perfektes Matching existiert, wenn jede Person eine Aufgabe übernehmen kann.

**Allgemein:**  
Ein **Matching** erlaubt Auslassungen einzelner Knoten;  
perfektes Matching = Spezialfall mit vollständiger Paarung.

**Frage:** Wie viele verschiedene Matchings mit k Kanten gibt es in $K_n$?  
$$
\text{Anzahl} = \frac{n!}{(n-2k)!·2^k·k!}
$$

---

## 🔹 4 Bipartite Graphen

Ein ungerichteter Graph heißt **bipartit**, wenn sich seine Knotenmenge $V$ in zwei disjunkte Mengen $L$ und $R$ teilen lässt,  
so dass **alle Kanten nur zwischen L und R** verlaufen.

Ein **vollständiger bipartiter Graph** mit $n$ Knoten in L und $m$ Knoten in R heißt $K_{n,m}$.

### Eigenschaften
- Keine Kante innerhalb einer Teilmenge.  
- Graph ist 2-färbbar.  
- Enthält keine ungeraden Kreise.

**Kantenanzahl:**  
$$
|E| = |L|·|R|
$$
Bei $|L|=|R|=\frac{n}{2}$:  
$$
|E|_{max} = \frac{n^2}{4}
$$

---

### Satz (Knotengrade)
Für bipartite Graphen mit $|L|=|R|$ gilt:
1. Summe der Knotengrade in L = Summe der Knotengrade in R.  
2. Beide Summen sind gerade (Handshake-Lemma).  
3. Damit ist auch die Gesamtsumme der Knotengrade gerade.

---

## 🔹 5 Planare Graphen und Euler-Theorem

Ein Graph heißt **planar**, wenn er so gezeichnet werden kann,  
dass sich keine Kanten schneiden.  

**Theorem von Euler (1736):**  
Für jeden zusammenhängenden planaren Graphen gilt  
$$
|V| - |E| + |F| = 2
$$
mit  
- $|V|$ = Knotenanzahl,  
- $|E|$ = Kantenanzahl,  
- $|F|$ = Flächen (inkl. Außenfläche).

### Beispiele
- Nachbarschaftsgraph von US-Bundesstaaten: planar.  
- **Utilities-Problem (3 Häuser, 3 Leitungen)** → nicht planar.

---

### Eigenschaften planarer Graphen

1. Für planare Graphen mit mehr als einer Kante gilt:  
   $$
   2·|E| ≥ 3·|F|
   $$
   da jede Fläche von mindestens drei Kanten begrenzt wird.

2. Mit Euler:  
   $$
   |E| < 3·|V| - 6
   $$

3. Nach Handshake-Lemma:  
   $$
   \bar g = \frac{2|E|}{|V|} < 6
   $$
   → Durchschnittlicher Knotengrad < 6.

**Konsequenz:**  
Planare Graphen sind immer „sparsam“ – keine zu hohe Knotenverbindung möglich.

---

### Beweisidee für das Utilities-Problem
Das Netz aus 3 Häusern × 3 Versorgern = $K_{3,3}$ ist **nicht planar**,  
da es $|E|=9$ Kanten und $|V|=6$ Knoten hat → Verstoß gegen $|E|<3|V|-6$.  

---

## 🔹 6 Bäume

Ein **Baum** ist ein zusammenhängender ungerichteter Graph ohne Kreise.

### Eigenschaften
- Immer genau eine Verbindung zwischen zwei Knoten.  
- Entfernt man eine Kante → nicht mehr zusammenhängend.  
- Fügt man eine neue Kante hinzu → entsteht ein Kreis.

### Formel
$$
|E| = |V| - 1
$$

### Begriffe
- **Wurzelknoten (Root):** Startpunkt des Baumes.  
- **Innere Knoten:** haben Kinder.  
- **Blattknoten:** keine Kinder.  
- **Eltern-, Kind-, Geschwisterknoten:** definieren Hierarchie.  
- **Tiefe:** Anzahl der Kanten von der Wurzel bis zum Knoten.

---

### k-fache Bäume
Ein Baum heißt **k-fach**, wenn jeder innere Knoten höchstens k Kinder hat.  
Ein 3-facher Baum mit Tiefe 8 hat höchstens
$$
1 + 3 + 3^2 + … + 3^8 = \frac{3^9 - 1}{2} = 9841
$$
Knoten.

---

### Beispiel – Darstellung als Adjazenzliste
```text
A: C, E, F  
B: E, F  
C: D, F  
D: A, C  
E: B, D  
F: A, B, C
```
→ Nicht alle dieser Strukturen sind Bäume, wenn Kreise vorkommen.  
Ein Baum hat stets |E| = |V| – 1 und keine Zyklen.

---

## 🧾 Zusammenfassung

| Typ                  | Eigenschaften                    | Formel             |     |                     |     |     |     |     |
| :------------------- | :------------------------------- | :----------------- | --- | ------------------- | --- | --- | --- | --- |
| k-regulär            | alle Knoten haben Grad k         | $                  | E   | =\frac{n·k}{2}$     |     |     |     |     |
| vollständig $K_n$    | alle Knoten verbunden            | $                  | E   | =\frac{n·(n-1)}{2}$ |     |     |     |     |
| Clique               | vollständig verbundene Teilmenge | –                  |     |                     |     |     |     |     |
| (Perfektes) Matching | 1-reguläre Kantenmenge           | $n!/(n−2k)!2^k k!$ |     |                     |     |     |     |     |
| bipartit $K_{n,m}$   | Kanten nur zw. L u. R            | $                  | E   | =n·m$               |     |     |     |     |
| planar               | keine Kantenkreuzungen           | $                  | V   | −                   | E   | +   | F   | =2$ |
| Baum                 | zusammenhängend, kreisfrei       | $                  | E   | =                   | V   | −1$ |     |     |

---

**Fazit:**  
Spezielle Graphen wie *k-reguläre*, *bipartite*, *planare* und *Baumstrukturen*  
bilden zentrale Modelle in der Informatik:  
von Netzwerken und Zuordnungsproblemen bis zu Hierarchien und Routing.  
Sie sind Grundlage vieler Optimierungs- und Strukturierungsverfahren.
