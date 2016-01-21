package spring.in.action.dessert;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("chips")
public class Cookies implements Dessert {
	public void eat() {
		System.out.println("eating cookies!");
	}
}