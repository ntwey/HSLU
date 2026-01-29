# 🧮 DMATH-ALGO – Serie 11 Graphen als Datenstruktur  
**Dozent:** Dr. Reto Berger • **HSLU Informatik (HS 24)**  

---

## 🎯 Lernziele
- Graphen lesen und ihre Eigenschaften kennen.  
- Das **Handshake-Lemma** verstehen.  
- Graphen mittels **Adjazenz** und **Inzidenz** speichern.  
- Isomorphe Graphen erkennen und prüfen.  

Graphen modellieren Beziehungen und Strukturen in vielen Informatik-Anwendungen: Netzwerke, Routenplanung, Datenbanken, soziale Netze, KI (Wissensgraphen, neuronale Netze) u.v.m. Sie sind Grundlage für Planungs-, Ressourcen- und Optimierungsalgorithmen.  

---

## 🔹 1 Was ist ein Graph?

Ein Graph besteht aus zwei Mengen:

- **V** = Menge von Knoten (Vertices)  
- **E** = Menge von Kanten (Edges)

Beispiel:  
$$V=\{A,B,C,D,E,F,G,H\},\quad  
E=\{[B,A],[C,B],[E,B],[E,C],[E,F],\{D,G\},\{G,H\}\}.$$

### Arten von Kanten
- **Gerichtet [ X,Y ]** → Start = X, Ziel = Y  
- **Ungerichtet { X,Y }** → verbindet beide Endknoten symmetrisch  

### Arten von Graphen
| Typ | Beschreibung | Beispiel |
|:--|:--|:--|
| Gerichtet | alle Kanten gerichtet | Twitter (Follower) |
| Ungerichtet | alle Kanten ungerichtet | Facebook (Freundschaften) |

---

## 🔹 2 Einfache Graphen – Schleifen und Parallelen

- **Schleife:** Kante von einem Knoten zu sich selbst.  
- **Parallelkanten:** mehrere gleiche Verbindungen zwischen zwei Knoten.  
- **Einfacher Graph:** keine Schleifen und keine Parallelkanten.  

Beispiele, wo sie Sinn machen / nicht machen:  

| Domäne | Schleifen / Parallelkanten | Kommentar |
|:--|:--:|:--|
| Soziales Netz | ❌ | keine Selbstfreundschaft |
| Internet (Linknetz) | ✅ | Seiten können mehrfach verlinken |
| Flugnetz | ✅ | mehrere Flüge zwischen gleichen Städten |
| E-Mail-Netz | ✅ | mehrere Nachrichten möglich |

---

## 🔹 3 Nachbarschaft und Inzidenz

Für eine Kante $e$:

- ungerichtet $\{X,Y\}$ → X und Y sind **benachbart** (adjacent).  
- gerichtet $[X,Y]$ → X = Vorgänger, Y = Nachfolger.  

### Definition (ung. Graph)
- $N(X)$ = Nachbarschaft von X  
- $g(X)$ = Grad von X (Anzahl Nachbarn)

### Definition (gericht. Graph)
- $N_{out}(X)$ = Ausgangsmenge von X  
- $N_{in}(X)$ = Eingangsmenge von X  
- $g_{out}(X)$ = Ausgangsgrad  
- $g_{in}(X)$ = Eingangsgrad  

---

## 🔹 4 Handshaking Lemma (ungerichtete Graphen)

> In jedem ungerichteten Graphen ist die Summe aller Knotengrade gleich dem Doppelten der Kantenzahl.  

$$
\sum_{v\in V} g(v)=2|E|
$$

### Folgen
- Die Zahl der Knoten mit **ungeradem Grad** ist immer **gerade**.  
- Durchschnittlicher Grad = $\frac{2|E|}{|V|}$  

**Beispiel:**  
100 Benutzer, 250 Freundschaften → $\bar g= 2·250 / 100 = 5$ Freunde im Schnitt.

---

## 🔹 5 Speicherung von Graphen  

### a) Adjazenzliste
Für jeden Knoten Liste seiner Nachbarn.

```text
A: B,E  
B: A,E  
C: –  
D: C,E  
E: A,B,D  
```

### b) Adjazenzmatrix
Matrix $A$ mit 1 falls Knoten $j$ Nachbar von $i$ ist, sonst 0.

|    | A | B | C | D | E |
|:--|:--:|:--:|:--:|:--:|:--:|
| A | 0 | 1 | 0 | 0 | 1 |
| B | 1 | 0 | 0 | 0 | 1 |
| C | 0 | 0 | 0 | 1 | 0 |
| D | 0 | 0 | 1 | 0 | 1 |
| E | 1 | 1 | 0 | 1 | 0 |

### c) Inzidenzmatrix
Matrix $I$ mit |V| Zeilen und |E| Spalten:  
1 = Start, –1 = Ende (gerichtet), bei ungerichtet beide 1.

|    | e₁ | e₂ | e₃ |
|:--|:--:|:--:|:--:|
| A | 1 | 0 | –1 |
| B | –1 | 1 | 0 |
| C | 0 | –1 | 1 |

### Grade auslesen
- Adjazenzmatrix: Zeilensumme = Grad.  
- Inzidenzmatrix: Summe Beträge der Einträge in Zeile = Grad.  

---

### Laufzeitanalyse (Fragen A und B)

| Frage | Adjazenzliste | Adjazenzmatrix |
|:--|:--|:--|
| A: Ist X Nachbar von Y? | Worst Case O(deg(X)) | O(1) |
| B: Nachbarschaft von X? | O(deg(X)) | O(|V|) |

→ Für dichte Graphen Matrix, für dünne Graphen Liste vorteilhaft.  

---

## 🔹 6 Isomorphe Graphen

Zwei Graphen $(V,E)$ und $(V',E')$ sind **isomorph**, wenn es eine bijektive Abbildung  
$f:V→V'$ gibt mit:

- ungerichtet: $\{x,y\}\in E ⇔ \{f(x),f(y)\}\in E'$  
- gerichtet: $[x,y]\in E ⇔ [f(x),f(y)]\in E'$  

### Beispiel

| Knoten in G₁ | Abbildung f(x) → G₂ |
|:--|:--|
| 1 | A |
| 2 | D |
| 3 | C |
| 4 | F |
| 5 | B |
| 6 | E |

---

### Komplexität des Isomorphie-Problems
- Suche nach bijektiver Abbildung hat $|V|!$ Möglichkeiten.  
- Für $|V| = 200$ → $200! ≈ 2.8 × 10^{460}$ Kombinationen.  
- Prüfung einer Abbildung dauert z. B. 0.001 s → im Worst Case ≈ $10^{450}$ Jahre 😅  

Das Problem liegt zwischen P und NP, kein bekannter polynomieller Algorithmus.  

---

### Erkennung isomorpher Graphen (Beispiele)
1. Vergleiche Knotenanzahl und Gradverteilungen.  
2. Falls gleich → Prüfe Adjazenzstruktur.  
3. Gleiche Adjazenzmatrizen bis auf Zeilen-/Spaltenpermutation ⇒ isomorph.  

---

## 🔹 7 Hasse-Diagramm und Relation (Verknüpfung zur Serie 10)
Ordnung von Knoten nach Erreichbarkeit in gerichteten Graphen führt zu Hasse-Diagrammen.  
So lassen sich z. B. Teilmengen-Beziehungen oder Vererbungs-Hierarchien darstellen.  

---

## 🧾 Zusammenfassung  

| Thema                    | Kernidee                                     |     |                |
| :----------------------- | :------------------------------------------- | --- | -------------- |
| Graph                    | Menge von Knoten und Kanten                  |     |                |
| Gerichtet / ungerichtet  | Richtungsabhängige oder symmetrische Kanten  |     |                |
| Grad / Nachbarschaft     | Zahl und Menge der verbundenen Knoten        |     |                |
| Handshaking Lemma        | $\sum g(v)=2                                 | E   | $              |
| Adjazenz-/Inzidenzmatrix | Speicherformen für Graphen                   |     |                |
| Isomorphie               | Gleiche Struktur → bijektive Knotenabbildung |     |                |
| Komplexität              | $O(                                          | V   | !)$ Worst Case |
| Anwendungen              | Netzwerke, Routing, Datenmodelle             |     |                |
|                          |                                              |     |                |

---

**Fazit:**  
Graphen sind eine universelle Struktur zur Darstellung von Beziehungen in der Informatik.  
Das Verständnis von Adjazenz, Inzidenz, Graden und Isomorphie ist zentrale Grundlage für spätere Themen wie Graphalgorithmen, Routenoptimierung und Netzwerkanalyse.
