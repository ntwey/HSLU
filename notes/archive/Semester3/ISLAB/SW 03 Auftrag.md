# Zusammenfassung: Laborübung Access Management Windows (v1.3.0)
**Hochschule Luzern (HSLU) - Modul I.BA_ISLAB**

Dieses Dokument fasst die Laborübungen und Lösungen zum Identitäts- und Zugriffsmanagement (IAM) in einer Microsoft Active Directory-Umgebung auf Basis des Szenarios der fiktiven CKTECK AG zusammen.

---

## 1. Ausgangslage & Szenario (CKTECK AG)
* **Unternehmen:** Die CKTECK AG ist ein multinationales Unternehmen (8'000 Mitarbeitende) mit Standorten in der Schweiz, Polen, Norwegen und Indien.
* **Problemstellung:** Unorganisierte Rechtevergabe, mangelnder Schutz gegen Angreifer und Non-Compliance mit neuen Richtlinien.
* **Fokus:** Reorganisation des Standorts Schweiz (495 Mitarbeitende verteilt auf Abteilungen wie Finanzen, HR, Marketing, Vertrieb, Produkt, IT und die neue Abteilung ISS).

---

## 2. Active Directory Grundlagen & Struktur

### Zentrale AD-Konzepte
* **Active Directory (AD):** Ein zentraler Verzeichnisdienst zur Speicherung und Verwaltung von Identitäten, Geräten, Gruppen und Sicherheitsrichtlinien in einer Windows-Domäne.
* **Organisationseinheiten (OUs):** Logische Container innerhalb einer Domäne, auf die spezifische Gruppenrichtlinien (GPOs) und administrative Berechtigungen angewendet werden können. Sie können Benutzer, Gruppen, Computer und andere OUs enthalten.
* **Unterschied OU vs. Standard-Container:** OUs können direkt mit Gruppenrichtlinien (GPOs) verknüpft werden; Standard-Container (wie `Users` oder `Computers`) können das nicht.

### Design der OU-Struktur
Für die CKTECK AG wird ein **geografisches Modell** gewählt, da es sich um ein internationales Unternehmen mit dezentraler IT handelt (ermöglicht länderspezifische GPOs). Als Best Practice gilt: *Keep it simple* (maximal 3 Ebenen tief).

**Erstellte Struktur unter `Switzerland/Users`:**
* `Privileged`: Für Konten mit administrativen Rechten (z. B. Abteilungsleiter).
* `Restricted`: Für herkömmliche Benutzer ohne Admin-Rechte.
* `Service Accounts`: Für System- und Dienstkonten (Schutz vor versehentlichem Löschen deaktiviert für Übungszwecke).

---

## 3. Benutzer- und Gruppenverwaltung

### Gruppentypen & Geltungsbereiche
1. **Sicherheitsgruppen (Security Groups):** Dienen der Verwaltung von Zugriffsrechten auf Ressourcen.
2. **Verteilergruppen (Distribution Groups):** Werden ausschliesslich für E-Mail-Verteiler verwendet.

**Geltungsbereiche (Group Scopes):**
* **Globale Gruppen (GG):** Bilden organisatorische Rollen / Jobfunktionen ab (Multi-Domain Forest).
* **Domänenlokale Gruppen (DL):** Werden direkt den Ressourcen zugeordnet und beschreiben die konkreten Zugriffsrechte.
* **Universelle Gruppen:** Domänenübergreifend, sollten nach Möglichkeit vermieden werden.

### Das IGDLA-Prinzip (Role-Based Access Control)
Microsoft empfiehlt die Verschachtelung nach dem **IGDLA**-Ansatz:
$$\text{Identitäten (User/Computer)} \rightarrow \text{Globale Gruppen (Businessrollen)} \rightarrow \text{Domänenlokale Gruppen (Ressourcen-Zugriff)} \rightarrow \text{Zugriffsrechte (Permissions)}$$

**In der Übung erstellte Gruppen:**
* `GG_CH_Management` (Scope: Global | Type: Security)
* `GG_CH_IT` (Scope: Global | Type: Security)
* `GG_CH_Helpdesk` (Scope: Global | Type: Security)

---

## 4. Delegation von Rechten & Gruppenrichtlinien (GPOs)

### Delegation
Dem Helpdesk (`GG_CH_Helpdesk`) wird die Berechtigung erteilt, Passwörter von regulären Benutzern in der OU `Switzerland/Users` zurückzusetzen. Dies wird über den *Delegation of Control Wizard* umgesetzt und erzeugt die expliziten Rechte `Reset password` und `Read all properties` auf untergeordnete Benutzerobjekte.

### Gruppenrichtlinien (GPOs)
GPOs sind hierarchische Infrastrukturen zur Durchsetzung von Sicherheits- und Konfigurationseinstellungen für Benutzer und Computer.

* **CH Computer Policy:** Erstellt und verknüpft mit der OU `Workstations Unmanaged`.
* **Restricted Groups (Eingeschränkte Gruppen):** Die lokale Gruppe `Administrators` auf den Clients wird so konfiguriert, dass sie automatisch die Domänen-Gruppen `GG_CH_Systemadmins` und `GG_CH_Helpdesk` sowie die lokalen Konten `labadmin` und `eduadm` beinhaltet.
* **Remotezugriff:** Die Domänengruppe `Domain Users` wird via GPO in die lokale Gruppe `Remote Desktop Users` der Clients aufgenommen, um RDP-Zugriff zu erlauben.
* **Sofortige Anwendung:** Da GPOs zeitverzögert greifen, wird die Übernahme im Terminal mittels `gpupdate /force` erzwungen.

### Firewall & UAC (User Account Control) Einstellungen via GPO
* **UAC Zweck:** Verhindert, dass Anwendungen unautorisiert mit erhöhten Rechten ausgeführt werden. Im *Admin Approval Mode* verhält sich ein Admin-Konto wie ein Standardnutzer, bis eine explizite Bestätigung erfolgt.
* **Firewall GPO:** Blockiert standardmässig eingehende Verbindungen im Domänenprofil und sperrt die Option für Benutzer, die Firewall lokal zu deaktivieren (Meldung: *"For your security, some settings are managed by your system administrator"*).

---

## 5. Berechtigungskonzept & Fileserver-Verwaltung

Ein Berechtigungskonzept basiert immer auf: **Identität**, **Operation (Aktion)** und **Ressource**.

### Verzeichnisstruktur und Freigabe
Auf dem Windows Server wird unter `C:\Daten` eine Ordnerstruktur angelegt:
* `Allgemeine Dokumente` (Freigabe für alle Domänen-Benutzer via `Domain Users`).
* `IT` (Zugriff geschützt über IGDLA-Verschachtelung in `DL_CH_IT`).
* `Management` (Zugriff geschützt über IGDLA-Verschachtelung in `DL_CH_Management`).

Die NTFS-Vererbung (*Inheritance*) wird für die Unterordner aufgehoben (*Disable inheritance*), und geerbte Berechtigungen werden in explizite Berechtigungen umgewandelt, um Standard-Usergruppen (`Users`) zu entfernen.

### Access-Based Enumeration (Zollbasierte Aufzählung)
Dieses Feature wird in den *File and Storage Services* für die Freigabe aktiviert. Es sorgt dafür, dass Benutzern **nur** die Ordner und Dateien im Explorer angezeigt werden, für die sie mindestens Leserechte besitzen. Der Ordner `Management` bleibt für einen regulären IT-Mitarbeiter somit komplett unsichtbar.

---

## 6. Active Directory Management mit PowerShell (Optional)
PowerShell ist ein objektbasiertes (auf .NET CLR aufbauendes) Automatisierungs-Framework.

* **Wichtige Befehle der Übung:**
  * Gruppenmitglieder auslesen (nur User): `Get-ADGroupMember -Identity Administrators | Where-Object -Property objectClass -eq user`
  * Neue User & Gruppe anlegen: `New-ADGroup`, `New-ADUser`
  * Mitglieder hinzufügen: `Add-ADGroupMember -Identity "GG_CH_IT" -Members "Benutzer1"`
  * CSV-Export (1 Zeile): `Get-ADGroupMember -Identity "GG_CH_IT" | Where-Object { $_.objectClass -eq "user" } | Select-Object Name, SamAccountName, DistinguishedName | Export-Csv -Path "C:\ADGroupMember_export.csv" -NoTypeInformation`
  * Objekte verschieben: `Move-ADObject`
  * Account-Status ändern: `Disable-ADAccount`, `Enable-ADAccount`

---

## Wichtigste Key Takeaways

> [!key] **Zentrale Erkenntnisse für das Access Management**
> 1. **Das Prinzip der minimalen Rechtevergabe (Least Privilege):** IAM-Konzepte müssen so granular wie nötig, aber so einfach wie möglich gehalten werden, um den administrativen Aufwand zu minimieren.
> 2. **IGDLA als Best Practice:** Direktes Zuweisen von Benutzern zu Ressourcenberechtigungen skaliert nicht. Benutzer gehören in *Globale Gruppen* (Rollen), diese in *Domänenlokale Gruppen* (Ressourcen-Zugang).
> 3. **OUs steuern Richtlinien, Container nicht:** Für administrative Delegationen und die Zuweisung von GPOs müssen zwingend Organisationseinheiten (OUs) verwendet werden.
> 4. **Sicherheitsgewinn durch UAC & Access-Based Enumeration:** UAC schützt vor unbemerktem Rechtemissbrauch durch Malware. Access-Based Enumeration erhöht die Informationssicherheit ("Need-to-Know"), indem nicht zugriffsberechtigte Ordnerstrukturen vor dem Anwender unsichtbar gemacht werden.
> 5. **PowerShell für Skalierung:** In modernen Infrastrukturen erfolgt die Identitätsverwaltung effizient und reproduzierbar über CLI/PowerShell-Skripte statt über die grafische GUI.