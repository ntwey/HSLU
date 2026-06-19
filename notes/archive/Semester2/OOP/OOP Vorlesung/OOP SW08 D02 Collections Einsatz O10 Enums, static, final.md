# Java Collections & I/O-Datenströme – Zusammenfassung

## 1. Java Collections Framework

### 1.1 Grundidee
- Collections verwalten Mengen von Objekten
- Zugriff über Interfaces → Austauschbarkeit von Implementierungen
- Einheitliche Bedienung aller Datenstrukturen

### 1.2 Zentrale Interfaces

#### Collection
- Basis aller Collections
- Abfragen: size(), isEmpty(), contains()
- Modifikation: add(), remove(), clear()
- Massenoperationen: addAll(), removeAll(), retainAll()

#### Set 
- Keine Duplikate (equals() + hashCode())
- Reihenfolge nicht garantiert
- null maximal einmal erlaubt
- Objekte nach Einfügen nicht verändern
- Implementierungen: HashSet, LinkedHashSet, TreeSet

#### Liste
- Geordnete, indexbasierte Struktur
- Duplikate erlaubt
- Zero-based Index
- Vergleichbar mit Arrays
- Implementierungen: ArrayList, LinkedList
- Synchronisierte Klassen (Vector, Stack) eher vermeiden

#### Queue
- Warteschlange, meist FIFO
- Einfügen am Ende, Entfernen am Anfang
- Typisch für Puffer und Warteschlangen
- Implementierungen: ArrayDeque, PriorityQueue, BlockingQueues

#### Deque
- Double Ended Queue
- FIFO oder FILO (Stack)
- push() / pop() für Stack-Semantik
- Implementierungen: ArrayDeque, LinkedList

#### Map<K,V>
- Schlüssel-Wert-Paare
- Schlüssel eindeutig
- Werte dürfen mehrfach vorkommen
- Schlüssel nach Einfügen nicht verändern
- Implementierungen: HashMap, TreeMap, EnumMap

---

## 2. Iteratoren

### 2.1 Motivation
- Traversieren unabhängig von der Datenstruktur
- Kein Index erforderlich

### 2.2 Iterator-Typen
- Iterator: nur vorwärts
- ListIterator: vorwärts und rückwärts (nur für Lists)

### 2.3 Verwendung
- iterator() liefert neuen Iterator
- next() ist konsumierend
- Entfernen nur über iterator.remove()

### 2.4 for-each-Schleife
- Vereinfachte Iteration
- Typsicher und empfohlen
- Intern Iterator-basiert

---

## 3. Manipulation von Collections

### Collections-Hilfsklasse
- sort(), reverseOrder()
- min(), max()
- frequency()

### Voraussetzungen
- Comparable oder Comparator
- Konsistente equals() / hashCode()

---

## 4. I/O-Datenströme in Java

### 4.1 Grundkonzept
- Abstraktion von Quelle, Senke und Datenform
- Betriebssystem-Ressourcen → immer schliessen
- Packages: java.io, java.nio

---

## 5. Arten von Datenströmen

### 5.1 Byte-Datenströme
- Klassen: InputStream, OutputStream
- Binär, effizient, kompakt
- Beispiele:
  - FileInputStream / FileOutputStream
  - BufferedInputStream / BufferedOutputStream
  - DataInputStream / DataOutputStream

### 5.2 Zeichen-Datenströme
- Klassen: Reader, Writer
- Unicode-Zeichen
- Encoding zwingend beachten
- Beispiele:
  - BufferedReader, PrintWriter
  - InputStreamReader, OutputStreamWriter

---

## 6. Stream-Kaskadierung (Decorator Pattern)
- Streams werden verkettet
- Kombination von Datei, Puffer, Encoding
- Flexible Wiederverwendbarkeit

---

## 7. Exceptionhandling bei I/O

### try-with-resources
- Automatisches Schliessen von Closable-Ressourcen
- Seit Java 7

### Multi-catch
- Mehrere Exceptiontypen in einem catch
- Reduziert Code-Duplikation

---

## 8. Encoding

### Grundlagen
- Zeichen werden als Zahlen gespeichert
- Encoding ordnet Zahlen Zeichen zu

### Wichtige Encodings
- ASCII / ISO-8859-x (historisch)
- Unicode
- UTF-8 (empfohlen)

### Empfehlungen
- Encoding immer explizit setzen
- UTF-8 als Standard verwenden
- Encoding-Probleme niemals ignorieren

---

## 9. Dateihandling

### Wichtige Klassen
- java.io.File
- java.nio.file.Path
- java.nio.file.Files

### Herausforderungen
- Unterschiedliche Pfade
- Zeilenumbrüche
- Byteorder
- Encoding

---

## 10. Kernaussagen
- Interfaces definieren Semantik, Implementierungen Verhalten
- Iteratoren ermöglichen saubere Traversierung
- Byte- vs. Zeichenströme bewusst wählen
- Encoding ist kritisch
- I/O-Ressourcen immer korrekt schliessen
