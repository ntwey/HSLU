# Numbering System 
Binary Hex rechnen simpel

# Packet tracer
## Cheat Sheet


## CHAT GPT ZUSAMMENFASSUNG
### **Modulübersicht**

Das Modul behandelt die grundlegende Konfiguration von Switches und Endgeräten im Netzwerk, einschließlich Passwortschutz, IP-Adressierung und dem Festlegen eines Standard-Gateways.

### **Wichtige Themen**

1. **Zugriff auf Cisco IOS**
    
    - Zugriff über Konsole, SSH oder Telnet (SSH wird empfohlen, da sicherer).
    - Nutzung von Terminal-Emulationsprogrammen wie PuTTY oder Tera Term.
2. **Navigieren im IOS**
    
    - Verschiedene Modi:
        - **User EXEC Mode** (Grundlegender Zugriff, Prompt: `>`)
        - **Privileged EXEC Mode** (Erweiterte Befehle, Prompt: `#`)
        - **Global Configuration Mode** für Konfigurationsänderungen.
    - Wechsel zwischen Modi mit `enable`, `configure terminal`, `exit` und `end`.
3. **Befehlssyntax**
    
    - **Keywords:** vordefinierte Parameter (z. B. `ip address`).
    - **Argumente:** vom Benutzer definierte Werte (z. B. `192.168.1.1`).
    - Kontext-sensitive Hilfe mit `?`.
4. **Grundlegende Geräte-Konfiguration**
    
    - **Hostname setzen:** `hostname [Name]`
    - **Passwörter konfigurieren:**
        - Konsolenpasswort: `line console 0`, dann `password [Passwort]` und `login`.
        - VTY (Remote-Zugriff): `line vty 0 15`, dann `password [Passwort]` und `login`.
        - Verschlüsselung aller Passwörter: `service password-encryption`.
    - **Banner-Nachricht:** `banner motd # [Nachricht] #`.
5. **Speichern und Verwalten von Konfigurationen**
    
    - **Laufende Konfiguration speichern:** `copy running-config startup-config`.
    - **Zurücksetzen auf Werkseinstellungen:** `erase startup-config` gefolgt von `reload`.
6. **Ports und Adressen**
    
    - **IP-Adressen für Endgeräte:** Manuell oder über DHCP.
    - **Switch Virtual Interface (SVI) für Fernzugriff:**
        - `interface vlan 1`
        - `ip address [IP] [Subnetzmaske]`
        - `no shutdown`.
7. **Überprüfung der Konnektivität**
    
    - `ping [IP-Adresse]` zur Überprüfung der Erreichbarkeit.
    - `traceroute [IP-Adresse]` zur Analyse des Netzwerkwegs.

### **Zusammenfassung**

- Cisco IOS wird über die CLI verwaltet.
- Geräte benötigen eine grundlegende Konfiguration (Hostname, Passwörter, IP-Adressen).
- Die Konfiguration sollte gespeichert werden, um Datenverlust zu vermeiden.
- Netzwerkkommunikation basiert auf IP-Adressen und Schnittstellen.
- Befehle zur Fehlerbehebung und Überprüfung sind essenziell.
