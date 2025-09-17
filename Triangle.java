import java.lang.Math; //import for doing trig functions 
import java.text.DecimalFormat;

public class Triangle {
	
	
	public static final String POLYGONSHAPE = "Triangle";
	public static final double DEFAULT_SIDE = 1.0;
	
	private  double sideA;
	private  double sideB;
	private  double sideC;
	
	//default constructor sets all the sides to 1
	public Triangle() { 
		sideA = 1;
		sideB = 1;
		sideC = 1;
	}

	//triangle constructor
	//checks that inputs pass the rule for a triangle and 
	//non of the inputs are zeros or negative
	public Triangle(double sideA, double sideB, double sideC) {
		if(Triangle.isTriangle(sideA, sideB, sideC) == true) { 
			this.sideA = sideA;
			this.sideB = sideB;
			this.sideC = sideC;
		}
		else {
			this.sideA = DEFAULT_SIDE;
			this.sideB = DEFAULT_SIDE;
			this.sideC = DEFAULT_SIDE;
		}
	}
	
	//triangle constructor that constructs from an array list
	public Triangle(double[] sides) {
		//check if triangle is valid and the array is also a valid input
		if (Triangle.isTriangle(sides) == true && sides != null && sides.length == 3) {
			sideA = sides[0];
			sideB = sides[1];
			sideC = sides[2];
		}
		else {
			sideA = DEFAULT_SIDE;
			sideB = DEFAULT_SIDE;
			sideC = DEFAULT_SIDE;
		}
	}
	
	//copy constructor 
	public Triangle(Triangle triangle) { 
		if (triangle != null) {
			this.sideA = triangle.sideA;
			this.sideB = triangle.sideB;
			this.sideC = triangle.sideC;
		}
		else{
			this.sideA = DEFAULT_SIDE;
			this.sideB = DEFAULT_SIDE;
			this.sideC = DEFAULT_SIDE;
		}
	
	}
	
	
	//test if triangle is valid from an input of sides and an array input
	public static boolean isTriangle(double a, double b, double c) {
	
		if(a + b > c && b + c > a && c + a > b) { //if any side is negative or 0 then this test would fail 
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean isTriangle(double[] sides) {
		if(sides == null || sides.length != 3) {
			return false;
		}
		
		return isTriangle(sides[0], sides[1], sides[2]);
	}
	
	/* Law of Cosines method
	 * the law -- (a^2 + b^2 - c^2) / (2 * a * b) = cos(C)  
	 * */
	public static double lawOfCosines(double a, double b, double sideOpAngle)
	{
		
		double cosAngle = (Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(sideOpAngle, 2))
							/ (2 * a * b);	
		
		/* the value must be clamped as a result from rounding errors because 
		 * the output of cos(theta) will always lie between -1 and 1 
		*/
		cosAngle = Math.max(-1.0, cosAngle);
		cosAngle = Math.min(1.0, cosAngle);
		
		//take the arccos of the angle and convert the output into degrees
		cosAngle = Math.acos(cosAngle);
		double degreesAngle = Math.toDegrees(cosAngle);
		
		//round the value to 4 decimal places
		degreesAngle = Math.round(degreesAngle * 10000.0);
		degreesAngle = degreesAngle/10000.0;
		
		return degreesAngle;
	}
	

	//getters for sides 
	public double getSideA() {
		return sideA;
	}
	public double getSideB() {
		return sideB;
	}
	public double getSideC() {
		return sideC;
	}
	public double[] getSides() {
		double[] sides = {sideA, sideB, sideC};
		return sides; 
	}
	
	//getters for angles
	public  double getAngleA() {
		return Triangle.lawOfCosines(sideC, sideB, sideA);
	}
	public  double getAngleB() {
		return Triangle.lawOfCosines(sideA, sideC, sideB);
	}
	public  double getAngleC() {
		return Triangle.lawOfCosines(sideA, sideB, sideC);
	}
	
	
	public double[] getAngles() 
	{
		double[] angles = new double[3];
		
		angles[0] = Triangle.lawOfCosines(sideB, sideC, sideA);
		angles[1] = Triangle.lawOfCosines(sideA, sideC, sideB);
		angles[2] = Triangle.lawOfCosines(sideA, sideB, sideC);
		
		return angles;
		
	}
		
	//setters for sides
	//checks if the side is valid
	public boolean setSideA(double sideA)
	{
		if(isTriangle(sideA, this.sideB, this.sideC) == true)
		{
			this.sideA = sideA;
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean setSideB(double sideB)
	{
		if(isTriangle(this.sideA, sideB, this.sideC) == true)
		{
			this.sideB = sideB;
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean setSideC(double sideC)
	{
		if(isTriangle(this.sideA, this.sideB, sideC) == true)
		{
			this.sideC = sideC;
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean setSides(double[] sides) {
		if (Triangle.isTriangle(sides) == true && sides.length == 3 && sides != null) {
			sideA = sides[0];
			sideB = sides[1];
			sideC = sides[2];
			return true;
		}
		else {
			return false;
		}

	}
	
	//prints a readable line to terminal of the triangle's sides
	public String toString() {
		DecimalFormat fourPlaces = new DecimalFormat("0.0000");
		return POLYGONSHAPE + "(" + fourPlaces.format(sideA) + ", " 
								+ fourPlaces.format(sideB) + ", " 
								+ fourPlaces.format(sideC) + ")";
	}

	
	
	
}

