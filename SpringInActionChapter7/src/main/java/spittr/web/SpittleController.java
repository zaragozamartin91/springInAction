package spittr.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.Spittle;
import spittr.data.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
	private SpittleRepository spittleRepository;
	private static final String MAX_LONG_AS_STRING = "" + Long.MAX_VALUE;

	@Autowired
	public SpittleController(@Qualifier("mapSpittleRepository") SpittleRepository spittleRepository) {
		super();
		this.spittleRepository = spittleRepository;
	}

	// @RequestMapping(method = RequestMethod.GET)
	// public String spittles(Model model) {
	// final long max = Long.MAX_VALUE;
	// final int count = 20;
	//
	// // Add spittles to model
	//
	/*
	 * The Model is essentially a map (that is, a collection of key-value pairs)
	 * that will be handed off to the view so that the data can be rendered to
	 * the client. When addAttribute() is called without specifying a key, the
	 * key is inferred from the type of object being set as the value. In this
	 * case, because it’s a List<Spittle>, the key will be inferred as
	 * spittleList
	 */
	// model.addAttribute(spittleRepository.findSpittles(max, count));
	//
	// // Return view name
	// return "spittles";
	// }

	/*
	 * If you’d prefer to work with a non-Spring type, you can ask for a
	 * java.util.Map instead of Model. Here’s another version of spittles()
	 * that’s functionally equivalent to the others:
	 */
	// @RequestMapping(method = RequestMethod.GET)
	// public String spittles(Map model) {
	// model.put("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE,
	// 20));
	// return "spittles";
	// }

	/*
	 * This version is quite a bit different from the others. Rather than return
	 * a logical view name and explicitly setting the model, this method returns
	 * the Spittle list. When a handler method returns an object or a collection
	 * like this, the value returned is put into the model, and the model key is
	 * inferred from its type (spittleList, as in the other examples). As for
	 * the logical view name, it’s inferred from the request path. Because this
	 * method handles GET requests for /spittles, the view name is spittles
	 * (chopping off the leading slash).
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Spittle> spittles(
			@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count", defaultValue = "20") int count) {
		return spittleRepository.findSpittles(max, count);
	}

	/*
	 * Spring MVC allows for placeholders in an @RequestMapping path. The
	 * placeholders are names surrounded by curly braces ({ and })
	 */
	/*
	 * If no value attribute is given for @PathVariable, it assumes the
	 * placeholder’s name is the same as the method parameter name
	 */
	@RequestMapping(path = "/{spittleId}", method = RequestMethod.GET)
	public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
		Spittle spittle = spittleRepository.findOne(spittleId);

		if (spittle == null) {
			throw new SpittleNotFoundException(spittleId);
		}

		model.addAttribute(spittle);
		model.addAttribute("spittleId", spittleId);
		return "spittle";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveSpittle(SpittleForm spittleForm) {
		spittleRepository.save(new Spittle(spittleForm.getMessage(), new Date(), spittleForm
				.getLongitude(), spittleForm.getLatitude()));
		return "redirect:/spittles";
	}

	/*
	 * The @ExceptionHandler annotation has been applied to the handleDuplicate-
	 * Spittle() method, designating it as the go-to method when a
	 * DuplicateSpittle- Exception is thrown. It returns a String, which, just
	 * as with the request-handling method, specifies the logical name of the
	 * view to render, telling the user that they attempted to create a
	 * duplicate entry.
	 */
	@ExceptionHandler(DuplicateSpittleException.class)
	public String handleDuplicateSpittle() {
		return "error/duplicate";
	}
}
