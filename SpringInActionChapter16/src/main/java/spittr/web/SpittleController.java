package spittr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spittr.Spittle;
import spittr.data.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
	private SpittleRepository spittleRepository;
	private static final String MAX_LONG_AS_STRING = "" + Long.MAX_VALUE;

	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		super();
		this.spittleRepository = spittleRepository;
	}

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
	 * Normally, when a handler method returns a Java object (anything other
	 * than String or an implementation of View), that object ends up in the
	 * model for rendering in the view. But if you’re going to employ message
	 * conversion, you need to tell Spring to skip the normal model/view flow
	 * and use a message converter instead. There are a handful of ways to do
	 * this, but the simplest is to annotate the controller method with
	 * ResponseBody.
	 */
	/*
	 * moreSpittles responde como una solicitud REST que retorna una vista como
	 * Json en vez de inyectar la lista en el modelo del Html
	 */
	/*
	 * The ResponseBody annotation tells Spring that you want to send the
	 * returned object as a resource to the client, converted into some
	 * representational form that the client can accept.
	 */
	/*
	 * if the client’s Accept header specifies that the client will accept
	 * application/json, and if the Jackson JSON library is in the application’s
	 * classpath, then either MappingJacksonHttpMessageConverter or
	 * MappingJackson2HttpMessageConverter will be chosen. The message converter will
	 * convert the Spittle list returned from the controller into a JSON
	 * document that will be written to the body of the response.
	 */
	@RequestMapping(path = "/more", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Spittle> moreSpittles(
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
		model.addAttribute(spittle);
		model.addAttribute("spittleId", spittleId);
		return "spittle";
	}
}
