package spring.in.action.spel;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackageClasses = TimeChecker.class)
@PropertySource("classpath:app.properties")
public class SpelConfig {
	@Bean
	@Qualifier("sweetChildOfMine")
	public Song sweetChildOfMine(@Value("guns and roses") String artist, @Value("sweetChildOfMine") String track, @Value("3.25") Double duration) {
		return new Single(artist, track, duration);
	}
	
	@Bean
	@Qualifier("someday")
	public Song someday(@Value("the strokes") String artist, @Value("someday") String track, @Value("3.21") Double duration) {
		return new Single(artist, track, duration);
	}
	
	@Bean
	@Qualifier("reptilia")
	public Song reptilia(@Value("the strokes") String artist, @Value("reptilia") String track, @Value("4.33") Double duration) {
		return new Single(artist, track, duration);
	}
	
	@Bean
	@Qualifier("darkness")
	public Song darkness(@Value("the strokes") String artist, @Value("darkness") String track, @Value("5.16") Double duration) {
		return new Single(artist, track, duration);
	}
}
