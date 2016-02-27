package spittr.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spittr.Spitter;
import spittr.data.SpitterRepository;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
	private SpitterRepository spitterRepository;

	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {
		super();
		this.spitterRepository = spitterRepository;
	}

	/*
	 * In the preceding code (registerForm.jsp), you set commandName to spitter.
	 * Therefore, there must be an object in the model whose key is spitter, or
	 * else the form won’t be able to render (and you’ll get JSP errors). That
	 * means you need to make a small change to ensure that a Spitter object is
	 * in the model under the spitter key
	 */
	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public String spitter(Model model) {
		model.addAttribute(new Spitter());
		return "registerForm";
	}

	/*
	 * when a controller method results in a redirect, the original request ends
	 * and a new HTTP GET request begins. Any model data carried in the original
	 * request dies with the request. Spring can do something about it if
	 * returning the view in some other way... (eg.
	 * redirect:/spitter/{username})
	 */
	/*
	 * this view specification is different from what you’ve seen before. Rather
	 * than just return a view name and let the view resolver sort it out, here
	 * you’re returning a redirect specification. When
	 * InternalResourceViewResolver sees the redirect: prefix on the view
	 * specification, it knows to interpret it as a redirect specification
	 * instead of as a view name. In this case, it will redirect to the path for
	 * a user’s profile page. For example, if the Spitter .username property is
	 * jbauer, then the view will redirect to /spitter/jbauer When a controller
	 * method returns a String whose value starts with redirect:, that String
	 * isn’t used to look up a view, but is instead used as a path to redirect
	 * the browser to.
	 */
	/*
	 * If there are any validation errors, they’re available in the Errors
	 * object that you’re now asking for as a parameter to
	 * processRegistration(). (Note that it’s important that the Errors
	 * parameter immediately follow the @Valid-annotated parameter that’s being
	 * validated.)
	 */
	/*
	 * you just need to change the processRegistration() method to accept the
	 * uploaded image. One way to do that is to add a byte array parameter
	 * that’s annotated with @RequestPart. The picture param can also be a
	 * MultipartFile or a Part instance. It’s worth noting that if you write
	 * your controller handler methods to accept file uploads via a Part
	 * parameter, then you don’t need to configure the StandardServlet-
	 * MultipartResolver bean. StandardServletMultipartResolver is required only
	 * when you’re working with MultipartFile.
	 */
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String processRegistration(@RequestPart("profilePicture") MultipartFile profilePicture,
			@Valid Spitter spitter, Errors errors, RedirectAttributes model)
			throws IllegalStateException, IOException {
		if (errors.hasErrors()) {
			return "registerForm";
		}

		spitterRepository.save(spitter);
		model.addAttribute("username", spitter.getUsername());

		/*
		 * Flash attributes, by definition, carry data until the next request;
		 * then they go away.
		 */
		/*
		 * Spring offers a way to set flash attributes via RedirectAttributes, a
		 * sub-interface of Model. RedirectAttributes offers everything that
		 * Model offers, plus a few methods for setting flash attributes
		 */
		/*
		 * Before the redirect takes place, all flash attributes are copied into
		 * the session. After the redirect, the flash attributes stored in the
		 * session are moved out of the session and into the model.
		 */
		model.addFlashAttribute("spitter", spitter);

		{
			File profilePictureDest = new File("/data/spittr/"
					+ profilePicture.getOriginalFilename());
			System.out.println("Saving picture in " + profilePictureDest.getAbsolutePath());
			profilePicture.transferTo(profilePictureDest);
		}

		{
			File profilePictureDest = new File(profilePicture.getOriginalFilename());
			System.out.println("Saving picture in " + profilePictureDest.getAbsolutePath());
			profilePicture.transferTo(profilePictureDest);
		}

		/*
		 * Instead of concatenating your way to a redirect URL, Spring offers
		 * the option of using templates to define redirect URLs. For example,
		 * the last line could be written like this:
		 */
		return "redirect:/spitter/{username}";
		/* All you need to do is set the value in the model */
		/*
		 * Because the spitterId attribute from the model doesn’t map to any URL
		 * placeholders in the redirect, it’s tacked on to the redirect
		 * automatically as a query parameter. If the username attribute is
		 * habuma and the spitterId attribute is 42, then the resulting redirect
		 * path will be /spitter/habuma?spitterId=42
		 */
	}

	/*
	 * The @ExceptionHandler annotation has been applied method, designating it
	 * as the go-to method when an FileNotFoundException is thrown. It returns a
	 * String, which, just as with the request-handling method, specifies the
	 * logical name of the view to render.
	 */
	@ExceptionHandler(FileNotFoundException.class)
	public String fileNotFoundHandler() {
		System.out.println("No profile picture being uploaded");
		return "home";
	}

	@RequestMapping(path = "/{username}", method = RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {

		/*
		 * Antes de buscar el spitter en la base de datos, se verifica si el
		 * mismo se encuentra en el modelo. Usando flash attributes en
		 * processRegistrationm, un atributo 'spitter' es guardado temporalmente
		 * en la sesion y permanece en el modelo sobreviviendo un redirect...
		 */
		if (!model.containsAttribute("spitter")) {
			model.addAttribute(spitterRepository.findByUsername(username));
		}

		return "profile";
	}

}
