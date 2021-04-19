package aufgaben.elements;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

import static aufgaben.elements.Temperature.celsius;
import static aufgaben.elements.Temperature.kelvin;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ElementTest {

    @Test
    void elementWithSameAtomicNumber_compareTo_return0() {
        Element elementA = new TestElement(3);
        Element elementB = new TestElement(3);

        assertEquals(0, elementA.compareTo(elementB));
    }

    @Test
    void elementAWithGreaterAtomicNumber_compareTo_returnPlusOne() {
        Element elementA = new TestElement(26);
        Element elementB = new TestElement(3);

        assertEquals(1, elementA.compareTo(elementB));
    }

    @Test
    void elementBWithGreaterAtomicNumber_compareTo_returnMinusOne() {
        Element elementA = new TestElement(3);
        Element elementB = new TestElement(7);

        assertEquals(-1, elementA.compareTo(elementB));
    }

    @Test
    void verifyEquals() {
        EqualsVerifier.forClass(Element.class).suppress(Warning.ALL_FIELDS_SHOULD_BE_USED).verify();
    }

    @Test
    void d_aggregationState_N() {
        assertEquals(AggregationState.SOLID, new Nitrogen().getAggregationState(kelvin(0)));
        assertEquals(AggregationState.SOLID, new Nitrogen().getAggregationState(celsius(-210.2f)));
        assertEquals(AggregationState.LIQUID, new Nitrogen().getAggregationState(celsius(-210.1f)));
        assertEquals(AggregationState.LIQUID, new Nitrogen().getAggregationState(celsius(-196.1f)));
        assertEquals(AggregationState.GASEOUS, new Nitrogen().getAggregationState(celsius(-196)));
        assertEquals(AggregationState.GASEOUS, new Nitrogen().getAggregationState(celsius(15)));
    }

    @Test
    void d_aggregationState_Hg() {
        assertEquals(AggregationState.SOLID, new Mercury().getAggregationState(kelvin(0)));
        assertEquals(AggregationState.SOLID, new Mercury().getAggregationState(celsius(-38.84f)));
        assertEquals(AggregationState.LIQUID, new Mercury().getAggregationState(celsius(-38.83f)));
        assertEquals(AggregationState.LIQUID, new Mercury().getAggregationState(celsius(356.9f)));
        assertEquals(AggregationState.GASEOUS, new Mercury().getAggregationState(celsius(357)));
        assertEquals(AggregationState.GASEOUS, new Mercury().getAggregationState(celsius(500)));
    }

    @Test
    void d_aggregationState_Pb() {
        assertEquals(AggregationState.SOLID, new Lead().getAggregationState(kelvin(0)));
        assertEquals(AggregationState.SOLID, new Lead().getAggregationState(celsius(327.42f)));
        assertEquals(AggregationState.LIQUID, new Lead().getAggregationState(celsius(327.43f)));
        assertEquals(AggregationState.LIQUID, new Lead().getAggregationState(celsius(1743.9f)));
        assertEquals(AggregationState.GASEOUS, new Lead().getAggregationState(celsius(1744)));
        assertEquals(AggregationState.GASEOUS, new Lead().getAggregationState(celsius(2000)));
    }


    private static class TestElement extends Element {
        private TestElement(int atomicNumber) {
            super(atomicNumber, "", "", 0, 0);
        }
    }
}