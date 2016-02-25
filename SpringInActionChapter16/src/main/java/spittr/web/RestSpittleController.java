package spittr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spittr.Spittle;
import spittr.data.SpittleRepository;

@RestController
@RequestMapping("/restspittles")
public class RestSpittleController {
	private SpittleRepository spittleRepository;
	private static final String MAX_LONG_AS_STRING = "" + Long.MAX_VALUE;

	@Autowired
	public RestSpittleController(@Qualifier("mapSpittleRepository") SpittleRepository spittleRepository) {
		super();
		this.spittleRepository = spittleRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Spittle> spittles(
			@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count", defaultValue = "20") int count) {
		return spittleRepository.findSpittles(max, count);
	}

	@RequestMapping(path = "/{spittleId}", method = RequestMethod.GET)
	public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
		Spittle spittle = spittleRepository.findOne(spittleId);
		model.addAttribute(spittle);
		model.addAttribute("spittleId", spittleId);
		return "spittle";
	}
	
	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	public Spittle saveSpittle(@RequestBody Spittle spittle){
		spittleRepository.save(spittle);
		return spittle;
	}
}
