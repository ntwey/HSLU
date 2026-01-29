# Detailzusammenfassung: Protokollanalyse & Labor 1

Diese Zusammenfassung kombiniert die theoretischen Grundlagen der Netzwerkprotokolle mit den praktischen Erkenntnissen und Lösungen aus dem Labor "Protokollanalyse".

## 1. Theoretische Grundlagen (Theorie-Präsentation)

### Das OSI-Modell vs. TCP/IP-Modell
Das Verständnis der Schichtenarchitektur ist die Basis für die Protokollanalyse:
* **OSI-Modell (7 Schichten):** Application, Presentation, Session, Transport, Network, Data Link, Physical.
* **TCP/IP-Modell (4 Schichten):** Application, Transport, Internet, Network Access (Link Layer).
* **Kapselung:** Jede Schicht fügt ihre eigenen Header-Informationen hinzu, bevor die Daten an die untere Schicht weitergereicht werden.

### Wichtige Protokolle im Detail
* **Layer 2 (Link):**
    * **ARP (Address Resolution Protocol):** Löst IP-Adressen in MAC-Adressen auf. Wichtig: Ein ARP-Request ist ein Broadcast (an alle), die Reply ein Unicast (direkt an den Anfragenden).
* **Layer 3 (Internet):**
    * **IP (Internet Protocol):** Verantwortlich für das Routing. Enthält Quell- und Ziel-IP sowie die TTL (Time to Live).
    * **ICMP (Internet Control Message Protocol):** Dient dem Austausch von Status- und Fehlermeldungen (z. B. Ping).
* **Layer 4 (Transport):**
    * **TCP (Transmission Control Protocol):** Verbindungsorientiert, zuverlässig (3-Way-Handshake: SYN -> SYN/ACK -> ACK).
    * **UDP (User Datagram Protocol):** Verbindungslos, schnell, keine Übergarantie.
* **Layer 7 (Application):**
    * **DNS:** Namensauflösung (Domain zu IP). Nutzt meist Port 53 (UDP).
    * **HTTP/HTTPS:** Übertragung von Webseiten. Ports 80 (HTTP) und 443 (HTTPS/TLS).

---

## 2. Zusammenfassung der Laborergebnisse (Labor 1)

Das Labor vertieft die Theorie durch praktische Analysen mit Wireshark.

### Sektion A: ARP (Address Resolution Protocol)
* **Zweck:** Ohne die MAC-Adresse des Empfängers kann auf Layer 2 kein Paket im lokalen Netz (LAN) zugestellt werden.
* **Prozess:** Wenn ein Host die MAC für eine IP nicht kennt, sendet er einen **ARP-Request** an die Broadcast-MAC `ff:ff:ff:ff:ff:ff`.
* **Wireshark-Erkenntnis:** Im ARP-Header sieht man den "Opcode" (1 für Request, 2 für Reply). Die Ziel-MAC im Request-Paket ist meist genullt (`00:00:00:00:00:00`), da sie ja gesucht wird.

### Sektion B: ICMP & IP
* **Ping-Analyse:** Ein Ping besteht aus einem `Echo (ping) request` (Type 8) und einem `Echo (ping) reply` (Type 0).
* **IP-Header:** * Die **TTL** verhindert Endlosschleifen. Jeder Router dekrementiert diesen Wert um 1.
    * Das Feld **Protocol** im IP-Header gibt an, welches Protokoll folgt (z.B. 1 für ICMP, 6 für TCP, 17 für UDP).

### Sektion C: TCP & Der 3-Way-Handshake
Die Analyse einer HTTP-Verbindung zeigt den Verbindungsaufbau:
1. **SYN:** Client sendet Sequence Number (J).
2. **SYN/ACK:** Server bestätigt mit Acknowledgment (J+1) und sendet eigene Seq (K).
3. **ACK:** Client bestätigt mit Ack (K+1).
* **Ports:** Client-Ports sind dynamisch (High Ports > 1024), Server-Ports sind "Well Known" (z.B. 80 für HTTP).

### Sektion D: DNS & UDP
* **Charakteristik:** DNS-Abfragen sind sehr kurz. UDP wird bevorzugt, da der Overhead für einen TCP-Handshake bei einer einzelnen Anfrage zu groß wäre.
* **Struktur:** Ein DNS-Paket enthält die "Queries" (Frage nach dem Record, z.B. Typ A für IPv4) und "Answers" (die aufgelöste IP-Adresse).

### Sektion E: HTTP vs. HTTPS
* **HTTP:** Alle Daten (auch Passwörter bei Formularen) sind im Klartext lesbar. Im Wireshark-Stream kann man via "Follow TCP Stream" den gesamten Inhalt (HTML, Header) mitlesen.
* **HTTPS:** Die Daten sind verschlüsselt. Wireshark zeigt nur noch den TLS-Handshake und danach "Application Data", die unlesbar sind.

---

## 3. Nützliche Praxis-Tipps aus dem Labor
* **Wireshark-Filter:** * `arp`, `icmp`, `tcp.port == 80` oder `ip.addr == 192.168.1.1` sind essenziell zur Fehlersuche.
* **Kommandozeile:** * `ipconfig /all`: Zeigt eigene IP und MAC.
    * `arp -d *`: Löscht den ARP-Cache, um einen neuen Request zu erzwingen.
    * `nslookup [domain]`: Manuelle DNS-Abfrage.