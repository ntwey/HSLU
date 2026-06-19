# 🧠 Datenbankmodellierung & SQL

**Dozent:** Prof. Dr. Michael Kaufmann  
**Datum:** 11. September 2025  
**Fach:** Datenmanagement / Datenbankmodellierung  
**Hochschule Luzern – Informatik**

---

## 📋 Themenübersicht
1. DIKW-Pyramide (Daten–Information–Wissen–Weisheit)  
2. Abbildung von Wissen in Datenbanken  
3. Relationale Datenbankverwaltung (RDBMS)  
4. Tabellen & SQL-Grundlagen  
5. Entity-Relationship-Modell (ERM)  
6. Zuordnungsregeln von Entitäten & Beziehungen  
7. Normalisierung (1NF–3NF)  
8. Praxisbeispiel: Filmempfehlungssystem  

---

# 1️⃣ DIKW-Pyramide

**DIKW = Data → Information → Knowledge → Wisdom**

| Ebene | Bedeutung |
|--------|------------|
| **Daten** | Rohinformationen, ohne Kontext |
| **Information** | Daten + Kontext → Bedeutung |
| **Wissen** | Information, die interpretiert & angewendet wird |
| **Weisheit** | Erfahrungsbasiertes Wissen (Handlungskompetenz) |

📘 Quelle: Lumen Learning – [Introduction to Conducting Research](https://courses.lumenlearning.com/wm-businesscommunicationmgrs/chapter/introduction-to-conducting-research/)

---

## 💡 Kodierung von Wissen in Daten
> Wissen entsteht durch **Interpretation** von Daten, die vorher **kodiert** wurden.

**Sender (Wissen)** → **Kodierung in Daten** → **Empfänger (Wissen durch Interpretation)**  

Frage:  
> „Was sind die wichtigsten Anforderungen, damit Wissen in Tabellen effektiv gespeichert und interpretiert werden kann?“

➡️ Antwort:  
- Strukturierte Daten  
- Einheitliche Definitionen (Schemas, Typen)  
- Eindeutige Schlüssel  
- Kontextinformationen (Metadaten)

---

# 2️⃣ Relationale Datenbankverwaltung (RDBMS)

## 🧱 Grundkonzept
> Ein relationales DBMS speichert Daten in **Tabellen (Relationen)**, bestehend aus **Zeilen (Tupeln)** und **Spalten (Attributen)**.

**Beispiel:**
| E# | Name | City |
|----|------|------|
| E19 | Stewart | Stow |
| E4 | Bell | Kent |
| E1 | Murphy | Kent |

**Begriffe:**
- **Tabelle** = Menge von Tupeln  
- **Attribut** = Spalte mit eindeutiger Bezeichnung  
- **Tupel** = Zeile mit Datenwerten  
- **Primärschlüssel (PK)** = Eindeutige Identifikation eines Datensatzes  
- **Fremdschlüssel (FK)** = Referenz auf PK einer anderen Tabelle  

---

# 3️⃣ Konzeptionelles Datenmodell

## 🧩 Beispiel: Employee – Department
```sql
CREATE TABLE Employee (
  E# INTEGER,
  Name TEXT,
  City TEXT,
  D#_Sub INTEGER
);

CREATE TABLE Department (
  D# INTEGER,
  Dept_Name TEXT
);
```

**Entitäten:**
- `Employee`: E#, Name, City  
- `Department`: D#, Dept_Name  

**Beziehungen:**
- Department → Employee (Abteilungsleiter, Zugehörigkeit)

---

# 4️⃣ Zuordnungsregeln: Von ER-Modell zu SQL

| Regel | Beschreibung | Beispiel |
|--------|---------------|----------|
| **R1** | Jede **Entitätsmenge** = eigene Tabelle mit **Primärschlüssel** | `CREATE TABLE Department (...)` |
| **R2** | Jede **Beziehung** kann als separate Tabelle abgebildet werden | z. B. N:M-Relation |
| **R3** | Komplex–komplexe Beziehungen → eigene Tabelle mit zusammengesetztem PK | `involved (E#, P#, Percentage)` |
| **R4** | Einfach–komplexe Beziehungen → Fremdschlüssel in referenzierender Entität | `Employee.D#_Sub REFERENCES Department(D#)` |
| **R5** | Einfache 1:1-Beziehungen → Fremdschlüssel in referenzierender Tabelle | `Department.E#_DeptHead REFERENCES Employee(E#)` |

---

## 🧮 Beispiel: N:M-Beziehung
```sql
CREATE TABLE involved (
  E# INTEGER REFERENCES Employee(E#),
  P# INTEGER REFERENCES Project(P#),
  Percentage DECIMAL(3,1),
  PRIMARY KEY (E#, P#)
);
```

**Interpretation:**
- Eine Person kann in mehreren Projekten arbeiten  
- Ein Projekt hat mehrere Mitarbeitende  

---

# 5️⃣ Beziehung (ERM) vs. Relation (RDBMS)

| Begriff | Ebene | Bedeutung |
|----------|--------|------------|
| **Beziehung** | Konzeptionelles Modell (Chen) | Assoziation zwischen Entitäten |
| **Relation** | Logisches Modell (Codd) | Tabelle im Datenbankschema |

**Formale Definition:**  
Eine Relation R ist eine Teilmenge des kartesischen Produkts der Domänen:  
> R ⊆ D₁ × D₂ × … × Dₙ  

Jedes Tupel t ∈ R ist eine Liste von Attributwerten (v₁, v₂, …, vₙ).

---

## 🧩 Reflexionsfragen
1. Welche Beziehungen werden **nicht als Relationen** gespeichert?  
   → 1:1 oder 1:n, die über Fremdschlüssel abgebildet sind.  
2. Welche werden **als Relationen** gespeichert?  
   → N:M-Beziehungen.  
3. Welche Relationen **speichern keine Beziehungen**?  
   → Reine Entitätstabellen.  

---

# 6️⃣ Normalisierung

## 🎯 Ziel
Redundanzen vermeiden, Konsistenz sichern, Anomalien verhindern.

**Anomalien:**
- **Einfügeanomalie**  
- **Änderungsanomalie**  
- **Löschungsanomalie**

---

## 🧩 Erste Normalform (1NF)
- Nur **atomare Werte** (keine Listen, Arrays, Mengen)
- Jede Spalte enthält **nur einen Wert pro Zeile**
- Keine Spaltenreihenfolge erforderlich

```sql
-- Nicht erlaubt:
CREATE TABLE Orders (Items TEXT[]);  -- kein Array!
```

---

## 🧩 Zweite Normalform (2NF)
- 1NF erfüllt  
- **Jedes Nicht-Schlüsselattribut** ist **vollständig funktional abhängig** vom Primärschlüssel  
- Keine Abhängigkeit von Teilen eines zusammengesetzten Schlüssels  

---

## 🧩 Dritte Normalform (3NF)
- 2NF erfüllt  
- **Keine transitiven Abhängigkeiten**  
  (wenn A → B und B → C, dann darf A → C nur direkt gelten)

**Ziel:**  
Nur noch direkte Beziehungen zwischen Schlüssel und Nicht-Schlüsselattributen.

---

# 7️⃣ Praxisbeispiel: Filmempfehlungssystem

## 🎬 Ziel
Empfehlung von Filmen basierend auf Nutzerbewertungen.

---

## 🧱 Entitäten & Attribute

| Entität | Attribute |
|----------|------------|
| **Movie** | MovieID, Title, Release Date, Budget, Genres, Languages |
| **Rating** | UserID, MovieID, Rating, Timestamp |
| **Genre** | GenreID, Name |

---

## 🔗 Beziehungen
- Ein Film hat viele Genres → N:M  
- Ein Benutzer bewertet viele Filme → N:M  

---

## 🧩 SQL-Schema (3NF)
```sql
CREATE TABLE Movies (
  MovieID INTEGER PRIMARY KEY,
  Title TEXT,
  Release_date DATE,
  Budget INTEGER,
  Sprachen JSON
);

CREATE TABLE Bewertungen (
  BenutzerID INTEGER,
  FilmID INTEGER,
  Bewertung INTEGER,
  Timestamp DATETIME,
  PRIMARY KEY (FilmID, BenutzerID),
  FOREIGN KEY (FilmID) REFERENCES Movies(MovieID)
);

CREATE TABLE Genres (
  GenreID INTEGER PRIMARY KEY,
  Name TEXT
);

CREATE TABLE Movie_has_Genre (
  MovieID INTEGER,
  GenreID INTEGER,
  PRIMARY KEY (MovieID, GenreID),
  FOREIGN KEY (MovieID) REFERENCES Movies(MovieID),
  FOREIGN KEY (GenreID) REFERENCES Genres(GenreID)
);
```

---

# 8️⃣ Verbindung zur Praxis

**Anwendungsfall:** Filmempfehlungen mit MySQL + Metabase  

| Ebene | Beschreibung |
|--------|---------------|
| **Datenarchitektur** | MySQL Server + Metabase (SQL API) |
| **Datenverwaltung** | Tabellen: Movies, Bewertungen, Genres |
| **Datentechnik** | SQL `SELECT`, `INSERT INTO ... SELECT`, `CREATE INDEX` |
| **Datennutzung** | Visualisierung & Interaktion über Web-UI |
| **Zugriffsrechte** | Admin-Port 3306, Metabase Port 3000 |

---

# 🧾 Zusammenfassung

| Thema | Kernaussage |
|--------|--------------|
| **DIKW-Pyramide** | Daten → Information → Wissen → Weisheit |
| **RDBMS** | Strukturierte Speicherung, ACID, Schlüsselkonzepte |
| **ER-Modell** | Grundlage der Datenmodellierung |
| **Zuordnungsregeln** | Abbildung von Entitäten & Beziehungen auf SQL |
| **Normalformen** | Redundanzvermeidung & Datenintegrität |
| **Praxisbeispiel** | Filmempfehlungssystem (3NF) mit MySQL & Metabase |

---

## 💡 Takeaways
- Datenmodellierung = Übersetzung von Wissen in Datenstrukturen  
- Gute Tabellenstruktur verhindert Anomalien  
- Normalisierung schafft stabile, konsistente Datenmodelle  
- ER-Modelle helfen, Beziehungen korrekt zu verstehen  
- SQL ist das Werkzeug zur logischen Umsetzung des konzeptionellen Modells  

---

**Kontakt:**  
👨‍🏫 Prof. Dr. Michael Kaufmann  
📧 [m.kaufmann@hslu.ch](mailto:m.kaufmann@hslu.ch)  
📞 +41 41 757 68 48  
🏫 Hochschule Luzern – Informatik  

---
