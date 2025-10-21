class Temperatur{
    public float temperaturCelsius = 20.0f;

    public Temperatur(float temperaturCelsius){
        this.temperaturCelsius = temperaturCelsius;
    }

    public float getTemperaturCelsius() {
        return temperaturCelsius;
    }
    public void setTemperaturCelsius(float temperaturCelsius) {
        this.temperaturCelsius = temperaturCelsius;
    }
import java.util.Map;

    public class Temperature {
        private float celsius;

        public Temperature(float celsius) {
            this.celsius = celsius;
        }

        public void setCelsius(float celsius) {
            this.celsius = celsius;
        }

        public float getCelsius() {
            return celsius;
        }

        // --- 1.3 d) Variante mit if/else ---
        public String stateForElementIfElse(String symbol) {
            String s = symbol.trim();
            float t = celsius;

            if (s.equalsIgnoreCase("N")) { // Stickstoff
                float melt = -210.0f;
                float boil = -195.8f;
                return phaseByThresholds(t, melt, boil);
            } else if (s.equalsIgnoreCase("Hg")) { // Quecksilber
                float melt = -38.83f;
                float boil = 356.73f;
                return phaseByThresholds(t, melt, boil);
            } else if (s.equalsIgnoreCase("Pb")) { // Blei
                float melt = 327.46f;
                float boil = 1749.0f;
                return phaseByThresholds(t, melt, boil);
            } else {
                return "unbekanntes Element";
            }
        }

        // --- 1.3 e) Variante mit switch ---
        public String stateForElementSwitch(String symbol) {
            String s = symbol.trim();
            float t = celsius;
            switch (s.toUpperCase()) {
                case "N":
                    return phaseByThresholds(t, -210.0f, -195.8f);
                case "HG":
                    return phaseByThresholds(t, -38.83f, 356.73f);
                case "PB":
                    return phaseByThresholds(t, 327.46f, 1749.0f);
                default:
                    return "unbekanntes Element";
            }
        }

        // --- 1.3 f) skalierbar mit Map (leicht erweiterbar um ~100+ Elemente) ---
        private static final Map<String, float[]> PHASE_LIMITS = Map.of(
                "N",  new float[]{-210.0f, -195.8f},
                "HG", new float[]{-38.83f, 356.73f},
                "PB", new float[]{327.46f, 1749.0f}
        );

        public String stateForElement(String symbol) {
            float[] limits = PHASE_LIMITS.get(symbol.trim().toUpperCase());
            if (limits == null) return "unbekanntes Element";
            return phaseByThresholds(celsius, limits[0], limits[1]);
        }

        // Hilfsfunktion: bestimmt Phase anhand Schmelz- und Siedepunkt
        private static String phaseByThresholds(float t, float melt, float boil) {
            if (t < melt) return "fest";
            if (t >= melt && t < boil) return "flüssig";
            return "gasförmig";
        }
    }

    public float getTemperaturKelvin() {
        return temperaturCelsius + 273.15f
    }
}