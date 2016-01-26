package spittr.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	 * this view specification is different from what you’ve seen before. Rather
	 * than just return a view name and let the view resolver sort it out, here
	 * you’re returning a redirect specification. When
	 * InternalResourceViewResolver sees the redirect: prefix on the view
	 * specification, it knows to interpret it as a redirect specification
	 * instead of as a view name. In this case, it will redirect to the path for
	 * a user’s profile page. For example, if the Spitter .username property is
	 * jbauer, then the view will redirect to /spitter/jbauer
	 */
	/*
	 * If there are any validation errors, they’re available in the Errors
	 * object that you’re now asking for as a parameter to
	 * processRegistration(). (Note that it’s important that the Errors
	 * parameter immediately follow the @Valid-annotated parameter that’s being
	 * validated.)
	 */
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String processRegistration(@Valid Spitter spitter, Errors errors) {
		if (errors.hasErrors()) {
			return "registerForm";
		}

		final String username = spitter.getUsername();
		spitterRepository.save(spitter);

		return "redirect:/spitter/" + username;
	}

	@RequestMapping(path = "/{username}", method = RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		final Spitter spitter = spitterRepository.findByUsername(username);
		model.addAttribute(spitter);

		return "profile";
	}
}
