# Laborübung: Access-Management Linux (I.BA_ISLAB)

## 1. Grundlagen der Linux-Zugriffsrechte
Das Linux-Rechtesystem basiert auf der Zuweisung von Berechtigungen für drei Benutzerklassen auf Ressourcen (Dateien/Verzeichnisse).

### Benutzerklassen & Rechte
- **u (User):** Eigentümer der Datei.
- **g (Group):** Mitglieder der zugewiesenen Gruppe.
- **o (Others):** Alle anderen Benutzer.

**Berechtigungstypen:**
- **Read (r / 4):** Datei lesen / Verzeichnisinhalt auflisten (`ls`).
- **Write (w / 2):** Datei ändern / Dateien im Verzeichnis erstellen oder löschen.
- **Execute (x / 1):** Datei ausführen / In Verzeichnis wechseln (`cd`).

### Wichtige Befehle
- `chmod`: Ändert Zugriffsrechte (z. B. `chmod 755 [Datei]`).
- `chown`: Ändert den Besitzer und/oder die Gruppe (z. B. `sudo chown user:group [Datei]`).
- `ls -l`: Zeigt detaillierte Informationen inklusive Berechtigungs-String (z. B. `-rw-r--r--`).
- `id [User]`: Zeigt UID (User ID) und GID (Group ID) eines Benutzers an.

---

## 2. Sonderrechte (Special Bits)
Neben den Standardrechten gibt es spezielle Modi für fortgeschrittene Szenarien:

- **SUID (Set User ID):** Programm wird mit den Rechten des Dateibesitzers ausgeführt. **Gefahr:** Kann als Backdoor missbraucht werden (z. B. SUID auf `nano` erlaubt Root-Zugriff auf `/etc/shadow`).
- **SGID (Set Group ID):** Programm wird mit Gruppenrechten ausgeführt; bei Verzeichnissen erben neue Dateien die Gruppe des Ordners.
- **Sticky Bit (t):** In Verzeichnissen (wie `/tmp`) können Benutzer nur ihre eigenen Dateien löschen, auch wenn sie Schreibrechte für den Ordner haben.
---
## 3. Active Directory Integration (SSSD)
Moderne Linux-Systeme nutzen den **SSSD (System Security Services Daemon)**, um Linux-Clients in eine Windows-Domäne zu integrieren.
### Kernkomponenten & Ablauf
1. **Realmd:** Vereinfacht den Beitritt zur Domäne (`realm join`).
2. **SSSD:** Ruft Identitäten vom AD ab und speichert sie in einem lokalen Cache (erlaubt Offline-Login).
3. **Kerberos:** Protokoll für die Authentifizierung mittels Tickets (Vermeidung von Klartext-Passwörtern).
    - **TGT (Ticket Granting Ticket):** Erster Nachweis der Identität nach dem Login.
    - **KDC (Key Distribution Center):** Der Domänencontroller fungiert als vertrauenswürdige Instanz.

### Zugriffskontrolle (Whitelisting)
Anstatt allen AD-Benutzern Zugriff zu gewähren, wird mittels `realm permit -g [Gruppe]` der Zugriff auf spezifische AD-Gruppen (z. B. IT-Abteilung) eingeschränkt.
---
## 4. Betrieb von Services (systemd)
Applikationen sollten nach dem **Principle of Least Privilege** betrieben werden.

### Best Practices für Services
- **Service User:** Applikationen niemals als `root` laufen lassen, sondern als dedizierter Service-User ohne Login-Shell (`/sbin/nologin`).
- **Ports:** Nur `root` darf Ports < 1024 binden. Services sollten auf höheren Ports laufen und ggf. per Reverse Proxy oder IP-Tables weitergeleitet werden.
- **Verzeichnisstruktur:** Programm-Code gehört nach `/usr/local/bin` oder `/usr/bin`, Konfigurationen nach `/etc`.

### systemd Konfiguration
Services werden über `.service`-Dateien in `/etc/systemd/system/` definiert.
- `systemctl daemon-reload`: Lädt neue Konfigurationen.
- `systemctl start/status/enable [Service]`: Steuert den Dienst.

---

## 🚀 Key Take-Aways
* **Minimalprinzip:** Vergebe nur so viele Rechte wie unbedingt nötig.
* **Zentrale Identität:** SSSD ermöglicht die Nutzung von AD-Accounts unter Linux, inklusive Offline-Caching und Single-Sign-On via Kerberos.
* **Sicherheitsrisiko SUID:** Das Setzen von SUID-Bits auf Editoren oder Shells ist eine kritische Sicherheitslücke.
* **Automatisierung:** `systemd` ist der Standard für das Prozessmanagement und sorgt für automatische Restarts und sauberes Logging von Hintergrunddiensten.
* **Dateisystem-Standard:** Halte dich an den *Filesystem Hierarchy Standard (FHS)*, um die Wartbarkeit des Systems zu gewährleisten.