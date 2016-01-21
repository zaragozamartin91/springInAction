package spring.in.action;

import java.util.Map;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.in.action.aspect.AspectConfig;
import spring.in.action.aspect.BlankDisc;
import spring.in.action.aspect.Encoreable;
import spring.in.action.aspect.EncoreableIntroducer;
import spring.in.action.aspect.Performance;
import spring.in.action.aspect.TrackCounter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AspectConfig.class)
public class AspectTest {
	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	@Qualifier("basicPerformance")
	Performance basicPerformance;

	@Value("#{sgtPeppers}")
	BlankDisc sgtPeppers;

	@Autowired
	TrackCounter trackCounter;

	@Value("#{ { 'With a Little Help from My Friends':2 , 'Lucy in the Sky with Diamonds':3} }")
	Map<String, Integer> expectedCount;

	@Autowired
	EncoreableIntroducer encoreIntroducer;

	@Test
	public void testBasicPerformance() {
		basicPerformance.perform();
		System.out.println(basicPerformance.getNames());
	}

	@Test
	public void checkTrackCount() {
		String trackName = "With a Little Help from My Friends";
		sgtPeppers.playTrack(trackName);
		sgtPeppers.playTrack(trackName);

		trackName = "Lucy in the Sky with Diamonds";
		sgtPeppers.playTrack(trackName);
		sgtPeppers.playTrack(trackName);
		sgtPeppers.playTrack(trackName);

		assertEquals(expectedCount, trackCounter.getTrackCount());
	}

	@Test
	public void checkIntroducedInterface() {

	}
}
