package spring.in.action;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.in.action.dessert.Cold;
import spring.in.action.dessert.Cookies;
import spring.in.action.dessert.Dessert;
import spring.in.action.dessert.DessertConfig;
import spring.in.action.dessert.DessertEater;
import spring.in.action.dessert.Fruity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DessertConfig.class)
public class DessertTest {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private Dessert primaryDessert;

	@Autowired
	@Qualifier("iceCream")
	private Dessert iceCream;

	@Autowired
	private DessertEater eater;

	@Autowired
	private Cookies cookies;
	
	/*es posible crear anotations anotadas como qualifier para describir componentes*/
	@Autowired
	@Cold
	@Fruity
	private Dessert popsicle;

	@Test
	public void primaryIsIceCream() {
		Assert.assertEquals(primaryDessert, iceCream);
	}

	@Test
	public void eatingCookies() {
		Assert.assertEquals(eater.getDessert(), cookies);
	}
	
}
