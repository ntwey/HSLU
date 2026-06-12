# Zusammenfassung: Laborübung Access Management Windows (I.BA_ISLAB)

Diese Übung behandelt die praktische Umsetzung eines Identity & Access Management (IAM) Konzepts in einer Microsoft Active Directory Umgebung am Beispiel der CKTECK AG.

## 1. Einleitung & Grundlagen
* **Identity & Access Management (IAM):** Ziel ist ein effizientes Zugriffsmanagement nach dem Prinzip der minimalen Berechtigungen (Least Privilege).
* **Active Directory (AD):** Dient als zentraler Verzeichnisdienst zur Verwaltung von Identitäten, Computern und Ressourcen in einem Netzwerk.
* **Informationssicherheit:** Ein gut konzipiertes IAM schützt Systeme vor unbefugtem Zugriff und reduziert den Administrationsaufwand.

## 2. Strukturierung mit Organisationseinheiten (OUs)
* **Zweck von OUs:** Virtuelle Container zur logischen Sortierung von Objekten (User, Computer, Gruppen) nach Standorten oder Abteilungen.
* **Vorteile:** Ermöglichen die gezielte Zuweisung von Gruppenrichtlinien (GPOs) und die Delegation von administrativen Rechten.
* **Design-Prinzip:** "Keep it simple" – eine flache Struktur mit maximal 3 Ebenen wird empfohlen, um die Komplexität gering zu halten.

``` mermaid

graph TD
    CH[OU=Schweiz] --> CH_Users[OU=Benutzer]
	CH --> CH_COMPS[OU=Computer]
    
    %% Funktionale Abteilungen
    CH_Users --> Fin[OU=Finanzen]
    CH_Users --> IT[OU=IT]
    CH_Users --> ISS[OU=ISS]
    CH_Users --> HR[OU=Personal]
    CH_Users --> Mark[OU=Marketing]

    style CH fill:#f9f,stroke:#333,stroke-width:2px
    
```


## 3. Benutzer- und Gruppenverwaltung
* **Gruppentypen:**
    * **Sicherheitsgruppen:** Werden zur Rechtevergabe auf Ressourcen (Fileshares, Drucker) genutzt.
    * **Verteilergruppen:** Dienen ausschließlich Kommunikationszwecken (E-Mail-Verteiler).
* **Gruppen-Scopes:** Unterscheidung in Global, Domänenlokal und Universal (Universal sollte nach Möglichkeit vermieden werden).
* **IGDLA-Konzept (Best Practice):**
    * **I**dentitäten (User/Computer) -> Mitglied von...
    * **G**lobalen Gruppen (Business-Rollen) -> Mitglied von...
    * **D**omänen-**L**okalen Gruppen (Ressourcen-Rollen) -> Erhalten...
    * **A**ccess (Berechtigungen) auf die Ressource.

## 4. Delegation & Gruppenrichtlinien (GPOs)
* **Delegation:** Übertragung spezifischer Rechte an Teil-Administratoren (z. B. darf der Helpdesk nur Passwörter zurücksetzen), ohne volle Admin-Rechte zu gewähren.
* **GPO-Funktion:** Zentrale Konfigurationsvorgaben für Benutzer und Computer (z. B. Desktop-Beschränkungen, Softwareverteilung).
* **Sicherheitseinstellungen:**
    * **UAC (User Account Control):** Verhindert unbefugte Systemänderungen durch Bestätigungsaufforderungen.
    * **Windows Firewall:** Zentrale Steuerung des Netzwerkverkehrs über Richtlinien.
    * **Restricted Groups:** Definition, wer Mitglied in lokalen Gruppen (z. B. lokale Administratoren) sein darf.

## 5. Berechtigungskonzept & Fileserver
* **NTFS-Berechtigungen:** Steuern den Zugriff auf Ordnerebene (Vollzugriff, Ändern, Lesen/Ausführen).
* **Access-Based Enumeration (ABE):** Ein Feature, das Ordner für Benutzer unsichtbar macht, wenn diese keine Leserechte besitzen.
* **Vererbung:** Berechtigungen werden standardmäßig von übergeordneten auf untergeordnete Ordner weitergegeben ("Inherited"), können aber für spezifische Ordner unterbrochen werden.

## 6. Administration mit PowerShell
* **Vorteil:** Ermöglicht die Automatisierung von Massenvorgängen durch Skripte.
* **Objektbasiert:** PowerShell arbeitet mit .NET-Objekten statt mit reinem Text, was mächtige Filter- und Manipulationsmöglichkeiten bietet (z. B. via Pipes `|`).

---

## 🚀 Wichtigste Key Take-Aways

* **Struktur vor Technik:** Ein durchdachtes Design der OUs ist die Basis für eine sichere Delegation und effiziente GPO-Verwaltung.
* **IGDLA konsequent anwenden:** Verschachtelte Gruppenmodelle sind zwar anfangs aufwendiger, verhindern aber langfristig "Berechtigungs-Wildwuchs".
* **Least Privilege:** Nutzer sollten immer nur die Rechte erhalten, die