public abstract class Polygon extends Shape {
    private final double PERIMETER;

    protected Polygon(double...sides) {
        if (sides == null) {
            throw new IllegalArgumentException("null sides");
        }
        if (sides.length < 3) {
            throw new IllegalArgumentException("Invalid number of sides: " + sides.length);
        }
        double total = 0.0;
        for (double side : sides) {
            if (side <= 0) {
                throw new IllegalArgumentException("Nonpositive side length: " + side);
            }
            total += side;
        }

    for(double side : sides) {
       double sum = total - side;
         if(side >= sum) {
            throw new IllegalArgumentException("Polygon inequality violated: " + side + " >= " + sum);
        }
    }
        this.PERIMETER = total;
    }
    public double getPerimeter() {
        return PERIMETER;
    }
}
