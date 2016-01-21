package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spittr.data.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
	private SpittleRepository spittleRepository;

	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		super();
		this.spittleRepository = spittleRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String spittles(Model model) {
		final long max = Long.MAX_VALUE;
		final int count = 20;

		// Add spittles to model

		/*
		 * The Model is essentially a map (that is, a collection of key-value
		 * pairs) that will be handed off to the view so that the data can be
		 * rendered to the client. When addAttribute() is called without
		 * specifying a key, the key is inferred from the type of object being
		 * set as the value. In this case, because it’s a List<Spittle>, the key
		 * will be inferred as spittleList
		 */
		model.addAttribute(spittleRepository.findSpittles(max, count));

		// Return view name
		return "spittles";
	}

	/*
	 * If you’d prefer to work with a non-Spring type, you can ask for a
	 * java.util.Map instead of Model. Here’s another version of spittles()
	 * that’s functionally equivalent to the others:
	 * 
	 * @RequestMapping(method=RequestMethod.GET) public String spittles(Map
	 * model) { model.put("spittleList",
	 * spittleRepository.findSpittles(Long.MAX_VALUE, 20)); return "spittles"; }
	 */

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
	/*
	 * @RequestMapping(method=RequestMethod.GET) public List<Spittle> spittles()
	 * { return spittleRepository.findSpittles(Long.MAX_VALUE, 20)); }
	 */
}
