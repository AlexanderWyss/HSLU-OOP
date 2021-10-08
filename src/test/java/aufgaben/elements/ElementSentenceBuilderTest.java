package aufgaben.elements;

import aufgaben.temperature.Temperature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementSentenceBuilderTest {

    @Test
    void leadWithTemp20_build_getCorrectSentenceSolid() {
        String text = getBuilder().build(Temperature.celsius(20), new Lead());

        assertEquals("Lead at 20.0\u00b0C is solid.", text);
    }

    @Test
    void mercuryWithTemp35_build_getCorrectSentenceLiquid() {
        String text = getBuilder().build(Temperature.celsius(35.2), new Mercury());

        assertEquals("Mercury at 35.2\u00b0C is liquid.", text);
    }
    @Test
    void nitrogenWithTemp12_build_getCorrectSentenceGaseous() {
        String text = getBuilder().build(Temperature.celsius(12), new Nitrogen());

        assertEquals("Nitrogen at 12.0\u00b0C is gaseous.", text);
    }

    private ElementSentenceBuilder getBuilder() {
        return new ElementSentenceBuilder();
    }
}