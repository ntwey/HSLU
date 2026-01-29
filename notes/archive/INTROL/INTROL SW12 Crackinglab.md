# Zusammenfassung Labor 7: Cracking Lab & IT-Sicherheit
## Modul: INTROL (Einführendes Labor Sicherheit)

Dieses Dokument fasst die praktischen Übungen und die theoretischen Hintergründe des Labors zusammen. Ziel war es, Passwörter zu verstehen, Hashing-Methoden kennenzulernen und Angriffe praktisch durchzuführen (Passwort-Cracking und Systemübernahme).

---

## 1. Theoretische Grundlagen: Passwörter und Hashing

### Was macht ein sicheres Passwort aus?
Ein sicheres Passwort schützt die digitale Identität. Wichtige Regeln sind:
* **Länge:** Mindestens 12 Zeichen.
* **Komplexität:** Mischung aus Groß-/Kleinbuchstaben, Zahlen und Sonderzeichen.
* **Zufälligkeit:** Keine echten Wörter oder logische Sätze (Wörterbuch-Attacken).
* **Einzigartigkeit:** Für jeden Dienst ein anderes Passwort nutzen.
* **Keine Muster:** Keine angehängten Zahlen (z.B. "Passwort1", "Passwort2"), da Hacker diese Muster kennen.

### Die Theorie des "Hashing"
Wenn ein Passwort gespeichert wird, wird es nicht im Klartext (z.B. "Hallo123") in der Datenbank abgelegt, sondern als **Hash-Wert**.

* **Funktionsweise:** Eine Hash-Funktion (wie SHA-256 oder MD5) ist eine mathematische "Einwegfunktion". Sie verwandelt eine Eingabe beliebiger Länge in eine Zeichenkette fester Länge (den Hash).
* **Einweg-Prinzip:** Aus dem Hash (z.B. `a5b9...`) kann man das ursprüngliche Passwort mathematisch nicht zurückrechnen.
* **Determinismus:** Die gleiche Eingabe erzeugt immer denselben Hash.
* **Avalanche-Effekt:** Schon die Änderung eines einzigen Zeichens im Passwort verändert den kompletten Hash-Wert massiv.

**Das Problem:** Wenn zwei Benutzer dasselbe Passwort haben, haben sie denselben Hash. Ein Hacker erkennt sofort: "Aha, dieser Hash entspricht '123456'".

### Die Lösung: "Salting" (Salzen)
Um zu verhindern, dass gleiche Passwörter gleiche Hashes ergeben und um Angriffe zu erschweren, nutzt man "Salz".
* **Vorgehen:** Vor dem Hashing wird eine zufällige Zeichenfolge (der Salt) an das Passwort angehängt.
* **Formel:** `Hash(Passwort + Salt) = Gespeicherter Wert`
* **Effekt:** Selbst wenn zwei Nutzer das Passwort "Haus" haben, ist ihr Salt unterschiedlich.
    * Nutzer A: "Haus" + "Xy9" -> Hash A
    * Nutzer B: "Haus" + "Lm2" -> Hash B
* **Sicherheitsgewinn:** Dies macht vorberechnete Tabellen (Rainbow Tables, siehe unten) nutzlos, da der Angreifer für jeden möglichen Salt eine eigene Tabelle berechnen müsste.

---

## 2. Angriffsarten auf Passwörter

Im Labor wurden verschiedene Methoden theoretisch und praktisch behandelt:

1.  **Wörterbuchangriff (Dictionary Attack):**
    * Der Angreifer nutzt eine Liste mit echten Wörtern (Duden, geleakte Passwortlisten). Er hasht jedes Wort und vergleicht es mit dem gestohlenen Hash.
    * *Effektiv gegen:* Schwache Passwörter wie "Sommer2025".

2.  **Brute-Force-Angriff:**
    * Der Angreifer probiert systematisch **alle** Zeichenkombinationen durch (aaaa, aaab, aaac...).
    * *Vorteil:* Findet garantiert das Passwort.
    * *Nachteil:* Dauert bei langen Passwörtern Jahre oder Jahrhunderte.

3.  **Rainbow Tables (Regenbogen-Tabellen):**
    * **Theorie:** Dies ist ein Kompromiss zwischen Zeit und Speicherplatz (Time-Memory Tradeoff).
    * Anstatt jeden Hash live zu berechnen (kostet Zeit) oder alle Hashes zu speichern (kostet zu viel Speicherplatz), werden "Ketten" berechnet.
    * Eine Kette besteht aus Startwert -> Hash -> Reduktionsfunktion (macht aus Hash wieder Text) -> Hash -> ... -> Endwert.
    * In der Tabelle werden nur Start- und Endwerte gespeichert. Findet man einen Hash, kann man die Kette zurückverfolgen.
    * *Nachteil:* Funktioniert nicht, wenn Passwörter "gesalzen" (Salted) sind.

4.  **Maskenangriff:**
    * Eine optimierte Brute-Force-Attacke, bei der man Wissen über das Passwort hat (z.B. "Es fängt groß an und endet mit zwei Zahlen"). Man testet nur Muster wie `GkkkkZZ` (G=Groß, k=klein, Z=Zahl).

---

## 3. Praktische Durchführung im Labor

Das Labor fand in einer isolierten Umgebung (Rotes Netzwerk) statt, da echte Hacking-Tools verwendet wurden.

### Schritt A: Passwörter auslesen (Windows)
* Auf dem Windows-PC wurden die lokalen Benutzerkonten analysiert.
* Mit dem Tool **pwdump8** wurden die Passwort-Hashes aus dem Windows-System (SAM-Datenbank) ausgelesen.
* Ergebnis: Eine Textdatei mit Benutzernamen und den dazugehörigen kryptischen Hash-Werten.

### Schritt B: Cracking mit Ophcrack (Vorgefertigte Rainbow Tables)
* Das Tool **Ophcrack** wurde genutzt, um die ausgelesenen Hashes zu knacken.
* Es wurden Standard-Tabellen ("Vista free") geladen.
* **Ergebnis:** Einfache Passwörter (wie "Hslu123" oder "testtest") wurden in Sekunden gefunden, da ihre Hashes bereits in den Tabellen vorberechnet waren.

### Schritt C: Eigene Rainbow Tables erstellen (WinRTGen)
* Szenario: Wir wissen, ein Passwort hat genau 4 bis 6 Zeichen (Kleinbuchstaben).
* Mit **WinRTGen** wurde eine spezifische Rainbow Table für diesen Fall erstellt.
* **Theorie:** Durch die Einschränkung auf "nur Kleinbuchstaben" und "Länge 4-6" ist die Tabelle viel kleiner und schneller zu erstellen als eine Tabelle für alle möglichen Passwörter.
* Mit dem Tool **RainbowCrack** wurde diese eigene Tabelle dann genutzt, um das Passwort eines bestimmten Studenten-Accounts ("passw") extrem schnell zu finden.

---

## 4. Systemübernahme (Post-Exploitation)

Im letzten Teil wurde gezeigt, wie ein Angreifer volle Kontrolle über einen PC erlangt, wenn er einen Nutzer dazu bringt, eine Datei auszuführen.

### Das Werkzeug: Metasploit Framework (auf Kali Linux)
Metasploit ist ein mächtiges Framework für Penetrationstests.

1.  **Payload erstellen (msfvenom):**
    * Es wurde eine schadhafte Datei (`word.exe`) erstellt.
    * Diese enthielt eine **Reverse Shell**.
    * **Theorie Reverse Shell:** Normalerweise verbindet sich ein Nutzer zum Server. Hier aber verbindet sich das Opfer (Windows) *zurück* zum Angreifer (Kali), sobald die Datei gestartet wird. Das umgeht oft Firewalls, die eingehende Verbindungen blockieren, aber ausgehende erlauben.

2.  **Bereitstellung:**
    * Der Angreifer startete einen Webserver auf Kali Linux, damit das Opfer die Datei herunterladen kann.

3.  **Der Angriff:**
    * Auf Kali Linux wurde ein "Listener" (Wartestellung) gestartet (`msfconsole`).
    * Das Opfer (Windows-User) lud die Datei herunter, ignorierte Sicherheitswarnungen (SmartScreen) und führte sie aus.

4.  **Kontrolle (Meterpreter):**
    * Sobald die Datei lief, öffnete sich auf dem Kali-Rechner eine Session.
    * Der Angreifer hatte nun Zugriff auf die Windows-Konsole.
    * **Aktionen:**
        * `sysinfo`: Systemdaten auslesen.
        * `download`: Dateien vom Opfer stehlen (z.B. `win.ini`).
        * `run vnc`: Den Bildschirm des Opfers live beobachten (Screen Sharing).

---

## Fazit & Lernerfolg

1.  **Passwortsicherheit:** Kurze oder wörterbuchbasierte Passwörter sind in Sekunden knackbar. Salting ist essenziell, um Massen-Cracking (Rainbow Tables) zu verhindern.
2.  **Hash-Verständnis:** Hashes sind keine Verschlüsselung (man kann sie nicht entschlüsseln), aber man kann sie durch Ausprobieren (Brute Force/Rainbow Tables) "umkehren".
3.  **Gefahr durch Ausführung:** Das Ausführen einer unbekannten `.exe` Datei kann einem Angreifer sofortigen, vollen Zugriff (inkl. Bildschirmübertragung) auf das System geben, oft ohne dass der Nutzer es merkt.