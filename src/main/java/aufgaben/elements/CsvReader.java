package aufgaben.elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public List<String[]> read(File file) throws IOException {
        List<String[]> lines = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file); BufferedReader reader = new BufferedReader(fileReader)) {
            reader.lines().forEach(line -> lines.add(line.split(";")));
        }
        return lines;
    }
}
