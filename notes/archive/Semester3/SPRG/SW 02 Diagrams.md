# Zusammenfassung: Requirements Engineering (Tag 2)

## 1. System- und Kontextabgrenzung
* **Systemkontext**: Der Teil der Umgebung, der für die Definition und das Verständnis der Anforderungen relevant ist.
* **Systemgrenze**: Trennt das zu entwickelnde System vom Kontext (Entscheidung: Was gehört zum System, was nicht?).
* **Kontextgrenze**: Trennt den relevanten Kontext von der irrelevanten Umgebung.
* **Grauzone**: Da Systemschnittstellen oft erst am Ende des RE-Prozesses präzise definiert sind, existiert anfangs eine vage Trennung.

## 2. Hauptaktivitäten im Requirements Engineering
Der Prozess basiert auf vier Kernsäulen:
1. **Elicitation (Ermittlung)**: Gewinnung von Anforderungen aus verschiedenen Quellen (Stakeholder, Dokumente).
2. **Documentation (Dokumentation)**: Adäquate Beschreibung der Anforderungen (natürliche Sprache oder Modelle).
3. **Validation & Negotiation (Prüfung & Abstimmung)**: Sicherstellung der Qualität und Konsensfindung.
4. **Management**: Strukturierung, Verwaltung von Änderungen und Sicherstellung der Umsetzung über den gesamten Lebenszyklus.

## 3. Stakeholder-Management
* **Rolle des RE-Ingenieurs**: Er spricht die Sprache der Stakeholder, kennt die Anwendungsdomäne und pflegt respektvolle Beziehungen.
* **Rolle der Stakeholder**: Sie liefern Anforderungen, priorisieren diese und treffen zeitnahe Entscheidungen.
* **Stakeholder-Analyse**: Identifikation von Kunden, Nutzern, Entscheidern und regulatorischen Instanzen ist essenziell für den Projekterfolg.

## 4. Ermittlungstechniken (Elicitation)
Es gibt keine Universalmethode; die Wahl hängt von Budget, Zeit und Stakeholdern ab:
* **Interviews**: Tiefgehende Infos, aber zeitintensiv.
* **Fragebögen**: Viele Teilnehmer in kurzer Zeit, aber kein direktes Feedback.
* **Workshops**: Schnelle Ergebnisse durch Gruppeninteraktion.
* **Kreativitätstechniken**: 
    * **Brainstorming**: Offene Sammlung in Gruppen.
    * **6-3-5 Methode**: Strukturiertes Brainwriting (6 Personen, 3 Ideen, 5 Minuten pro Runde).

## 5. Dokumentation von Anforderungen
### Natürliche Sprache
* Vorteil: Keine neue Notation nötig.
* Risiko: Mehrdeutigkeit.
* Lösung: Verwendung von **Satz-Templates** (z. B. "Das System MUSS/SOLL/KANN
*  bereitstellen").

### Konzeptuelle Modelle (UML)
* **Use Case Diagramme**: Überblick über Systemfunktionen aus Nutzersicht.
* **Klassendiagramme**: Dokumentation der statischen Datenstruktur und Domänenbegriffe.
* **Aktivitätsdiagramme**: Darstellung von Geschäftsprozessen und Abläufen.
* **Zustandsdiagramme**: Dokumentation des ereignisgesteuerten Verhaltens.

## 6. Datenflussdiagramme (DFD) & Security
* **Fokus**: Transformation von Input zu Output durch Prozesse und Datenspeicher.
* **Trust Boundaries**: Wichtig für die Sicherheit. Übergänge zwischen Netzwerken oder verschiedenen Privilegienstufen sind potenzielle Angriffspunkte.
* **Regel**: Daten fließen nicht "magisch"; sie müssen immer durch einen Prozess laufen.

## 7. Validierung & Abnahme
* Die Qualitätssicherung erfolgt durch interne Reviews und die formale Abnahme (Sign-off) durch den Kunden.
* **Versionsmanagement**: Änderungen müssen nachverfolgt werden (Traceability), besonders wenn sie Auswirkungen auf Implementierung und Tests haben.

---

## Key Takeaways
* **Fokus auf das "WAS"**: RE definiert, was erstellt werden soll, nicht wie es technisch gelöst wird.
* **Innovation erzwingen**: Nicht nur bauen, was der Kunde sagt, sondern durch Kreativitätstechniken und Metaphern echte Innovation fördern.
* **Risikoorientierung**: Bei Zeitdruck sollten riskante Anforderungen detaillierter spezifiziert werden als unkritische ("Good enough" statt Perfektionismus).
* **Glossar ist Pflicht**: Missverständnisse durch unterschiedliche Interpretationen technischer Begriffe sind eine der häufigsten Fehlerquellen.
* **Security by Design**: Datenflüsse und Vertrauensgrenzen (Trust Boundaries) müssen frühzeitig in Diagrammen identifiziert werden.