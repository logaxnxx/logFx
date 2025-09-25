import java.util.Arrays;

public class Playlist {

	private Song[] songs;
	private int numSongs;
	private static final int MIN_CAPACITY = 3;
	
	//constructors 
	public Playlist()
	{
		songs = new Song[MIN_CAPACITY];
		numSongs = 0;
	}
	public Playlist(int capacity)
	{
		if (capacity < MIN_CAPACITY) {
			songs = new Song[MIN_CAPACITY];
			numSongs = 0;
		}
		else {
			songs = new Song[capacity];
			numSongs = 0;
		}
	}
	
	//getters
	public int getCapacity() 
	{
		return songs.length;
	}
	public int getNumSongs()
	{
		return numSongs;
	}
	public Song getSong(int index)
	{
		if(index > -1 && index < numSongs)
		{
			return songs[index];
		}
		else {
			return null;
		}
	}
	public Song[] getSongs() 
	{
		return Arrays.copyOf(songs, numSongs); //can't be leaked has to be a copy with a different reference 
	}
	
	
	//adding new elements
	public boolean addSong(Song addedSong)
	{
		if (numSongs < songs.length && addedSong != null)
		{
			songs[numSongs] = addedSong;
			numSongs++;
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean addSong(int index, Song song)
	{
		if(index < 0 || song == null || index > numSongs || numSongs == songs.length)
		{
			return false;
		}
		else
		{
			for(int i = numSongs; i > index; i--)
			{
				songs[i] = songs[i-1];
			}
			
			songs[index] = song;
			numSongs++;
			return true;
			
		}
	}
	public int addSongs(Playlist playlist)
	{
		if(playlist == null) return 0;
		
		int count = 0;
		int size = playlist.getNumSongs();
		
		for (int i = 0; i < size && numSongs < songs.length; i++) {
			songs[numSongs] = playlist.getSong(i);
			numSongs++;
			count++;
		}
		
		return count;
	}
	
	//removing elements
	public Song removeSong()
	{
		if (songs == null || numSongs == 0) 
		{
			return null;
		}
		else
		{
			Song temp = songs[numSongs -1];
			songs[numSongs - 1] = null;
			numSongs--;
			return temp;
		}
	}
	public Song removeSong(int index)
	{
		if(index < 0 || index >= numSongs || index >= songs.length)
		{
			return null;
		}
		else
		{
			Song temp = songs[index];
			for(int i = index +1; i < numSongs; i++)
			{
				songs[i-1] = songs[i];
			}
			
			numSongs--; //adjust size
			songs[numSongs] = null; //removes the last element of array
			return temp;
		}
	}
}
