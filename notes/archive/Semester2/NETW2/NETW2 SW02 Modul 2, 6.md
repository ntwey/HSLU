## **Modul 2: Switching Concepts**

> **Ziel:** Erklären, wie Layer-2-Switches Frames weiterleiten.

### Themen:

- **Ingress/Egress:** Eingehende vs. ausgehende Frames.
    
- **MAC Address Table:** Switch lernt Quell-MAC und merkt sich Portzuordnung.
    
- **Learn and Forward:**
    
    - _Lernen:_ Quelladresse merken.
        
    - _Weiterleiten:_ Wenn Ziel-MAC bekannt → Port; sonst Flooding.
        
- **Switching-Methoden:**
    
    - _Store-and-Forward:_ prüft FCS (CRC), puffert Frames.
        
    - _Cut-Through:_ leitet sofort nach Ziel-MAC-Analyse weiter (niedrige Latenz, evtl. Fehlerweitergabe).
        
- **Domänen:**
    
    - _Collision Domain:_ pro Port.
        
    - _Broadcast Domain:_ pro VLAN.
## **Modul 6: EtherChannel**

> **Ziel:** EtherChannel konfigurieren und Fehler beheben.

### Themen:

- **Funktion:** Bündelt mehrere physische Verbindungen zu einer logischen Leitung (Port-Channel).
    
- **Vorteile:**
    
    - Höhere Bandbreite, Redundanz
        
    - STP behandelt Channel als eine Verbindung
        
- **Protokolle:**
    
    - _PAgP_ (Cisco-proprietär)
        
    - _LACP_ (IEEE 802.3ad, Standard)
        
- **Modi:**
    
    - On, Desirable, Auto (PAgP)
        
    - Active, Passive (LACP)
        
- **Einschränkungen:** gleiche Geschwindigkeit, Duplex, VLAN-Konfig notwendig.