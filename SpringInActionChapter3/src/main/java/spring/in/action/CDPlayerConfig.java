package spring.in.action;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

@Configuration
@PropertySource("classpath:app.properties")
public class CDPlayerConfig {
	@Bean
	@Profile("pepper")
	public MediaPlayer peppersPlayer() {
		return new CDPlayer(new SgtPeppers());
	}

	@Bean
	@Profile("white")
	public MediaPlayer whitePlayer() {
		return new CDPlayer(new WhiteAlbum());
	}

	@Autowired
	private Environment env;

	@Bean
	public BlankDisc blankDisc() {
		return new BlankDisc(env.getProperty("disc.title"), env.getProperty("disc.artist"));
	}
	
	@Bean
	public static PropertyPlaceholderConfigurer placeConfigurer() throws IOException{
		PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
		Properties properties = new Properties();
		properties.load( new ClassPathResource("app.properties").getInputStream() );
		propertyPlaceholderConfigurer.setProperties(properties );
		return propertyPlaceholderConfigurer;
	}
}
