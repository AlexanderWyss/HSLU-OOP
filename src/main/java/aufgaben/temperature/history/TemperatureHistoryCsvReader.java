package aufgaben.temperature.history;

import aufgaben.temperature.Temperature;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TemperatureHistoryCsvReader {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("\"yyyy/MM/dd HH:mm:ss\"");

    public TemperatureHistory read(File file) throws IOException {
        List<String[]> data = new CsvReader().read(file);
        TemperatureHistory history = new TemperatureHistory();
        for (String[] row : data) {
            Temperature temperature = Temperature.celsius(Double.parseDouble(row[2]));
            LocalDateTime dateTime = LocalDateTime.parse(row[1], DATE_TIME_FORMATTER);
            history.add(new MeasurePoint(temperature, dateTime));
        }
        return history;
    }
}
