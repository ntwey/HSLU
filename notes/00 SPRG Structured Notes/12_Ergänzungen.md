
### 1. Requirements Engineering (Ergänzungen)
* **Befragungstechniken:** Bei der Erhebung von Anforderungen bei Stakeholdern wird methodisch zwischen **offenen Fragen** (für breite, detaillierte Antworten) und **geschlossenen Fragen** (z. B. Ja/Nein oder Multiple-Choice für enge Eingrenzungen) unterschieden [1].
* **Qualitätskriterien für Requirements:** Ein gutes Anforderungsdokument muss zwingend Kriterien wie **Eindeutigkeit, Konsistenz, Modifizierbarkeit, Vollständigkeit und Nachverfolgbarkeit (Traceability)** erfüllen.
* **Bedeutung des Glossars:** Ein sehr häufiger Grund für Konflikte in Projekten sind unterschiedliche Interpretationen von Begriffen (z. B. Synonyme, Homonyme, Abkürzungen). Ein Glossar ist daher unerlässlich, um das Verständnis bei allen Parteien anzugleichen.
* **Ursachen für Bugs:** Softwarefehler und Schwachstellen entstehen nicht nur durch einfache Tippfehler, sondern sehr oft durch **mangelnde Kommunikation, sich ändernde Anforderungen, veraltete Automatisierungsskripte** oder schlichtweg durch ein zu grosses Ego/Selbstüberschätzung bei den Entwicklern ("nobody is perfect").

### 2. Detaillierte Systemmodellierung & UML-Diagramme
Während in Zusammenfassungen oft nur DFDs (Data Flow Diagrams) behandelt werden, fordern die Folien die genaue Kenntnis folgender UML-Diagramme aus der Systemanalyse:
* **Use Case Diagram (UCD):** Beschreibt die funktionale Endnutzer-Perspektive (eingeführt von Dr. Ivar Jacobson). Es dokumentiert den Dialog mit dem System, beinhaltet jedoch keine Implementierungsdetails. Es hat 4 Kernelemente: **Akteure** (wer), das **System(grenze)**, die **Use Cases** (was/welcher Zweck) und die **Relationen** (Verbindungslinien).
* **Class Diagram (Klassendiagramm):** Dokumentiert die *statische* Struktur der Daten und Systemabhängigkeiten. Wichtige Modellelemente sind hier Klassen, Attribute, Methoden und Multiplizitäten (wie `1..*`).
* **Activity Diagram (Aktivitätsdiagramm):** Modelliert Geschäftsprozesse oder sequentielle Abläufe innerhalb des Systemkontexts (inklusive paralleler Ausführungen via Fork/Join und Verzweigungen/Entscheidungen).
* **State Diagram (Zustandsdiagramm):** Dokumentiert das *ereignisgesteuerte* Verhalten eines Systems (Zustände, auslösende Events und die entsprechenden Übergangsbedingungen, z.B. *GPS-Signal verloren $\rightarrow$ Warnung ausgeben*).

### 3. Konkrete Mitigation-Techniken (STRIDE-Ergänzung)
In den Folien werden den STRIDE-Bedrohungen spezifische technische Abwehrmassnahmen (Mitigations) zugeordnet, die du kennen solltest:
* **Spoofing (Schutzziel: Authentifizierung):** Abwehr durch Cookie-Authentifizierung, Kerberos, PKI-Systeme (SSL/TLS) oder digitale Signaturen.
* **Tampering (Schutzziel: Integrität):** Abwehr durch Access Control Lists (ACLs), Hashes, MACs, digitale Signaturen oder manipulationssichere Protokolle.
* **Repudiation (Schutzziel: Nicht-Abstreitbarkeit):** Revisionssicheres Logging und Auditing, Audit-Trails, Timestamps und digitale Signaturen.
* **Information Disclosure (Schutzziel: Vertraulichkeit):** Autorisierung (ACLs), Verschlüsselung, Schutz von Secrets und Privacy-Enhancing-Protokolle.
* **Denial of Service (Schutzziel: Verfügbarkeit):** Filtern, Throttling (Drosselung), Quality of Service (QoS) und korrekte Autorisierung/Authentifizierung.

### 4. OWASP Top 10, API Security & SSRF
* **OWASP API Security Top 10:** Neben den klassischen Top 10 existiert ein eigenes Projekt für APIs. Spezifische Risiken sind hier z.B. *API1: Broken Object Level Authorization*, *API4: Unrestricted Resource Consumption* und *API10: Unsafe Consumption of APIs*.
* **SSRF (Server-Side Request Forgery - A10):** Ein kritischer Angriff, bei dem ein Angreifer den Webserver zwingt, bösartige HTTP-Anfragen an interne (geschützte) oder externe Drittsysteme zu stellen, um so z.B. Firewalls zu umgehen.
  * **Gegenmassnahmen:** Niemals nutzergesteuerte Eingaben direkt in URLs übernehmen, striktes Whitelisting zur Input-Validierung und Netzwerk-Segregation anwenden.
* **OWASP Top 10 Evolution:** Die Top 10 sind nicht statisch. Du solltest wissen, dass sich Kategorien weiterentwickeln und anpassen (gezeigt anhand der Versionen 2017 vs. 2021 vs. 2025 RC). So wurde 2021 beispielsweise *Insecure Design* (A04) neu aufgenommen.
