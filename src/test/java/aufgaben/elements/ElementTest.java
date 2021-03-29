package aufgaben.elements;

import org.junit.jupiter.api.Test;

import static aufgaben.elements.Temperature.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ElementTest {
    @Test
    void d_aggregationState_N() {
        assertEquals("solid", new Nitrogen().getAggregationState(kelvin(0)));
        assertEquals("solid", new Nitrogen().getAggregationState(celsius(-210.2f)));
        assertEquals("liquid", new Nitrogen().getAggregationState(celsius(-210.1f)));
        assertEquals("liquid", new Nitrogen().getAggregationState(celsius(-196.1f)));
        assertEquals("gaseous", new Nitrogen().getAggregationState(celsius(-196)));
        assertEquals("gaseous", new Nitrogen().getAggregationState(celsius(15)));
    }

    @Test
    void d_aggregationState_Hg() {
        assertEquals("solid", new Mercury().getAggregationState(kelvin(0)));
        assertEquals("solid", new Mercury().getAggregationState(celsius(-38.84f)));
        assertEquals("liquid", new Mercury().getAggregationState(celsius(-38.83f)));
        assertEquals("liquid", new Mercury().getAggregationState(celsius(356.9f)));
        assertEquals("gaseous", new Mercury().getAggregationState(celsius(357)));
        assertEquals("gaseous", new Mercury().getAggregationState(celsius(500)));
    }

    @Test
    void d_aggregationState_Pb() {
        assertEquals("solid", new Lead().getAggregationState(kelvin(0)));
        assertEquals("solid", new Lead().getAggregationState(celsius(327.42f)));
        assertEquals("liquid", new Lead().getAggregationState(celsius(327.43f)));
        assertEquals("liquid", new Lead().getAggregationState(celsius(1743.9f)));
        assertEquals("gaseous", new Lead().getAggregationState(celsius(1744)));
        assertEquals("gaseous", new Lead().getAggregationState(celsius(2000)));
    }
}