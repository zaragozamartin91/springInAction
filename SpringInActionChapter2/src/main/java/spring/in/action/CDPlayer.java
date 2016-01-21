package spring.in.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CDPlayer implements MediaPlayer {
	private CompactDisc cd;

	public void play() {
		cd.play();
	}

	@Autowired
	public CDPlayer(CompactDisc cd) {
		super();
		this.cd = cd;
	}

	public CompactDisc getCd() {
		return cd;
	}

}
