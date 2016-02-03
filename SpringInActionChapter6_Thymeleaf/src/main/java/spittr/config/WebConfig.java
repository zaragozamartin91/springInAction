package spittr.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan("spittr.web")
public class WebConfig extends WebMvcConfigurerAdapter {
	/*
	 * In order to use Thymeleaf with Spring, you’ll need to configure three
	 * beans that enable Thymeleaf-Spring integration: > A ThymeleafViewResolver
	 * that resolves Thymeleaf template views from logical view names > A
	 * SpringTemplateEngine to process the templates and render the results > A
	 * TemplateResolver that loads Thymeleaf templates
	 */
	@Bean
	public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		return viewResolver;
	}

	@Bean
	public TemplateEngine templateEngine(TemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine;
	}

	@Bean
	public TemplateResolver templateResolver() {
		TemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");

		/*
		 * Its templateMode property is also set to HTML5, indicating that the
		 * templates resolved are expected to render HTML5 output
		 */
		templateResolver.setTemplateMode("HTML5");
		return templateResolver;
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
