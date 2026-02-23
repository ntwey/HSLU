# 📘 SRWE Module 9 – FHRP Concepts

**Thema:** First Hop Redundancy Protocols  
**Ziel:** Erklären, wie FHRPs Standard-Gateway-Dienste in redundanten Netzwerken bereitstellen  

---

## 🧩 1. First Hop Redundancy Protocols (FHRP)

### Standard-Gateway-Limitierungen
- Hosts besitzen meist **nur ein Default-Gateway**.  
- Fällt dieses aus, verlieren alle Geräte die Verbindung nach außen.  
- FHRP schafft Redundanz, indem mehrere Router **eine virtuelle IP & MAC-Adresse teilen**.  

### Funktionsweise
- Mehrere Router arbeiten als **virtueller Router**.  
- Nur **ein aktiver Router** leitet Datenverkehr weiter.  
- Fällt der aktive Router aus, übernimmt automatisch der **Standby-Router**.  
- Der Wechsel ist für Endgeräte **transparent**.  

---

## 🔁 2. Router-Failover-Prozess
1. Standby-Router empfängt keine „Hello“-Nachrichten mehr vom aktiven Router.  
2. Er wird automatisch zum neuen aktiven Router.  
3. Die virtuelle MAC- & IP-Adresse bleiben gleich → **keine Unterbrechung**.  

---

## ⚙️ 3. FHRP-Optionen

| Protokoll | Typ | Beschreibung |
|------------|------|---------------|
| **HSRP** | Cisco-proprietär | Transparentes Failover zwischen Routern (IPv4). |
| **HSRPv6** | Cisco-proprietär | Gleiches Prinzip für IPv6 (Router Advertisements). |
| **VRRPv2** | Standardisiert | Wählt Master-Router für IPv4-Netzwerke. |
| **VRRPv3** | Standardisiert | Unterstützt IPv4 & IPv6, herstellerübergreifend. |
| **GLBP** | Cisco-proprietär | Failover + **Load Balancing** zwischen Routern. |
| **GLBPv6** | Cisco-proprietär | IPv6-Version von GLBP. |
| **IRDP** | RFC 1256 | Veraltetes Verfahren zur Router-Erkennung (IPv4). |

---

## 🚦 4. HSRP – Hot Standby Router Protocol

### Überblick
- Cisco-proprietäres FHRP für IPv4/IPv6.  
- Sorgt für **Ausfallsicherheit des Default-Gateways**.  
- Eine Routergruppe wählt:  
  - **Active Router** (leitet Daten)  
  - **Standby Router** (übernimmt bei Ausfall)  

---

### Priorität & Preemption
- Höchste Priorität = Active Router.  
- Standardpriorität: **100**  
- Bereich: **0–255**
- Bei gleichen Prioritäten → Router mit **höchster IP-Adresse** wird aktiv.  
- **Preemption** erlaubt, dass ein Router mit höherer Priorität aktiv wird, sobald er online ist:  
  ```bash
  standby 1 preempt
  standby 1 priority 150
  ```

---

### HSRP-Zustände
| Zustand | Beschreibung |
|----------|---------------|
| Initial | Interface gerade aktiviert oder geändert |
| Learn | Kennt virtuelle IP noch nicht |
| Listen | Kennt IP, ist aber weder aktiv noch standby |
| Speak | Sendet Hello-Nachrichten, nimmt an Wahl teil |
| Standby | Wartet auf Aktivierung als Active Router |

**Standard-Timer:**  
- Hello = 3 s  
- Hold = 10 s  
→ Router wird aktiv, wenn 10 s keine Hello-Pakete empfangen werden.  

---

### 🧠 Zusammenfassung Modul 9
- FHRP = Redundante Gateways für VLANs  
- Optionen: HSRP, VRRP, GLBP, IRDP  
- HSRP: Active/Standby, Priorität & Preemption  
- Zustände: Initial, Learn, Listen, Speak, Standby  
- Failover ohne Paketverlust  

---

# 🔐 SRWE Module 10 – LAN Security Concepts

**Ziel:** Erklären, wie Schwachstellen LAN-Sicherheit gefährden und wie sie gemindert werden können.  

---

## 🧩 1. Endpoint Security

### Aktuelle Angriffe
- **DDoS:** Viele infizierte Geräte (Botnet) überlasten Server.  
- **Data Breach:** Vertrauliche Daten werden gestohlen.  
- **Malware:** Schadsoftware wie *WannaCry* verschlüsselt Daten.  

### Netzwerk-Sicherheitsgeräte
| Gerät | Funktion |
|--------|-----------|
| **VPN-Router** | Sichere Verbindung über öffentliche Netze |
| **NGFW** | Stateful Inspection, NGIPS, AMP, URL-Filter |
| **NAC (Network Access Control)** | AAA & Richtlinienverwaltung (z. B. Cisco ISE) |

### Endpoint-Schutz
- Kombination aus **NAC + AMP + ESA + WSA**  
- Traditionelle Maßnahmen (Antivirus, Firewall, HIPS) sind nicht mehr ausreichend.  

---

### Cisco Email Security Appliance (ESA)
- Überwacht SMTP, blockiert schadhafte Mails.  
- Bezieht Threat Intelligence von **Cisco Talos** (alle 3–5 Minuten aktualisiert).  
- Funktionen:  
  - Blockiert bekannte Bedrohungen  
  - Entfernt versteckte Malware  
  - Sperrt schädliche Links  
  - Verschlüsselt ausgehende E-Mails  

---

### Cisco Web Security Appliance (WSA)
- Schützt vor Web-basierten Bedrohungen.  
- Kontrolliert Internetzugang & Anwendungen.  
- Funktionen: Malware-Scanning, URL-Filter, App-Kontrolle, HTTPS-Überwachung.

---

## 🧾 2. Access Control

### Authentifizierung mit lokalem Passwort
- Einfache Variante, aber nicht skalierbar.  
- SSH statt Telnet verwenden.  
- Kein Fallback bei Ausfall der lokalen Benutzer.  

---

### AAA – Authentication, Authorization, Accounting
| Komponente | Beschreibung |
|-------------|--------------|
| **Authentication** | Prüft Identität |
| **Authorization** | Definiert Berechtigungen |
| **Accounting** | Protokolliert Aktivitäten |

**Serverbasiert:**  
- AAA-Server (RADIUS oder TACACS+)  
- Zentrale Benutzerverwaltung  
- Besser skalierbar als lokale Accounts  

---

### Authorization
- Benutzerrechte werden nach erfolgreicher Anmeldung automatisch zugewiesen.  
- AAA-Server bestimmt, welche Befehle oder Dienste erlaubt sind.  

---

### Accounting
- Zeichnet alle Benutzeraktionen auf (Befehle, Zeit, Bytes).  
- Ermöglicht Nachvollziehbarkeit & forensische Analyse.  

---

### IEEE 802.1X
- Portbasierte Authentifizierung  
- Rollen:
  - **Supplicant (Client)** – Gerät mit 802.1X-Software  
  - **Authenticator (Switch/AP)** – Vermittler  
  - **Authentication Server** – prüft Identität (z. B. RADIUS)  
- Nur autorisierte Geräte dürfen Netzwerkzugriff erhalten.  

---

## ⚠️ 3. Layer 2 Security Threats

### Layer 2-Schwachstellen
- Wird Layer 2 kompromittiert, sind alle höheren Schichten betroffen.  
- Angriffe: MAC Flooding, VLAN Hopping, DHCP Spoofing, ARP Poisoning, STP Manipulation.  

### Kategorien
| Kategorie | Beispiel |
|------------|-----------|
| MAC Table | Flooding |
| VLAN | Hopping, Double Tagging |
| DHCP | Starvation, Spoofing |
| ARP | Spoofing, Poisoning |
| Address Spoofing | IP/MAC Manipulation |
| STP | Root-Bridge-Manipulation |

---

### Gegenmaßnahmen
| Angriff | Schutzmaßnahme |
|----------|----------------|
| MAC Flooding | Port Security |
| DHCP Spoofing | DHCP Snooping |
| ARP Spoofing | Dynamic ARP Inspection (DAI) |
| IP Spoofing | IP Source Guard (IPSG) |
| STP Manipulation | BPDU Guard |
| CDP Reconnaissance | CDP deaktivieren auf untrusted Ports |

Weitere Best Practices:
- SSH, SCP, SFTP, TLS für Management  
- Dedizierte Management-VLANs  
- ACLs zum Schutz von Managementschnittstellen  

---

## 🧠 4. MAC Address Table Attack

### Angriff
- Angreifer überflutet Switch mit gefälschten MAC-Adressen (z. B. mit *macof*).  
- MAC-Tabelle läuft über → Switch floodet Frames an alle Ports.  
- Angreifer kann Traffic anderer Hosts mitschneiden.  

### Abwehr
- **Port Security** → begrenzt Anzahl der MAC-Adressen pro Port.  

---

## 🌐 5. LAN Attacks

### VLAN-Hopping
- Angreifer imitiert Switch & aktiviert Trunking (DTP) → Zugriff auf alle VLANs.  

**Abwehr:**  
- Trunking auf Access-Ports deaktivieren  
- Native VLAN nur für Trunk nutzen  

---

### VLAN Double Tagging
- Angreifer schickt doppelt getaggte Frames.  
- Inneres Tag erlaubt Zugriff auf andere VLANs.  

**Abwehr:**  
- Native VLAN nur auf Trunk verwenden  
- Auto-Trunk deaktivieren  

---

### DHCP-Angriffe
- **Starvation:** Angreifer fordert alle IPs an → DoS.  
- **Spoofing:** Rogue-Server verteilt falsche Konfigurationen (z. B. falsches Gateway).  

**Abwehr:**  
- DHCP Snooping aktivieren  

---

### ARP-Angriffe
- Angreifer sendet gefälschte ARP-Replies („Gratuitous ARP“)  
- Setzt sich als Gateway → Man-in-the-Middle  

**Abwehr:**  
- Dynamic ARP Inspection (DAI)  

---

### Address Spoofing
- IP oder MAC-Adresse wird manipuliert, um andere Geräte zu imitieren.  

**Abwehr:**  
- IP Source Guard (IPSG)  

---

### STP-Angriffe
- Angreifer sendet gefälschte BPDUs mit niedriger Priorität → wird Root Bridge.  
- Leitet Traffic über sich selbst.  

**Abwehr:**  
- BPDU Guard auf Access-Ports  

---

### CDP Reconnaissance
- CDP sendet regelmäßig unverschlüsselte Infos (IP, IOS-Version, VLAN).  
- Angreifer kann daraus Netzwerktopologie rekonstruieren.  

**Abwehr:**  
- CDP auf untrusted Ports deaktivieren  
  ```bash
  no cdp enable
  ```
- Optional: LLDP ebenfalls deaktivieren (`no lldp run`)  

---

## 🧾 6. Modul-Zusammenfassung

- Endgeräte → Angriffsfläche für DDoS, Malware, Data Breaches  
- Schutz: ESA, WSA, AMP, NAC  
- AAA → Kontrolle über Zugriff, Rechte & Protokollierung  
- 802.1X → Authentifizierung pro Port  
- Layer 2 → Schwachstelle, wenn ungesichert  
- Wichtige Schutzmaßnahmen:
  - Port Security  
  - DHCP Snooping  
  - DAI  
  - IPSG  
  - BPDU Guard  
  - CDP deaktivieren  

---

**Fazit:**  
Sichere Netzwerke beginnen auf **Layer 2**.  
Durch korrekte Authentifizierung, Zugriffskontrolle und Schutzmechanismen kann das Risiko von LAN-Angriffen drastisch reduziert werden.
