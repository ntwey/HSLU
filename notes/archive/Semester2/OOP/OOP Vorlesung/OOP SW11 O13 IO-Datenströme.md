# I/O-Datenströme in Java – Zusammenfassung

## 1. Grundkonzept der I/O-Datenströme

### 1.1 Idee
- I/O-Datenströme (Streams) abstrahieren **Datenquelle**, **Datensenke** und **Datenform**
- Ein Programm liest aus einem Stream oder schreibt in einen Stream
- Unabhängig von der konkreten Quelle:
  - Dateien
  - Konsole (stdin/stdout/stderr)
  - Netzwerk (Sockets)

### 1.2 Historischer Hintergrund
- Ursprung in Unix (stdin, stdout, stderr)
- Streams können mit Pipes kombiniert werden
- Konzept ist bis heute zentral

### 1.3 Wichtige Packages
- `java.io` (klassisch)
- `java.nio` (neuere Erweiterungen)

---

## 2. Zwei Arten von Datenströmen

### 2.1 Byte-Datenströme
- Reine Folge von Bytes (8 Bit)
- Binäre Daten
- Für Menschen nicht direkt lesbar
- Effizient und platzsparend

### 2.2 Zeichen-Datenströme
- Folge von Zeichen (Unicode)
- Textdaten, für Menschen lesbar
- Immer **encodiert**
- Weniger effizient als Byte-Streams

---

## 3. Byte-Datenströme

### 3.1 Grundlagen
- Basisklassen:
  - `InputStream`
  - `OutputStream`
- Lesen und Schreiben byteweise
- EOF/EOS wird mit `-1` signalisiert
- Standard-Streams:
  - `System.in`
  - `System.out`
  - `System.err`

### 3.2 Wichtige Klassen
- `FileInputStream` / `FileOutputStream`
- `BufferedInputStream` / `BufferedOutputStream`
- `DataInputStream` / `DataOutputStream`
- `PrintStream` (ASCII, kein Unicode!)

### 3.3 Kaskadierung (Decorator-Pattern)
- Streams werden verkettet
- Kombination von Funktionen:
  - Datei
  - Pufferung
  - Datentypen
- Beispiel:
  - Datei + DataInputStream + optional Buffer

---

## 4. Exceptionhandling bei I/O

### 4.1 Problem
- I/O verwendet Betriebssystem-Ressourcen
- Garbage Collection reicht nicht aus
- Streams müssen **immer geschlossen** werden

### 4.2 try-with-resources
- Seit Java 7
- Automatisches Schliessen aller `Closable`-Ressourcen
- Kein explizites `close()` nötig
- Mehrere Ressourcen möglich

### 4.3 Multi-catch
- Mehrere Exceptiontypen in einem `catch`
- Reduziert Code-Duplikation
- Nur verwenden, wenn keine sinnvolle gemeinsame Basisklasse existiert

---

## 5. Zeichen-Datenströme

### 5.1 Grundlagen
- Basisklassen:
  - `Reader`
  - `Writer`
- Arbeiten mit Unicode-Zeichen (16 Bit)
- EOF wird ebenfalls mit `-1` signalisiert
- Ideal für Textdateien (z.B. XML, JSON)

### 5.2 Wichtige Klassen
- `BufferedReader` / `BufferedWriter`
- `InputStreamReader` / `OutputStreamWriter`
  - Adapter zwischen Byte- und Zeichenströmen
- `FileReader` / `FileWriter`
  - Verwenden Default-Encoding (problematisch!)
- `PrintWriter`
  - Unicode-Ausgabe

### 5.3 Kaskadierung bei Text
- Typische Kette:
  - FileOutputStream
  - OutputStreamWriter (mit Charset)
  - BufferedWriter
  - PrintWriter

---

## 6. Encoding

### 6.1 Problemstellung
- Zeichen müssen Zahlenwerten zugeordnet werden
- Unterschiedliche Encodings führen zu Darstellungsfehlern
- Plattformabhängige Defaults sind gefährlich

### 6.2 ASCII
- 7-Bit-Zeichensatz
- Keine Umlaute
- Später erweitert (ISO-8859-x, cp1252)

### 6.3 Unicode
- Universeller Zeichensatz
- Unterstützt alle Sprachen
- Benötigt Transformationsformate:
  - UTF-8
  - UTF-16

### 6.4 UTF-8
- Variabel lange Codierung (1–4 Bytes)
- ASCII-kompatibel
- Platzsparend
- Standard im Internet

### 6.5 Empfehlung
- Encoding immer explizit setzen
- UTF-8 überall verwenden
- Default-Encoding niemals voraussetzen

---

## 7. Encoding in der Praxis

### 7.1 Typische Fehler
- Sonderzeichen werden falsch angezeigt
- Plattformübergreifende Probleme
- Besonders kritisch bei:
  - Dateiaustausch
  - Datenbanken
  - Internationalen Anwendungen

### 7.2 Leitsatz
- Encoding-Probleme nicht ignorieren
- Je später erkannt, desto teurer

---

## 8. Dateihandling in Java

### 8.1 Herausforderungen
- Unterschiedliche Dateisysteme
- Unterschiedliche Pfade
- Unterschiedliche Zeilenumbrüche
- Unterschiedliche Encodings
- Unterschiedliche Byteorder

### 8.2 Klasse java.io.File
- Repräsentiert Dateien und Verzeichnisse
- Keine I/O-Operationen selbst
- Metadaten und Verzeichnisoperationen
- Plattformunabhängig, aber mit Unterschieden

### 8.3 java.nio.file.Path und Files
- Modernere API
- Mehr native Funktionen
- Teilweise dateisystemabhängig
- Komfortabler für viele Operationen

---

## 9. Empfehlungen für den Einsatz

- Byte-Streams für binäre, effiziente Daten
- Zeichen-Streams für Text
- Encoding immer explizit setzen
- UTF-8 bevorzugen
- try-with-resources konsequent nutzen
- Multi-catch sinnvoll einsetzen
- Streams immer sauber schliessen

---

## 10. Kernaussagen
- I/O-Streams abstrahieren Ein- und Ausgabe
- Byte- und Zeichenströme bewusst unterscheiden
- Streams lassen sich flexibel kaskadieren
- Encoding ist ein zentrales Qualitätsmerkmal
- UTF-8 ist der De-facto-Standard
- Sauberes Exceptionhandling ist Pflicht
- File, Path und Files sind zentrale Hilfsklassen
