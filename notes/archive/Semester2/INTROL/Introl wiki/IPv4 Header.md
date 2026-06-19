# 📦 IPv4 Header - Prüfungsvorbereitung (Spicker)

> [!abstract] Schnell-Fakten
> * **Standard-Länge:** 20 Bytes (Minimum).
> * **Maximal-Länge:** 60 Bytes (wenn Optionen genutzt werden).
> * **Adressen:** 32 Bit (4 Oktette, dezimal `192.168.0.1`).
> * **Hauptunterschied zu IPv6:** Enthält Checksumme und Fragmentierungs-Infos direkt im Header.

---

## 1. Der Aufbau (Visualisierung)

Der Header ist 32 Bits (4 Bytes) breit.

| Bit 0-3 | Bit 4-7 | Bit 8-15 | Bit 16-31 |
| :--- | :--- | :--- | :--- |
| **Version** (4) | **IHL** (4) | **ToS / DSCP** (8) | **Total Length** (16) |
| **Identification** (16) | | **Flags** (3) | **Fragment Offset** (13) |
| **TTL** (8) | **Protocol** (8) | **Header Checksum** (16) | |
| **Source Address** (32 Bit) | | | |
| **Destination Address** (32 Bit) | | | |
| **Options** (0-320 Bit, optional) | | | **Padding** |

---

## 2. Felder im Detail (Cheat Sheet)

| Feld | Bits | Funktion & Klausur-Fallen |
| :--- | :--- | :--- |
| **Version** | 4 | Immer Wert `4` (`0100`). |
| **IHL** (Header Length) | 4 | Länge des Headers in **32-Bit Worten**. <br>Min: `5` ($5 \times 4 = 20$ Bytes). <br>Max: `15` ($15 \times 4 = 60$ Bytes). |
| **ToS / DSCP** | 8 | "Type of Service". Heute für QoS genutzt (Differentiated Services). |
| **Total Length** | 16 | Gesamtlänge (Header + Daten) in Bytes. Max 65.535 Bytes. |
| **Identification** | 16 | ID, um Fragmente wieder zusammenzusetzen. Alle Fragmente eines Pakets haben dieselbe ID. |
| **Flags** | 3 | Steuert Fragmentierung (siehe Abschnitt 3). |
| **Fragment Offset**| 13 | Wo gehört dieses Stück hin? Gezählt in **8-Byte Blöcken**! |
| **TTL** | 8 | "Time to Live". Router zählen -1. Bei 0 = Drop + ICMP "Time Exceeded". |
| **Protocol** | 8 | Welches Protokoll steckt in den Daten? (TCP, UDP, ICMP). |
| **Checksum** | 16 | Prüfsumme **nur über den Header**. Wird bei **jedem** Hop neu berechnet (weil sich TTL ändert). |
| **Src / Dst IP** | 32 | Absender und Empfänger. |

---

## 3. Fragmentierung (Klausur-Schwerpunkt!)

Da IPv4-Router fragmentieren dürfen (im Gegensatz zu IPv6), sind diese 3 Felder extrem wichtig für Rechenaufgaben.

### Die Flags (3 Bit)
1.  **Bit 0:** Reserviert (immer 0).
2.  **Bit 1 (DF):** **Don't Fragment**.
    * `1` = "Du darfst nicht teilen!". Wenn Paket zu groß für MTU -> Verwerfen + ICMP Meldung.
    * `0` = Fragmentierung erlaubt.
3.  **Bit 2 (MF):** **More Fragments**.
    * `1` = "Es kommt noch was nach mir".
    * `0` = "Ich bin das letzte Stück" (oder das einzige).

### Fragment Offset (13 Bit)
Gibt an, an welcher Stelle der Original-Daten dieses Fragment beginnt.
> [!danger] Achtung Rechenfalle!
> Der Wert im Offset-Feld muss **mit 8 multipliziert** werden, um die Byte-Position zu bekommen.
>
> *Beispiel:* Offset Feld steht auf `185`.
> Startposition = $185 \times 8 = 1480$ Byte.

---

## 4. Wichtige Protocol-Nummern

Das "Protocol" Feld (8 Bit) sagt dem Empfänger, an wen er die Daten (Payload) weitergeben soll.

| Dezimal | Protokoll | Merkhilfe |
| :--- | :--- | :--- |
| **1** | **ICMP** | Die "1" für das wichtigste Diagnose-Tool (Ping). |
| **6** | **TCP** | "Six" wie "Fix" (verbindungsorientiert/sicher). |
| **17** | **UDP** | 17 ist prim -> etwas "krumm" wie unzuverlässiges UDP. |
| **47** | **GRE** | Generic Routing Encapsulation (Tunnel). |
| **50** | **ESP** | IPSec (Verschlüsselung). |
| **51** | **AH** | IPSec (Authentifizierung). |
| **89** | **OSPF** | Routing Protokoll. |

---

## 5. Rechenbeispiel für die Prüfung

**Gegeben sei dieser Hex-Dump:**
`45 00 00 3c 1c 46 40 00 40 06 ...`

> [!example] Analyse Schritt-für-Schritt
> 1.  **`4`** (Nibble 1) -> **Version 4**.
> 2.  **`5`** (Nibble 2) -> **IHL**.
>     * Rechnung: $5 \times 4 \text{ Bytes} = 20 \text{ Bytes}$ Header-Länge (Standard, keine Optionen).
> 3.  **`00`** -> **ToS** (Standard).
> 4.  **`00 3c`** -> **Total Length**.
>     * Hex `3c` = $3 \times 16 + 12 = 60$ Bytes Gesamtlänge.
>     * Payload-Größe = $60 - 20 (\text{Header}) = 40$ Bytes Daten.
> 5.  **`1c 46`** -> **Identification** (ID des Pakets).
> 6.  **`40 00`** -> **Flags & Offset**.
>     * Binär: `0100 0000 ...`
>     * Flag `010`: DF (Don't Fragment) ist gesetzt!
> 7.  **`40`** -> **TTL**.
>     * Hex `40` = 64 Hops Lebensdauer.
> 8.  **`06`** -> **Protocol**.
>     * 06 = **TCP**.

---

## 6. IPv4 Optionen (Selten, aber möglich)

Wenn IHL > 5 ist, folgen Optionen.
* **Record Route:** Router schreiben ihre IP in den Header (wie Traceroute, aber im Header).
* **Timestamp:** Zeitstempel der Router.
* **Loose/Strict Source Routing:** Der Absender bestimmt den Weg (Sicherheitsrisiko, meist blockiert).
* **Padding:** Füllt den Header mit Nullen auf, damit er durch 32 Bit teilbar ist.