# Zusammenfassung: IPv6 Grundlagen und Praxis

Diese Zusammenfassung deckt die theoretischen Grundlagen von IPv6 sowie die praktische Anwendung (Konfiguration und Analyse) ab, basierend auf den bereitgestellten Kursmaterialien der HSLU.

## 1. Motivation und Einführung
Das Internet Protocol Version 6 (IPv6) ist der Nachfolger von IPv4. Der Hauptgrund für die Einführung ist die **Adressknappheit** von IPv4. Während IPv4 mit seinen 32-Bit-Adressen nur etwa 4,3 Milliarden Adressen bietet (die längst aufgebraucht sind), bietet IPv6 mit **128-Bit-Adressen** einen nahezu unendlichen Adressraum (ca. 340 Sextillionen Adressen).

**Weitere Vorteile von IPv6 gegenüber IPv4:**
* **Effizienteres Routing:** Keine Fragmentierung durch Router (nur durch den Sender), kleinere Routing-Tabellen durch hierarchische Adressvergabe.
* **Vereinfachte Verwaltung:** Durch automatische Konfiguration (SLAAC) ist kein DHCP-Server zwingend notwendig.
* **Bessere Header-Struktur:** Der Header hat eine feste Länge, was die Verarbeitung beschleunigt.
* **Eingebaute Sicherheit:** IPsec (Sicherheitsprotokoll) wurde direkt in das Design integriert (ist aber optional nutzbar).
* **Kein NAT (Network Address Translation) mehr:** Jedes Gerät kann wieder eine öffentliche IP erhalten und direkt Ende-zu-Ende kommunizieren.

---

## 2. Die IPv6-Adresse
Eine IPv6-Adresse besteht aus **128 Bit**.

### 2.1 Schreibweise
* **Hexadezimal:** Die Adresse wird in 8 Blöcken zu je 4 Hexadezimalzeichen dargestellt, getrennt durch Doppelpunkte.
    * *Beispiel:* `2001:0db8:85a3:08d3:1319:8a2e:0370:7344`
* **Vereinfachungsregeln (RFC 5952):**
    1.  **Führende Nullen** in einem Block können weggelassen werden (`0db8` -> `db8`).
    2.  Ein oder mehrere aufeinanderfolgende Blöcke, die nur Nullen enthalten, können **einmalig** durch `::` ersetzt werden.
    * *Beispiel:* `2001:0db8:0000:0000:0000:0000:1428:57ab` wird zu `2001:db8::1428:57ab`.

### 2.2 Aufbau einer Adresse
Eine Adresse teilt sich meistens in zwei Hälften (je 64 Bit):
1.  **Network Prefix (Netzwerkanteil):** Die ersten 64 Bit. Sie identifizieren das Netz.
    * Davon sind oft die ersten 48 Bit der "Global Routing Prefix" (vom Provider zugewiesen) und die nächsten 16 Bit die "Subnet ID" (für eigene Subnetze).
2.  **Interface ID (Hostanteil):** Die hinteren 64 Bit. Sie identifizieren das spezifische Gerät (Interface) in diesem Netz. Sie können aus der MAC-Adresse generiert (EUI-64) oder zufällig erstellt werden (Privacy Extensions).

### 2.3 Adress-Typen
In IPv6 gibt es keine Broadcasts mehr. Stattdessen gibt es:

* **Unicast:** Ein Paket an genau ein Interface.
    * **Global Unicast (`2000::/3`):** Öffentlich routbare Adressen (wie öffentliche IPv4).
    * **Link-Local (`fe80::/10`):** Nur im lokalen Netzsegment (LAN) gültig. Werden nicht von Routern weitergeleitet. Jedes IPv6-Interface hat *immer* eine Link-Local-Adresse.
        * *Wichtig:* Bei Link-Local-Adressen muss oft eine "Zone ID" (z.B. `%eth0` oder `%12`) angegeben werden, damit der Rechner weiß, über welche Netzwerkkarte er senden soll, da das Präfix `fe80::` auf allen Karten gleich ist.
    * **Unique Local (`fc00::/7` bzw. oft `fd00::/8`):** Private Adressen, ähnlich wie `192.168.x.x` bei IPv4. Routbar innerhalb einer Firma, aber nicht im Internet.
    * **Loopback (`::1/128`):** Die eigene Adresse (localhost).
    * **Unspecified (`::/128`):** Adresse fehlt (z.B. beim Booten vor der Adressvergabe).

* **Multicast (`ff00::/8`):** Ein Paket an eine Gruppe von Interfaces.
    * *Beispiel:* `ff02::1` (Alle Knoten im lokalen Netz), `ff02::2` (Alle Router).
    * Ersetzt den Broadcast.

* **Anycast:** Ein Paket an den "nächsten" Empfänger einer Gruppe (z.B. für Lastverteilung oder DNS-Server). Mehrere Geräte teilen sich dieselbe Adresse.

---

## 3. Der IPv6-Header
Der Basis-Header ist mit **40 Bytes** fixiert (bei IPv4 war er variabel).
* **Wichtige Felder:**
    * **Traffic Class:** Priorisierung von Paketen (QoS).
    * **Flow Label:** Kennzeichnung zusammengehöriger Datenströme (für Echtzeit-Anwendungen).
    * **Payload Length:** Länge der Nutzdaten.
    * **Next Header:** Zeigt an, welches Protokoll folgt (z.B. TCP, UDP) oder verweist auf einen **Extension Header**.
    * **Hop Limit:** Ersetzt die TTL (Time to Live).

* **Extension Headers:** Zusatzfunktionen (wie Fragmentierung, Routing-Optionen, Sicherheit) werden in separate Header ausgelagert, die wie eine Kette zwischen dem IPv6-Header und den Nutzdaten hängen. Router müssen diese meist nicht anschauen, was das Routing schneller macht.

---

## 4. Automatische Konfiguration (Autoconfiguration)
Ein zentrales Feature von IPv6 ist, dass Geräte sich selbst konfigurieren können. Es gibt drei Hauptmethoden, gesteuert durch Flags in den **Router Advertisements (RA)**.

### 4.1 Neighbor Discovery Protocol (NDP)
NDP nutzt ICMPv6 und übernimmt Aufgaben, die bei IPv4 z.B. ARP erledigt hat.
* **Router Solicitation (RS):** Ein Client fragt: "Gibt es hier Router?" (sendet an `ff02::2`).
* **Router Advertisement (RA):** Der Router antwortet periodisch oder auf Anfrage. Die Nachricht enthält das Netzwerk-Präfix und Flags für die Konfigurationsmethode.
* **Neighbor Solicitation (NS) & Neighbor Advertisement (NA):** Dienen dazu, die MAC-Adresse zu einer IP zu finden (statt ARP) und zu prüfen, ob eine IP schon vergeben ist (**Duplicate Address Detection - DAD**).

### 4.2 Die Methoden der Adressvergabe
Die Router-Flags **M** (Managed), **O** (Other) und **A** (Autonomous) im RA bestimmen den Modus:

#### A. SLAAC (Stateless Address Autoconfiguration)
* **Flags:** M=0, O=0, A=1.
* **Ablauf:**
    1.  Router schickt Präfix im RA.
    2.  Client generiert sich seine eigene 64-Bit Interface ID (aus MAC oder zufällig).
    3.  Client kombiniert Präfix + Interface ID zur globalen Adresse.
    4.  Client führt DAD durch (prüft per NS, ob Adresse frei ist).
    5.  Standardmäßig erhält der Client so *keinen* DNS-Server (mittlerweile gibt es Erweiterungen dafür).

#### B. Stateless DHCPv6
* **Flags:** M=0, O=1, A=1.
* **Ablauf:**
    1.  Client generiert IP-Adresse selbst via SLAAC (wie oben).
    2.  Das **O-Flag** sagt dem Client: "Frag aber bitte einen DHCPv6-Server nach *anderen* Infos" (z.B. DNS-Server, Domain-Name).
    3.  Der DHCP-Server vergibt hier **keine** IP-Adressen, nur Zusatzinfos.

#### C. Stateful DHCPv6
* **Flags:** M=1, O=1 (meistens), A=0 (Präfix wird ignoriert für Autoconfig).
* **Ablauf:**
    1.  Das **M-Flag** sagt dem Client: "Hol dir deine komplette IP-Adresse vom DHCP-Server".
    2.  Funktioniert ähnlich wie DHCP bei IPv4. Der Server verwaltet die Adressen und vergibt sie (Stateful).

---

## 5. Routing und Netzbetrieb

### 5.1 Routing-Tabelle
Routing funktioniert prinzipiell wie bei IPv4 (längster Präfix gewinnt).
* Eine Default-Route wird oft über die Link-Local-Adresse (`fe80::...`) des Routers als Gateway definiert.

### 5.2 Tunneling und Übergangsmechanismen
Da man nicht das ganze Internet sofort umstellen kann, gibt es Übergangslösungen:
1.  **Dual Stack:** Geräte und Router haben sowohl IPv4 als auch IPv6 aktiviert. Sie können über beide Protokolle kommunizieren. (Bevorzugt wird meist IPv6).
    * *DNS:* Ein DNS-Record Typ **A** ist für IPv4, Typ **AAAA** für IPv6. Windows fragt erst nach AAAA.
2.  **Tunneling (IPv6 over IPv4):** Um zwei IPv6-Inseln über ein IPv4-Netzwerk (wie das heutige Internet) zu verbinden, wird das IPv6-Paket in ein IPv4-Paket "verpackt".
    * Das IPv4-Paket hat als Absender/Ziel die Router-Schnittstellen.
    * Der Inhalt (Payload) ist das komplette IPv6-Paket.
    * *Protokoll-Typ:* Im IPv4-Header steht bei "Protocol" der Wert 41 (für IPv6).

---

## 6. Praxis: Wichtige Befehle und Analyse

### 6.1 Cisco IOS Befehle
* **IPv6 aktivieren:** `ipv6 unicast-routing` (global).
* **IP setzen:** `ipv6 address 2001:db8::1/64` (im Interface).
* **Link-Local manuell:** `ipv6 address fe80::1 link-local`.
* **DHCPv6 Pool (Stateless):**
    ```
    ipv6 dhcp pool MEINPOOL
      dns-server 2001:4860:4860::8888
      domain-name labor.ch
    ```
    Dann im Interface: `ipv6 nd other-config-flag` und `ipv6 dhcp server MEINPOOL`.
* **DHCPv6 Pool (Stateful):**
    ```
    ipv6 dhcp pool STATEFULPOOL
      address prefix 2001:db8::/64
    ```
    Dann im Interface: `ipv6 nd managed-config-flag`.

### 6.2 Windows Befehle
* `ipconfig /all` -> Zeigt IPv6-Adresse, Link-Local (mit %), DAD-Status (Bevorzugt/Duplikat).
* `ipconfig /renew6` -> Erneuert IPv6-Konfiguration.
* `ping <ipv6>` -> Ping. Bei Link-Local muss oft die Zone-ID angegeben werden (z.B. `ping fe80::1%4`).
* `nslookup -type=AAAA google.com` -> Fragt explizit nach IPv6-Adresse.

### 6.3 Wireshark Analyse
* **Filter:** `icmpv6` (für RA, RS, NS, NA), `dhcpv6` (für DHCP-Vorgänge), `dns` (für AAAA-Anfragen).
* **Analyse Router Advertisement:** Im Detail-Fenster unter "ICMPv6 Option (Prefix Information)" schauen. Dort finden sich die **Flags** (L, A) und im Haupt-Header die Flags (M, O), die bestimmen, ob SLAAC oder DHCP genutzt wird.
* **Tunneling erkennen:** Man sieht einen IPv4-Header, und direkt danach (als Payload) wieder einen IPv6-Header.
### 6.4 Analyse von Tunneling (IPv6 over IPv4)
Im Labor und in der Theorie wird das **Tunneling** behandelt, um IPv6-Inseln über eine bestehende IPv4-Infrastruktur zu verbinden. Dies ist ein wichtiger Übergangsmechanismus.

* **Funktionsweise:**
    * Das ursprüngliche IPv6-Paket wird komplett in den Nutzdaten (Payload) eines IPv4-Pakets verpackt ("gekapselt").
    * **Äußerer Header (IPv4):** Enthält die IPv4-Quell- und Zieladressen der Tunnel-Endpunkte (z. B. der Router). Im Feld "Protocol" steht der Wert **41**. Das signalisiert: "Hier drin steckt ein IPv6-Paket".
    * **Innerer Header (IPv6):** Das eigentliche Paket mit den IPv6-Adressen der Endgeräte.
* **Wireshark-Ansicht:**
    * Du siehst zuerst den Ethernet-Frame, dann den IPv4-Header und *direkt danach* wieder einen IPv6-Header (anstatt z. B. TCP oder UDP).
    * Dies bestätigt, dass keine Übersetzung (NAT), sondern eine Einpackung (Tunneling) stattfindet.

---

## 7. Sicherheit in IPv6

IPv6 wurde mit dem Gedanken an Sicherheit entwickelt, bringt aber auch neue Herausforderungen mit sich.

### 7.1 IPsec (Internet Protocol Security)
* **Integration:** Bei IPv4 war IPsec ein nachträgliches Add-on. Bei IPv6 ist die Unterstützung für IPsec theoretisch "mandatorisch" (in der Praxis oft optional implementiert, aber fest im Design verankert).
* **Funktion:** Es ermöglicht Verschlüsselung (ESP) und Authentifizierung (AH) direkt auf der Netzwerkschicht (Layer 3), also unabhängig von der Anwendung.

### 7.2 Reconnaissance (Netzwerk-Scans)
* **Adressraum-Scan:** Aufgrund der gigantischen Größe eines Subnetzes ($2^{64}$ Adressen) ist es für Angreifer unmöglich, ein Netz komplett per "Brute Force" (Ausprobieren aller Adressen) zu scannen ("Ping Sweep").
* **Gefahr:** Angreifer konzentrieren sich daher auf DNS-Abfragen oder lauschen auf Multicast-Traffic, um aktive Geräte zu finden.

### 7.3 Neue Angriffsvektoren (NDP Spoofing)
* Da ARP durch **NDP (Neighbor Discovery Protocol)** ersetzt wurde, verlagern sich Angriffe wie "ARP Spoofing" auf "NDP Spoofing".
* Ein Angreifer kann gefälschte *Neighbor Advertisements* oder *Router Advertisements* senden, um Traffic umzuleiten (Man-in-the-Middle).
* **Gegenmaßnahme:** SEND (Secure Neighbor Discovery) oder RA-Guard auf Switches.

### 7.4 Firewall-Regeln & ICMPv6
* **Wichtig:** Man darf ICMPv6 an der Firewall nicht komplett blockieren!
* Da IPv6 zwingend auf ICMPv6 angewiesen ist (für Adressauflösung, Fragmentierungsmeldungen und Erreichbarkeit), würde ein komplettes Blockieren ("Deny All ICMP") die Verbindung zerstören.
* **Best Practice:** Nur bestimmte ICMPv6-Typen erlauben (z. B. Packet Too Big, Echo Request/Reply), andere filtern.

---

## 8. Routing-Protokolle bei IPv6
Damit Router wissen, wohin sie Pakete schicken sollen, nutzen sie Routing-Protokolle, die für IPv6 angepasst wurden.

* **Statische Routen:** Manuelles Eintragen von Wegen.
    * *Befehl (Cisco):* `ipv6 route 2001:db8:2::/64 2001:db8:1::2` (Ziel-Netz -> Next-Hop IP).
* **OSPFv3 (Open Shortest Path First version 3):**
    * Die Weiterentwicklung von OSPFv2 (IPv4).
    * Läuft direkt über IPv6-Link-Local-Adressen zwischen den Routern.
    * Nutzt Multicast-Adressen (`ff02::5` und `ff02::6`) zur Kommunikation.
* **RIPng:** Die IPv6-Version des alten RIP-Protokolls (einfach, aber langsam).
* **MP-BGP:** BGP mit Multiprotocol Extensions für das globale Internet-Routing.

---

## 9. Zusammenfassung der wichtigsten Unterschiede (IPv4 vs. IPv6)

| Merkmal | IPv4 | IPv6 |
| :--- | :--- | :--- |
| **Adresslänge** | 32 Bit (4 Byte) | 128 Bit (16 Byte) |
| **Adressdarstellung** | Dezimal (`192.168.1.1`) | Hexadezimal (`2001:db8::1`) |
| **Adressauflösung** | ARP (Broadcast) | NDP (Multicast / ICMPv6) |
| **Konfiguration** | Manuell oder DHCP | SLAAC, DHCPv6, Manuell |
| **Broadcasts** | Ja (belastet alle Hosts) | Nein (ersetzt durch Multicast) |
| **Fragmentierung** | Durch Router und Sender | Nur durch Sender (Path MTU Discovery) |
| **Header-Größe** | Variabel (20-60 Bytes) | Fix (40 Bytes) + Extension Header |
| **NAT** | Oft notwendig (wegen Adressmangel) | Nicht notwendig (Ende-zu-Ende Verbindung) |

---

## 10. Fazit aus dem Labor

In den durchgeführten Übungen hast du folgende Kernkompetenzen erarbeitet:

1.  **Dual Stack Betrieb:** Du hast gesehen, dass IPv4 und IPv6 parallel auf derselben Leitung laufen können, ohne sich zu stören.
2.  **Konnektivität herstellen:** Erfolgreiches Pingen über Link-Local und Global Unicast Adressen.
3.  **Tunneling:** Du hast zwei getrennte IPv6-Netze über eine simulierte IPv4-Strecke verbunden und analysiert, wie die Pakete verschachtelt werden.
4.  **Verständnis der Autokonfiguration:** Der Unterschied zwischen SLAAC (Router verteilt Präfix, Client baut Adresse) und DHCPv6 wurde praktisch klar.