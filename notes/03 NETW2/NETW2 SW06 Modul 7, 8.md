## **Modul 7: DHCPv4**

> **Ziel:** DHCPv4 in Multi-LAN-Umgebungen implementieren.

### Themen:

- **Funktionsweise:** Automatische IPv4-Zuweisung (Discover, Offer, Request, Ack).
    
- **Lease Renewal:** DHCPREQUEST → DHCPACK.
    
- **Konfiguration (Router als Server):**

ip dhcp excluded-address 192.168.1.1 192.168.1.10
ip dhcp pool LAN
network 192.168.1.0 255.255.255.0
default-router 192.168.1.1
dns-server 8.8.8.8

Client-Konfiguration: Interface mit ip address dhcp.

Relay: ip helper-address auf Router, wenn DHCP-Server in anderem Netz.
## **Modul 8: SLAAC und DHCPv6**

> **Ziel:** Dynamische Adressvergabe in IPv6-Netzen konfigurieren.

### Themen:

- **IPv6 Addressierung:** Global Unicast Address (GUA), Link-Local Address.
    
- **RA-Flags:**
    
    - A – SLAAC
        
    - O – Stateless DHCPv6 (weitere Infos)
        
    - M – Stateful DHCPv6 (vollständige Zuweisung)
        
- **SLAAC:** Host generiert eigene IPv6-Adresse auf Basis von Router-Advertisements.
    
- **DHCPv6:**
    
    - _Stateless:_ Nur Zusatzinfos (DNS, Domain).
        
    - _Stateful:_ Server verwaltet volle Adressvergabe.
        
- **Router-Konfiguration (SLAAC):**
ipv6 unicast-routing
interface g0/0
ipv6 address 2001:db8:acad:1::1/64