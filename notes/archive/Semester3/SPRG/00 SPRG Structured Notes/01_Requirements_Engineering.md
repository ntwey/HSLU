# Requirements Engineering (RE) & Security

Das Requirements Engineering (RE) ist der Prozess des Formulierens, Dokumentierens und Verwaltens von Softwareanforderungen. Das Hauptziel ist die Minimierung des Risikos, ein System zu liefern, das nicht den Bedürfnissen der Stakeholder oder regulatorischen Vorgaben entspricht.

## 1. Warum RE für Security essenziell ist
* **Kosten von Fehlern:** Je später ein Fehler im Projektzyklus entdeckt wird, desto teurer ist seine Behebung. Ein Fehler in der Anforderungsphase (Referenzwert 1) kostet im Betrieb im Durchschnitt das 250-fache.
* **Security als Qualitätsanforderung:** Sicherheit wird primär als nicht-funktionale Anforderung (Qualitätsanforderung) klassifiziert. Sie definiert, wie stabil und widerstandsfähig ein System gegen Angriffe ist.
* **Software Assurance (SwA):** Das Vertrauensniveau, dass Software frei von absichtlichen oder zufälligen Schwachstellen ist und absolut vorhersehbar funktioniert.
* **Creator Blindness:** Entwickler sind oft "betriebsblind" gegenüber ihrem eigenen Code. Da Entwickler Sicherheit selten als primäres funktionales Ziel vor Augen haben, muss der RE dieses Know-how von Anfang an aktiv einbringen.

## 2. Kernaktivitäten im RE
Der Prozess basiert kontinuierlich auf vier Hauptsäulen:
1. **Elicitation (Ermittlung):** Gewinnung von Anforderungen. 
2. **Documentation (Dokumentation):** Adäquate Beschreibung in natürlicher Sprache oder durch Modelle.
3. **Validation & Negotiation (Prüfung & Abstimmung):** Sicherstellung der Qualität.
4. **Management:** Strukturierung und Verwaltung der Traceability (Nachverfolgbarkeit).

## 3. Ermittlungstechniken (Prüfungs-Fokus: Vor- und Nachteile)
Es gibt keine Universalmethode. Die Wahl hängt von Stakeholdern, Zeit und Budget ab.

| Technik | Vorteile | Nachteile |
| :--- | :--- | :--- |
| **Interviews** | Liefert sehr tiefgehende, detaillierte Informationen; direkte Rückfragen möglich. | Sehr zeitintensiv in Vorbereitung und Ausführung; erreicht nur wenige Personen. |
| **Fragebögen** | Erreicht viele Teilnehmer in kurzer Zeit; quantitative Auswertung ist einfach. | Kein direktes Feedback möglich; Missverständnisse bei Fragen können nicht sofort geklärt werden. |
| **Workshops** | Schnelle Konsensbildung durch Gruppendynamik; schnelle Ergebnisse. | Terminfindung für alle Key-Stakeholder ist schwierig; dominante Personen können die Gruppe beeinflussen. |

## 4. Praxisbeispiel: Anforderungen an einen intelligenten Kaffeeautomaten

### Funktionale Anforderungen (Verhalten und Ergebnisse)
* Produktinteraktion: Kaltes oder heisses Getränk auswählen.
* Zahlung: Zahlung mit Karte und Bargeld, Geldrückgabe.
* Belege: Quittung digital oder physisch ausgeben.

### Qualitätsanforderungen / Nicht-Funktionale Anforderungen (Rahmenbedingungen)
* **Sicherheit & Compliance:** Währungsüberprüfung, **PCI DSS Compliance** für Kartenzahlungen.
* **Verfügbarkeit:** Dauerbetrieb (24/7), Failover Cluster.
* **Ergonomie:** Touch Display, korrekte Höhe der Ausgabe.

Siehe auch: [[02_Systemmodellierung_und_UML]] für die visuelle Modellierung dieser Use Cases.