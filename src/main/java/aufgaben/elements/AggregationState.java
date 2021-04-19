package aufgaben.elements;

public enum AggregationState {
    SOLID("solid"), LIQUID("liquid"), GASEOUS("gaseous");
    private final String textValue;

    AggregationState(String textValue) {
        this.textValue = textValue;
    }

    public String textValue() {
        return textValue;
    }
}
