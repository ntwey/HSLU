# Labor 1 – Protokollanalyse (Wireshark)

## Zielsetzung
- Grundlagen der Netzwerkprotokollanalyse mit Wireshark
- Verständnis zentraler Protokolle (Ethernet, ARP, IPv4, TCP, UDP, HTTP)
- Analyse realer Netzwerkpakete
- Erkennen typischer Kommunikationsmuster und Fehler

## Inhalte & Erkenntnisse

### 1. Wireshark Grundlagen
- Packet Capture starten/filtern
- Filter-Beispiele:
  - `arp`
  - `tcp.port == 80`
  - `ip.addr == <IP>`
- Farben im UI: helfen beim Erkennen von Protokollen/Anomalien.

### 2. Ethernet-Analyse
- MAC-Adressen sichtbar
- Frame-Struktur: Preamble, Header, Payload, FCS

### 3. ARP
- „Who-has?“ / „Is-at“-Frames
- ARP dient IP→MAC Auflösung
- Typische Beobachtung: ARP Cache Refreshes

### 4. IPv4
- Header-Felder: TTL, Flags, Fragment Offset
- ICMP oft sichtbar (Ping, Fehler)

### 5. TCP
- 3-Way-Handshake (SYN → SYN/ACK → ACK)
- Flags: SYN, ACK, FIN, RST, PSH
- Window Size Analyse
- Retransmissions bei Netzwerkproblemen

### 6. UDP
- Keine Verbindungsorientierung
- Einsatz: DNS, DHCP, Streaming

### 7. HTTP
- GET / POST Requests
- Statuscodes sichtbar (200, 404, 500)
- Host-Header, Cookies, User-Agent

## Lernresultat
- Fähigkeit, Protokolle zu identifizieren
- Verständnis für Ablauf von Netzwerkkommunikation
- Grundlagen für spätere Labs (MITM, Firewall etc.)
