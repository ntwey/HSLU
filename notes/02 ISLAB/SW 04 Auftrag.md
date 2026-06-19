# Zusammenfassung: Laborübung Access Management Linux

**Modul:** I.BA_ISLAB

**Institution:** Hochschule Luzern (HSLU)

**Letzte Änderung:** 2026-03-03

## 1. Vorbereitung & Grundlagen

### Einleitung ins IAM

- **Identity & Access Management (IAM):** Das Ziel ist ein effizientes Zugriffsmanagement nach dem **Prinzip der minimalen Rechte (Least Privilege)**, um die Informationssicherheit optimal zu gewährleisten.
    
- **Herausforderung:** Zu detaillierte Konzepte erhöhen den Administrationsaufwand; zu grobe Konzepte gefährden den Schutz kritischer Ressourcen.
    

### Versuchsumgebung

- **Windows Server 2022:** Läuft als Domänencontroller (DC) für die Domäne `gXXX.ckteck.com` (wobei `XXX` für die Gruppennummer steht).
    
- **Linux Client:** Betrieben mit **Debian 12 (bookworm)**, welcher im Laufe der Übung in das Active Directory (AD) integriert wird.
    

## 2. Access Management unter Linux

### 2.1. Linux Zugriffsrechte & Benutzerklassen

Linux unterscheidet standardmäßig drei Benutzerklassen für Dateiberechtigungen:

1. **Eigentümer (user / u):** Berechtigungen des Besitzers der Datei.
    
2. **Gruppe (group / g):** Berechtigungen für Mitglieder der zugewiesenen Gruppe.
    
3. **Sonstige (others / o):** Berechtigungen für alle anderen Personen.
    

Jede Klasse verfügt über drei grundlegende Rechte-Bits:

- **Read (r):** Datei lesen / Verzeichnisinhalt auflisten (`ls`).
    
- **Write (w):** Datei modifizieren / Dateien im Verzeichnis hinzufügen, umbenennen oder löschen.
    
- **Execute (x):** Datei ausführen / Verzeichnis betreten (`cd`).
    

#### Dateitypen in der symbolischen Notation:

- `-` = Einfache Datei (z. B. `/etc/passwd`)
    
- `d` = Verzeichnis (Directory)
    
- `l` = Verweis (Symlink)
    

#### Identifikatoren (UID & GID)

- Jede Datei und jeder Prozess ist einer **UID (User Identifier)** und einer **GID (Group Identifier)** zugeordnet.
    
- Der **root-User** besitzt immer die **UID 0**.
    
- Die Datei `/etc/passwd` enthält Informationen über alle Nutzer des Systems (Benutzername, UID, GID, Home-Verzeichnis, Shell).
    
- **Gefahr von ID-Konflikten:** Erhält ein neuer Mitarbeiter fälschlicherweise die UID/GID eines ausgeschiedenen Mitarbeiters, erbt er automatisch all dessen Rechte und Zugriff auf private Dateien.
    

### 2.2. Ändern von Berechtigungen (`chmod`)

Der Befehl `chmod` kann im symbolischen Modus oder im **absoluten (oktalen) Modus** verwendet werden. Im absoluten Modus werden die Rechte addiert:

- `Read (r)` = 4
    
- `Write (w)` = 2
    
- `Execute (x)` = 1
    

_Beispiel:_ `chmod 757` setzt:

- Owner: `rwx` ($4+2+1 = 7$)
    
- Group: `r-x` ($4+1 = 5$)
    
- Others: `rwx` ($4+2+1 = 7$)
    

### 2.3. Besondere Berechtigungsmodi (SUID, SGID, Sticky-Bit)

Besondere Modi werden durch eine vierte, vorangestellte Ziffer im absoluten Modus definiert:

|Modus|Absolute Zahl|Auswirkung auf Dateien|Auswirkung auf Verzeichnisse|
|---|---|---|---|
|**SUID**|`4000`|Wird mit den Rechten des **File-Owners** ausgeführt.|Keine Auswirkung.|
|**SGID**|`2000`|Wird mit den Rechten der **File-Gruppe** ausgeführt.|Neu erstellte Dateien erben die GID des Verzeichnisses.|
|**Sticky-Bit (t-Bit)**|`1000`|Keine Auswirkung.|Nur der Besitzer einer Datei darf diese im Ordner löschen (z. B. `/tmp`).|

#### Sicherheitsrisiko am Beispiel des Editors `nano`:

Wird das SUID-Bit auf ein Programm wie `nano` gesetzt (`sudo chmod u+s /bin/nano`), wird der Editor bei jedem Aufruf mit den Rechten von `root` ausgeführt. Ein nicht-privilegierter Benutzer (`bob`) kann dadurch sensible Systemdateien wie `/etc/shadow` manipulieren oder SSH-Keys für Root hinterlegen. **Dies erzeugt eine kritische Backdoor!**

### 2.4. Eigentümer und Gruppen anpassen (`chown`, `chgrp`)

- **`chown`**: Ändert den Besitzer und optional die Gruppe einer Datei (Syntax: `chown user:gruppe datei`). Dies kann nur vom Superuser durchgeführt werden.
    
- **`chgrp`**: Ändert die Gruppenzugehörigkeit (kann auch vom Eigentümer genutzt werden, sofern er Mitglied der Zielgruppe ist).
    

## 3. Integration eines Linux Clients in eine Windows Domäne

### 3.1. Samba (Winbind) vs. SSSD

- **Früher (Samba/Winbind):** Samba emulierte Windows-Umgebungen. Winbind agierte als Proxy via Kerberos und LDAP für PAM.
    
- **Heute (SSSD - System Security Services Daemon):** Gilt als moderne **Best Practice** zur Integration. SSSD verwaltet den Zugriff auf externe Verzeichnisdienste (Active Directory, FreeIPA, OpenLDAP) und bietet ein effizientes Caching.
    

### 3.2. SSSD-Funktionsweise

1. Der Linux-Host (SSSD Client) verbindet sich mit dem Remote Provider (Active Directory).
    
2. Er ruft Identitäts- und Authentifizierungsinformationen ab und speichert sie im **lokalen SSSD-Cache**.
    
3. _Vorteil:_ SSSD erstellt keine permanenten lokalen Linux-Benutzerkonten, ermöglicht aber ein lokales Home-Verzeichnis, das auch nach dem Abmelden bestehen bleibt. Dank des Caches (`cache_credentials = True`) ist eine Anmeldung auch offline möglich.
    

### 3.3. Wichtige Schritte zur AD-Integration

1. **DNS-Auflösung prüfen:** Mit `resolvectl dns` sicherstellen, dass die globale DNS-IP mit der IP des Domänencontrollers (`ws-XXX.islab.ls.eee.intern`) übereinstimmt.
    
2. **Paketinstallation:** `sssd-ad`, `sssd-tools`, `realmd`, `adcli`, `krb5-user`.
    
3. **AppArmor:** SSSD muss temporär in AppArmor deaktiviert werden (`/etc/apparmor.d/usr.sbin.sssd` nach `/etc/apparmor.d/disable/` verschieben).
    
4. **Domäne beitreten:** Über den realm-Befehl: `sudo realm join -U labadmin gXXX.ckteck.com -v`.
    
5. **Namenskonflikte (`/etc/nsswitch.conf`):** In der Zeile `passwd: files sss` wird festgelegt, dass bei Namensgleichheit zuerst lokale Benutzer (`files`) und erst danach AD-Benutzer (`sss`) abgefragt werden (lokale Benutzer haben Priorität).
    

### 3.4. Whitelisting von AD-Gruppen

Standardmäßig dürfen sich alle AD-Benutzer anmelden. Zur Absicherung wird ein **Default Deny** umgesetzt:

- Zugriff für alle sperren: `sudo realm deny --all`
    
- Gezielten Zugriff für eine AD-Gruppe erlauben (Whitelisting): `sudo realm permit -g "gXXX.ckteck.com\GG_CH_IT"`
    

### 3.5. Home-Verzeichnisse via PAM automatisch erstellen

Da im AD keine Linux-Home-Verzeichnisse hinterlegt sind, muss PAM (`Pluggable Authentication Modules`) so konfiguriert werden, dass es beim Erstlogin ein Home-Verzeichnis generiert.

- Eintrag am Ende von `/etc/pam.d/common-session`: `session required pam_mkhomedir.so skel=/etc/skel/ umask=0077`
    
- Das Verzeichnis erhält dadurch die restriktive Berechtigung `700` (`rwx------`).
    

### 3.6. Zentrales Sudo-Management im Active Directory

Um `sudo`-Berechtigungen nicht auf jedem Linux-Client einzeln pflegen zu müssen, kann das AD-Schema erweitert werden (`ldifde`-Befehl in Windows).

- **ADSI Edit:** Wird verwendet, um im AD ein Objekt der Klasse `sudoRole` (z. B. im Container `CN=sudoers`) anzulegen.
    
- **Attribute einer Sudo-Regel:**
    
    - `sudoCommand`: `/bin/systemctl restart apache2` (Erlaubter Befehl)
        
    - `sudoHost`: `lc-XXX` (Ziel-Host)
        
    - `sudoUser`: `%gg_ch_webdevs@gXXX.ckteck.com` (Berechtigte AD-Gruppe)
        
- **Client-Konfiguration:** Auf dem Linux-Client wird das Paket `libsss-sudo` installiert und in `/etc/nsswitch.conf` die Zeile `sudoers: files SSS` eingetragen.
    

### 3.7. Kerberos-Authentifizierung

Das Active Directory nutzt Kerberos als Standard. Der Domänencontroller agiert hierbei als **KDC (Key Distribution Center)**.

- Mit dem Befehl `klist` lassen sich aktive Tickets anzeigen.
    
- **Default Principal:** Die Identität des aktuell angemeldeten Benutzers.
    
- **Service Principal (SPN):** Identifiziert einen bestimmten Dienst im Netzwerk.
    
- **krbtgt (Ticket Granting Ticket):** Ein zentrales Ticket, das vom KDC ausgestellt wird und es dem Client erlaubt, Folgetickets für spezifische Services anzufordern, ohne das Passwort erneut einzugeben (Single Sign-On).
    
- **AD-Struktur:** Neu hinzugefügte Linux-Clients landen standardmäßig im Container `CN=Computers` im Wurzelverzeichnis der Domäne und können manuell in OUs verschoben werden.
    

## 4. Betreiben eines Service unter Linux

### 4.1. Prozess-Hierarchie & Herkunft

- Beim Booten startet der Kernel den **`init`-Prozess (PID 1)**.
    
- Alle weiteren Prozesse werden über den Systemcall `fork()` vom übergeordneten Prozess abgeleitet (Kindprozesse / Child Processes).
    

### 4.2. Service User & Best Practices

- **Sicherheitsrisiko Root:** Dienste sollten aus Sicherheitsgründen **nie** als `root` ausgeführt werden, um den potenziellen Schadensradius bei einer Kompromittierung zu minimieren.
    
- **System-Ports:** Ports von `1` bis `1023` (z. B. HTTP 80, HTTPS 443) können systembedingt nur von `root` gebunden werden.
    
- **Best Practice:** Anwendungen an Ports `> 1023` binden und als dedizierter Service-User ohne Login-Shell ausführen. Der externe Zugriff auf Port 80/443 wird dann über **iptables-Regeln** (Port-Weiterleitung) oder einen **Reverse Proxy** realisiert.
    

### 4.3. Erstellung eines Systemd-Services

1. **Service-User & Gruppe anlegen:**
    
    - `sudo groupadd -r myappgroup`
        
    - `sudo useradd -r -s /sbin/nologin -g myappgroup myappuser` _(Hinweis: `-r` erstellt eine System-UID < 1000. `/sbin/nologin` blockiert den interaktiven System-Login mit einer Fehlermeldung; `/bin/false` würde den Login ohne Meldung sofort beenden)._
        
2. **Anwendungsordner:** Gemäß _Filesystem Hierarchy Standard (FHS) 3.0_ gehört manuell installierte Software nach `/usr/local/` (ausführbare Dateien nach `/usr/local/bin/`).
    
3. **Skript ausführbar machen:** `sudo chmod +x /usr/local/bin/myapp` (inklusive Shebang-Deklaration `#!/bin/bash` in der ersten Zeile).
    
4. **Service-Datei erstellen (`/etc/systemd/system/myapp.service`):**
    
    ```
    [Unit]
    Description=Manage Myapp service
    
    [Service]
    ExecStart=/usr/local/bin/myapp
    User=myappuser
    Type=simple
    Restart=on-failure
    RestartSec=10
    
    [Install]
    WantedBy=multi-user.target
    ```
    
5. **Dienst aktivieren und starten:**
    
    - `sudo systemctl daemon-reload` (liest neue Definitionen ein)
        
    - `sudo systemctl start myapp`
        
    - `sudo systemctl status myapp` (zeigt unter anderem die dynamisch vergebene Prozess-ID an)
        

## Wichtigste Key Takeaways

1. **Principle of Least Privilege:** Berechtigungen (sowohl im lokalen Linux-Dateisystem als auch bei Sudo-Regeln im AD) dürfen nur so weit wie unbedingt nötig gefasst werden.
    
2. **Gefahrenherd SUID-Bit:** Das Setzen des SUID-Bits auf administrativen Programmen oder Texteditoren (wie `nano`) hebelt Sicherheitsarchitekturen komplett aus und platziert unabsichtlich kritische Root-Backdoors.
    
3. **SSSD als moderner Standard:** System Security Services Daemon (SSSD) ist die heutige Best-Practice-Lösung für die AD-Integration unter Linux. Er ermöglicht Offline-Caching (Credential Caching) und sorgt dafür, dass keine permanenten lokalen Konten für Domänenbenutzer erstellt werden müssen.
    
4. **Whitelisting (Default Deny):** Aus Sicherheitsgründen sollte der AD-Zugriff auf Linux-Clients standardmäßig für alle gesperrt (`realm deny --all`) und nur für explizit autorisierte AD-Gruppen freigegeben werden.
    
5. **Sicheres Service-Design:** Applikationen im Linux-Umfeld gehören in das Verzeichnis `/usr/local/bin/` und müssen unter einem dedizierten System-Account ohne interaktive Shell (`/sbin/nologin`) betrieben werden. System-Ports (< 1023) sollten via Port-Forwarding (iptables) oder Reverse-Proxies umgangen werden.