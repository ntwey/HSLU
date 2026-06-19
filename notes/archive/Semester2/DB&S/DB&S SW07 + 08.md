# 📘 Datenbank-Analytik mit SQL – Vorlesung (Zusammenfassung)

**Datum:** 21. Oktober 2025  
**Dozent:** Prof. Dr. Michael Kaufmann  
**Hochschule Luzern – Informatik**

---

## 1. Hintergrund von SQL
SEQUEL ("Structured English QUEry Language") wurde in den 1970er Jahren für System R (IBM) entwickelt.  
Es war eine relationale Abfragesprache, die statt mathematischer Symbole englische Schlüsselwörter wie `SELECT`, `FROM`, `WHERE` verwendete.  
1979 veröffentlichte Oracle (Relational Software Inc.) SQL mit Oracle V2.  
ANSI/ISO standardisierten SQL in Versionen: SQL-86, SQL-89, SQL-92, SQL:1999, SQL:2003, SQL:2006, SQL:2008, SQL:2011, SQL:2016.  
SQL ist relational vollständig und bleibt bis heute die dominierende Sprache zur Datenbankinteraktion.

---

## 2. Relationale Operationen in SQL
SQL kann alle Operatoren der relationalen Algebra darstellen:
- Selektion (WHERE)
- Projektion (SELECT)
- Kartesisches Produkt (FROM)
- Vereinigung (UNION)
- Differenz (NOT IN / EXCEPT)
- Umbenennung (AS)

### Projektion
SQL entfernt Duplikate nur mit DISTINCT:
```sql
SELECT DISTINCT City
FROM Employee;

SELECT Sub, Name
FROM Employee;
```

### Selektion
```sql
SELECT * 
FROM Employee 
WHERE City = 'Kent' AND Sub = 'D6';
```
`*` bedeutet alle Spalten; WHERE enthält logische Operatoren (AND, OR, NOT).

### Kartesisches Produkt
```sql
SELECT E#, Name, Street, Place, Sub, D#, DepartmentName  
FROM Employee, Department;
```

### Inner Join mit WHERE
```sql
SELECT E#, Name, Street, City, Sub, D#, Sub 
FROM Employee, Department 
WHERE Sub = D#;
```

### Alias (Umbenennung)
```sql
SELECT  
  S1.Name AS Participant1, 
  S2.Name AS Participant2 
FROM Sports_Club AS S1, Sports_Club AS S2 
WHERE S1.E# <> S2.E#;
```

---

## 3. Mengenoperationen
**Vereinigung**
```sql
SELECT * 
FROM Sports_Club 
UNION 
SELECT * 
FROM Photo_Club;
```

**Schnittmenge**
```sql
SELECT * 
FROM Sports_Club 
WHERE Sports_Club.E# IN (SELECT E# FROM Photo_Club);
```

**Differenz**
```sql
SELECT * 
FROM Sports_Club 
WHERE Sports_Club.E# NOT IN (SELECT E# FROM Photo_Club);
```

---

## 4. Nullwerte und dreiwertige Logik
Ein `NULL` steht für einen unbekannten Wert.  
Zusätzlich zu TRUE und FALSE existiert der Wahrheitswert UNKNOWN.  
WHERE-Bedingungen geben nur Zeilen mit TRUE weiter.

Beispiele:
```sql
INSERT INTO Studenten (MatrNr, Name) VALUES (12345, 'Edison');

SELECT * FROM Studenten WHERE Semester IS NULL;
SELECT * FROM Studenten WHERE Semester IS NOT NULL;
```

---

## 5. Datenanalysen mit SQL
### Aggregatfunktionen
Aggregatfunktionen berechnen aus Wertemengen einen Skalar:

- COUNT(X)
- COUNT(DISTINCT X)
- SUM(X)
- AVG(X)
- MAX(X)
- MIN(X)

```sql
SELECT COUNT(M#)
FROM Mitarbeiter
WHERE Unt = 'A6';
```

### Gruppierung von Aggregaten
```sql
SELECT COUNT(M#), Unt
FROM Mitarbeiter
GROUP BY Unt;
```

### Deskriptive Statistik mit SQL
1. JOIN von Dimension und Indikator
2. Aggregation (SUM, AVG, STDDEV, MIN, MAX)
3. Gruppierung (GROUP BY)
4. Selektion (WHERE)

```sql
SELECT s.supplier_name, 
       AVG(u.turnover_chf), 
       STDDEV(u.turnover_chf)
FROM ind_turnover u
JOIN dim_supplier s USING(supplier_key)
JOIN dim_time t USING(time_key)
WHERE t.yearmonth = 201901
  AND s.category = 'Software'
GROUP BY s.supplier_name;
```

### Bayessche Statistik mit SQL
Wahrscheinlichkeit p(x) = |{t ∈ T | Xt = x}| / |T|

```sql
SELECT s.supplier_name AS x, 
       t.quarter AS q,
       COUNT(*) AS n, 
       COUNT(*) / COUNT(*) OVER() AS P
FROM ind_orders o
JOIN dim_supplier s USING(supplier_key)
JOIN dim_time t USING(time_key)
GROUP BY s.supplier_name, t.quarter;
```

---

## 6. Unterabfragen (Subqueries)
Beispiel: Durchschnittliche Anzahl Lektionen pro Professor
```sql
SELECT ...
FROM (
  SELECT ...
  FROM ...
  WHERE ...
) alias
WHERE ...;
```

---

## 7. Beispiel: Movie Recommender System

**Ziel:** Filmempfehlung auf Basis von Nutzerbewertungen  
**Quelle:** MovieLens  
**Tools:** MySQL Workbench (Entwickler), Metabase (Endnutzer)

**Zugriffsrechte:**  
- Entwickler: Admin via Port 3306  
- Endnutzer: Nur Lesezugriff via Metabase  

**Technische Umsetzung:**
- MySQL Server + Metabase
- Transformation mit `INSERT INTO ... SELECT`
- Materialisierung: `CREATE TABLE AS SELECT`
- Indexierung: `CREATE INDEX`

### Datenmodell
```sql
CREATE TABLE Movies (
  MovieID INTEGER PRIMARY KEY,
  Title TEXT,
  Release_date DATE,
  Budget INTEGER,
  Spoken_languages JSON
);

CREATE TABLE Ratings (
  UserID INTEGER,
  MovieID INTEGER,
  Rating INTEGER,
  Timestamp DATETIME,
  PRIMARY KEY (MovieID, UserID),
  CONSTRAINT MovieID_FK FOREIGN KEY (MovieID) REFERENCES Movies(MovieID)
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

### SQL-Abfrage: Filmempfehlung
```sql
SELECT MovieID, Title, 
       COUNT(*) AS N5R
FROM Ratings5
WHERE UserID IN ( 
  SELECT UserId
  FROM Ratings5
  WHERE MovieID = 1371
)
GROUP BY MovieID
ORDER BY COUNT(*) DESC
LIMIT 10;
```

**Erklärung:**
1. Auswahl der Spalten MovieID, Title  
2. Zählt Bewertungen pro Film  
3. Tabelle Ratings5  
4–5. Unterabfrage: Nutzer, die Film 1371 bewertet haben  
6–7. Selbstbezug der Tabelle  
4. Gruppierung nach MovieID  
5. Sortierung nach Häufigkeit  
6. Ausgabe der Top 10 Filme  

---

## 8. Fazit
- SQL ist seit 50 Jahren zentral für relationale Datenbanken.  
- Es bleibt mächtig, standardisiert und analytisch vielseitig.  
- In Kombination mit BI-Tools (z. B. Metabase) bleibt SQL ein Schlüsselwerkzeug moderner Datenanalyse.

---

**Prof. Dr. Michael Kaufmann**  
Hochschule Luzern – Informatik  
📧 m.kaufmann@hslu.ch  
