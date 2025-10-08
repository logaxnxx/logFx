import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Playlist {
	
	private ArrayList<Song> songs;
	
	//default constructor
	public Playlist() {
		songs = new ArrayList<>();
	}
	//constructor from filename 
	public Playlist(String filename) throws IOException {
		this();
		addSongs(filename);
	}
	
	//adds songs from a file
	public void addSongs(String filename) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while(reader.ready())
		{
		String line = reader.readLine();
		
		Song s = new Song(line);
		songs.add(s);
		}
		reader.close();
	}
	//getters
	public int getNumSongs() {
		return songs.size();
	}
	public Song getSong(int index) {
		if (index < 0 || index >= songs.size()) {
			return null;
		}
		return songs.get(index);
	}
	public Song[] getSongs() {
		return songs.toArray(new Song[0]);
	}
	public int[] getTotalTime() {
		
		int totalSeconds = 0;
		for(Song s: songs) {
			int[] t = s.getTime();
			
			if(t.length >= 1) totalSeconds += t[0];
			if(t.length >= 2) totalSeconds += t[1] * 60;
			if(t.length == 3) totalSeconds += t[2] * 60 * 60;
		}
		
		//convert total seconds into hh:mm:ss format 
		int hrs = totalSeconds / 3600;
		totalSeconds -= hrs * 3600; //removes the hours from the total seconds
		
		int mins = totalSeconds / 60; 
		totalSeconds -= mins * 60;
		
		int secs = totalSeconds;
		
		//returns the array
		if(hrs > 0) return new int[] {secs, mins, hrs};
		if(mins > 0) return new int[] {secs, mins};
		else return new int[] {secs};
	}
	
	//setters 
	public boolean addSong(Song song) {
		return addSong(getNumSongs(), song);
	}
	public boolean addSong(int index, Song song) {
		if(song == null || index < 0 || index > songs.size()) {
			return false;
		}
		songs.add(index, song);
		return true;
	}
	public int addSongs(Playlist playlist) {
		if(playlist == null) return 0; 
		//adds each element to the desired playlist
		Song[] copy = playlist.getSongs();
		
		for(Song s: copy) {
			songs.add(s);
		}
		return copy.length;
	}
	
	//removes songs
	public Song removeSong() {
		return removeSong(getNumSongs() - 1);
	}
	public Song removeSong(int index) {
		//tests to make sure index is within bounds of the ArrayList
		if(index < 0 || index >= songs.size()) return null; 
		//else if(songs.get(index) == null) return null;
		return songs.remove(index);
	}
	
	//save songs to a file with the to string method 
	public void saveSongs(String filename) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		writer.write(this.toString());
		writer.close();
	}
	//calls the to string from the song class for each song in the playlist
	public String toString() {
		String result = "";
		
		for(int i = 0; i < songs.size(); i++) {
			result = result.concat(songs.get(i).toString());
			if(i < songs.size()-1) {
				result = result.concat("\n"); //adds new line after each song except the last
			}
		}
		
		return result;
	}
}


