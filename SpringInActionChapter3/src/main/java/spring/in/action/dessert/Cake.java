package spring.in.action.dessert;

import org.springframework.stereotype.Component;

@Component
public class Cake implements Dessert {
	public void eat() {
		System.out.println("eating cake!");
	}
}
