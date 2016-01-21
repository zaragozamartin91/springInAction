package spring.in.action.spel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Jukebox {
	@Autowired
	private List<Song> songs;

	public List<Song> getSongs() {
		return songs;
	}

	public void listSongs() {
		System.out.println("Jukebox songs:" + songs.toString());
	}
}
