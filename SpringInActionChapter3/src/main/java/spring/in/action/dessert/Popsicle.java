package spring.in.action.dessert;

import org.springframework.stereotype.Component;

@Component
@Cold
@Fruity
public class Popsicle implements Dessert {
	public void eat() {
		System.out.println("eating a popsicle!");
	}
}