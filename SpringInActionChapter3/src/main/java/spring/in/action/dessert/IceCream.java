package spring.in.action.dessert;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@Cold
@Creamy
public class IceCream implements Dessert {
	public void eat() {
		System.out.println("eating ice cream!");
	}
}