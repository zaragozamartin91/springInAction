package spring.in.action.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailChecker {
	@Value("#{person.email matches '[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com'}")
	public boolean isOk;
}
