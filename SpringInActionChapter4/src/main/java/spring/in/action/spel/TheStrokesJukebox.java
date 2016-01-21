package spring.in.action.spel;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TheStrokesJukebox {
	/* se obtiene una cancion aleatoria */
	@Value("#{jukebox.getSongs()[T(java.lang.Math).random() * jukebox.songs.size()]}")
	private Song randomSong;

	@Value("#{jukebox.getSongs().?[artist eq 'the strokes']}")
	private List<Song> theStrokesSongs;
	
	@Value("#{jukebox}")
	private Jukebox jukebox;

	public Song getRandomSong() {
		return randomSong;
	}

	public List<Song> getTheStrokesSongs() {
		return theStrokesSongs;
	}
}
