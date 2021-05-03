package aufgaben.elements;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        new App().run();
    }

    private static final DecimalFormat FORMATTER = new DecimalFormat("#0.00");

    private void run() {
        System.out.println("Temperature History ('exit' to end)");
        TemperatureHistory history = new TemperatureHistory();
        history.addListener(event -> {
            switch (event.getType()) {
                case MAX:
                    System.out.println("New max temp: " + tempAsUserReadableString(event.getTemperature()));
                    break;
                case MIN:
                    System.out.println("New min temp: " + tempAsUserReadableString(event.getTemperature()));
                    break;
            }
        });
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
        if (history.getCount() > 0) {
            System.out.println("Avg. temp: " + tempAsUserReadableString(history.average()));
            System.out.println("Max temp: " + tempAsUserReadableString(history.max()));
            System.out.println("Min temp: " + tempAsUserReadableString(history.min()));
        }
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

    private String tempAsUserReadableString(final Temperature temp) {
        return FORMATTER.format(temp.getCelsius()) + "C";
    }
}
