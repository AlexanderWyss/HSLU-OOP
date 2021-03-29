package aufgaben;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DemoTest {
    @Test
    void a_maxMmin() {
        assertEquals(7, getDemo().max(3, 7));
        assertEquals(9, getDemo().max(9, -15));
        assertEquals(9, getDemo().max(9, 9));

        assertEquals(3, getDemo().min(3, 7));
        assertEquals(-15, getDemo().min(9, -15));
        assertEquals(9, getDemo().min(9, 9));
    }

    @Test
    void b_max_3params() {
        assertEquals(7, getDemo().max(7, 3, 6));
        assertEquals(6, getDemo().max(5, 3, 6));
        assertEquals(3, getDemo().max(2, 3, -15));

        assertEquals(7, getDemo().maxAlt(7, 3, 6));
        assertEquals(6, getDemo().maxAlt(5, 3, 6));
        assertEquals(3, getDemo().maxAlt(2, 3, -15));
    }

    @Test
    void c_max_MoreParams() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> getDemo().max());
        assertEquals(9, getDemo().max(9));
        assertEquals(9, getDemo().max(7, 3, 6, 9));
        assertEquals(6, getDemo().max(5, 3, 6, 4));
        assertEquals(5, getDemo().max(2, 5, -15, 3));
    }

    //loops
    @Test
    void a_print1To10For() throws IOException {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(stream)) {
            getDemo().print1To10For(printStream);
            assertEquals(get1To10String(), stream.toString());
        }
    }

    @Test
    void a_print1To10Demo() {
        getDemo().print1To10For(System.out);
    }

    @Test
    void b_print1To10While() throws IOException {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(stream)) {
            getDemo().print1To10While(printStream);
            assertEquals(get1To10String(), stream.toString());
        }
    }

    @Test
    void b_print1To10DoWhile() throws IOException {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(stream)) {
            getDemo().print1To10DoWhile(printStream);
            assertEquals(get1To10String(), stream.toString());
        }
    }

    private String get1To10String() {
        return "0\n1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n".replaceAll("\n", System.lineSeparator());
    }

    @Test
    void c_whileFloatSmaller1() {
        int iterations = getDemo().whileFloatSmaller1();

        assertEquals(4000, iterations, 5);
    }

    @Test
    void e_forFloatSmaller1() {
        float finalValue = getDemo().forFloatSmaller1();

        assertEquals(1f, finalValue, 0.0002);
    }

    @Test
    void f_printBox() throws IOException {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream(); PrintStream printStream = new PrintStream(stream)) {
            getDemo().printBox(4, 10, printStream);
            assertEquals("##########\r\n#        #\r\n#        #\r\n##########\r\n", stream.toString());
        }
    }

    @Test
    void f_printBoxDemo() {
        getDemo().printBox(5, 7, System.out);
    }

    private Demo getDemo() {
        return new Demo();
    }
}