package test;

public class Calculator {
    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) throws DivisionException {
        if (b == 0) {
            throw new DivisionException("Cannot divide by zero");
        }
        return a / b;
    }
}
