package spring.in.action;


public class BlankDisc implements CompactDisc {
	private String title;
	private String artist;

	public void play() {
		System.out.print("Playing " + title + " by " + artist + "\n");
	}

	public BlankDisc(String title, String artist) {
		super();
		this.title = title;
		this.artist = artist;
	}
}
