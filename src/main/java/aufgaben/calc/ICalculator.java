package aufgaben.calc;

public interface ICalculator {
    /**
     * Returns the sum of its arguments,
     * throwing an exception if the result overflows an {@code double}.
     *
     * @param a the first value
     * @param b the second value
     * @return the result
     * @throws ArithmeticException if the result overflows an int
     */
    double add(double a, double b) throws ArithmeticException;
}
