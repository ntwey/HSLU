# Threat Modeling (Bedrohungsanalyse)

Threat Modeling ist ein strukturierter, risikobasierter Prozess zur systematischen Identifizierung von primären Assets, potenziellen Schwachstellen und optimalen Gegenmassnahmen im Applikations-Design.

## 1. Wichtige Begriffsunterscheidung (Prüfungs-Fokus)
Für das Verständnis von Risiken muss die Kette aus Schwachstelle, Bedrohung und der daraus resultierenden Aktion verstanden werden:

* **Vulnerability (Schwachstelle):** Ein Fehler im System (identifizierbar, korrigierbar).
* **Threat (Bedrohung):** Wer oder was die Schwachstelle ausnutzt (nicht kontrollierbar).
* **Threat Action (Aktion):** Was der Angreifer konkret tut.

**5 Beispiele für diese Wirkungskette:**
1. *Vulnerability:* Unvalidiertes Datenbank-Eingabefeld. $\rightarrow$ *Threat:* Cyberkrimineller. $\rightarrow$ *Action:* Führt eine SQL-Injection aus, um Daten zu stehlen.
2. *Vulnerability:* Fehlende Limitierung der Login-Versuche. $\rightarrow$ *Threat:* Automatisiertes Botnet. $\rightarrow$ *Action:* Führt einen Brute-Force-Angriff auf Passwörter aus.
3. *Vulnerability:* Veraltete Software-Bibliothek mit CVE. $\rightarrow$ *Threat:* Skript-Kiddie. $\rightarrow$ *Action:* Nutzt einen bekannten öffentlichen Exploit, um den Server zu übernehmen.
4. *Vulnerability:* Keine TLS-Verschlüsselung (HTTP). $\rightarrow$ *Threat:* Böswilliger Insider im Netzwerk. $\rightarrow$ *Action:* Führt eine Man-in-the-Middle (MitM) Attacke durch und liest Passwörter im Klartext mit.
5. *Vulnerability:* Fehlendes Hashing bei der Passwortspeicherung. $\rightarrow$ *Threat:* Ehemaliger Admin / Datenbank-Dieb. $\rightarrow$ *Action:* Verkauft die Klartext-Passwörter im Darknet.

## 2. Der Prozess der Bedrohungsanalyse
1. **Assets identifizieren:** Welche schützenswerten Daten und Systemkomponenten existieren?
2. **Architektur dokumentieren:** Erstellung detaillierter Datenflussdiagramme (DFDs).
3. **Bedrohungen identifizieren (STRIDE):** Was kann an Vertrauensgrenzen schiefgehen?
4. **Bewertung der Risiken (DREAD/CVSS):** Priorisierung der gefundenen Probleme.
5. **Gegenmassnahmen entwickeln (Mitigation).**

### Das STRIDE-Framework (Die Bedrohungen)
* **S**poofing (Identitätsvortäuschung) $\rightarrow$ *Ziel:* Authentifizierung.
* **T**ampering (Manipulation) $\rightarrow$ *Ziel:* Integrität. 
* **R**epudiation (Abstreitbarkeit) $\rightarrow$ *Ziel:* Nicht-Abstreitbarkeit. 
* **I**nformation Disclosure (Informationsenthüllung) $\rightarrow$ *Ziel:* Vertraulichkeit. 
* **D**enial of Service (Dienstverweigerung) $\rightarrow$ *Ziel:* Verfügbarkeit.
* **E**levation of Privilege (Rechteausweitung) $\rightarrow$ *Ziel:* Autorisierung.

### Das DREAD-Modell (Die Bewertung)
**Nutzen & Warum hilfreich:** DREAD hilft dabei, subjektive Ängste in messbare Werte umzuwandeln. Es ist besonders früh im Prozess hilfreich, um Entwicklungs-Ressourcen zu priorisieren, damit man sich zuerst um die "billigsten" und gleichzeitig schädlichsten Angriffswege kümmert. (Kategorien: Damage, Reproducibility, Exploitability, Affected Users, Discoverability).

## 3. Übungs-Szenario: Der intelligente Wartenautomat (Prüfung)
*Szenario: Ein Selecta-ähnlicher Automat am Bahnhof mit Bargeld, Karte, Mobile Payment und Remote-Wartung.*

*   **1. Assets:** Zahlungsdaten der Kunden (Kreditkarte/Mobile), Inventardaten/Preise, Betriebssoftware, Bargeldbestand im Automaten.
*   **2. Vertrauensgrenzen (DFD-Fokus):** Die kritischsten Punkte sind der Übergang vom Automaten zum Remote-Netzwerk des Herstellers und die Schnittstelle zwischen Kunde (Kartenleser) und Zahlungsprovider.
*   **3. STRIDE-Analyse (Beispiele):**
    *   *Spoofing:* Ein Angreifer gibt sich gegenüber dem Netzwerk als legitimer Automat aus, um manipulierte Transaktionen zu senden.
    *   *Tampering:* Ein Angreifer manipuliert physisch den Kartenleser (Skimming) oder die Preis-Datenbank per Remote-Zugriff.
    *   *Denial of Service:* Jemand blockiert den Kartenslot physisch oder flutet die Remote-Verbindung des Automaten, sodass keine Zahlungen mehr autorisiert werden können.
*   **4. Mitigation (Gegenmassnahmen):**
    *   Einsatz von TLS-Zertifikaten zur gegenseitigen Authentifizierung (Mutual TLS) zwischen Automat und Netzwerk (gegen Spoofing/Tampering).
    *   Physische Anti-Skimming-Aufsätze und Kameraüberwachung.
    *   Lokaler Fallback-Modus (nur Bargeld), falls die Netzwerkanbindung ausfällt (gegen DoS).