package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping("/users")
	public List<User> users(@RequestParam(value = "name", defaultValue = "World") String name) {
		return createDummyUsers();
	}

	private List<User> createDummyUsers() {
		List<User> users = new ArrayList<>();
		for (Long i = 0L; i < 5; i++) {
			users.add(new User("name_" + i, i));
		}
		
		return users;
	}

}