package aufgaben.elements;

public class ElementSentenceBuilder {
    public String build(Temperature temperature, Element element) {
        AggregationState state = element.getAggregationState(temperature);
        return String.format("%s at %s\u00b0C is %s.", element.getName(), temperature.getCelsius(), state.textValue());
    }
}
