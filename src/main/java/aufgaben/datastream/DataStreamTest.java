package aufgaben.datastream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataStreamTest {
    private static final Logger LOG = LogManager.getLogger(DataStreamTest.class);
    private static final Path FILE_PATH = Paths.get("C:\\Users\\alexs\\development\\HSLU-OOP\\src\\main\\resources\\test.dat");

    public static void main(String[] args) {
        writeInt(257);
        //writeString("fuck that äöü$/:");
        read();
    }

    private static void read() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(FILE_PATH.toFile()))) {
            int value;
            while ((value = dis.read()) != -1) {
                LOG.debug(value);
            }
        } catch (IOException ioe) {
            LOG.error("Datei nicht lesbar.", ioe);
        }
    }

    private static void writeString(String text) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_PATH.toFile()))) {
            dos.writeUTF(text);
        } catch (IOException ioe) {
            LOG.error("Datei nicht schreibbar.", ioe);
        }
    }

    private static void writeInt(int number) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_PATH.toFile()))) {
            dos.writeInt(number);
        } catch (IOException ioe) {
            LOG.error("Datei nicht schreibbar.", ioe);
        }
    }
}
