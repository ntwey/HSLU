package ch.hslu.oop.sw11;

import java.time.LocalDateTime;

public class Measurement {

    private final LocalDateTime timestamp;
    private final float temperature;
    private final int humidity;

    public Measurement(LocalDateTime timestamp, float temperature, int humidity) {
        if (timestamp == null) {
            throw new IllegalArgumentException("timestamp is null");
        }
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public float getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }
}
