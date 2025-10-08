import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Song {
	
	private String title;
	private String artist;
	private int[] time;
	
	private static final String INFO_DELIMITER = ";";
	private static final String TIME_DELIMITER = ":";
	private static final int IDX_TITLE = 0;
	private static final int IDX_ARTIST = 1;
	private static final int IDX_TIME = 2;
	
	public Song(String title, String artist, int[] time) {
		this.title = title;
		this.artist = artist;
		this.time = Arrays.copyOf(time, time.length);
	}
	public Song(String info) {
		
		String[] contents = info.split(";");
		
		//splits the string info by the semi-colon
		this.title = contents[0].trim();
		this.artist = contents[1].trim();
		String timeStamp = contents[2].trim();
	
		//splits the time part of the info based on the colon
		String[] timeContents = timeStamp.split(":");
		
		this.time = new int[timeContents.length]; //assigns class time a length
		
		//starts the loop at the end of time contents because the seconds 
		//the last elements in this array
		int k = 0;
		for(int i = timeContents.length -1; i >= 0; i--) 
		{
			//makes the time array in format of [seconds, minutes, hours]
			time[k] = Integer.parseInt(timeContents[i]);
			k++;
		}
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public int[] getTime() {
		return Arrays.copyOf(time, time.length);
	}
	
	public String toString() {
		String result = "";
		
		result = result.concat(title).concat("; ").concat(artist).concat("; ");
		
		String ss = "";
		String mm = "";
		
		//makes sure the time follows the hh:mm:ss format and appends zeros as needed
		if(time.length == 1) {
			result = result.concat(Integer.toString(time[0]));
		}
		else if(time.length == 2) {
			result = result.concat(Integer.toString(time[1]));
			result = result.concat(":");
			
			ss = String.format("%02d", time[0]);
			result = result.concat(ss);
		}
		else {
			result = result.concat(Integer.toString(time[2]));
			result = result.concat(":");
			
			mm = String.format("%02d", time[1]);
			result = result.concat(mm).concat(":");
			
			ss = String.format("%02d", time[0]);
			result = result.concat(ss);
		}
		return result;
	}
}

