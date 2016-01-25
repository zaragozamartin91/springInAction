package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public String spitter() {
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
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String processRegistration(Spitter spitter) {
		final String username = spitter.getUsername();

		spitterRepository.save(spitter);

		return "redirect:/spitter/" + username;
	}
}
