package spring.in.action.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TimeChecker {
	@Value("#{T(System).currentTimeMillis()}")
	private double currTimeInMillis;

	public double getCurrTimeInMillis() {
		return currTimeInMillis;
	}
}
