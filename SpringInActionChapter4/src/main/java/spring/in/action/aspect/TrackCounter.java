package spring.in.action.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TrackCounter {
	private Map<String, Integer> trackCount = new HashMap<>();

	@Pointcut("execution(* spring.in.action.aspect.BlankDisc.playTrack(String)) && args(trackName)")
	public void trackPlayed(String trackName) {
	}

	@Before("trackPlayed(trackName)")
	public void countTrack(String trackName) {
		int newCount = trackCount.containsKey(trackName) ? trackCount.get(trackName) + 1 : 1;
		trackCount.put(trackName, newCount);
	}

	public Integer getPlayCount(String trackName) {
		return trackCount.containsKey(trackName) ? trackCount.get(trackName) : 0;
	}

	public Map<String, Integer> getTrackCount() {
		return trackCount;
	}

}
