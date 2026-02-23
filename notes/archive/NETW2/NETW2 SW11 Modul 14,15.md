# Module 14 & 15 – Routing Concepts + Static Routing

# 1. Router Grundlagen
- Router bestimmen beste Route anhand Routing Table
- Best Path = Longest Match (längster Präfix)
- Routing Table enthält:
  - Direkt verbundene Netze
  - Statische Routen
  - Dynamische Routen
  - Default Route

# 2. Packet Forwarding
Schritte:
1. Paket kommt an Eingangsschnittstelle an  
2. Routing Table Lookup  
3. Längster Match entscheidet  
4. Neue L2-Encapsulation  
5. Weiterleitung oder Drop (wenn kein Match)

Mechanismen:
- Process Switching (langsam)
- Fast Switching
- Cisco Express Forwarding (CEF)

# 3. Statische Routen (IPv4/IPv6)
Arten:
- Next-Hop Route  
- Directly-Connected Static  
- Fully-Specified  
- Default Route  
- Floating Static Route (Backup via höherer Distance)

Befehle:
- IPv4: `ip route <netz> <mask> <next-hop/intf> [distance]`
- IPv6: `ipv6 route <prefix> <next-hop/intf> [distance]`

# 4. Default Routes
- IPv4: `ip route 0.0.0.0 0.0.0.0 <next-hop>`
- IPv6: `ipv6 route ::/0 <next-hop>`

# 5. Floating Static Routes
- Distance > 1 setzen, Beispiel:
  - Primär: OSPF (110)
  - Backup: `ip route ... distance 200`

# 6. Host Routes
- IPv4 Host Route: /32
- IPv6 Host Route: /128

# 7. Routing Troubleshooting (Basics)
- Tools:
  - ping
  - traceroute
  - show ip route
  - show ip interface brief
  - show cdp neighbors

Häufige Fehler:
- Falscher Next-Hop
- Interface down
- Kein Rückweg (Reverse Route fehlt)
- Falsche Masken
