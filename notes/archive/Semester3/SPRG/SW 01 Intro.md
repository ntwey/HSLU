![[1_Day_SPRG_FS2026.pdf]]

# Zusammenfassung: Secure Requirements Engineering (FS 2026)

## 📌 Metadaten
* **Thema**: Grundlagen, Prozesse und Methoden des Secure Requirements Engineering.
* **Dozent**: Silvan Leuenberger (Chief Cyber Defense Officer bei Swiss Post Cybersecurity AG).
* **Institution**: HSLU / Swiss Post Cybersecurity AG.
* **Jahr**: 2026.

---

## 🏢 Über Swiss Post Cybersecurity AG (SPCS)
* **Entstehung**: Gegründet 2024 durch den Zusammenschluss der Firmen terreActive (gegr. 1996) und Hacknowledge (gegr. 2016).
* **Status**: Eine autonome, marktorientierte Tochtergesellschaft der Schweizerischen Post, die unabhängig von deren interner IT-Abteilung operiert.
* **Eckdaten**: Über 150 Mitarbeiter an Standorten in Aarau (Hauptsitz), Morges, Zürich und Luxemburg.
* **Fokus**: 360° Cybersecurity; das Cyber Defense Center (SOC) macht etwa 75 % des Geschäfts aus.

---

## 🛠 Das Kernkonzept: Requirements Engineering (RE)

### Definition & Ziel
* **Business Analyse**: Forschungsdisziplin zur Identifizierung von Geschäftsanforderungen und Bestimmung von Lösungen für Probleme.
* **Requirements Engineering**: Prozess des Formulierens, Dokumentierens und Verwaltens von Softwareanforderungen.
* **Hauptziel**: Das Risiko minimieren, ein System zu liefern, das nicht den Bedürfnissen der Stakeholder entspricht.
### Wirtschaftliche Relevanz
* **Kosten von Fehlern**: Je später ein Fehler im Projektzyklus entdeckt wird, desto teurer ist seine Behebung.
* **Vergleich**: Ein Fehler in der Anforderungsphase (Referenzwert 1) kostet im Betrieb im Durchschnitt das **250-fache**.

---

## 🔒 Security im Requirements Engineering

### Security als Qualitätsanforderung
* **Klassifizierung**: Sicherheit wird primär als **nicht-funktionale Anforderung** (Qualitätsanforderung) klassifiziert.
* **Software Assurance (SwA)**: Das Vertrauensniveau, dass Software frei von absichtlichen oder zufälligen Schwachstellen ist und vorhersehbar funktioniert.

### Die Rolle des Requirements Engineer (RE)
* **Zentraler Punkt**: Der RE steht im Zentrum und hält direkten Kontakt zu den Stakeholdern.
* **Sicherheitsfokus**: Da Entwickler Sicherheit oft nicht als Hauptziel haben, muss der RE Sicherheits-Know-how aktiv einbringen.
* **Creator Blindness**: Entwickler sind oft "betriebsblind" gegenüber eigenem Code; der RE hilft, diese Hürde zu überwinden.

---

## 📊 Anforderungsarten & Dokumentation

### Arten von Anforderungen
* **Funktional**: Bezieht sich auf das Verhalten und die Ergebnisse einer Systemfunktion (z. B. Login).
* **Qualitätsanforderungen (Nicht-funktional)**: Aspekte wie Performance, Verfügbarkeit, Skalierbarkeit und Sicherheit.
* **Constraints (Randbedingungen)**: Anforderungen, die den Lösungsraum einschränken (z. B. Compliance-Vorgaben wie PCI-DSS).

### Dokumentationstechniken
* **Modelle**: Verwendung von System-Komponentendiagrammen, Use-Case-Diagrammen und Business-Object-Modellen.
* **Visualisierung**: Screen Flows (Navigation zwischen Bildschirmen) und detaillierte Inhaltsbeschreibungen.
* **Systemgrenzen**: Klare Trennung zwischen dem System, dem Systemkontext und der irrelevanten Umgebung.

---

## 💡 Wichtigste Key Take-Aways

* **Frühe Investition spart massiv Geld**: Die Kosten für Requirements Engineering stehen im umgekehrten Verhältnis zum Risiko, das man bereit ist zu tragen.
* **Security ist kein Zufallsprodukt**: Sicherheit muss als explizite Qualitätsanforderung erhoben werden, da Kunden sich der Risiken oft nicht bewusst sind.
* **Vier Kernaktivitäten**: Ermittlung (Elicitation), Dokumentation, Validierung/Verhandlung und Management sind die Grundpfeiler jedes RE-Prozesses.
* **Analytisches Profil**: Ein guter RE benötigt neben Fachwissen vor allem Soft Skills wie Empathie, Konfliktlösungsfähigkeit und Überzeugungskraft.
* **Agil vs. Statisch**: Während der Wasserfall alle Anforderungen vorab klärt, erfolgt RE im agilen Umfeld kontinuierlich "on the move".
