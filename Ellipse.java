public class Ellipse extends Shape {
    private double a;
    private double b;

    public Ellipse(double a, double b) {
        if(a <= 0 || b <= 0) {
            throw new IllegalArgumentException("Nonpositive value(s) provided for the constructor");
        }
        if (a < b) {
            throw new IllegalArgumentException("Semi-major axis length cannot be less than semi-minor axis length");
        }
        this.a = a;
        this.b = b;
    }

    public double getArea() {
        return Math.PI * a * b;
    }
    public double getPerimeter() {
        return Math.PI * (3 * (a + b) - Math.sqrt((3 * a + b) * (a + 3 * b)));
    }
    
    // Getters for a and b, if needed
    public double getA() {
        return a;
    }
    public double getB() {
        return b;
    }

}
