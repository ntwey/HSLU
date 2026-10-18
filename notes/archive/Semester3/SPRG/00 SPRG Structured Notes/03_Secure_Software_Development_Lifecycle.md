# Secure Software Development Lifecycle (SDLC & MSSDLC)

## 1. Software Development Life Cycle (SDLC)
Der SDLC ist ein grundlegendes Framework, das die Aufgaben in jedem Schritt des Softwareentwicklungsprozesses beschreibt und strukturiert. Er dient als Basis, in die später Security-Massnahmen integriert werden.

**Die 6 typischen Phasen des SDLC:**
* **Planning:** Projektplanung, Ressourcenzuteilung und Machbarkeitsanalyse.
* **Defining:** Definition und Dokumentation der Systemanforderungen.
* **Designing:** Architekturentwurf und detailliertes Systemdesign.
* **Building:** Eigentliche Programmierung und Implementierung der Software.
* **Testing:** Überprüfung der Software auf Fehler und Abgleich mit den Anforderungen.
* **Deployment:** Bereitstellung, Betrieb und Wartung der Software in der Produktionsumgebung.

---

## 2. Microsoft Security Development Lifecycle (MSSDLC)
Der MSSDLC erweitert den klassischen Softwareentwicklungszyklus um ganz spezifische Sicherheitsaspekte in jeder Phase. Das Ziel ist es, Schwachstellen proaktiv zu vermeiden ("Secure by Design").

**Die 7 Phasen des MSSDLC & zentrale Sicherheitsmassnahmen:**
1. **Training:**
   * Schulung des Entwicklungsteams in sicherem Programmieren und genereller Security-Awareness.
2. **Requirements:**
   * Durchführung von Security & Privacy Risk Assessments.
   * Definition von klaren Sicherheitsanforderungen.
3. **Design:**
   * **Threat Modeling:** Systematische Bedrohungsmodellierung.
   * Analyse und Minimierung der Attack Surface (Angriffsfläche).
4. **Implementation:**
   * Verwendung sicherer Tools, Compiler und Bibliotheken.
   * **Static analysis:** Statische Code-Analyse zur Erkennung bekannter Muster von Schwachstellen.
5. **Verification:**
   * **Dynamic / Fuzz testing:** Dynamische Tests der laufenden Anwendung (z.B. durch Einspeisen unerwarteter Daten, um Abstürze zu provozieren).
   * Regelmässige Security Reviews.
6. **Release:**
   * Finale Security Review vor dem Go-Live.
   * Definition und Vorbereitung eines **Incident Response** Plans.
7. **Response:**
   * Reaktion auf neu entdeckte Schwachstellen.
   * Ausführung des Incident Response Plans bei tatsächlichen Sicherheitsvorfällen.

---

## 3. Kategorisierung von Requirements
Im Requirements Engineering (insbesondere in der Repetitions-Phase) muss explizit zwischen zwei Arten von Anforderungen unterschieden werden:
* **F (Functional Requirements):** Beschreiben, *was* das System tun soll (z.B. "Der Benutzer muss sich mit E-Mail und Passwort einloggen können").
* **NF (Non-Functional Requirements):** Beschreiben, *wie* das System arbeiten soll. Hierunter fallen Qualitätsmerkmale und vor allem **Sicherheitsanforderungen** (z.B. "Passwörter müssen mit Argon2 gehasht in der Datenbank gespeichert werden").

---

## 4. Fazit: Risikobasierter Ansatz
Ein vollumfänglicher und **risikobasierter Ansatz** ist für die sichere Softwareentwicklung essenziell. 

* **Der Grund:** Es ist in der Praxis unmöglich und wirtschaftlich nicht tragbar, 100%ige Sicherheit zu erreichen. 
* Ein risikobasierter Ansatz hilft dabei, die stets begrenzten Entwicklungs- und Test-Ressourcen genau dort einzusetzen, wo die Wahrscheinlichkeit eines Angriffs und der potenzielle Schaden für das Unternehmen am grössten sind (Ressourcenschonung).