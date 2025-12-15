# Labor 3 – Man in the Middle (MITM)

## Zielsetzung
- Einführung in Kali Linux
- Durchführung eines ARP-Poisoning Angriffs
- DNS-Spoofing zur Umleitung auf eine Phishing-Seite
- Szenario: MyCampus-Login stehlen

## Ablauf

### 1. Setup
- Kali Linux + Ubuntu VM
- Angreifer: Kali
- Opfer: Ubuntu
- Tools: Nmap, ettercap, dnsmasq, apache2

### 2. Nmap Scanning
- Identifikation aktiver Hosts
- Portscan für „Opfer-Services“

### 3. ARP Request Poisoning
- Fälschen von ARP-Tabellen
- Opfer glaubt, Angreifer sei Router
- Router glaubt, Angreifer sei Opfer
- Resultat: vollständige Traffic-Weiterleitung durch Angreifer

### 4. DNS Spoofing
- DNS-Anfragen des Opfers manipulieren
- Ziel: `mycampus.hslu.ch → Fake-Webserver`
- Eintrag in DNS-Spoofing-Konfiguration:

### 5. Malicious Webserver
- Kopieren der MyCampus-Loginseite
- Anpassen des HTML für Credential Logging
- Weiterleitung auf Wartungsseite nach Login

### 6. Ergebnis
- MITM-Angriff erfolgreich
- Opfer bemerkt Angriff nicht (HTTPS wird umgangen)
- Verständnis der Verwundbarkeit ungeschützter LANs

## Wichtige Erkenntnisse
- ARP ist komplett vertrauensbasiert → anfällig
- DNS-Spoofing ermöglicht gezielte Phishing-Angriffe
- MITM funktioniert auch in WLAN analog
