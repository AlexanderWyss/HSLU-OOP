package aufgaben.elements;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class TemperatureHistoryStorage {
    private final Path filePath;

    public TemperatureHistoryStorage(final Path filePath) {
        this.filePath = filePath;
    }

    public void write(TemperatureHistory history) throws IOException {
        File file = filePath.toFile();
        if (!file.exists()) {
            file.createNewFile();
        }
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeInt(history.getCount());
            for (Temperature temp : history) {
                dos.writeDouble(temp.getCelsius());
            }
        }
    }
}
