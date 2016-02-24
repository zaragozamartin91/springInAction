package spittr.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Spitter not found!")
@SuppressWarnings("serial")
public class SpitterNotFoundException extends RuntimeException {

	public SpitterNotFoundException(String username) {
		super("Spitter " + username + " not found!");
	}

}
