package spring.in.action.spel;

public class Single implements Song {
	private String artist;
	private String track;
	private Double duration;

	public String getArtist() {
		return artist;
	}

	public String getTrack() {
		return track;
	}

	public Double getDuration() {
		return duration;
	}

	public Single(String artist, String track, Double duration) {
		super();
		this.artist = artist;
		this.track = track;
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Single [artist=" + artist + ", track=" + track + ", duration=" + duration + "]";
	}
}
