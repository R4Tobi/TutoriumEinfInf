package Tutorium.Math;

public class DualNumber {
    private double real;
    private double dual;

    public DualNumber(double real, double dual) {
        this.real = real;
        this.dual = dual;
    }

    public double getReal() {
        return real;
    }

    public double getDual() {
        return dual;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setDual(double dual) {
        this.dual = dual;
    }

    public void add(DualNumber other) {
        this.real += other.getReal();
        this.dual += other.getDual();
    }

    public void subtract(DualNumber other) {
        this.real -= other.getReal();
        this.dual -= other.getDual();
    }

    public void multiply(DualNumber other) {
        double newReal = this.real * other.getReal(); //multiplying the real numbers
        double newDual = this.real * other.getDual() + this.dual * other.getReal(); //summing up the angle
        this.real = newReal;
        this.dual = newDual;
    }

    public void divide(DualNumber other) {
        double denominator = Math.pow(other.getReal(), 2) + Math.pow(other.getDual(), 2);
        double newReal = (this.real * other.getReal() + this.dual * other.getDual()) / denominator;
        double newDual = (this.dual * other.getReal() - this.real * other.getDual()) / denominator;
        this.real = newReal;
        this.dual = newDual;
    }

    public void power(int exponent) {
        double newReal = Math.pow(this.real, exponent);
        double newDual = exponent * Math.pow(this.real, exponent - 1) * this.dual;
        this.real = newReal;
        this.dual = newDual;
    }

    @Override
    public String toString() {
        return String.format("%.2f + %.2fε", real, dual); //.2f areplaceholder for the vars following.
    }

    public static void main(String[] args) {
        // Beispiel der Verwendung
        DualNumber x = new DualNumber(3, 5);  // 2 + 3ε
        DualNumber y = new DualNumber(3, 5);  // 1 + 2ε

        // Rechenoperationen
        x.add(y);
        System.out.println("Addition: " + x);  // Ausgabe: 3.0 + 5.0ε

        x.subtract(y);
        System.out.println("Subtraktion: " + x);  // Ausgabe: 1.0 + 1.0ε

        x.multiply(y);
        System.out.println("Multiplikation: " + x);  // Ausgabe: 2.0 + 7.0ε

        x.divide(y);
        System.out.println("Division: " + x);  // Ausgabe: 2.4 - 0.6ε

        x.power(2);
        System.out.println("Potenzieren: " + x);  // Ausgabe: 5.76 + 6.72ε
    }
}
