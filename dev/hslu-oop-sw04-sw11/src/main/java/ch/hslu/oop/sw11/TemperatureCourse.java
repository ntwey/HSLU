package ch.hslu.oop.sw11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TemperatureCourse {

    private final List<Measurement> measurements = new ArrayList<>();

    public void add(Measurement measurement) {
        if (measurement != null) {
            measurements.add(measurement);
        }
    }

    public int size() {
        return measurements.size();
    }

    public List<Measurement> getAll() {
        return Collections.unmodifiableList(measurements);
    }

    public float getMinTemperature() {
        if (measurements.isEmpty()) {
            throw new IllegalStateException("no measurements");
        }
        float min = measurements.get(0).getTemperature();
        for (Measurement m : measurements) {
            if (m.getTemperature() < min) {
                min = m.getTemperature();
            }
        }
        return min;
    }

    public float getMaxTemperature() {
        if (measurements.isEmpty()) {
            throw new IllegalStateException("no measurements");
        }
        float max = measurements.get(0).getTemperature();
        for (Measurement m : measurements) {
            if (m.getTemperature() > max) {
                max = m.getTemperature();
            }
        }
        return max;
    }

    public float getAverageTemperature() {
        if (measurements.isEmpty()) {
            throw new IllegalStateException("no measurements");
        }
        float sum = 0.0f;
        for (Measurement m : measurements) {
            sum += m.getTemperature();
        }
        return sum / measurements.size();
    }

    public Measurement getMeasurementWithMaxTemperature() {
        if (measurements.isEmpty()) {
            throw new IllegalStateException("no measurements");
        }
        Measurement max = measurements.get(0);
        for (Measurement m : measurements) {
            if (m.getTemperature() > max.getTemperature()) {
                max = m;
            }
        }
        return max;
    }

    public Measurement getMeasurementWithMinTemperature() {
        if (measurements.isEmpty()) {
            throw new IllegalStateException("no measurements");
        }
        Measurement min = measurements.get(0);
        for (Measurement m : measurements) {
            if (m.getTemperature() < min.getTemperature()) {
                min = m;
            }
        }
        return min;
    }
}
