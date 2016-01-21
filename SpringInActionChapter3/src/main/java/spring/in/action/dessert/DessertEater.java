package spring.in.action.dessert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DessertEater {
	private Dessert dessert;

	public Dessert getDessert() {
		return dessert;
	}

	@Autowired
	@Qualifier("chips")
	public void setDessert(Dessert dessert) {
		this.dessert = dessert;
	}
}
