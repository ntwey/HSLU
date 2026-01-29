# 🧩 Database Integration & SQL

**Dozent:** Prof. Dr. Michael Kaufmann  
**Datum:** 7. Oktober 2025  
**Fach:** Information Technology / SQL & Datenintegration  
**Hochschule Luzern – Informatik**

---

## 📋 Themenübersicht
1. SQL-DML (Data Manipulation Language)  
2. Real-World Data Sources  
3. ETL – Extract, Transform, Load  
4. ELT – Extract, Load, Transform  
5. Data Matching  

---

# 1️⃣ SQL-DML – Data Manipulation Language

> DML = *Data Manipulation Language*  
> Befehle zum Erstellen, Einfügen, Ändern und Löschen von Daten.

---

## 🧱 CREATE TABLE
Erstellt Tabellen in SQL.

```sql
CREATE TABLE Employee (
  E# CHAR(6) NOT NULL,
  Name VARCHAR(20),
  ...
);
```

- **DROP TABLE** – löscht Tabellendefinition  
- **ALTER TABLE** – ändert Struktur  
- **CREATE TABLE AS SELECT ...** – erzeugt Tabelle aus Query-Ergebnis  

📖 Quelle: Meier & Kaufmann (2019)

---

## ✏️ INSERT
Fügt neue Datensätze hinzu.

```sql
INSERT INTO Employee VALUES
('E22', 'Cole', '7th Street', 'Manhattan', 'A6'),
('E23', 'Smith', 'Reseda Blvd', 'Los Angeles', 'A5');
```

Oder:
```sql
INSERT INTO Employee (E#, Name, Street, Place)
SELECT A#, Name, Street, Place
FROM Applicants, Addresses
WHERE AdressFK# = Adress# AND Hired = TRUE;
```

---

## 🔁 UPDATE
Ändert bestehende Datensätze.

```sql
UPDATE Employee
SET City = 'Basilea'
WHERE City = 'Basel';
```

- Set-basiert: kann mehrere Zeilen ändern  
- Vorher immer mit **SELECT** prüfen!  
- Backup mit `CREATE TABLE ... AS SELECT ...` empfohlen  

---

## 🧹 DELETE
Löscht Datensätze aus Tabellen.

```sql
DELETE FROM Employee
WHERE City = 'Basilea';
```

- Wirkt auf mehrere Tupel, wenn Bedingung mehrfach erfüllt  
- ⚠️ Immer mit Backup & Test-SELECT prüfen  

---

## 🔍 SELECT
Wählt Daten aus Tabellen aus.

```sql
SELECT Name
FROM Employees
WHERE City = 'Kent';
```

**Syntax:**
```
SELECT <attribute list>
FROM <tables>
WHERE <conditions>;
```

- Logische Operatoren: `AND`, `OR`, `NOT`  
- Vergleichsoperatoren: `=`, `>`, `<`, `<=`, `<>`  

📘 Quelle: Meier & Kaufmann (2023)

---

# 2️⃣ Real-World Data Sources

> „Woher kommen Daten eigentlich?“  
> Datenbanken sind nur so gut wie die Daten, die hineingeladen werden.

---

## 💾 Typische Datenquellen
- CSV- oder Excel-Dateien  
- JSON- oder XML-Daten  
- API-Schnittstellen  
- Streaming-Systeme (Kafka, MQTT etc.)

---

## 🧩 Beispiel – Movie & Rating Data

| Entity | Attribute |
|----------|-----------|
| **Movie** | Movie ID, Title, Release Date, Budget, Genres, Languages |
| **Rating** | User ID, Movie ID, Rating, Timestamp |

**Ziel:**  
CSV-Dateien in **normalisiertes MySQL-Schema** laden.

---

## 🚫 SQL-INSERT vs. Bulk Load

SQL-INSERT ist ineffizient für grosse Dateien, da:
- jede Zeile einzeln geparst wird  
- kein direkter File-Import möglich ist  

**Alternative:**
| Tool | Funktion |
|------|-----------|
| `LOAD DATA LOCAL INFILE` | CSV-Import (MySQL) |
| `MySQL Shell util.importTable` | Parallel & Multithreading |
| `BULK INSERT` | MS SQL |
| `COPY` | PostgreSQL |
| `Tableau Prep`, `Python Pandas` | ETL / Transformationsunterstützung |

---

# 3️⃣ ETL – Extract, Transform, Load

> Klassischer Ansatz zur Datenintegration  
> Erst transformieren, dann laden.

---

## 🔄 Ablauf

```
Extract → Transform → Load
```

1. **Extract** – Daten aus Quellsystemen holen  
2. **Transform** – Formatierung, Typkonvertierung, Mapping  
3. **Load** – In Zieltabellen speichern  

---

## 🧩 Beispiel: MySQL LOAD mit Transformation

```sql
LOAD DATA LOCAL INFILE '/path/to/ratings.csv'
INTO TABLE Ratings
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
IGNORE 1 LINES
(UserID, MovieID, Rating, @Timestamp)
SET Timestamp = FROM_UNIXTIME(@Timestamp);
```

➡️ Umwandlung (Transform) beim Laden – effizienter als nachträgliche Updates.  

---

## ⚙️ MySQL Import-Tools
- **Workbench:** langsam (generiert SQL-INSERTs)
- **LOAD DATA LOCAL INFILE:** schnell, nativ
- **MySQL Shell:** `util.importTable` mit Multithreading  
- **Parallel Loads:** erhöhen Performance durch mehrere Commit-Punkte  

📎 [MySQL Shell Documentation](https://dev.mysql.com/doc/mysql-shell/8.3/en/)

---

# 4️⃣ ELT – Extract, Load, Transform

> Moderne Variante: Daten erst laden, dann transformieren (z. B. mit SQL).

---

## 🔧 Prinzip

```
Extract → Load → Transform
```

1. **Extract:** Quell-Dateien laden  
2. **Load:** 1:1 in Staging Tables (alle Spalten = TEXT)  
3. **Transform:** SQL `INSERT INTO ... SELECT ...` für Zieldatenmodell  

---

## 🧱 Architektur

```
Source Files → Staging Tables → Target Schema
```

- Staging-Tabellen enthalten Rohdaten  
- Zieltabellen erhalten gefilterte & normalisierte Daten  

---

## 💡 Beispiel: JSON-Genres transformieren

JSON-Daten (Beispiel):
```json
[
  {"id": 16, "name": "Animation"},
  {"id": 35, "name": "Comedy"},
  {"id": 10751, "name": "Family"}
]
```

---

### 🧩 Normalisierung in SQL (MySQL)
```sql
INSERT INTO Movie_has_Genre
SELECT DISTINCT MovieID, GenreID
FROM Movies
JOIN JSON_TABLE(Genres, '$[*]'
  COLUMNS (GenreID INTEGER PATH '$.id')
) AS Genres;

INSERT INTO Genre
SELECT DISTINCT GenreID, GenreName
FROM Movies
JOIN JSON_TABLE(Genres, '$[*]'
  COLUMNS (
    GenreID INTEGER PATH '$.id',
    GenreName TEXT PATH '$.name'
  )
) AS Genres;
```

➡️ Trennt Genres in eigene Tabelle – eliminiert Redundanz.  

---

# 5️⃣ Data Matching – Integration verteilter Daten

> Wie kann man Daten aus unterschiedlichen Datenbanken zusammenführen?

---

## ⚠️ Typische Herausforderungen

| Problem | Beispiel |
|----------|-----------|
| Unterschiedliche Strukturen | Spaltennamen: `C_ID` vs. `C_NR` |
| Unterschiedliche Inhalte | Beide haben Spalte `C_ID`, aber verschiedene Werte |
| Fehlende Eindeutigkeit | Kein gemeinsamer Primärschlüssel |
| Datenkonflikte | Gleiche Kunden, aber unterschiedliche Attribute |

---

## 🔍 Lösungsansätze
- **Datenmapping:** Zuordnung unterschiedlicher Spaltennamen  
- **Master Data Management (MDM):** zentrale, einheitliche Datensätze  
- **Data Wrangling / Transformation:** Skriptbasiertes Matching  
- **Fuzzy Matching / KI-Ansätze:** z. B. Ähnlichkeitsabgleich bei Texten  
- **ETL/ELT-Pipelines:** Kombination aus Regeln und statistischem Matching  

---

# 🧾 Zusammenfassung

| Thema | Kernaussage |
|--------|--------------|
| **SQL-DML** | CRUD-Operationen (Create, Read, Update, Delete) |
| **ETL** | Transformation vor dem Laden (klassisch) |
| **ELT** | Transformation nach dem Laden (modern, SQL-basiert) |
| **Bulk Loading** | Schnellere Importe mit nativem DB-Tool |
| **JSON & Normalisierung** | Flattening strukturierter Daten mit SQL |
| **Data Matching** | Harmonisierung unterschiedlicher Quellen |

---

## 💡 Takeaways
- **SQL DML** ist Grundlage jeder Datenmanipulation  
- **ETL**: nützlich für strukturierte, saubere Datenquellen  
- **ELT**: besser bei grossen, heterogenen Quellen (Cloud, JSON, CSV)  
- **Bulk Loading** spart massiv Zeit  
- **Integration** = nicht nur Technik, sondern auch Semantik  

---

**Kontakt:**  
👨‍🏫 Prof. Dr. Michael Kaufmann  
📧 [m.kaufmann@hslu.ch](mailto:m.kaufmann@hslu.ch)  
🏫 Hochschule Luzern – Informatik  

---
