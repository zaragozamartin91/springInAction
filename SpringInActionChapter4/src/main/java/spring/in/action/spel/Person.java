package spring.in.action.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person {
	@Value("#{T(Math).random()}")
	private double id;
	@Value("#{'zaragoza@gmail.com'}")
	private String email;

	public double getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", email=" + email + "]";
	}
}
