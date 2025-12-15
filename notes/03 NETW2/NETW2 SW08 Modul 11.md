# Module 11 – Switch Security Concepts

## 1. Ziel des Moduls
Grundlagen zur Absicherung von Switches, Layer-2-Sicherheit, Angriffsarten und Schutzmechanismen.

## 2. Switch Security Bedrohungen
- MAC Flooding / CAM Table Overflow
- VLAN Hopping
- DHCP Spoofing
- ARP Spoofing
- Telnet-/SSH-Angriffe

## 3. Sicherheitsmechanismen
### Port Security
- Limitiert MAC-Adressen pro Port
- Modi: protect, restrict, shutdown
- Sticky MAC möglich

### DHCP Snooping
- verhindert Rogue DHCP-Server
- Ports: trusted / untrusted

### Dynamic ARP Inspection (DAI)
- Prüft ARP-Pakete gegen DHCP Snooping Binding Table

### Storm Control
- Begrenzung von Broadcast / Multicast / Unknown Unicast

### BPDU Guard / Root Guard
- Schutz vor falschen Spanning-Tree-Root-Switches

## 4. Best Practices
- Nur benötigte Ports aktiv halten
- Standard VLAN 1 nicht nutzen
- Management VLAN vom User-VLAN trennen
