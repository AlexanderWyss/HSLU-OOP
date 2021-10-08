package aufgaben.elements;

import aufgaben.temperature.Temperature;
import aufgaben.temperature.history.MeasurePoint;
import aufgaben.temperature.history.TemperatureHistory;
import aufgaben.temperature.history.TemperatureHistoryBinaryStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureHistoryBinaryStorageTest {
    @TempDir
    Path testDir;
    Path testFile;

    @BeforeEach
    void setUp() {
        testFile = testDir.resolve("test.txt");
    }

    @AfterEach
    void tearDown() {
        testFile.toFile().delete();
    }

    @Test
    void write_temperatureHistory_correctFile() throws IOException {
        TemperatureHistory history = new TemperatureHistory();
        history.add(Temperature.celsius(16.4));
        history.add(Temperature.celsius(0));
        history.add(Temperature.celsius(-4.75));
        history.add(Temperature.celsius(90));

        getStorage().write(history);

        assertTrue(testFile.toFile().exists());
        byte[] expectedResult = ByteBuffer.allocate(36).putInt(4).putDouble(16.4).putDouble(0).putDouble(-4.75).putDouble(90).array();
        assertArrayEquals(expectedResult, readBytes());
    }

    @Test
    void write_emptyTemperatureHistory_correctFile() throws IOException {
        TemperatureHistory history = new TemperatureHistory();

        getStorage().write(history);

        assertTrue(testFile.toFile().exists());
        byte[] expectedResult = ByteBuffer.allocate(4).putInt(0).array();
        assertArrayEquals(expectedResult, readBytes());
    }

    @Test
    void read_fileWithTemperatureHistory_correctFile() throws IOException, URISyntaxException {
        TemperatureHistoryBinaryStorage historyStorage = new TemperatureHistoryBinaryStorage(Paths.get(getClass().getResource("temperature.dat").toURI()));
        TemperatureHistory history = historyStorage.read();

        assertEquals(5, history.getCount());
        Iterator<MeasurePoint> iterator = history.iterator();
        assertEquals(5, iterator.next().getTemperature().getCelsius());
        assertEquals(4, iterator.next().getTemperature().getCelsius());
        assertEquals(10, iterator.next().getTemperature().getCelsius());
        assertEquals(16, iterator.next().getTemperature().getCelsius());
        assertEquals(-4, iterator.next().getTemperature().getCelsius());
        assertFalse(iterator.hasNext());
    }

    private byte[] readBytes() throws IOException {
        try (FileInputStream fis = new FileInputStream(testFile.toFile())) {
            return fis.readAllBytes();
        }
    }

    private TemperatureHistoryBinaryStorage getStorage() {
        return new TemperatureHistoryBinaryStorage(testFile);
    }
}