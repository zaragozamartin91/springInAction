package spring.in.action;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CDPlayerConfig_2 {
	@Bean(name = "lonelyHeartsClubBand")
	public CompactDisc sgtPeppers() {
		return new SgtPeppers();
	}

	/*
	 * It appears that the CompactDisc is provided by calling sgtPeppers, but that’s not exactly true. Because the sgtPeppers() method is annotated
	 * with @Bean, Spring will intercept any calls to it and ensure that the bean produced by that method is returned rather than allowing it to be
	 * invoked again
	 */
	@Bean
	public CDPlayer cdPlayer() {
		return new CDPlayer(sgtPeppers());
	}

	/* ambos beans de CDPlayer apuntan a sgtPeppers */
	@Bean
	public CDPlayer anotherCdPlayer(CompactDisc compactDisc) {
		return new CDPlayer(compactDisc);
	}
}
