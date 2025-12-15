# Module 16 – Troubleshoot Static & Default Routes

## 1. Packet Processing Review
- Router prüft:
  1. Ziel-IP
  2. Routing Table
  3. Longest Match
  4. Default Route (falls keine spezifische Route)
  5. Paket Dropping + ICMP Rückmeldung

## 2. Routing Fehlerursachen
- Interfaces down
- Falsche Next-Hop Adressen
- Falsche Masken/Subnetze
- Fehlerhafte Default Route
- Kein Rückweg (Reverse Path fehlt)
- Provider-Issues / physische Fehler

## 3. Troubleshooting Commands
- `ping` (Layer-3 Test)
- `traceroute` (Pfad)
- `show ip route` (Table)
- `show ip interface brief` (Status)
- `show cdp neighbors` (Layer 2/1 Connectivity)

## 4. Beispiel Fehleranalyse
- Ping zu R3 schlägt fehl  
- Analyse zeigt falschen Next-Hop  
- Löschen der falschen Route  
- Neue Route eintragen → Problem gelöst

Beispielkorrektur:
ip route 172.16.3.0 255.255.255.0 172.16.2.1