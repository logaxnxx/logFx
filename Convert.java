
import java.util.Scanner;
import java.io.*;


public class Convert 
{

	public static void convertFile(String fileName) throws IOException 
	{
		String filePath = "triplog.csv";
		BufferedWriter fileOut = new BufferedWriter(new FileWriter(filePath));
		Scanner scanner = new Scanner(new File(fileName));
		
		fileOut.write("Time,Latitude,Longitude");
		fileOut.newLine();
		
		String textLine = "";
		int time = 0;

		//removes the first 3 lines in the gpx file then passes textLine the line of text containing lat and lon
		scanner.nextLine();
		scanner.nextLine();
		scanner.nextLine();
		textLine = scanner.nextLine().trim();


		while(scanner.hasNextLine())
		{	
			//find the starting index of lat and lon
			int startLat = textLine.indexOf("lat=\"");
			int startLon = textLine.indexOf("lon=\"");
			
			//checks if the line of text has both lat and lon
			if(startLat == -1 || startLon == -1) {
				textLine = scanner.nextLine();
				continue;
			}
			
			//find the end index of lat and lon
			int endLat = textLine.indexOf("\"", startLat +5);
			int endLon = textLine.indexOf("\"", startLon +5);
			
			//their may be malformed lines if so..
			/*
			 * if(endLat == -1 || endLon == -1) {
			 * 	textLine = scanner.nextLine();
			 * 	continue;
			 * }
			 */

			String lat = textLine.substring(startLat + 5, endLat).trim();
			String lon = textLine.substring(startLon + 5, endLon).trim();
			
			lat = Convert.makePretty(lat);
			lon = Convert.makePretty(lon);
			
			fileOut.write(time + "," + lat + "," + lon);
			fileOut.newLine();
			
			time += 5;
			textLine = scanner.nextLine();
			textLine = textLine.trim(); //loop primer 
		}
		
		scanner.close();
		fileOut.close();
	}	
	
	public static String makePretty(String line) {
		String prettyString = "";
		char keepIt = 'X';
		
		for(int i = 0; i < line.length(); i++) {
			keepIt = line.charAt(i);
			
			if((keepIt >= '0' && keepIt <= '9') || keepIt == '.' || keepIt == '-')
			{
				prettyString = prettyString.concat(Character.toString(keepIt));
			}
			
		}
		return prettyString;
	} 
}
