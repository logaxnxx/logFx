
public abstract class Shape implements Comparable<Shape> {
	private int id;
	private static int nextId = 0;

	public Shape() {
		this.id = nextId++;
	}
	public abstract double getPerimeter();
	public abstract double getArea();

	@Override
	public int compareTo(Shape other) {
		int typeComparison = getClass().getName().compareTo(other.getClass().getName());
		if(typeComparison != 0) {
			return typeComparison;
		}

		int perimeterComparison = Double.compare(getPerimeter(), other.getPerimeter());
		if(perimeterComparison != 0) {
			return perimeterComparison;
		}

		return Double.compare(getArea(), other.getArea());
	}
	@Override
	public String toString() {
		return "<"
				+ getClass().getName()
				+ ", ID: " + id
				+ ", PERIMETER: " + String.format("%.1f", getPerimeter())
				+ ", AREA: " + String.format("%.1f", getArea())
				+ ">";
	}
}