package spring.in.action.aspect;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("basicPerformance")
public class BasicPerformance implements Performance {
	@Value("#{ {'martin'} }")
	private List<String> names;

	public void perform() {
		System.out.println("basic performance running!");
	}

	public List<String> getNames() {
		return names;
	}

}
