package spring.in.action.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Driver {
	@Value("#{car.model}")
	private String vehicleModel;
	@Value("Martin")
	private String name;

	public void drive() {
		System.out.println(name + " is driving a " + vehicleModel); 
	}
}
