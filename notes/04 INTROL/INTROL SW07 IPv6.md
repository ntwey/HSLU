# 🌐 IPv6 – Grundlagen  
**Kurs:** Einführung in Informations- und Cyber-Sicherheit  
**Datum:** 27. Oktober 2025  
**Dozent:** Hochschule Luzern – Informatik  

---

## 🧭 Einleitung
Seit über 15 Jahren wird über das Ende der IPv4-Adressen diskutiert.  
Bereits 1998 definierte die IETF im RFC 2460 (2027 ersetzt durch RFC 8200) das IPv6-Protokoll.  
Trotz des nahezu unbegrenzten Adressraums erfolgt die Einführung nur langsam.

**Vorteile von IPv6 gegenüber IPv4:**
1. Zustandslose Adressautokonfiguration (SLAAC) → einfachere Verwaltung  
2. Erweiterter Adressraum (128 Bit statt 32 Bit) → keine NAT mehr notwendig  
3. Vereinfachte Header-Struktur → geringerer Overhead  
4. Effizienteres Routing (keine Fragmentierung unterwegs)  
5. Eingebaute Netzwerksicherheit (IPsec)  
6. Quality of Service (QoS) integriert  

---

## ⚙️ Motivation und Historie
- IPv4 ist 42 Jahre alt – neue Technologien benötigen neue Konzepte.  
- IPv6 entstand aus „IPng“ (Internet Protocol next generation).  
- Offizielle Spezifikation: **IPv6 = RFC 2460 (1998)** → später RFC 8200.

---

## 🧩 IPv6 – Technische Grundlagen
**Wesentliche Unterschiede zu IPv4:**
- 128-Bit-Adressraum  
- Keine Adressklassen  
- Header-Format komplett neu  
- Erweiterbare Headerstruktur  
- Eingebaute Unterstützung für Audio/Video (Flow Labels, QoS)

---

## 📦 IPv6-Paketstruktur
- **Basis-Header:** feste Länge (40 Byte)  
- **Keine Fragmentierung durch Router!** Nur durch Endsysteme.  
- **Optionale Erweiterungs-Header:** z. B. Routing, Fragmentation, Authentication, Destination Options.

---

## 🧮 Adressierung in IPv6
IPv6-Adressen bestehen aus:
- **Network Prefix** (Netzwerk)
- **Host Suffix** (Interface ID)
→ 128 Bit Gesamt.

### Adresstypen
| Typ | Beschreibung |
|------|---------------|
| Unicast | Adresse für ein einzelnes Gerät |
| Multicast | Kommunikation an mehrere Geräte gleichzeitig |
| Anycast | Paket wird an das nächstgelegene Ziel mit derselben Adresse gesendet |

Broadcasts gibt es **nicht mehr** – alles wird über Multicast abgewickelt.

---

## ✍️ IPv6-Adressnotation
- Hexadezimale Darstellung mit Doppelpunkten (`:`)
- Gruppen zu je 16 Bit  
- Beispiel:  
  `69DC:8864:FFFF:FFFF:0:1280:8C0A:FFFF`
- **Zero Compression:** `FF0C:0:0:0:0:0:0:B1` → `FF0C::B1`
- **IPv4-kompatible Adressen:** enthalten 96 führende Nullen.

---

## 📏 Kurzschreibweise nach RFC 5952
1. Buchstaben immer **klein** schreiben  
2. Führende Nullen weglassen  
3. Längste Nullsequenz zu `::` komprimieren (nur einmal pro Adresse)  

Beispiel:  
`2001:0db8:0000:0000:0001:0000:0000:0001` → `2001:db8::1:0:0:1`

---

## 🧱 Adress-Aufteilung
Endsysteme haben:
- 64 Bit Netzwerk-Prefix  
- 64 Bit Interface ID  

Hierarchie:
- **RIR** (z. B. RIPE, ARIN, APNIC)  
- **ISP**  
- **Site (Unternehmen)**  

Typisch:
- Unternehmen erhalten ein **/48-Präfix**
- LAN-Segmente sind **/64**

---

## 🌍 Wichtige IPv6-Adressbereiche
| Adresse | Typ | Beschreibung |
|----------|------|--------------|
| `::/128` | nicht spezifiziert | keine Adresse |
| `::1/128` | Loopback | lokale Rückschleife |
| `2000::/3` | Global Unicast | öffentliche IPv6-Adressen |
| `fe80::/64` | Link-Local Unicast | nur lokales Netz |
| `fd00::/64` | Site-Local | privat, innerhalb des Unternehmens |
| `ff00::/8` | Multicast | ersetzt Broadcast |

**Multicast-Beispiele:**
- `ff02::1` → alle Nodes im LAN  
- `ff02::2` → alle Router im LAN  
- `ff02::1:2` → alle DHCPv6-Server  
- `ff05::1:3` → interne DHCPv6-Server (site-local)

---

## 🧪 Praxisbeispiel (IPv6 zu Hause)
- Öffentliche Adresse: `2001:8e0:9ff:2080:52e6:36ff:fe3b:90e3`  
- WAN-Präfix: `2001:8e0:9ff:2080::/64`  
- LAN-Präfix: `2001:8e0:208e:a300::/64`  
- Beispiel-IP im Heimnetz: `2001:8e0:208e:a300:4cc:dbcb:9674:1b0c`

---

## 🔧 IPv6-Adresskonfiguration
### 1️⃣ SLAAC (Stateless Address Autoconfiguration)
- Kein DHCP nötig  
- Automatische Adressbildung anhand Router Advertisements  
- Kein ARP, da MAC-Adresse Teil der IPv6-Adresse ist  
- Optionale Zusatzinfos via DHCPv6  

**Ablauf:**
1. Neighbor Solicitation for DAD  
2. Router Solicitation  
3. Router Advertisement → Prefix  
4. Neighbor Solicitation (DAD #2)

---

### 2️⃣ Stateless DHCPv6
- Ergänzt SLAAC um Zusatzinformationen (DNS, NTP usw.)
- IP-Adresse wird per SLAAC generiert, Zusatzdaten via DHCPv6 abgefragt.  

**Kombination:**  
SLAAC → Adresse  
DHCPv6 → Zusatzinfos  

---

### 3️⃣ Stateful DHCPv6
- Vollständige Adressvergabe durch DHCPv6 (analog zu IPv4)  
- Kommunikation über UDP-Ports 546 (Client) / 547 (Server)  
- Vergibt auch NTP/DNS usw.

---

### 4️⃣ Übersicht Konfigurationsverfahren
| Verfahren | Beschreibung |
|------------|--------------|
| Manuell | Adresse fix eingetragen |
| SLAAC | automatisch vom Router |
| Stateless DHCPv6 | SLAAC + Zusatzinfos |
| Stateful DHCPv6 | vollständige DHCP-Zuweisung |
| Hybrid | Kombination |

**Router Advertisements:**  
- **M-Flag:** Managed → Stateful DHCPv6  
- **O-Flag:** Other → Stateless DHCPv6

---

## 📡 IPv6-Multicast
- Kein Broadcast mehr → nur Multicast (`ff...`)
- Beispiele:
  - `ff02::1` – alle Knoten im LAN  
  - `ff02::2` – alle Router  
  - `ff02::1:2` – DHCPv6-Server  
- Siehe RFC 2375, RFC 3306, RFC 3307.

---

## ⚠️ Sicherheitsmängel bei Autokonfiguration
- Keine Authentifizierung in Neighbor Discovery (ND)
- Angriffsvektoren:
  - Umleitungen, Adressdiebstahl, DoS, Spoofing
- Jeder kann sich als Router ausgeben
- Lösung: Kombination aus SLAAC und DHCPv6 (stateless oder stateful)

---

## 🧠 IPv6-Basisheader
**Struktur:**
- 40 Byte Länge, fester Aufbau  
- Keine Fragmentierung unterwegs  
- Kein Checksum-Feld → schnellere Verarbeitung  
- Erweiterungen via „Next Header“-Feld  
- Enthält:
  - Version
  - Traffic Class
  - Flow Label
  - Payload Length
  - Next Header
  - Hop Limit
  - Source Address (128 Bit)
  - Destination Address (128 Bit)

---

## 🔍 Beispiel: Wireshark-Analyse
- IP-Version: `0110` (6 in Hex)
- Payload Length: `00 3c` → 60 Bytes
- Next Header: `11` → UDP
- Hop Limit: `1` → Link-local
- Source: `fe80::a00:27ff:fefe:8f95`
- Destination: `ff02::1:2` (DHCPv6-Multicast)

---

## 🔗 IPv6 – Next Header
**Funktion:** Zeigt an, welcher Header folgt.  
Kann auf Protokolle (TCP/UDP/ICMPv6) oder Erweiterungsheader zeigen.  

| Nummer | Header |
|----------|--------|
| 0 | Hop-by-Hop Options |
| 43 | Routing |
| 44 | Fragment |
| 50 | Encapsulation Security Payload |
| 51 | Authentication Header |
| 60 | Destination Options |
| 135 | Mobility |
| 139 | Host Identity |
| 140 | Shim6 |
| 1 | ICMP |
| 6 | TCP |
| 17 | UDP |
| 59 | No Next Header |

---

## 🧱 Erweiterungsheader
- Erlauben zusätzliche Funktionen ohne Änderung des Basisheaders  
- Beispiel: Routing-Header, Fragmentation, Security (IPsec)

---

## ✂️ Fragmentierung & MTU
- Nur **Endsysteme** fragmentieren Pakete (nicht Router)
- Router droppen zu grosse Pakete
- Quelle nutzt „Path MTU Discovery“:
  - Sendet Testpakete verschiedener Grösse
  - Passt MTU dynamisch an

---

## 🔄 Übergang IPv4 → IPv6
Kein „Flag Day“ – paralleler Betrieb nötig.  
Zwei Ansätze:

1. **Dual Stack:** IPv4 & IPv6 parallel  
   - Langfristig wird IPv4 abgeschaltet  
   - DNS liefert bei IPv6-fähigen Hosts AAAA-Records

2. **Tunneling:**  
   - IPv6-Pakete werden in IPv4 verpackt  
   - Beim nächsten IPv6-fähigen Router entpackt

---

## 🔗 Nützliche Ressourcen
- [ComputerWeekly IPv6 Einführung](https://www.computerweekly.com/de/definition/IPv6-Internet-Protocol-Version-6)
- [test-ipv6.com](https://test-ipv6.com)
- [ultratools IPv6 Lookup](https://www.ultratools.com/tools/ipv6Info)
- [BSI IPv6 Leitfaden (PDF)](https://www.bsi.bund.de/SharedDocs/Downloads/DE/BSI/Internetsicherheit/isi_lana_leitfaden_IPv6_pdf.pdf)
- [RIPE IPv6 Dokumentation](https://www.space.net/~gert/RIPE/)

---

## 📚 Fazit
