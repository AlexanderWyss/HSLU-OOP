package aufgaben.temperature;

import aufgaben.temperature.history.MeasurePoint;
import aufgaben.temperature.history.TemperatureHistory;
import aufgaben.temperature.history.TemperatureHistoryCsvReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class TemperatureCsvApp {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.00");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public static void main(String[] args) throws URISyntaxException, IOException {
        File file = new File(TemperatureCsvApp.class.getClassLoader().getResource("netatmo-export-201801-201804.csv").toURI());
        TemperatureHistory history = new TemperatureHistoryCsvReader().read(file);
        System.out.println("Avg. Temp: " + DECIMAL_FORMAT.format(history.average().getCelsius()) + "C");
        MeasurePoint max = history.max();
        System.out.println("Max. Temp: " + DECIMAL_FORMAT.format(max.getTemperature().getCelsius()) +
                "C at " + DATE_TIME_FORMATTER.format(max.getDatetime()));
        MeasurePoint min = history.min();
        System.out.println("Min. Temp: " + DECIMAL_FORMAT.format(min.getTemperature().getCelsius()) +
                "C at " + DATE_TIME_FORMATTER.format(min.getDatetime()));
    }
}
