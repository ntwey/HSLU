public class Demo {
    // --- kleine Demo im main (freiwillig) ---
    public static void main(String[] args) {
        System.out.println("max(4,7) = " + max(4, 7));
        System.out.println("max3_reuse(4,7,5) = " + max3_reuse(4, 7, 5));

        print0to10For();
        accumulateFloatWhile();
        accumulateFloatFor();
        printBox(10, 4);
        printSwissCross(15);
    }

    // --- 1.3 a) ---
    public static int max(int a, int b) {
        return (a >= b) ? a : b;
    }

    // --- 1.3 b) verschiedene Varianten ---
    // Variante 1: nur if
    public static int max3_if(int a, int b, int c) {
        int m = a;
        if (b > m) m = b;
        if (c > m) m = c;
        return m;
    }

    // Variante 2: Wiederverwendung von a)
    public static int max3_reuse(int a, int b, int c) {
        return max(max(a, b), c);
    }

    // Variante 3: mit else-if (kompakt)
    public static int max3_elseif(int a, int b, int c) {
        if (a >= b && a >= c) return a;
        else if (b >= a && b >= c) return b;
        else return c;
    }

    // --- 2.3 a) / b) Zahlen 0..10 ausgeben ---
    public static void print0to10While() {
        int i = 0;
        while (i <= 10) {
            System.out.println(i);
            i++;
        }
    }

    public static void print0to10DoWhile() {
        int i = 0;
        do {
            System.out.println(i);
            i++;
        } while (i <= 10);
    }

    public static void print0to10For() {
        for (int i = 0; i <= 10; i++) {
            System.out.println(i);
        }
    }

    // --- 2.3 c) / d) Float-Addition bis "1.0f" ---
    // Liefert die Anzahl Iterationen zurück und druckt den Endwert.
    public static int accumulateFloatWhile() {
        float x = 0.9f;
        int iterations = 0;
        final float step = 0.000025f;
        final float target = 1.0f;
        // robuste Abbruchbedingung wegen Rundungsfehlern (Epsilon)
        final float EPS = 1e-7f;

        // Sicherheitsnetz gegen Endlosschleifen
        final int HARD_LIMIT = 1_000_000;

        while (x + EPS < target && iterations < HARD_LIMIT) {
            x += step;
            iterations++;
        }
        System.out.println("Endwert x = " + x + " nach " + iterations + " Iterationen");
        return iterations;
    }

    // --- 2.3 e) exakt 4000 Iterationen mit for ---
    // Gibt den Endwert zurück (sollte nahe, aber i.d.R. nicht exakt 1.0f sein).
    public static float accumulateFloatFor() {
        float x = 0.9f;
        final float step = 0.000025f;
        for (int i = 0; i < 4000; i++) {
            x += step;
        }
        System.out.println("Endwert nach 4000 Schritten: " + x);
        return x;
    }

    // --- 2.3 f) Box ausgeben ---
    public static void printBox(final int width, final int height) {
        if (width < 2 || height < 2) {
            // Kleinste sinnvolle Box ist 2x2
            for (int r = 0; r < height; r++) {
                for (int c = 0; c < width; c++) System.out.print("#");
                System.out.println();
            }
            return;
        }

        // Top
        for (int c = 0; c < width; c++) System.out.print("#");
        System.out.println();

        // Middle
        for (int r = 0; r < height - 2; r++) {
            System.out.print("#");
            for (int c = 0; c < width - 2; c++) System.out.print(" ");
            System.out.println("#");
        }

        // Bottom
        for (int c = 0; c < width; c++) System.out.print("#");
        System.out.println();
    }

    // --- 2.3 g) (optional) variables Schweizerkreuz ---
    // size >= 5, ungerade empfohlen
    public static void printSwissCross(int size) {
        if (size < 5) size = 5;
        int arm = Math.max(1, size / 5);      // Armbreite
        int bar = Math.max(arm * 3, arm + 2); // Mittelbalkenbreite
        int n = size;

        int mid = n / 2;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                boolean horiz =
                        r >= mid - arm && r <= mid + arm && c >= mid - bar && c <= mid + bar;
                boolean vert =
                        c >= mid - arm && c <= mid + arm && r >= mid - bar && r <= mid + bar;
                System.out.print(horiz || vert ? "#" : " ");
            }
            System.out.println();
        }
    }
}
