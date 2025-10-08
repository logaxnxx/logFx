import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


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
		int added = 0;
		for(int i = 0; i < playlist.getNumSongs(); i++) {
			songs.add(playlist.getSong(i));
			added++;
		}
		return added;
	}
	
	//removes songs
	public Song removeSong() {
		return removeSong(getNumSongs() - 1);
	}
	public Song removeSong(int index) {
		//tests to make sure index is within bounds of the ArrayList
		if(index < 0 || index >= songs.size()) return null; 
		else if(songs.get(index) == null) return null;
		return songs.remove(index);
	}
}
