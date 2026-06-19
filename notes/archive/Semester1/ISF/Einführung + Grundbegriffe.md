## Daten
![[Screenshot 2025-02-18 at 10.22.11.png]]

## Safety Security
![[Screenshot 2025-02-18 at 10.23.43.png]]
## Informations-,  Cyber-, IT-Sicherheit
![[Screenshot 2025-02-18 at 10.24.12.png]]
## Integrale Sicherheit
Sicherheit im Allgemeinen -> Versicherungen, Physische Sicherheit

## Risiko
Möglicher negativer Ausgang bei einer Unternehmung, mit dem Nachteile, Verlust, Schäden
verbunden sind; mit einem Vorhaben, Unternehmen o. Ä. verbundenes Wagnis.‟
	**Risiko = Wahrscheinlichkeit * Schadensausmass**
Wahrscheinlichkeiten sind extrem schwer zu berechnen
→ Eintretenshäufigkeit verwenden = Geschätzte Häufigkeiten (x-mal pro Zeitraum)
	**Risiko = Eintretenshäufigkeit * Schadensausmass**
![[Screenshot 2025-02-18 at 10.32.32.png]]
## Drei Säulen Confidentiality, Availability, Integrity 
Confidentiality: Vertraulichkeit
Integrity: Integrität
Availability: Verfügbarkeit

![[Screenshot 2025-02-18 at 10.30.15.png]]
### Confidentiality
Vertraulichkeit ist gegeben, wenn sichergestellt werden kann, dass Informationen nicht
durch unautorisierte Personen, Instanzen oder Prozesse eingesehen werden können

Technische Massnahmen:
- Verschlüsselung: AES, …
- Zugriffsschutz: RBAC, …
- Bell-LaPadula Prinzip
Organisatorische Massnahmen:
- Briefgeheimnis
- Fernmeldegeheimnis
- Non disclosure agreement (NDA)
#### Vertraulichkeit – Bell-LaPadula Prinzip
Das Bell-LaPadula-Sicherheitsmodell gilt als das erste
vollständig formalisierte. Es schützt die Vertraulichkeit von
Informationen mittels eines Systems durchgesetzter Regeln. Es setzt
somit das Konzept Mandatory Access Control der IT-
Systemsicherheit um.![[Screenshot 2025-02-18 at 10.35.10.png]]
### Availability
Verfügbarkeit ist gewährleistet, wenn in der vom Benutzer gewünschten Zeit auf
Dienste oder Informationen zugegriffen werden kann.

### Integrity
Integrität ist gewährleistet, wenn Daten oder Systeme nicht unautorisiert oder zufällig
manipuliert oder verändert werden können.
Technische Massnahmen:
- HMAC: AES, …
- Digitale Signaturen, …
- Biba-Prinzip
Organisatorische Massnahmen:
- PKI
- Blockchain
#### Integrität – Biba-Prinzip
Das Biba-Modell wurde 1975 von Kenneth J. Biba beschrieben.
Gegenstand des Modells ist die Integrität der Daten. Das Biba-
Modell ist eine Umkehrung des Bell-LaPadula-Sicherheitsmodelles,
welches vor allem die Vertraulichkeit von Datenzugriffen
anspricht.
Es werden vor jedem Zugriff Regeln überprüft:
▪ No-Read-Down: Es darf einer höher eingestuften Ebene nicht
möglich sein, Informationen einer tieferen Sicherheitsebene zu
lesen.
▪ No-Write-Up: Es darf Schichten, welche über eine tiefere
Sicherheitsebene verfügen, nicht erlaubt sein, Daten in eine
höhere Sicherheitsebene zu schreiben

![[Screenshot 2025-02-18 at 10.37.17.png]]
#### Prüfsumme
Definition: Prüfsummen sind Werte, die vor und nach der Übertragung aus den übertragenen
Daten selbst erzeugt werden. Sie dienen zur Erkennung von Verfälschungen der Daten.
Beispiel: Prüfsumme einer Zahl = Quersumme der Zahl
Zahl: 34567
Quersumme: 3 + 4 + 5 + 6 + 7 = 25

Lösung: Ändert sich eine der Dezimalstellen, kann das die Prüfsumme feststellen.
Problem: Ändern sich zwei oder mehr Dezimalstellen, kann das die Prüfsumme nicht feststellen.

#### Integrität Anwendungsarten und Nutzen
![[Screenshot 2025-02-18 at 10.25.23.png]]

## Schutzziele 
### Privatsphäre – Privacy
Privatsphäre bezeichnet den nicht-öffentlichen Bereich, in dem ein Mensch, unbehelligt von
äußeren Einflüssen, sein Recht auf freie Entfaltung seiner Persönlichkeit wahrnimmt.
#### Beispiele:
Briefgeheimnis
Privatsphäre im Internet: Einwilligungsmanagement
### Anonymisierung – Pseudonymisierung
Die Anonymisierung ist das **Verändern personenbezogener Daten** derart, dass diese Daten **nicht mehr** oder nur mit einem unverhältnismäßig großen Aufwand an Zeit, Kosten und Arbeitskraft einer bestimmten oder **bestimmbaren natürlichen Person zugeordnet werden können**. Eine vollständige Anonymisierung ist sehr schwer zu erlangen. BDSG

„Pseudonymisierung die **Verarbeitung personenbezogener Daten in einer Weise**, dass die personenbezogenen Daten **ohne Hinzuziehung zusätzlicher Informationen nicht mehr einer spezifischen betroffenen Person zugeordnet werden können,** sofern diese zusätzlichen Informationen gesondert aufbewahrt werden und technischen und
organisatorischen Maßnahmen unterliegen, die gewährleisten, dass die personenbezogenen Daten nicht einer identifizierten oder identifizierbaren natürlichen Person zugewiesen werden;“ DSGVO

![[Screenshot 2025-02-18 at 10.57.09.png]]

### Ausfallsicherheit – resilience
Ausfallsicherheit ist die definierte Sicherheit gegen einen Ausfall. Sie wird meist durch den Einsatz von Redundanzen erhöht.
![[Screenshot 2025-02-18 at 10.58.18.png]]
###  Zutritts-, Zugangs-, Zugriffskontrolle
![[Screenshot 2025-02-18 at 10.58.45.png]]
### Weitere
Weitere Sicherheitsziele
Neben den drei Grundschutzzielen gibt es weitere Sicherheitsziele wie
- Authentizität / Authentisierung / Authentifizierung
- Autorisierung (Berechtigung) – authorization
- Zugriffskontrolle – access control, accountability
- Zugreifbarkeit – accessibility
- Zuverlässigkeit, Verlässlichkeit, Glaubwürdigkeit – reliability
- Verbindlichkeit, Nicht-Abstreitbarkeit – non-repudiation, legal enforceability, non-deniability
- Vollständigkeit – completeness
- Unbeobachtbarkeit – non-observability
- Wirksamkeit, Effektivität – effectiveness
- Kopierschutz – copyprotection
- Privatsphäre, Datenschutz – privacy
### Sicherheitsanforderungen in offenen Kommunikationsnetzen
Für die sichere Nutzung des Internets ist die
Gewährleistung folgender Sicherheitsziele zu garantieren:
− Unveränderte Identität des Senders,
− Unverfälschtheit Integrität der Informationen,
− Vertraulichkeit der Informationen und
− Verbindlichkeit des Informationsaustauschs (Sender hat
gesendet, Empfänger hat entgegengenommen)