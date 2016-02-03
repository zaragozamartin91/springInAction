package spittr.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*El nombre de la app a crear es Spittr*/
/** Representa la configuracion del servlet despachador */
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
	 * You can override the customize- Registration() method to configure
	 * multipart (see WebConfig#multipartResolver) details.
	 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		super.customizeRegistration(registration);

		/*
		 * suppose you want to limit files to no more than 2 MB, to limit the
		 * entire request to no more than 4 MB, and to write all files to disk.
		 * The following use of MultipartConfigElement sets those thresholds:
		 */
		final int MB = 1024 * 1024;
		final String location = "/tmp/spittr/uploads";
		final long maxFileSize = 2 * MB;
		final long maxRequestSize = 4 * MB;
		final int fileSizeThreshold = 0;
		registration.setMultipartConfig(new MultipartConfigElement(location, maxFileSize,
				maxRequestSize, fileSizeThreshold));
	}
}
