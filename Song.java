import java.util.Arrays;

public class Song {
	
	private String title;
	private String artist;
	private int[] time; // might need to make this length of 3 but dont know yet 
	
	//constructor
	public Song(String title, String artist, int[] time) 
	{
		this.title = title;
		this.artist = artist;
		this.time = Arrays.copyOf(time, time.length);
		/*
		 * time array has 3 values...[seconds, minutes, hours]
		 * all time arrays have a length no greater than 3, but 
		 * can have a length of 1 (if song has no minutes or hours and etc.)
		 * seconds and minutes is limited to 0 - 59 (inclusive)
		 * hours no limit but has to be greater or equal to 0 
		 */
	}
	
	//getters 
	public String getTitle() 
	{
		return title;
	}
	public String getArtist()
	{
		return artist;
	}
	public int[] getTime()
	{
		return Arrays.copyOf(time, time.length);  //returns a copy of the song array with a different reference so that the class array cannot be changed
	}

}
