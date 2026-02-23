# 🛡️ CYSECO IR_1 – Effektives Melden von Schwachstellen  
**Datum:** 22. Oktober 2025  
**Kurs:** Cybersecurity Kommunikation (CYSECO)  
**Dozent:** Frank Heinzmann – Hochschule Luzern, Informatik

---

## 🎯 Lernziele
Die Teilnehmenden können:
- Begriffe und Grundprinzipien verstehen  
- Die Einbettung dieser Prinzipien im Unternehmen erklären  
- Regulatorische Anforderungen verstehen und umsetzen  
- Prozesse und Akteure im Schwachstellenmanagement beschreiben  
- Kommunikationsanforderungen verstehen  
- Ein „Vulnerability Disclosure Advisory“ (VDA) erstellen

---

## ⚖️ Wesentliche Regelwerke: NIS2 & CRA

### NIS2 – Netzwerk- und Informationssicherheit
**Fokus:** Organisationen und Dienste  
**Adressaten:** Betreiber kritischer und wichtiger Dienste (Energie, Gesundheit, Transport, digitale Infrastruktur)  
**Ziel:** Stärkung der organisatorischen Cybersicherheit  
**Pflichten:**  
- Risikomanagement & Sicherheitsmaßnahmen  
- Meldepflicht für Sicherheitsvorfälle (innerhalb 24h)  
- Nutzung zertifizierter Produkte möglich

### CRA – Cyber Resilience Act
**Fokus:** Produkte mit digitalen Elementen  
**Adressaten:** Hersteller, Importeure, Händler (z. B. IoT, Software, Cloud)  
**Ziel:** Cybersicherheit „by Design“  
**Pflichten:**  
- Sicherheitsupdates & Schwachstellenmanagement  
- CE-Kennzeichnung & Konformitätsbewertung  
- Meldepflicht für aktiv ausgenutzte Schwachstellen innerhalb 24 h an Behörden/ENISA  

**Zusammenhang:**  
- NIS2 → Schutz von Organisationen  
- CRA → Schutz von Produkten  
→ Gemeinsames Ziel: Höheres Cybersicherheitsniveau in der EU  

---

## 🧩 Was muss gemeldet werden?

### Schwachstellen
- Sicherheitslücken in IT-Systemen oder Prozessen  
- Beispiele: ungepatchte Software, Standardpasswörter, offene Ports  
- **NIS2:** keine direkte Meldepflicht, nur bei Vorfällen  
- **CRA:** Meldepflicht für aktiv ausgenutzte Schwachstellen binnen 24 h  

### Sicherheitsvorfälle
- Ereignisse, die Vertraulichkeit, Integrität oder Verfügbarkeit beeinträchtigen  
- **NIS2:** Meldepflicht für erhebliche Vorfälle binnen 24 h + Folgeberichte  
- **CRA:** Meldepflicht für schwerwiegende Produktvorfälle binnen 24 h

---

## 🔁 Lebenszyklus einer Schwachstelle
1. Entstehung  
2. Entdeckung  
3. Exploit  
4. Veröffentlichung (Disclosure)  
5. Patch verfügbar  
6. Patch implementiert  

Phasen: *Pre-Disclosure → Post-Disclosure → Post-Patch*

---

## 📘 Wichtige Begriffe & Standards

| Begriff | Bedeutung | Beschreibung | Beispiel |
|----------|------------|---------------|-----------|
| **CVD** | Coordinated Vulnerability Disclosure | Abgestimmte Offenlegung mit Hersteller | Forscher meldet Lücke, Hersteller fixt sie vor Veröffentlichung |
| **CVE** | Common Vulnerabilities and Exposures | Eindeutige Kennung | z. B. CVE-2025-26399 |
| **CVSS** | Common Vulnerability Scoring System | Bewertungsskala (0–10) | Score 9.8 = kritisch |
| **VDA** | Vulnerability Disclosure Advisory | Öffentliche Mitteilung mit Maßnahmen | Patch-Info, Workaround |
| **VDR** | Vulnerability Disclosure Report | Interner/externer Bericht | Enthält technische Analyse |
| **VDP** | Vulnerability Disclosure Policy | Organisationsrichtlinie | Regelung von Meldungen |
| **ISO 29147** | Vulnerability Disclosure | Standard zur Meldung | Kommunikationsprozess |
| **ISO 30111** | Vulnerability Handling | Standard zur Bearbeitung | Prozessbeschreibung |

---

## ⚙️ Typisches Verfahren der Offenlegung
1. **VDR:** Forscher entdeckt Schwachstelle und dokumentiert sie (Beschreibung, CVSS, Fix-Vorschlag).  
2. **CVD:** Meldung an Hersteller, Analyse, Patch-Entwicklung, Abstimmung der Veröffentlichung (z. B. 90 Tage).  
3. **CVE:** Vergabe einer eindeutigen Kennung (z. B. CVE-2025-26399).  
4. **CVSS:** Bewertung der Kritikalität.  
   - Beispiel: CVSS 9.8 = kritisch, RCE über Netzwerk, keine Authentifizierung nötig  
5. **VDA:** Hersteller veröffentlicht Advisory mit CVE, Score, Maßnahmen und Patchlink.  
6. **VDP:** Richtlinie, wie Meldungen entgegengenommen und bearbeitet werden (z. B. Meldeformular, PGP-Key).  

---

## 🧾 1. VDR – Vulnerability Disclosure Report
**Zweck:** Dokumentation, Kommunikation & Nachvollziehbarkeit  

**Typische Inhalte:**
| Abschnitt | Beschreibung |
|------------|---------------|
| Vulnerability Source | CVE, interner Fund, Bug-Bounty |
| Severity Level | CVSS Score |
| Affected Component | Modul, Version, Lieferant |
| Impact Analysis | Auswirkungen |
| Mitigation Strategy | Patch, Workaround |
| Disclosure Status | Offen, geprüft, behoben |
| Timestamp & Signature | Nachvollziehbarkeit |
| SBOM-Link | Referenz auf Software Bill of Materials |

---

## 🤝 2. CVD – Coordinated Vulnerability Disclosure
**Akteure:**  
- Benutzer  
- Hersteller/Vendor  
- Entdecker/Forscher  
- Koordinator (z. B. CERT, NCSC)  
- Angreifer (nutzt offengelegte Lücken aus)  

**Ziel:** Sicherstellung einer abgestimmten, verantwortungsvollen Offenlegung gemäß **ISO 29147**  

---

## 🧾 3. CVE – Common Vulnerabilities and Exposures
**Ablauf:**
1. Entdeckung der Schwachstelle  
2. Meldung an CVE Numbering Authority (CNA, z. B. MITRE, Microsoft, Google)  
3. Validierung & Vergabe einer CVE-ID (`CVE-YYYY-NNNNN`)  
4. Beschreibung, Referenzen, CVSS-Bewertung  
5. Veröffentlichung auf [cve.org](https://www.cve.org)  
6. Ergänzung durch NVD (National Vulnerability Database)  

---

## 📊 4. CVSS – Common Vulnerability Scoring System
**Ziel:** Bewertung der Schwere einer Schwachstelle  

**Metriken:**
- Attack Vector (AV): lokal / Netzwerk  
- Attack Complexity (AC): hoch / niedrig  
- Privileges Required (PR): keine / gering / hoch  
- User Interaction (UI): erforderlich / nicht erforderlich  
- Scope (S): unverändert / verändert  
- Auswirkungen: Confidentiality, Integrity, Availability  

**Scorebereiche:**
| Score | Bewertung |
|--------|------------|
| 0.0 | Keine |
| 0.1–3.9 | Niedrig |
| 4.0–6.9 | Mittel |
| 7.0–8.9 | Hoch |
| 9.0–10.0 | Kritisch |

---

## 📰 5. VDA – Vulnerability Disclosure Advisory
**Zweck:** Öffentliche Kommunikation von Schwachstellen

**Inhalte:**
- CVE-ID  
- Beschreibung  
- Betroffene Produkte  
- CVSS-Score  
- Auswirkungen (z. B. RCE, Datenverlust)  
- Workarounds / Patches  
- Zeitplan der Offenlegung  
- Kontakt & Ansprechpartner  

---

## 🧭 6. VDP – Vulnerability Disclosure Policy
**Inhalte:**
1. Ziel & Umfang  
2. Kontakt (z. B. security@domain.tld)  
3. Erwartungen an Melder (kein Missbrauch)  
4. Reaktionszeiten & Verhalten der Organisation  
5. Veröffentlichung auf Website oder disclose.io  
6. Analyse, Kommunikation, Belohnung  
7. Integration in VDR/VDA-Prozesse  

---

## 🧑‍💻 Zuständigkeiten

| Kürzel | Name | Zuständige Organisation |
|---------|------|--------------------------|
| CVD | Coordinated Vulnerability Disclosure | Hersteller, CERTs |
| CVE | Common Vulnerabilities & Exposures | MITRE, CNAs |
| CVSS | Common Vulnerability Scoring System | FIRST.org |
| VDA | Vulnerability Disclosure Advisory | Hersteller, CERT |
| VDR | Vulnerability Disclosure Report | Finder, Bug-Bounty |
| VDP | Vulnerability Disclosure Policy | Organisation |
| NVD | National Vulnerability Database | NIST, ENISA |

---

## 💥 Beispiel: Log4Shell (CVE-2021-44228)
- Entdeckt: Chen Zhaojun (Alibaba Cloud)  
- Meldung: 24. Nov 2021 → Apache  
- Veröffentlichung: 10. Dez 2021  
- Betroffen: Log4j 2.0 beta9 – 2.14.1  
- Problem: JNDI Lookup erlaubt RCE via manipulierte Log-Einträge `${jndi:ldap://…}`  
- CVSS 10.0 (Kritisch)  
- Koordination: Apache Foundation, CISA, CERTs  
- Ergebnis: Emergency Directive 22-02 (CISA)  

---

## 💸 Globale Kosten durch Schwachstellen
- 2023: 8 Billionen USD  
- 2024: 9,5 Billionen USD  
- 2025 (Prognose): 10,5 Billionen USD  
- Durchschnitt pro Vorfall (2024): 4,88 Mio USD  
- Branchen mit höchsten Schäden: Gesundheitswesen, USA  

---

## 💰 Bug-Bounty-Programme
- **Microsoft (2024):** 16,6 Mio USD – 343 Forscher, max 200 000 USD  
- **Google (2023):** 10 Mio USD – max 113 337 USD  
- **Apple:** bis 2 Mio USD für kritische Lücken  

**Schätzung:** Alle CVEs eines Jahres „einzukaufen“ ≈ 80–300 Mio USD  

---

## 🔍 Schwachstellen finden & verwalten
- **Scans:** Nessus, OpenVAS, Qualys  
- **Pen-Tests:** Metasploit, Burp Suite  
- **Codeanalyse:** SonarQube, Checkmarx  
- **Bug-Bounty:** HackerOne, Bugcrowd  
- **Management:** Tenable.io, Rapid7, Qualys VMDR  
- **Patch-Management:** WSUS, Ivanti  

---

## ⚖️ ISO27001, CRA & NIS2 Vergleich

| Element | ISO/IEC 27001 | CRA | NIS-2 |
|----------|----------------|-----|-------|
| Schwachstellen-Scan | empfohlen | verpflichtend für Hersteller | verpflichtend für Betreiber |
| CVE/CVSS-Nutzung | empfohlen | empfohlen | empfohlen |
| CVD/VDP | optional | verpflichtend | empfohlen |
| Meldepflicht | keine | an ENISA | an nationale Behörden |
| Auditierbarkeit | hoch | sehr hoch | sehr hoch |

---

## 📢 Quellen für Schwachstellenkommunikation
- **Security Advisories:** Microsoft, Cisco, SAP, Fortinet  
- **CERT-Meldungen:** BSI, US-CERT, GovCERT  
- **Bug-Bounty-Plattformen:** HackerOne, Bugcrowd  
- **CVE-Einträge:** NVD, CVE Details  
- **Pressemitteilungen & Medienberichte**

---

## 🧑‍🤝‍🧑 Akteure & Kommunikation

| Akteur | Rolle | Bezug |
|---------|-------|--------|
| Finder | Entdeckt & meldet | CVD, VDR, CVE |
| Hersteller | Analysiert & fixt | CVD, VDA, VDP |
| CNA | Vergibt CVE | CVE |
| CERTs | Koordinieren Offenlegung | CVD, VDA |
| Behörden (ENISA, BSI) | Regulieren & prüfen | CVD, CRA, NIS2 |
| Bug-Bounty-Plattformen | Vermitteln | VDR, VDP |
| Security-Teams | Bewerten & patchen | CVSS, VDA |
| Compliance | Prüfen rechtliche Vorgaben | VDP, CRA, NIS2 |

---

## 📄 RFC 9116 & Security.txt
**Ziel:** Standardisierte Kontaktmöglichkeit für Sicherheitsmeldungen  

**Beispielinhalt:**
```
Contact: mailto:security@domain.tld
Encryption: https://domain.tld/pgp-key.txt
Acknowledgments: https://domain.tld/thanks
Policy: https://domain.tld/vdp
Preferred-Languages: en, de
```
**Beispiele:**  
- https://www.google.com/.well-known/security.txt  
- https://www.sbb.ch/.well-known/security.txt  
- https://www.post.ch/.well-known/security.txt  
- https://threema.ch/.well-known/security.txt  

---

## ✅ Checkliste: Kommunikation im Schwachstellenmanagement

| Thema | Standard | Aufgabe | Zuständig |
|--------|-----------|----------|------------|
| Schwach
