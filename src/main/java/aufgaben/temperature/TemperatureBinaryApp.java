package aufgaben.temperature;

import aufgaben.temperature.history.TemperatureHistory;
import aufgaben.temperature.history.TemperatureHistoryBinaryStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TemperatureBinaryApp {
    private static final Path FILE_PATH = Paths.get("C:\\Users\\alexs\\development\\HSLU-OOP\\src\\main\\resources\\temperature.dat");

    public static void main(String[] args) {
        try {
            TemperatureHistoryBinaryStorage storage = new TemperatureHistoryBinaryStorage(FILE_PATH);
            TemperatureBinaryApp app;
            if (Files.exists(FILE_PATH)) {
                app = new TemperatureBinaryApp(storage.read());
            } else {
                app = new TemperatureBinaryApp();
            }
            app.run();
            storage.write(app.getHistory());
        } catch (Throwable e) {
            LOGGER.error("Unhandled exception", e);
        }
    }

    private static final Logger LOGGER = LogManager.getLogger(TemperatureBinaryApp.class);
    private static final DecimalFormat FORMATTER = new DecimalFormat("#0.00");
    private final TemperatureHistory history;

    public TemperatureBinaryApp() {
        this(new TemperatureHistory());
    }

    public TemperatureBinaryApp(TemperatureHistory history) {
        this.history = history;
        history.addListener(event -> {
            LOGGER.trace("Temperature History event {}", event);
            switch (event.getType()) {
                case MAX:
                    System.out.println("New max temp: " + tempAsUserReadableString(event.getMeasurePoint().getTemperature()));
                    break;
                case MIN:
                    System.out.println("New min temp: " + tempAsUserReadableString(event.getMeasurePoint().getTemperature()));
                    break;
            }
        });
    }

    private void run() {
        System.out.println("Temperature History ('exit' to end)");
        Scanner scanner = new Scanner(System.in);
        boolean exited = false;
        do {
            System.out.print("Temperature: ");
            String input = scanner.next().trim();
            LOGGER.trace("Input {}", input);
            if ("exit".equals(input)) {
                exited = true;
                printStatistics(history);
            } else {
                try {
                    history.add(parseTemperature(input));
                } catch (TemperatureParseException exception) {
                    System.out.println(exception.getMessage());
                    //LOGGER.error(exception.getMessage(), exception); // causes problems in ide console :(
                }
            }
        } while (!exited);
        LOGGER.debug("Exit");
    }

    private void printStatistics(TemperatureHistory history) {
        System.out.println("History Statistics:");
        System.out.println("Nr. of entries: " + history.getCount());
        if (history.getCount() > 0) {
            System.out.println("Avg. temp: " + tempAsUserReadableString(history.average()));
            System.out.println("Max temp: " + tempAsUserReadableString(history.max().getTemperature()));
            System.out.println("Min temp: " + tempAsUserReadableString(history.min().getTemperature()));
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

    public TemperatureHistory getHistory() {
        return history;
    }
}
