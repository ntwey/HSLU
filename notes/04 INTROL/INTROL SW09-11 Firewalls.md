

# Firewall Basics & Firewall Advanced – Zusammenfassung

## Zielsetzung
- Umgang mit der Palo Alto PA-440 Firewall
- Netzwerkzonierung & Interface-Konfiguration
- Sicherheit durch Layer-7 Analyse, Threat Prevention und User Identification
- Captive Portal & Authentifizierung
- URL- und Application-Filtering

---

# Teil 1 – Firewall Basics

## 1. Netzwerkzonierung
- Trennung von Inside / Outside / DMZ
- Minimierung von Angriffsflächen

## 2. Interfaces
- Layer 3 Interfaces konfigurieren
- IP-Adressen, Zonen, Virtual Router zuweisen

## 3. Verbindungskontrolle
- Security Policies: allow/deny
- Grundregeln:
  - allow inside → outside
  - deny outside → inside

## 4. NAT
### SNAT
- Outgoing Traffic maskieren
- Verwendung z.B. für Internetzugriff

### DNAT
- Eingehender Traffic an internen Server weiterleiten

## 5. DHCP
- Firewall kann Adressen im internen Netz vergeben

## 6. DNS-Proxy
- DNS-Requests der Clients weiterleiten
- Logging & Kontrolle

---

# Teil 2 – Firewall Advanced

## 1. Layer 7 Firewalling
- Analyse von Applikationen (App-ID)
- Erkennen von:
  - Facebook
  - TikTok
  - VPNs
  - Proxies

## 2. SSL Inspection (Decryption)
- Firewall entschlüsselt HTTPS für tiefere Analyse
- Installation eines Stammzertifikats auf Clients notwendig

## 3. Malware Threat Analysis
- Antivirus + Anti-Spyware Profile
- Wildfire Cloud: unbekannte Dateien werden analysiert

## 4. Authentication
- Benutzer anlegen
- Captive Portal einrichten:
  - DNS-Eintrag
  - Zertifikat
  - Authentication Profile
  - Enforcement via Policy

## 5. URL Filtering
- Kategorien:
  - adult, malware, social media, etc.
- Block / Allow / Alert
- Auf User-Basis konfigurierbar (requiring User-ID)

## 6. Application Filtering
- Sperren gezielter Anwendungen (WhatsApp, SSH, Telegram, …)

## Erkenntnisse
- Moderne Firewalls arbeiten überwiegend auf Layer 7
- Benutzer- & Applikationsidentifikation zentral
- SSL Inspection ermöglicht Threat Prevention
- Kombination aus Regeln, Profilen, NAT und DNS ergibt umfassende Kontrolle
