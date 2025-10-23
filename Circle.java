public class Circle extends Ellipse{
    public Circle(double radius) {
        super(radius, radius);
    }
    public double getRadius() {
        return super.getA(); // or super.getB(), since both are equal for a circle
    }
}
