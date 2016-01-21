package spring.in.action.aspect;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class AspectConfig {
	@Bean
	public Audience audience() {
		return new Audience();
	}

	@Bean
	@Qualifier("sgtPeppers")
	public BlankDisc sgtPeppers(
			@Value("sgt peppers") String title,
			@Value("the beatles") String artist,
			@Value("#{ {'Sgt Peppers Lonely Hearts Club Band','With a Little Help from My Friends','Lucy in the Sky with Diamonds'} }") List<String> tracks) {
		return new BlankDisc(title, artist, tracks);
	}
}
