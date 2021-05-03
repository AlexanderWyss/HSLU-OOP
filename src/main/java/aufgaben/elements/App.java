package aufgaben.elements;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("Temperature History ('exit' to end)");
        TemperatureHistory history = new TemperatureHistory();
        Scanner scanner = new Scanner(System.in);
        boolean exited = false;
        do {
            System.out.print("Temperature: ");
            String input = scanner.next().trim();
            if ("exit".equals(input)) {
                exited = true;
                printStatistics(history);
            } else {
                try {
                    Temperature temp = parseTemperature(input);
                    history.add(temp);
                } catch (TemperatureParseException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        } while (!exited);
        System.out.println("Exited");
    }

    private void printStatistics(TemperatureHistory history) {
        System.out.println("History Statistics:");
        System.out.println("Nr. of entries: " + history.getCount());
        System.out.println("Avg. temp: " + history.average().getCelsius() + "C");
        System.out.println("Max temp: " + history.max().getCelsius() + "C");
        System.out.println("Min temp: " + history.min().getCelsius() + "C");
    }

    private Temperature parseTemperature(String input) throws TemperatureParseException {
        try {
            TemperatureUnit unit = TemperatureUnit.fromUnit(String.valueOf(input.charAt(input.length() - 1)));
            double value = Double.parseDouble(input.substring(0, input.length() - 1));
            return Temperature.of(value, unit);
        } catch (NumberFormatException e) {
            throw new TemperatureParseException("Invalid value. Not a number.", e);
        } catch (IllegalArgumentException e) {
            throw new TemperatureParseException("No valid Unit specified. End Temperature with one of "
                    + Arrays.stream(TemperatureUnit.values()).map(TemperatureUnit::getUnit)
                    .collect(Collectors.joining(", ")), e);
        }
    }
}