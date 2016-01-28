package spittr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/*TODO : CONFIGURACION INCOMPLETA DE TILES*/
@Configuration
@EnableWebMvc
@ComponentScan("spittr.web")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver(@Value("/WEB-INF/views/") String prefix,
			@Value(".jsp") String suffix) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix(prefix);
		viewResolver.setSuffix(suffix);

		/*
		 * JSTL’s formatting tags need a Locale to properly format
		 * locale-specific values such as dates and money. And its message tags
		 * can use a Spring message source and a Locale to properly choose
		 * messages to render in HTML. By resolving JstlView, the JSTL tags will
		 * be given the Locale and any message source configured in Spring
		 */
		viewResolver.setViewClass(JstlView.class);

		/*
		 * This will make all such beans accessible in plain ${...}} expressions
		 * in a JSP 2.0 page, as well as in JSTL's c:out value expressions.
		 */
		viewResolver.setExposeContextBeansAsAttributes(true);
		return viewResolver;
	}

	/*
	 * In order to use Tiles with Spring, you’ll have to configure a couple of
	 * beans. You need a TilesConfigurer bean whose job is to locate and load
	 * tile definitions and generally coordinate Tiles. In addition, you need a
	 * TilesViewResolver bean to resolve logical view names to tile definitions.
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();

		// This property takes an array of Strings where each entry specifies
		// the location of tile-definition XML files. For the Spittr
		// application,
		// you’ll have it look for a file named tiles.xml in the
		// /WEB-INF/layout/ directory.
		// Although you’re not taking advantage of it here, it’s also possible
		// to specify multiple tile-definition files and even use wildcards in
		// the location path.
		// For example, you could ask that TilesConfigurer look for any file
		// named tiles.xml
		// anywhere under the /WEB-INF/ directory by setting the definitions
		// property like this:
		// tiles.setDefinitions(new String[] {
		// "/WEB-INF/**/tiles.xml"
		// });
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/layout/tiles.xml" });
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}

	/*
	 * Whereas TilesConfigurer loads tile definitions and coordinates with
	 * Apache Tiles, TilesViewResolver resolves logical view names to views that
	 * reference tile definitions. It does this by looking for a tile definition
	 * whose name matches the logical view name.
	 */
	@Bean
	public TilesViewResolver tilesViewResolver() {
		return new TilesViewResolver();
	}

	/*
	 * It loads messages from a properties file whose name is derived from a
	 * base name.
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

		/*
		 * The key thing in this bean declaration is the setting of the basename
		 * property. You can set it to any value you’d like, but here I’ve
		 * chosen to set it to messages. By setting it to messages, you can
		 * expect ResourceBundleMessageResolver to resolve messages from
		 * properties files at the root of the classpath whose names are derived
		 * from that base name.
		 */
		messageSource.setBasename("messages");
		return messageSource;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
