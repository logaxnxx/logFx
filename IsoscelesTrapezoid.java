public class IsoscelesTrapezoid extends Polygon {
    private final double top;
    private final double base;
    private final double leg;
    private final double area;
    private final double triangleBase;
    private final double height;

    public IsoscelesTrapezoid(double top, double base, double leg) {
        super(top, base, leg, leg);
        if (top <= 0 || base <= 0 || leg <= 0) {
            throw new IllegalArgumentException("Nonpositive value(s) provided for the constructor");
        }
       
        this.top = top;
        this.base = base;
        this.leg = leg;

        triangleBase = (base - top) / 2;
        height = Math.sqrt((leg * leg) - (triangleBase * triangleBase));
        area = ((top + base) / 2) * height;
    }

    @Override
    public double getArea() {
        return area; 
    }

    //getters 
    public double getTop() {
        return top;
    }
    public double getBase() {
        return base;
    }
    public double getLeg() {
        return leg;
    }
    public double getHeight() {
        return height;
    }

    public Rectangle getCenterRectangle() {
        return new Rectangle(top, height);
    }

}
