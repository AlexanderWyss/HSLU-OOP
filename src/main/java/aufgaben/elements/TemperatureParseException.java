package aufgaben.elements;

public class TemperatureParseException extends Exception {
    public TemperatureParseException(String message, Throwable causingException) {
        super(message, causingException);
    }

    public TemperatureParseException(String message) {
        super(message);
    }
}
