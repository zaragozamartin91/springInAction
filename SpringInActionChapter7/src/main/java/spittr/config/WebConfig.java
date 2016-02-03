package spittr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

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

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
