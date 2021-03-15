package bluej.to.intellij;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BlueJToIntelliJ {
    public static final int UNDERSCORE = 95;
    public static final int ZERO = 48;
    public static final int NINE = 57;
    public static final int CAP_A = 65;
    public static final int CAP_Z = 90;
    public static final int A = 97;
    public static final int Z = 122;
    private final Path mainPath;
    private final Path testPath;
    private final boolean overwrite;
    private final List<String> generatedFiles = new ArrayList<>();
    private final List<String> copiedFiles = new ArrayList<>();

    public BlueJToIntelliJ(Path intellijProjectPath, boolean overwrite) {
        mainPath = intellijProjectPath.resolve("src/main/java");
        assertDir(mainPath.toFile());
        testPath = intellijProjectPath.resolve("src/test/java");
        assertDir(testPath.toFile());
        this.overwrite = overwrite;
    }


    public void convert(Path blueJProjectPath) throws IOException {
        File dir = blueJProjectPath.toFile();
        assertDir(dir);
        convertDir(dir, toValidPackageName(dir.getName()));
    }

    private void convertDir(File dir, String packageName) throws IOException {
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                convertDir(file, packageName + "." + toValidPackageName(file.getName()));
            } else {
                convertFile(file, packageName);
            }
        }
    }

    static final String[] keywords = {"abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "extends", "false",
            "final", "finally", "float", "for", "goto", "if", "implements",
            "import", "instanceof", "int", "interface", "long", "native",
            "new", "null", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient", "true",
            "try", "void", "volatile", "while"};

    private String toValidPackageName(String name) {
        byte[] bytes = name.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            byte currentByte = bytes[i];
            if (currentByte != UNDERSCORE && (currentByte < ZERO || (currentByte > NINE && currentByte < CAP_A) || (currentByte > CAP_Z && currentByte < A) || currentByte > Z)) {
                bytes[i] = 95;
            }
        }
        String result = new String(bytes);
        if (bytes[0] >= ZERO && bytes[0] <= NINE || Arrays.binarySearch(keywords, result) >= 0) {
            result = "_" + result;
        }
        return result;
    }

    private void convertFile(File file, String packageName) throws IOException {
        if (isJavaFile(file)) {
            String[] packages = packageName.split("\\.");
            String className = file.getName().split("\\.")[0];
            if (className.endsWith("Test")) {
                copyFile(file, packageName, resolveDir(testPath, packages));
            } else {
                copyFile(file, packageName, resolveDir(mainPath, packages));
                createTestFile(file, packageName, className, resolveDir(testPath, packages));
            }
        }
    }

    private void createTestFile(File file, String packageName, String className, Path testDir) throws IOException {
        testDir.toFile().mkdirs();
        File testFile = testDir.resolve(className + "Test.java").toFile();
        if (!testFile.exists() || (overwrite && !copiedFiles.contains(testFile.getAbsolutePath()))) {
            testFile.createNewFile();
            try (FileWriter writer = new FileWriter(testFile);) {
                writer.write("package " + packageName + ";\n" +
                        "\n" +
                        "import org.junit.jupiter.api.Test;\n" +
                        "import static org.junit.jupiter.api.Assertions.*;\n" +
                        "\n" +
                        "class " + className + "Test {\n" +
                        "\n" +
                        "    @Test\n" +
                        "    void test() {\n" +
                        "        assertTrue(true);\n" +
                        "    }\n" +
                        "}");
                System.out.println("Test: " + file.getAbsolutePath() + " -> " + testFile.getAbsolutePath());
                generatedFiles.add(testFile.getAbsolutePath());
            }
        }
    }

    private void copyFile(File file, String packageName, Path dir) throws IOException {
        dir.toFile().mkdirs();
        File newFile = dir.resolve(file.getName()).toFile();
        if (!newFile.exists() || overwrite || generatedFiles.contains(newFile.getAbsolutePath())) {
            newFile.createNewFile();
            try (FileWriter writer = new FileWriter(newFile); FileInputStream reader = new FileInputStream(file)) {
                writer.write("package " + packageName + ";\n");
                writer.write(new String(reader.readAllBytes()));
                System.out.println(file.getAbsolutePath() + " -> " + newFile.getAbsolutePath());
                copiedFiles.add(newFile.getAbsolutePath());
            }
        }
    }

    private Path resolveDir(Path path, String[] packages) {
        Path dir = path;
        for (String subPackageName : packages) {
            dir = dir.resolve(subPackageName);
        }
        return dir;
    }

    private boolean isJavaFile(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".")).equals(".java");
    }

    private void assertDir(File dir) {
        if (!dir.isDirectory()) {
            throw new InvalidParameterException(dir.getAbsolutePath() + " is not a directory.");
        }
    }
}
