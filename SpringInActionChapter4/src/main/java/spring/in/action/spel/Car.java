package spring.in.action.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle {
	@Value("#{systemProperties['car.model']}")
	private String model;
	@Value("#{systemProperties['car.maxSpeed']}")
	private String maxSpeed;

	public String getModel() {
		return model;
	}

	public String getMaxSpeed() {
		return maxSpeed;
	}

	@Override
	public String toString() {
		return "Car [model=" + model + ", maxSpeed=" + maxSpeed + "]";
	}

}
