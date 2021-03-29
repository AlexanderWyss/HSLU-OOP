package aufgaben.calc;

public class Calculator implements ICalculator {
    @Override
    public double add(final double a, final double b) throws ArithmeticException {
        final double result = a + b;
        if(result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY) {
            throw new ArithmeticException();
        }
        return result;
    }
}
