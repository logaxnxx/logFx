public class Rectangle extends IsoscelesTrapezoid {

    public Rectangle(double width, double height) {
        super(width, width, height);
    }

    // Returns the width of the rectangle (top or base)
    public double getWidth() {
        return getBase();
    }

    // Returns the height of the rectangle (leg)
    public double getHeight() {
        return getLeg();
    }
}
