# Zusammenfassung: Physische Sicherheit von Schlössern & Schlüsseln
(Basierend auf dem Vortrag des Forensischen Instituts Zürich)

## 1. Einleitung und Geschichte
Der Vortrag behandelt die Entwicklung, Funktionsweise und Überwindung von Schliesssystemen aus forensischer Sicht.

* **Ursprünge:** Die Geschichte der Schlösser reicht weit zurück. Das älteste bekannte Schloss (ein ägyptisches Fallriegelschloss aus Holz) wird auf ca. 4000 Jahre geschätzt.
* **Mittelalter:** Es dominierten grosse, metallene Schlösser mit ebenso grossen, unhandlichen Schlüsseln.
* **Der Wendepunkt (Neuzeit):** Vor ca. 160 Jahren revolutionierte Linus Yale Junior die Technik. Er erfand das moderne Zylinderschloss mit Stiftzuhaltungen. Dies ermöglichte kleine, flache Schlüssel und legte den Grundstein für die heutigen Standardschlösser.

## 2. Theoretische Grundlagen: Verschluss vs. Schloss
Es ist wichtig, zwei Begriffe zu unterscheiden:

* **Verschluss:** Ein Mechanismus, der eine Tür oder Klappe lediglich zuhält (z. B. eine Falle am Türgriff). Er bietet *keine* Sicherheit gegen unbefugtes Öffnen.
* **Schloss:** Ein mechanisches System, das den Verschluss in einer gesperrten Position hält. Es kann nur durch ein "Geheimnis" (meistens ein Schlüssel) entsperrt werden.

## 3. Die Funktionsweise eines modernen Stiftzylinders (Theorie)
Dies ist der wichtigste theoretische Teil zum Verständnis von Sicherheit und Lockpicking.

### Der Aufbau
Ein Zylinderschloss besteht im Inneren aus mehreren Bohrungen, in denen sich jeweils ein **Stiftpaar** und eine **Feder** befinden.
1.  **Kernstifte (unten):** Diese berühren den Schlüssel. Sie sind unterschiedlich lang, passend zu den Einkerbungen des Schlüssels.
2.  **Gehäusestifte (oben):** Diese drücken auf die Kernstifte.
3.  **Federn:** Diese drücken alles nach unten in den Schlüsselkanal.

### Der Zustand "Verschlossen"
Ohne Schlüssel drücken die Federn die Gehäusestifte so weit nach unten, dass sie *gleichzeitig* im Gehäuse und im drehbaren Kern stecken. Dadurch blockieren sie die Drehung des Kerns mechanisch (ähnlich wie ein Riegel).

### Der Zustand "Geöffnet" (Das Prinzip der Scherlinie)
Wenn der *richtige* Schlüssel eingeführt wird, heben dessen Zacken die Stiftpaare an.
* Das Ziel: Die Trennlinie zwischen Kernstift und Gehäusestift muss exakt auf der **Scherlinie** (der Trennlinie zwischen dem drehbaren Kern und dem festen Gehäuse) liegen.
* Sind alle Stifte korrekt ausgerichtet, gibt es kein Hindernis mehr. Der Kern lässt sich drehen, das Schloss öffnet.

## 4. Arten von Schlössern

### Mechanische Schlösser
* **Buntbartschloss:** Einfache Bauweise (z. B. alte Zimmertüren), der Schlüsselbart muss nur durch eine Form passen. Geringe Sicherheit.
* **Chubbschloss (Zuhaltungsschloss):** Nutzt mehrere hebelartige Zuhaltungen.
* **Zylinderschloss:** Der heutige Standard (wie oben beschrieben), oft als Profilzylinder.
* **Zahlenkombinationsschloss:** Basiert auf drehbaren Scheiben mit Einkerbungen (Gate). Wenn alle Einkerbungen ausgerichtet sind, fällt ein Riegel hinein und das Schloss öffnet.

### Elektronische Schlösser
Nutzen Strom statt Mechanik zur Identifikation (PIN-Code, RFID-Chip, Fingerabdruck, Smartphone).
* **Vorteile:** Komfort (kein Schlüsselverlust), Protokollierung wer wann Zutritt hatte, einfache Sperrung von Benutzern.
* **Nachteile:** Benötigen Strom, potenziell anfällig für Hacking, teurer.

## 5. Methoden der Überwindung (Einbruch & Öffnung)

Man unterscheidet grundsätzlich zwischen zerstörerischen und zerstörungsfreien Methoden.

### A. Gewaltsame Überwindung (Zerstörend)
Hier wird die Funktionstüchtigkeit des Schlosses oder der Tür vernichtet.
* **Methoden:** Aufhebeln der Tür, Herausreissen oder Aufbohren des Zylinders ("Ziehen"), Nutzung von Bolzenschneidern bei Vorhängeschlössern.
* **Extremfälle:** Sprengung von Geldautomaten (Gasangriffe).

### B. Zerstörungsfreie Überwindung (Intelligent / "Lockpicking")
Hier wird das Schloss geöffnet, ohne es kaputtzumachen. Dies nutzt oft Fertigungstoleranzen aus.

#### 1. Lockpicking (Aufsperren)
Das manuelle Setzen der Stifte ohne Schlüssel.
* **Werkzeuge:** Ein **Spanner** (setzt den Kern unter leichte Drespannung) und ein **Pick** (Haken, Diamant, Snake).
* **Theorie dahinter:** Aufgrund minimaler Ungenauigkeiten bei der Herstellung sind die Bohrungen für die Stifte nie perfekt in einer Reihe. Wenn man den Kern mit dem Spanner leicht dreht, klemmt zuerst *ein* Stift (der sogenannte "Bindestift"). Man drückt diesen Stift mit dem Pick herunter, bis er an der Scherlinie einrastet. Dann sucht man den nächsten klemmenden Stift.

#### 2. Racking / Picking mit Hilfsmitteln
* **Pickpistole:** Ein Gerät schlägt schnell gegen alle Stifte. Durch den Impuls springen die Gehäusestifte kurz nach oben (ähnlich wie Billardkugeln), und es entsteht kurzzeitig eine Lücke an der Scherlinie, in der man den Kern drehen kann.
* **Schlagschlüssel (Bump Key):** Ein speziell gefräster Schlüssel, der ins Schloss geschlagen wird. Funktioniert nach dem gleichen Impuls-Prinzip wie die Pickpistole.

#### 3. Impressioning (Abdruckverfahren)
Man nimmt einen Schlüsselrohling, steckt ihn ins Schloss und wackelt. Die harten Stifte im Schloss hinterlassen winzige Marken auf dem weichen Rohling. An diesen Stellen feilt man Material ab. Dies wiederholt man, bis ein funktionierender Schlüssel entsteht.

#### 4. Dekodieren (Abtasten)
Spezialwerkzeuge (wie Lishi-Tools) messen die Tiefe der Stifte im Schloss, um die "Schließung" auszulesen. Danach kann ein Nachschlüssel gefertigt werden.

#### 5. Umgehung (Bypass)
Der Angriff erfolgt nicht auf den Zylinder selbst, sondern auf den Schliessmechanismus dahinter.
* Beispiel: Ein Blech ("Shim") wird in ein Vorhängeschloss geschoben, um die Verriegelung direkt wegzudrücken.
* Beispiel: Öffnen von Kippfenstern durch Angeln des Griffs.

## 6. Forensik
Das Forensische Institut untersucht nach Einbrüchen die Spuren.
* **Werkzeugspuren:** Kratzer an den Stiften oder im Schlüsselkanal verraten, ob Picking-Werkzeuge oder Schlagschlüssel benutzt wurden.
* **Materialanalysen:** Untersuchung von Metallspänen oder verformten Teilen nach gewaltsamer Öffnung.