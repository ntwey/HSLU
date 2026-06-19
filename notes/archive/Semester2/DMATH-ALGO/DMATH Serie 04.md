# 🧮 DMATH-ALGO – Serie 04 Beweiskalküle  
**Dozent:** Dr. Reto Berger  
**Hochschule Luzern – Informatik (HS 24)**  

---

## 🎯 Lernziele
- Unterschied zwischen **semantischem Folgern** und **syntaktischem Ableiten**  
- Funktionsweise des **Resolutionskalküls**  
- Aussagen in **konjunktive Normalform (KNF)** umwandeln  
- Bedeutung der **Gödel-Theoreme** für die Informatik verstehen  

---

## 🔹 1 Einführung
In der KI wird Wissen als logische Wissensbasis (WB) dargestellt.  
Ein automatischer Beweiser soll zeigen, welche Aussagen aus WB folgen.  
Deklarative Programmiersprachen (z. B. PROLOG) beschreiben Probleme als logische Aussagen.  
Ein logisches Programm ist eine Beschreibung eines Teils der Welt;  
Anfragen werden nach logischen Regeln gelöst.  

---

## 🔹 2 Was ist ein Beweiskalkül?  

**Definition 1 (Semantisches Folgern):**  
Ein Term $Q$ folgt aus WB, wenn für alle Interpretationen, die WB wahr machen, auch $Q$ wahr ist.  
$$
WB \models Q
$$

**Beispiel**  
$WB=(A\lor B)\land(C\lor(\lnot B\land\lnot A)\lor B)$, $Q=A\lor B$

→ Mit Wahrheitstabelle prüfbar → Tautologie  

**Deduktionstheorem:**  
$$
WB\models Q\ \Leftrightarrow\ (WB\Rightarrow Q)\text{ ist allgemeingültig.}
$$

---

## 🔹 3 Widerspruchsbeweis
$$
WB\models Q\ \Leftrightarrow\ WB\land\lnot Q\ \text{ist unerfüllbar.}
$$

**Beispiel**  
$(A\lor B)\land(C\lor(\lnot B\land\lnot A)\lor B)\land\lnot(A\lor B)$  
→ Kontradiktion $\Rightarrow$ $Q$ folgt aus WB.  

---

## 🔹 4 Syntaktisches Ableiten
Statt Wahrheitstabellen nutzt man **Ableitungsregeln**.  
**Beweiskalkül** = System von Regeln zur Erzeugung gültiger Schlüsse.  

**Definition 2 (Syntaktisches Ableiten):**  
Ein Term $Q$ ist Ableitung aus WB, wenn er nach Regeln eines Beweiskalküls hergeleitet werden kann.  
Notation: $WB \vdash Q$

---

## 🔹 5 Resolutionsregel
Für die Informatik zentral:

$$
(A_1\lor…\lor A_m\lor B)\land(\lnot B\lor C_1\lor…\lor C_p)
\;\;\Rightarrow\;\;
(A_1\lor…\lor A_m\lor C_1\lor…\lor C_p)
$$

Einfaches Beispiel:  
$$(A\lor B)\land(\lnot B\lor C)\Rightarrow(A\lor C)$$  

**Aufgabe:** Mit Wahrheitstabelle oder Gesetzen überprüfen, dass diese Folgerung gilt.

---

## 🔹 6 Konjunktive Normalform (KNF)
Ein Term ist in KNF, wenn er eine UND-Verknüpfung von Klauseln ist:  
$$
WB=K_1\land K_2\land …\land K_m
$$
Jede Klausel $K_i$ ist eine ODER-Verknüpfung von Literalen $L_j$.  

**Beispiel:**  
Forme $(A\lor B)\Rightarrow(C\land D)$ in KNF um:

$$
(A\lor B)\Rightarrow(C\land D)
\equiv \lnot(A\lor B)\lor(C\land D)
\equiv (\lnot A\land\lnot B)\lor C\land D
$$
$$
\Rightarrow [(\lnot A\lor C)\land(\lnot B\lor C)]\land[(\lnot A\lor D)\land(\lnot B\lor D)]
$$

---

## 🔹 7 Resolutionskalkül – Beispiel
Zeige mit Resolutionskalkül, dass aus  
$$
WB=(S\lor N)\land(\lnot A\lor S)\land(P\lor N)\land(A\lor \lnot N\lor P)
$$
die Aussage $(S\lor A\lor P)$ folgt.  

1️⃣ Ergänze Negation von $Q$: $WB\land\lnot Q$  
2️⃣ Wandle alles in KNF um  
3️⃣ Wende Resolutionsregel an, bis leere Klausel $\Box$ entsteht  
→ Widerspruch → $WB\models Q$

---

## 🔹 8 Eigenschaften von Beweiskalkülen
| Begriff | Bedeutung |
|:--|:--|
| **Korrekt** | Wenn $WB \vdash Q$ → $WB \models Q$ |
| **Vollständig** | Wenn $WB \models Q$ → $WB \vdash Q$ |

**Gödel’s Vollständigkeitssatz:**  
Für die Prädikatenlogik 1. Stufe existieren vollständige und korrekte Beweiskalküle.  

---

## 🔹 9 Resolutionskalkül in der KI
1965 → Resolutionskalkül für Prädikatenlogik 1. Stufe.  
Er ist **vollständig und korrekt**, löste eine Logik-Euphorie in der KI aus.  

Probleme:  
- Hohe Rechenkomplexität  
- Endlosschleifen bei unerfüllbaren Zielen  
→ Prädikatenlogik 1. Stufe ist **halbentscheidbar**, Aussagenlogik **entscheidbar**.  

---

## 🔹 10 Gödel’s Theoreme
- **Vollständigkeitssatz:** 1. Stufe → vollständig und korrekt  
- **Unvollständigkeitssatz:** Ab 2. Stufe existieren wahre Aussagen, die kein Kalkül beweisen kann.  

---

## 🔹 11 Beweiser programmieren
Logik → deklarativ statt imperativ:  
**Algorithm = Logic + Control** (Robert Kowalski).  

→ Sprache **PROLOG** kombiniert Logik und Kontrolle.  

---

## 🔹 12 PROLOG-Beispiel (Einstein-Rätsel)

**Aufgabe:**  
Fünf Häuser verschiedener Farbe, Nationalität, Tier, Getränk, Zigarette.  
Finde heraus: *Wer hat den Fisch?*  

**Fakten (Auszug):**
- Der Brite wohnt im roten Haus.  
- Der Schwede hält einen Hund.  
- Der Däne trinkt Tee.  
- Das grüne Haus steht links vom weissen.  
- Der Deutsche raucht Rothmans.  
- … (weitere Regeln gegeben)

**PROLOG-Lösung:**
```prolog
links(A,B,S) :- S=[A,B,_,_,_]; S=[_,A,B,_,_]; S=[_,_,A,B,_]; S=[_,_,_,A,B].
neben(A,B,S) :- links(A,B,S); links(B,A,S).
mittleres(M,S) :- S=[_,_,M,_,_].
erstes(E,S) :- S=[E,_,_,_,_].

loesung :-
  S=[_,_,_,_,_],
  member([rot,brite,_,_,_],S),
  member([_,schwede,_,_,hund],S),
  member([_,daene,tee,_,_],S),
  links([gruen,_,_,_,_],[weiss,_,_,_,_],S),
  member([gruen,_,kaffee,_,_],S),
  member([_,_,_,pallmall,vogel],S),
  mittleres([_,_,milch,_,_],S),
  member([gelb,_,_,dunhill,_],S),
  erstes([_,norweger,_,_,_],S),
  neben([_,_,_,marlboro,_],[_,_,_,_,katze],S),
  neben([_,_,_,_,pferd],[_,_,_,dunhill,_],S),
  member([_,_,bier,winfield,_],S),
  neben([_,norweger,_,_,_],[blau,_,_,_,_],S),
  member([_,deutsche,_,rothmans,_],S),
  neben([_,_,_,marlboro,_],[_,_,wasser,_,_],S),
  member([_,N,_,_,fisch],S),
  write('Der '), write(N), write(' hat den Fisch.').
```

---

## 🔹 13 Optionale Übungen
### Einstellungstest
8 Kästchen mit Kreis/Kreuz füllen unter Bedingungen → Finde gültige Belegung per PROLOG.

### Shoppingcenter-Rätsel
Fünf Personen mit Alter, Kleidung, Farbe usw. → PROLOG-Programm zur Lösung der Constraints.  

---

## 🧾 Zusammenfassung
| Begriff | Bedeutung |
|:--|:--|
| $WB\models Q$ | semantische Folgerung |
| $WB\vdash Q$ | syntaktische Ableitung |
| Resolutionsregel | $(A\lor B)\land(\lnot B\lor C)\Rightarrow(A\lor C)$ |
| KNF | UND von Klauseln, jede Klausel ODER von Literalen |
| Korrektheit | Ableitung ⇒ Folgerung |
| Vollständigkeit | Folgerung ⇒ Ableitung |
| Gödel 1 (Vollständigkeit) | 1. Stufe vollständig & korrekt |
| Gödel 2 (Unvollständigkeit) | Ab 2. Stufe nicht mehr vollständig |
| PROLOG | Logik + Kontrolle → deklarative Programmierung |

---

**Fazit:**  
Beweiskalküle sind Grundlage automatischer Beweiser in der Informatik.  
Der Resolutionskalkül bietet eine algorithmische Basis für formale Schlussfolgerungen und KI-Systeme.  
Gödel zeigte die Grenzen formaler Systeme – nicht alles Wahre ist beweisbar.
