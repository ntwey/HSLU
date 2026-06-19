```java

/**
 * Listener-Interface (Funktionaler Vertrag)[cite: 77].
 */
@FunctionalInterface
interface AlarmListener {
    void onAlarm(String nachricht);
}

/**
 * Event-Quelle (Subject).
 * Reduziert die Kopplung, da sie nur das Interface kennt[cite: 79].
 */
class Sensor {
    private final List<AlarmListener> listeners = new ArrayList<>();

    public void registriereListener(AlarmListener listener) {
        listeners.add(listener);
    }

    public void ausloesen() {
        // Benachrichtigung aller registrierten Listener [cite: 76]
        for (AlarmListener l : listeners) {
            l.onAlarm("Rauch entdeckt!");
        }
    }
}

```