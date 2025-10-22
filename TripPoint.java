import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TripPoint {

	//private data
	private double lat;
	private double lon;
	private int time;
	
	private static ArrayList<TripPoint> trip = new ArrayList<>();

	public TripPoint(int time, double lat, double lon) {
		this.time = time;
		this.lat = lat;
		this.lon = lon;
	}
	
	//getters
	public int getTime() {
		return time;
	}
	public double getLat() {
		return lat;
	}
	public double getLon() {
		return lon;
	}
	//returns a copy of the original array
	public static ArrayList<TripPoint> getTrip() {
		ArrayList<TripPoint> copy = new ArrayList<>(trip);
		return copy;
	}
	
	public static void readFile(String filename) throws FileNotFoundException {
		trip.clear(); //prevents dupes from being added
		
		File file = new File(filename);
		Scanner susan = new Scanner(file); //susan will read my file
		susan.nextLine(); //skips header
		
		//reads each line in the file
		while(susan.hasNextLine()) {
			String line = susan.nextLine();
			line = line.trim();
			
			if(!line.isEmpty()) {
				String[] data = line.split(",");
				
				int time = Integer.parseInt(data[0]);
				double lat = Double.parseDouble(data[1]);
				double lon = Double.parseDouble(data[2]);
				
				trip.add(new TripPoint(time, lat, lon));
			}
		}
		susan.close();
	}
	
	public static double totalTime() {
		int startTime = trip.get(0).getTime();
		int endTime = trip.get(trip.size() -1).getTime();
		
		int totalTime = endTime - startTime;
		double totalHours = totalTime / 60.0;
		
		return totalHours;
	}
	
	public static double haversineDistance(TripPoint a, TripPoint b) { //google helped me
		final double EARTH_RADIUS = 6371.0; //Earth's radius in km
		
		//convert lat and lon into radians 
		double dLat = Math.toRadians(b.getLat() - a.getLat());
		double dLon = Math.toRadians(b.getLon() - a.getLon());
		
		double latA = Math.toRadians(a.getLat());
		double latB = Math.toRadians(b.getLat());
		
		//haversine formula 
		double result = Math.pow(Math.sin(dLat / 2),  2) +
						Math.cos(latA) * Math.cos(latB) * Math.pow(Math.sin(dLon / 2), 2);
		
		double result2 = 2 * Math.asin(Math.sqrt(result));
		
		return EARTH_RADIUS * result2;
	}
	
	public static double totalDistance() { 
		double total = 0.0;
		
		for(int i = 1; i < trip.size(); i++) {
			TripPoint first = trip.get(i - 1);
			TripPoint second = trip.get(i);
			
			total = total + haversineDistance(first, second);
		}
		
		return total;
	}
	
	public static double avgSpeed(TripPoint a, TripPoint b) {
		double distance = haversineDistance(a,b);
		double time = Math.abs(b.getTime() - a.getTime());
		time = time/60.0;
		
		if (time == 0) return 0.0; //avoids dividing by zero
		
		return distance / time;
	}
	
	
}
