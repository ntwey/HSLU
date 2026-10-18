# Threat Modeling Deep Dive: STRIDE & DREAD

Beim Threat Modeling (der Bedrohungsanalyse) in der Designphase eines Softwareprojekts greifen zwei essenzielle Methoden nahtlos ineinander: **STRIDE** wird genutzt, um systematisch Bedrohungen zu identifizieren, während **DREAD** im Anschluss verwendet wird, um diese gefundenen Risiken messbar zu bewerten.

---

## 1. STRIDE: Bedrohungen identifizieren (Das "Was kann passieren?")
STRIDE ist ein Framework zur Klassifizierung von Bedrohungen. Es dient als strukturierte Gedächtnisstütze. Die Regel lautet: An jedem Element eines Datenflussdiagramms (DFD), das eine Vertrauensgrenze (Trust Boundary) überschreitet, muss STRIDE angewendet werden. 

Jeder Buchstabe steht für eine Bedrohungskategorie, die ein spezifisches Schutzziel der IT-Sicherheit angreift:

| Buchstabe | Bedrohung (Threat) | Angegriffenes Schutzziel | Typische Gegenmassnahmen (Mitigation) |
| :--- | :--- | :--- | :--- |
| **S** | **Spoofing** (Identitätsvortäuschung) | Authentifizierung | Starke Authentifizierung, MFA, PKI, SSL/TLS, sichere Cookies. |
| **T** | **Tampering** (Datenmanipulation) | Integrität | Autorisierungsprüfungen, kryptografische Hashes, digitale Signaturen, ACLs. |
| **R** | **Repudiation** (Abstreitbarkeit) | Nicht-Abstreitbarkeit | Digitales Signieren, sichere Zeitstempel, lückenlose Audit-Logs. |
| **I** | **Information Disclosure** (Informationsenthüllung) | Vertraulichkeit | Durchgehende Verschlüsselung (at rest / in transit), restriktive ACLs, Datenschutzprotokolle. |
| **D** | **Denial of Service** (Dienstverweigerung) | Verfügbarkeit | Quotenregelungen, Throttling (Drosselung), IP-Filterung, Authentifizierung vor Ressourcenfreigabe. |
| **E** | **Elevation of Privilege** (Rechteausweitung) | Autorisierung | Konsequentes Prinzip der geringsten Rechte (Least Privilege), strenge Input-Validierung. |

---

## 2. DREAD: Risiken bewerten (Das "Wie schlimm ist es?")
Nicht jede Bedrohung, die mit STRIDE gefunden wird, ist gleich kritisch. Um zu entscheiden, welche Sicherheitslücken zuerst behoben werden müssen (Priorisierung), wird das DREAD-Modell angewandt. Es wandelt subjektive Ängste in messbare Werte um.

DREAD bewertet jede identifizierte Bedrohung in fünf Kategorien:

* **Damage (Schadenspotenzial):** Wie gross ist der finanzielle, technische oder rufschädigende Schaden, wenn der Angriff erfolgreich ist?
* **Reproducibility (Reproduzierbarkeit):** Wie einfach lässt sich der Angriff wiederholen? (Ein Angriff, der jedes Mal klappt, ist gefährlicher als einer, der spezielle Timing-Zufälle benötigt).
* **Exploitability (Ausnutzbarkeit):** Wie viel technisches Wissen, Zeit und Werkzeug benötigt ein Angreifer, um die Schwachstelle auszunutzen?
* **Affected Users (Betroffene Nutzer):** Wie viele Nutzer oder Systeme sind von dem Ausfall oder Datenleck betroffen (einzelne User vs. die gesamte Datenbank)?
* **Discoverability (Entdeckbarkeit):** Wie leicht ist die Schwachstelle von aussen überhaupt zu finden?

### Berechnung und Nutzen
In der Praxis vergibt man pro Kategorie Punkte (meist von 1 bis 3). Diese Punkte werden addiert, um einen Gesamtwert zu ermitteln, der das Risiko in **High**, **Medium** oder **Low** einstuft. 

**Der massive Vorteil:** Dieses Vorgehen hilft bei der gezielten Priorisierung im Projektmanagement. Entwickler können sich dadurch zuerst um die "billigen" (einfach auszunutzenden) und gleichzeitig schädlichsten Angriffswege kümmern, bevor sie Zeit in komplexe, aber unwahrscheinliche theoretische Risiken investieren. Alternativ zu DREAD wird in der modernen Industrie oft das standardisierte CVSS (Common Vulnerability Scoring System) herangezogen.

---

## Zusammenfassung für die Prüfung
* **Schritt 1:** Zeichne das System (DFD) und identifiziere die Vertrauensgrenzen.
* **Schritt 2:** Wende **STRIDE** an, um alle theoretisch möglichen Bedrohungen an diesen Grenzen systematisch zu finden.
* **Schritt 3:** Nutze **DREAD**, um die gefundenen Bedrohungen zu bewerten und die Umsetzung der Gegenmassnahmen (Mitigations) zu priorisieren.