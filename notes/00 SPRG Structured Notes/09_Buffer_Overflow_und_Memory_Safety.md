# Buffer Overflow & Memory Safety

In hardwarenahen Sprachen wie C oder C++ existiert kein automatisches, integriertes Speichermanagement. Die manuelle Verwaltung des Speichers ist die fundamentale Ursache für gravierende Sicherheitslücken auf Systemebene.

## 1. Technischer Ablauf eines Buffer Overflows
Ein Buffer Overflow (Pufferüberlauf) entsteht immer dann, wenn eine Anwendung Daten in einen definierten Speicherbereich (Buffer) schreibt, die zugewiesene Kapazität dieses Buffers jedoch im Code niemals überprüft wird. Dadurch werden direkt angrenzende Speicherbereiche auf dem Stack gnadenlos überschrieben.

### Das klassische, unsichere Beispiel in C:
```c
#include <stdio.h>
#include <string.h>

int main() {
    char buffer[4]; // Reserviert exakt 4 Bytes auf dem Stack
    printf("Enter your input: ");
    gets(buffer);   // REINES GIFT: liest unbegrenzt Input ein, bis ein Newline kommt!
    printf("You entered: %s\n", buffer);
    return 0;
}
```
* **Die Auswirkung:** Wenn ein Benutzer eine Zeichenkette eingibt, die länger als 4 Bytes ist (z. B. `abcdefghijklmnopqrstuvwxyz`), läuft der zugewiesene Speicherbereich über. Das Betriebssystem bricht die Ausführung meist mit einem kritischen Systemfehler ab: `Segmentation fault (core dumped)`.

## 2. Der Stack-Mechanismus und Exploitation
Der Stack dient der Verwaltung von lokalen Variablen und dem Kontrollfluss von Funktionen. 
* Wenn eine Funktion aufgerufen wird, wird ein **Stack-Frame** erstellt. Dieser speichert lokale Variablen (wie unseren Puffer `buffer`).
* Direkt hinter den lokalen Variablen (in Richtung der höheren Speicheradressen) liegen kritische Verwaltungsdaten: der *Saved Frame Pointer* und die **Return Address** (die Rücksprungadresse, welche der CPU mitteilt, wohin sie nach dem Beenden der aktuellen Funktion im Programmcode zurückspringen muss).
* **Wachstumsrichtung:** Wenn Daten in einen Puffer geschrieben werden, bewegen sie sich im Speicher linear vorwärts in Richtung der höheren Speicheradressen – also direkt auf die Return Address zu.
* **Der Exploit:** Ein Angreifer füllt den Puffer gezielt mit exakt berechneten Fülldaten (Padding), überschreibt den Saved Frame Pointer und ersetzt die legitime Return Address durch eine von ihm manipulierte Speicheradresse. Dadurch biegt er den Kontrollfluss des gesamten Programms um und zwingt die CPU, zu seinem eigenen Schadcode (**Shellcode**) zu springen, welchen er ebenfalls im überlaufenden Stack platziert hat.

## 3. Eingebaute Schutzmechanismen von OS und Compiler
Moderne Systeme erschweren die Ausnutzung von Buffer Overflows massiv durch drei standardisierte Barrieren:

1. **Stack Canaries (Stack-Sicherheitswerte):** Der Compiler fügt beim Kompilieren automatisch eine Prüfung ein. Er platziert einen zufälligen Schutzwert (Canary) direkt vor die Return Address auf den Stack. Bevor die Funktion per `ret` zurückspringt, wird geprüft, ob der Canary noch intakt ist. Wurde er durch einen Pufferüberlauf modifiziert, terminierte das Programm sofort, noch bevor die manipulierte Return Address geladen wird.
2. **ASLR (Address Space Layout Randomisation):** Das Betriebssystem randomisiert die Speicheradressen von Programmkomponenten, Stack, Heap und Bibliotheken bei jedem einzelnen Programmstart komplett neu. Angreifer können dadurch die exakten Sprungziele für ihren Shellcode nicht mehr vorhersagen.
3. **DEP / NX (Data Execution Prevention / Non-executable code segments):** Der Prozessor markiert beschreibbare Speicherbereiche (wie den Stack) explizit als "nicht ausführbar". Die CPU weigert sich strikt, Code auszuführen, der in diesen Segmenten liegt. Es gilt das Prinzip: Was beschreibbar ist, darf nicht ausführbar sein.

## 4. ROP: Die Umgehung von DEP (Return-Oriented Programming)
Da durch DEP kein eigener Shellcode mehr auf dem Stack ausgeführt werden kann, haben Angreifer **ROP** entwickelt. 
* Bei ROP wird *kein eigener Code* eingeschleust. Stattdessen missbraucht der Angreifer den bereits vorhandenen, legitimen und vom Betriebssystem als ausführbar freigegebenen Programmcode (z. B. Funktionen aus geladenen Systembibliotheken wie der `libc`).
* Der Angreifer durchsucht den ausführbaren Speicher nach winzigen Code-Fragmenten, die auf die Assembler-Anweisung `ret` (`c3`) enden. Diese Fragmente nennt man **Gadgets**. Eine typische Gadget-Sequenz erhöht beispielsweise ein Register oder verschiebt einen Pointer.
* Durch das präzise Platzieren einer Kette von manipulierten Return-Adressen auf dem Stack werden diese Gadgets vom Prozessor nacheinander wie Dominosteine ausgeführt (**ROP-Chain**). Da jedes Gadget für sich aus legitimem, erlaubtem Speicher stammt, schlägt DEP/NX nicht an, während die logische Verkettung im Gesamten die bösartige Aktion des Angreifers ausführt.

## 5. Gegenmassnahmen für Entwickler
Sicherheit ist ein permanentes Wettrüsten. Die einzig nachhaltige Verteidigung ist sauberer Code:
* **Eingabe-Längen zwingend validieren:** Vor jedem Schreibvorgang in einen Buffer muss die Länge des Inputs strikt geprüft werden.
* **Nutzung sicherer Funktionen:** Die Verwendung unsicherer Funktionen wie `gets()`, `strcpy()`, `sprintf()` oder `strcat()` ist im modernen Software Engineering strengstens untersagt. Es müssen ausnahmslos die sicheren, längenbeschränkten Varianten wie `fgets()`, `strncpy()`, `snprintf()` genutzt werden.
* **Compiler-Optionen erzwingen:** Moderne Sicherheitsfeatures wie Stack Canaries (`-fstack-protector`), ASLR und DEP/NX-Support standardmässig beim Kompilieren erzwingen.
* **Sichere Programmiersprachen einsetzen:** Wo keine direkte Hardwarenähe erforderlich ist, sollten von Grund auf speichersichere Sprachen (z. B. Rust, Java, Go, C#) eingesetzt werden, welche Array-Grenzen automatisch zur Laufzeit oder Kompilierzeit überprüfen.