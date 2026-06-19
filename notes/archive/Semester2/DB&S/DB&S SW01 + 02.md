# 🧠 Datenmanagement – Einführungsvorlesung

**Dozent:** Prof. Dr. Michael Kaufmann  
**Datum:** 16. September 2025  
**Fach:** Datenmanagement  
**Hochschule Luzern – Informatik**

---

## 📋 Themenübersicht
1. Was ist eine Datenbank?  
2. Dateisystem vs. Datenbank  
3. Datenmanagement vs. Datenbankmanagement  
4. SQL und NoSQL  
5. Big Data & Datenexplosion  
6. Datenwertschöpfung und Data Value Cycle  
7. Systemarchitektur (Beispiel Semesterprojekt)  

---

# 1️⃣ Was ist eine Datenbank?

> „Eine Datenbank ist eine organisierte und strukturierte Menge von Datensätzen,  
> die für einen gemeinsamen Zweck gespeichert und verwaltet wird.“  
> – *Kaufmann & Meier, SQL- & NoSQL-Datenbanken, Springer 2023*

**Begriffserklärung:**
- **Datensatz:** Einheit, die einen komplexen Sachverhalt beschreibt  
- **Datenbank:** Sammlung solcher Datensätze, organisiert durch ein DBMS (Datenbank-Management-System)

📚 Quelle: [SpringerLink DOI 10.1007/978-3-662-67092-7](https://doi.org/10.1007/978-3-662-67092-7)

---

## 💭 Frage:
> Brauchen wir im Zeitalter von KI noch Datenbanken?

➡️ Antwort: **Ja.**  
Auch bei KI- und Cloud-Anwendungen ist strukturierte Datenspeicherung die Grundlage für  
Nachvollziehbarkeit, Reproduzierbarkeit und Datenschutz.

---

# 2️⃣ Datenexplosion – „Daten schlafen nie“

## 🌍 Historischer Kontext
- Seit **2002**: Beginn des **digitalen Zeitalters**
- Weltweite Speicherfähigkeit 1986–2014:  
  von **2,6 Exabyte** → **4,6 Zettabyte**  
  (jährliches Wachstum ca. 30 %)
- 2002 erstmals: **mehr digitale als analoge** gespeicherte Informationen

📊 Quelle: Martin Hilbert, *Encyclopedia of Big Data* (2017)

---

## 📈 „Mehr ist anders“
> Anderson, P.W. (1972): *More is Different.*  
> Nature of Hierarchical Structures in Science.

**Gedanke:**  
Exponentielles Datenwachstum verändert nicht nur *Mengen*,  
sondern auch *Methoden*, *Organisation* und *Erkenntnisprozesse.*

---

## ❓ Reflexionsfrage:
> Was ändert sich, wenn global verfügbare Datenmengen exponentiell steigen?

➡️ **Antwortideen:**
- Bedeutung von Datenqualität & Metadaten nimmt zu  
- Automatisierung & KI nötig zur Auswertung  
- Datensilos werden zu Innovationshindernissen  
- Datenschutz & Governance werden kritisch  

---

# 3️⃣ Datei vs. Datenbank

## 💾 Warum nicht einfach Dateien?

| Datei-System | Datenbank-System |
|---------------|------------------|
| Unstrukturierte Ablage | Strukturierte Datenspeicherung |
| Einzelzugriff pro Anwendung | Gleichzeitiger Multiuser-Zugriff |
| Keine Integritätskontrolle | ACID-Transaktionen |
| Keine Abfragesprache | SQL & Indexierung |
| Sicherheitsrisiken | Zugriffskontrolle & Rollen |
| Kein Metadatenmanagement | Einheitliche Datenbeschreibung |

---

## 🧩 Datenbank-Management-System (DBMS)
**Komponenten:**
- Datenbankserver  
- Datenbankdatei (Speicher)  
- Anwendungen (Clients)  
- Benutzer / Rollenverwaltung  

**Funktionen:**
- Transaktionsmanagement  
- Datenkonsistenz & Integrität  
- Performanceoptimierung  
- Backup & Recovery  
- Sicherheit auf Datensatzebene  

---

## 🧠 Warum verwenden wir Datenbanken?
- Multiuser-Operationen & Transaktionskontrolle  
- Automatische Sicherung von Konsistenz  
- Effiziente Abfrage & Indizierung  
- Trennung von Daten & Anwendungen  
- Einheitliches Schema- und Metadatenmanagement  
- Wiederverwendung & Wartbarkeit  

---

# 4️⃣ SQL & NoSQL

## 💬 Strukturierte Abfragesprache SQL
- Standard für **relationale Datenbanken**
- Deklarativ → *Was* soll abgefragt werden, nicht *wie*  
- Beispiele:
  ```sql
  SELECT * FROM students WHERE grade > 5.0;
  ```

---

## 🧩 Realität: SQL- und NoSQL-Datenbanken

| Kategorie | Eigenschaften |
|------------|----------------|
| **SQL (relational)** | Tabellen, strukturierte Daten, feste Schemas |
| **NoSQL (nicht-relational)** | Flexibel, schemafrei, verteilt, skalierbar |
| **Beispiele SQL** | MySQL, PostgreSQL, Oracle, MS SQL Server |
| **Beispiele NoSQL** | MongoDB, Cassandra, Redis, Neo4j |

**Einsatz:**
- SQL → Strukturierte, stabile Daten  
- NoSQL → Unstrukturierte, grosse Datenmengen / Big Data  

---

## 💡 Big Data-Herausforderungen (Demchenko et al., 2013)
- **Horizontale Skalierung** (Verteilung auf mehrere Server)
- **Schema-Freiheit** (NoSQL)
- **In-Memory-Verarbeitung**
- **Parallelisierung**

➡️ Optimierung für grosse, verteilte Datenvolumen und Echtzeitanalysen.

---

# 5️⃣ Datenmanagement

## 📘 Definition
> „Datenmanagement umfasst alle Methoden, die sich auf die Wertschöpfung mit der Ressource Daten beziehen.“  
> – Wikipedia, *Data Management*

**Abgrenzung:**
| Datenmanagement | Datenbankmanagement |
|-----------------|----------------------|
| Strategische & organisatorische Perspektive | Technische & operative Verwaltung |
| Datenqualität, Governance, Compliance | Abfragen, Speichern, Optimieren |
| Fokus auf Data Value Cycle | Fokus auf Datenbankbetrieb |

---

# 6️⃣ Data Value Cycle (OECD, 2015)

> Zyklus der Datenwertschöpfung:

1. **Datenerhebung (Collection)**  
2. **Speicherung & Verwaltung**  
3. **Analyse (Analytics)**  
4. **Entscheidung & Nutzung (Decision Making)**  
5. **Mehrwert (Value Added)**

➡️ Daten sind Wirtschaftsgut – ihr Nutzen entsteht erst durch **Analyse & Anwendung**.

---

# 7️⃣ Beispiel: Movie Recommendation System

## 🎬 Ziel
Ein System, das auf Basis der Lieblingsfilme neue Filme vorschlägt.

**Komponenten:**
- Datenquelle (Filmdatenbank)
- Abfrage & Filter (SQL)
- Analyse (Recommender Logic)
- Visualisierung (Web-Interface)
- Benutzerinteraktion  

📊 Demo: *Metabase Workbench (ILIAS)*  

---

## 🧱 Systemarchitektur (Beispiel Semesterprojekt)

```
+-------------------------+
|   Local Client Machine  |
|-------------------------|
| MySQL Workbench         |
| dbm.sqlite / MySQL DB   |
+-------------------------+
         │ SQL
         ▼
+-------------------------+
|   Virtual Server (HSLU) |
|   MySQL Server          |
|   Port 3306             |
+-------------------------+
         │
         ▼
+-------------------------+
| Metabase Server         |
| Web App Port 3000       |
+-------------------------+
         │
         ▼
+-------------------------+
| End User (Browser)      |
+-------------------------+
```

**Technologien:**
- MySQL, Metabase, VPN/RDP, Browser  
- Integration über Ports (3306, 3000, 3389)

---

# 8️⃣ Planung einer Datenbankanwendung

**Eckpfeiler der Datenverwaltung:**

| Ebene | Aufgaben |
|--------|-----------|
| **Datenarchitektur** | Systemkomponenten, Datenmodell, Schema |
| **Datentechnik** | DB-Software, Eingabe, Verarbeitung, Performance |
| **Datenadministration** | Organisation, Zugriff, Rechte |
| **Datennutzung** | Anwendungsfälle, Visualisierung, Interaktion |

---

# 🧭 Semesterprojekt – Expedition

Phasenübersicht:
1. **Projektidee entwickeln**  
2. **DB-Struktur definieren**  
3. **Daten laden (Loading)**  
4. **Analyse & Visualisierung**  
5. **Security & Performance**  
6. **Lessons Learned**

---

# 🧾 Zusammenfassung

| Thema | Kernaussage |
|--------|--------------|
| **Datenbank** | Strukturierte Sammlung von Datensätzen |
| **Dateisystem vs. DB** | DB bietet Konsistenz, Mehrbenutzerfähigkeit, Sicherheit |
| **SQL vs. NoSQL** | Unterschiedliche Modelle für strukturierte & unstrukturierte Daten |
| **Datenmanagement** | Wertschöpfung & Governance rund um Daten |
| **Data Value Cycle** | Von Datenerfassung bis Entscheidungsunterstützung |
| **Architektur** | Mehrschichtige Systeme für Analyse & Präsentation |
| **Big Data** | Skalierung, Parallelität & Schema-Freiheit als neue Paradigmen |

---

## 💡 Takeaways
- Daten sind die Grundlage der Digitalisierung  
- Datenmanagement ist strategisch, nicht nur technisch  
- Datenbanken = Rückgrat moderner Informationssysteme  
- Big Data & KI verstärken die Bedeutung strukturierter Datensysteme  
- Praxis: Vom Datenmodell bis zur Nutzerinteraktion denken  

---

**Kontakt:**  
👨‍🏫 Prof. Dr. Michael Kaufmann  
📧 [m.kaufmann@hslu.ch](mailto:m.kaufmann@hslu.ch)  
📞 +41 41 757 68 48  
🏫 Hochschule Luzern – Informatik  

---
