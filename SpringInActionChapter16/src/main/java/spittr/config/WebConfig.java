package spittr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan("spittr.web")
public class WebConfig extends WebMvcConfigurerAdapter {
	/* <restContentConfig> */
	/*
	 * When it comes to resolving view names into views that can produce
	 * resource representations, there’s an additional dimension to consider.
	 * Not only does the view need to match the view name, but the view also
	 * needs to be chosen to suit the client. If the client wants JSON data,
	 * then an HTML-rendering view won’t do—even if the view name matches.
	 * Spring’s ContentNegotiatingViewResolver is a special view resolver that
	 * takes the content type that the client wants into consideration.
	 */
	/*
	 * A lot is going on in that simple bean declaration. Understanding how
	 * Content- NegotiatingViewResolver works involves getting to know the
	 * content-negotiation two-step: 1 Determine the requested media type(s). 2
	 * Find the best view for the requested media type(s).
	 */
	/*
	 * ContentNegotiatingViewResolver assumes that the client wants HTML, as
	 * configured in its ContentNegotiationManager. But if the client specifies
	 * that it wants JSON (either with a .json extension on the request path or
	 * via the Accept header), then ContentNegotiatingViewResolver attempts to
	 * find a view resolver that can serve a JSON view.
	 */
	public ViewResolver cnViewResolver(ContentNegotiationManager cnm) {
		ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
		contentNegotiatingViewResolver.setContentNegotiationManager(cnm);
		return contentNegotiatingViewResolver;
	}

	/*
	 * The media-type selection process, as described so far, outlines the
	 * default strategy for determining the requested media types. But you can
	 * change how it behaves by giving it a ContentNegotiationManager
	 */
	/*
	 * There are three ways to configure a ContentNegotiationManager: > Directly
	 * declare a bean whose type is ContentNegotiationManager. > Create the bean
	 * indirectly via ContentNegotiationManagerFactoryBean. > Override the
	 * configureContentNegotiation() method of WebMvcConfigurer- Adapter.
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		/*
		 * La siguiente configuracion establece que al intentar resolver una
		 * vista a partir de un identificador, el tipo de contenido por defecto
		 * es HTML (delegando la busqueda de vistas a Thymeleaf)
		 */
		configurer.defaultContentType(MediaType.TEXT_HTML);
	}

	/* Look up views as beans */
	@Bean
	public ViewResolver beanNameViewResolver() {
		return new BeanNameViewResolver();
	}

	/* “spittles” JSON view */
	/*
	 * el siguiente bean establece que la vista "spittles" debe ser interpretada
	 * como contenido Json y no Html.
	 */
	@Bean
	public View spittles() {
		return new MappingJackson2JsonView();
	}

	/* </restContentConfig> */

	/* <thymeleafConfig> */
	/*
	 * In order to use Thymeleaf with Spring, you’ll need to configure three
	 * beans that enable Thymeleaf-Spring integration: > A ThymeleafViewResolver
	 * that resolves Thymeleaf template views from logical view names > A
	 * SpringTemplateEngine to process the templates and render the results > A
	 * TemplateResolver that loads Thymeleaf templates
	 */
	@Bean
	public ViewResolver viewResolver(@Value("#{templateEngine}") SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		return viewResolver;
	}

	@Bean
	public TemplateEngine templateEngine(
			@Value("#{templateResolver}") TemplateResolver templateResolver) {
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

	/* </thymeleafConfig> */

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
