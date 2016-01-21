package spring.in.action;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.in.action.spel.Driver;
import spring.in.action.spel.EmailChecker;
import spring.in.action.spel.Jukebox;
import spring.in.action.spel.Person;
import spring.in.action.spel.SpelConfig;
import spring.in.action.spel.TheStrokesJukebox;
import spring.in.action.spel.TimeChecker;
import spring.in.action.spel.Vehicle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpelConfig.class)
public class SpelTest {
	static {
		System.setProperty("car.model", "Peugeot 206");
		System.setProperty("car.maxSpeed", "221.23");
	}

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	@Qualifier("car")
	Vehicle car;

	@Autowired
	Driver driver;

	@Autowired
	Person person;

	@Autowired
	Jukebox jukebox;

	@Autowired
	TheStrokesJukebox theStrokesJukebox;
	
	@Test
	public void testTimeChecker() {
		System.out.println(applicationContext.getBean(TimeChecker.class).getCurrTimeInMillis());
	}

	@Test
	public void checkCar() {
		System.out.println(car);
	}

	@Test
	public void checkDriver() {
		driver.drive();
	}

	@Test
	public void checkPerson() {
		System.out.println(person);
	}

	@Test
	public void checkEmail() {
		Assert.assertTrue(applicationContext.getBean(EmailChecker.class).isOk);
	}

	@Test
	public void checkJukebox() {
		jukebox.listSongs();
	}

	@Test
	public void checkStrokesJukebox() {
		System.out.println("RANDOM STROKES SONG IS: " + theStrokesJukebox.getRandomSong());
		System.out.println("THE STROKES SONGS ARE: " + theStrokesJukebox.getTheStrokesSongs());
	}
}
