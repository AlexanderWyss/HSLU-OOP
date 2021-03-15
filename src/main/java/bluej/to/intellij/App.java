package bluej.to.intellij;

import java.io.IOException;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) throws IOException {
        BlueJToIntelliJ converter = new BlueJToIntelliJ(Paths.get("C:\\Users\\alexs\\development\\HSLU-OOP"), false);
        converter.convert(Paths.get("C:\\Users\\alexs\\development\\HSLU-OOP\\bluej\\OFWJ-chapter03\\chapter03"));
    }
}
