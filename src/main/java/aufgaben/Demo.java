package aufgaben;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintStream;

class Demo {
    private static final Logger LOGGER = LogManager.getLogger(Demo.class);

    /**
     * Returns the greater of two {@code int} values. That is, the
     * result is the argument closer to the value of
     * {@link Integer#MAX_VALUE}. If the arguments have the same value,
     * the result is that same value.
     *
     * @param a an argument.
     * @param b another argument.
     * @return the larger of {@code a} and {@code b}.
     */
    public int max(int a, int b) {
        return a > b ? a : b;
    }

    public int min(int a, int b) {
        return a < b ? a : b;
    }

    public int max(int a, int b, int c) {
        return max(a, max(b, c));
    }

    public int maxAlt(int a, int b, int c) {
        if (a > b) {
            return a > c ? a : c;
        } else {
            return b > c ? b : c;
        }
    }

    public int max(int... numbers) throws IllegalArgumentException {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("At least one number required");
        }
        int currentMax = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            currentMax = max(currentMax, numbers[i]);
        }
        return currentMax;
    }

    public void print1To10For(PrintStream out) {
        for (int i = 0; i <= 10; i++) {
            out.println(i);
        }
    }

    public void print1To10While(PrintStream out) {
        int i = 0;
        while (i <= 10) {
            out.println(i);
            i++;
        }
    }

    public void print1To10DoWhile(PrintStream out) {
        int i = 0;
        do {
            out.println(i);
            i++;
        } while (i <= 10);
    }

    public int whileFloatSmaller1() {
        float value = 0.9f;
        int i = 0;
        while (value <= 0.999999f) {
            value += 0.000025f;
            i++;
        }
        LOGGER.debug("WhileFloatSmaller1 final value: {}", value);
        return i;
    }

    public float forFloatSmaller1() {
        float value = 0.9f;
        for (int i = 0; i < 4000; i++) {
            value += 0.000025f;
        }
        return value;
    }

    public void printBox(int height, int width, PrintStream out) {
        String border = "#".repeat(width) + System.lineSeparator();
        String middle = "#" + " ".repeat(width - 2) + "#" + System.lineSeparator();
        out.print(border + middle.repeat(height - 2) + border);
        /*
        StringBuilder border = new StringBuilder();
        StringBuilder middle = new StringBuilder();
        for (int currentWidth = 1; currentWidth <= width; currentWidth++) {
            if (currentWidth == 1 || currentWidth == width) {
                middle.append("#");
            } else {
                middle.append(" ");
            }
            border.append("#");
        }
        for (int currentHeight = 1; currentHeight <= height; currentHeight++) {
            if (currentHeight == 1 || currentHeight == height) {
                out.println(border);
            } else {
                out.println(middle);
            }
        }
         */
        /*
        for (int currentHeight = 1; currentHeight <= height; currentHeight++) {
            for (int currentWidth = 1; currentWidth <= width; currentWidth++) {
                if (currentHeight == 1 || currentHeight == height || currentWidth == 1 || currentWidth == width) {
                    out.print("#");
                } else {
                    out.print(" ");
                }
            }
            out.println();
        }
        */
    }
}
