package spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({ "/", "/homepage" })
public class HomeController {
	/*
	 * it returns a String value of “home”. This String will be interpreted by
	 * Spring MVC as the name of the view that will be rendered.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		return "home";
	}
}
