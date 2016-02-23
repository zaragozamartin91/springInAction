package spittr.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*El nombre de la app a crear es Spittr*/
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	/*
	 * <multipartConfig> you can override the customize- Registration() method
	 * (which is given a Dynamic as a parameter) to configure multipart details.
	 * suppose you want to limit files to no more than 2 MB, to limit the entire
	 * request to no more than 4 MB, and to write all files to disk...
	 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement("/tmp/spittr/uploads", 2097152,
				4194304, 0));
	}
	/* </multipartConfig> */
}
