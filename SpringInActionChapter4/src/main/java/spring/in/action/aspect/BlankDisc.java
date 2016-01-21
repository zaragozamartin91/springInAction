package spring.in.action.aspect;

import java.util.List;

public class BlankDisc {
	private String title;
	private String artist;
	private List<String> tracks;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setTracks(List<String> tracks) {
		this.tracks = tracks;
	}

	public BlankDisc(String title, String artist, List<String> tracks) {
		super();
		this.title = title;
		this.artist = artist;
		this.tracks = tracks;
	}

	public void play() {
		System.out.println("Playing " + title + " by " + artist);

		for (String track : tracks) {
			playTrack(track);
		}
	}

	public void playTrack(String track) {
		System.out.println("-Track: " + track);
	}
}
