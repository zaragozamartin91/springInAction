package spring.in.action;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CDPlayerConfig.class })
@ActiveProfiles("white")
public class CDPlayerTest {
	@Rule
	public final SystemOutRule log = new SystemOutRule().enableLog();

	@Autowired
	private MediaPlayer player;
	
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void play() {
		player.play();
		assertEquals("Playing White album" + " by The Beatles\n", log.getLog());
		
		
		applicationContext.getBean("asd");
	}
}
