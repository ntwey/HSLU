# Zusammenfassung: Labor Man-in-the-Middle (MITM)

Diese Zusammenfassung deckt den gesamten Inhalt des Labors ab, in dem ein Angriff auf ein Netzwerk simuliert wird, um Daten abzufangen. Ziel ist es, die Funktionsweise von ARP Request Poisoning und DNS Spoofing zu verstehen sowie Gegenmassnahmen kennenzulernen.

## 1. Einführung und Grundlagen

### Was ist ein Man-in-the-Middle (MITM) Angriff?
Ein MITM-Angriff liegt vor, wenn sich ein Angreifer unbemerkt zwischen zwei Kommunikationspartner (z. B. ein Opfer und das Internet/Gateway) schaltet. Der Angreifer kann Daten mitlesen, verändern oder weiterleiten.

**Die Labor-Umgebung:**
* **Angreifer (Attacker):** Kali Linux (eine spezielle Linux-Version für Sicherheitstests).
* **Opfer (Victim):** Ubuntu Client.
* **Gateway:** Der Router (oder Windows-Host), der den Zugang zum Internet bereitstellt.
* **Ziel:** Login-Daten des Opfers für die Webseite "mycampus.hslu.ch" abfangen.

---

## 2. Vorbereitung und Netzwerk-Scan

Bevor der Angriff startet, muss der Angreifer das Netzwerk erkunden.

1.  **IP-Adressen & MAC-Adressen:** Im lokalen Netzwerk (LAN) kommunizieren Geräte über MAC-Adressen (Hardware-Adressen), nicht direkt über IP-Adressen.
2.  **Scannen mit Nmap:** Der Angreifer nutzt das Tool `nmap`, um alle aktiven Geräte im Netzwerk zu finden.
    * Ergebnis: Man identifiziert die IP des Gateways (Router) und des Opfers (Ubuntu).
3.  **Vorbereitung des Angreifers:** Damit das Opfer nicht merkt, dass seine Verbindung unterbrochen ist, muss der Angreifer-PC (Kali) die Datenpakete weiterleiten.
    * Dies geschieht durch Aktivieren des **IP-Forwardings** im Linux-Kernel (`sysctl -w net.ipv4.ip_forward=1`). Ohne diesen Schritt hätte das Opfer kein Internet mehr, sobald der Angriff beginnt.

---

## 3. Angriff Teil 1: ARP Request Poisoning

Dies ist die Basis, um sich "in die Mitte" zu setzen.

### Das technische Konzept: ARP
Das **Address Resolution Protocol (ARP)** verknüpft eine IP-Adresse (z. B. 192.168.1.1) mit einer MAC-Adresse. Geräte führen eine Tabelle (ARP-Cache), in der steht: "Wer IP X erreichen will, muss an MAC-Adresse Y senden". Das Protokoll ist "vertrauensselig" – es glaubt jeder Antwort, auch ungefragten.

### Der Angriff (Arpspoof)
Der Angreifer nutzt das Tool `arpspoof`, um das Netzwerk mit gefälschten ARP-Antworten zu fluten:
1.  **Zum Opfer sagt er:** "Ich bin das Gateway" (Die IP des Routers gehört zu meiner MAC-Adresse).
2.  **Zum Gateway sagt er:** "Ich bin das Opfer" (Die IP des Opfers gehört zu meiner MAC-Adresse).

**Das Ergebnis:**
* Das Opfer sendet seinen Internetverkehr nicht mehr an den Router, sondern an den Angreifer.
* Der Angreifer liest mit und leitet die Daten an den echten Router weiter.
* In der ARP-Tabelle des Opfers (`arp -a`) ändert sich die MAC-Adresse des Gateways zu der des Angreifers.

### Analyse mit Wireshark
Mit dem Analyse-Tool **Wireshark** kann der Angreifer nun den Datenverkehr sehen:
* **HTTP (unverschlüsselt):** Passwörter und Inhalte sind im Klartext lesbar.
* **HTTPS (verschlüsselt):** Man sieht zwar den Verbindungsaufbau (TLS Handshake), aber die eigentlichen Daten (wie Passwörter) sind verschlüsselt und nicht lesbar.
* **Gegenmassnahme (DAI):** Moderne Switches/Router nutzen "Dynamic ARP Inspection". Sie erkennen, wenn eine MAC-Adresse plötzlich für eine falsche IP antwortet und blockieren dies oder senden Warnungen (ICMP Redirects).

---

## 4. Angriff Teil 2: DNS Spoofing

Da HTTPS den Inhalt schützt, muss der Angreifer das Opfer auf eine gefälschte Seite locken, die kein HTTPS nutzt oder unter seiner Kontrolle steht.

### Das technische Konzept: DNS
Das **Domain Name System (DNS)** ist das Telefonbuch des Internets. Es übersetzt Namen wie `mycampus.hslu.ch` in IP-Adressen (z. B. 147.88.201.69).

### Der Angriff (Ettercap)
Der Angreifer manipuliert diese Übersetzung. Er nutzt das Framework **Ettercap** und ein Plugin (`dns_spoof`).
1.  **Konfiguration:** In einer Datei (`etter.dns`) wird festgelegt: "Wenn jemand nach `mycampus.hslu.ch` fragt, antworte mit der IP des Angreifers (Kali Linux), nicht mit der echten IP."
2.  **Konfiguration der Rechte:** Anpassung der Datei `etter.conf`, um Root-Rechte und IP-Weiterleitung (via `iptables`) zu ermöglichen.
3.  **Ausführung:** Da der Angreifer durch das ARP-Poisoning bereits den Verkehr kontrolliert, kann er die DNS-Anfragen des Opfers abfangen und die gefälschte Antwort zurücksenden.

**Das Ergebnis:**
Gibt das Opfer `mycampus.hslu.ch` im Browser ein, landet es auf dem Webserver des Angreifers, glaubt aber, auf der richtigen Seite zu sein.

---

## 5. Angriff Teil 3: Phishing / Webserver Manipulation

Um nun wirklich an das Passwort zu kommen, muss der Angreifer dem Opfer eine täuschend echte Login-Seite präsentieren.

### Vorgehensweise
1.  **Klonen der Seite:** Mit dem Tool **httrack** kopiert der Angreifer die echte MyCampus-Webseite auf seinen lokalen Apache-Webserver.
2.  **Manipulation (Die Falle):**
    * Der Angreifer bearbeitet den HTML-Code der gefälschten Seite (`index.html`).
    * Er ändert das `action`-Attribut des Login-Formulars. Anstatt die Daten an den echten HSLU-Server zu senden, sendet das Formular die Daten an ein Skript oder eine Seite, die der Angreifer kontrolliert (im Laborbeispiel eine Wartungsseite über HTTP).
3.  **Credential Harvesting (Ernte):**
    * Das Opfer sieht die Login-Maske.
    * Es gibt Benutzername und Passwort ein.
    * Da die manipulierte Seite unverschlüsseltes HTTP nutzt (oder der Angreifer das so eingestellt hat), werden die Daten im Klartext versendet.
    * Der Angreifer sieht in Wireshark einen `HTTP POST`-Request, der Benutzername und Passwort im Klartext enthält.

### Woran erkennt man den Angriff?
Für das Opfer gibt es Warnsignale:
* **Kein HTTPS:** Im Browser fehlt das Schloss-Symbol oder es erscheint eine Warnung "Nicht sicher".
* **Zertifikatsfehler:** Da der Angreifer kein gültiges Zertifikat für die echte Domain hat, würde der Browser bei HTTPS massiv warnen.
* **Verdächtiges Verhalten:** Nach dem Login landet man z. B. auf einer Fehler- oder Wartungsseite, anstatt eingeloggt zu werden.

## Zusammenfassung der Tools

* **Nmap:** Zum Finden von Geräten im Netz.
* **Arpspoof:** Um sich als Router auszugeben (Basis für MITM).
* **Wireshark:** Zum Analysieren und Mitlesen der Datenpakete.
* **Ettercap:** Mächtiges Tool für MITM, hier speziell für DNS Spoofing genutzt.
* **Httrack:** Zum Kopieren von Webseiten für Phishing.
* **Apache2:** Der Webserver auf dem Angreifer-PC, der die Fake-Seite ausliefert.