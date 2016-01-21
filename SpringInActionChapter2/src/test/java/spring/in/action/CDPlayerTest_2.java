package spring.in.action;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CDPlayerConfig_2.class })
public class CDPlayerTest_2 {
	@Rule
	public final SystemOutRule log = new SystemOutRule().enableLog();

	@Autowired
	@Qualifier("cdPlayer")
	private CDPlayer cdPlayer;

	@Autowired
	@Qualifier("anotherCdPlayer")
	private CDPlayer anotherCdPlayer;

	@Test
	public void playersCdsShouldBeTheSame() {
		assertEquals(cdPlayer.getCd(), anotherCdPlayer.getCd());
	}
}
