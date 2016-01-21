package spring.in.action;

public class WhiteAlbum implements CompactDisc {
	private String title = "White album";
	private String artist = "The Beatles";

	public void play() {
		System.out.print("Playing " + title + " by " + artist + "\n");
	}
}
