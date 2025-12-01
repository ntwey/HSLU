package ch.hslu.oop.sw11;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TemperatureCourseTest {

    @Test
    public void testStatistics() {
        TemperatureCourse course = new TemperatureCourse();

        course.add(new Measurement(LocalDateTime.of(2023, 1, 10, 10, 0), 10.0f, 50));
        course.add(new Measurement(LocalDateTime.of(2023, 1, 10, 11, 0), 5.0f, 55));
        course.add(new Measurement(LocalDateTime.of(2023, 1, 10, 12, 0), 15.0f, 45));

        assertEquals(3, course.size());
        assertEquals(5.0f, course.getMinTemperature(), 0.0001f);
        assertEquals(15.0f, course.getMaxTemperature(), 0.0001f);
        assertEquals(10.0f, course.getAverageTemperature(), 0.0001f);

        assertEquals(15.0f, course.getMeasurementWithMaxTemperature().getTemperature(), 0.0001f);
        assertEquals(5.0f, course.getMeasurementWithMinTemperature().getTemperature(), 0.0001f);
    }

    @Test
    public void testStatisticsOnEmptyCourse() {
        TemperatureCourse course = new TemperatureCourse();
        assertThrows(IllegalStateException.class, course::getMinTemperature);
        assertThrows(IllegalStateException.class, course::getMaxTemperature);
        assertThrows(IllegalStateException.class, course::getAverageTemperature);
    }
}
