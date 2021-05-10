package aufgaben.elements;

import java.io.*;
import java.nio.file.Path;

public class TemperatureHistoryBinaryStorage {
    private final Path filePath;

    public TemperatureHistoryBinaryStorage(final Path filePath) {
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

    public TemperatureHistory read() throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(filePath.toFile()))) {
            TemperatureHistory history = new TemperatureHistory();
            int count = dis.readInt();
            for (int i = 0; i < count; i++) {
                history.add(Temperature.celsius(dis.readDouble()));
            }
            return history;
        }
    }
}
