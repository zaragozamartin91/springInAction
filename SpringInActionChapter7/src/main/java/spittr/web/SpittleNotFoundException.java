package spittr.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*When SpittleNotFoundException is thrown, it’s a situation where a requested
 resource isn’t found. The HTTP status code of 404 is precisely the appropriate
 response status code when a resource isn’t found. So, let’s use @ResponseStatus to
 map SpittleNotFoundException to HTTP status code 404.

 After introducing this @ResponseStatus annotation, if a SpittleNotFoundException
 were to be thrown from a controller method, the response would have a status code of
 404 and a reason of Spittle Not Found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Spittle not found!")
@SuppressWarnings("serial")
public class SpittleNotFoundException extends RuntimeException {

	public SpittleNotFoundException(long spittleId) {
		super("Unable to find " + spittleId + " spittle!");
	}

}
