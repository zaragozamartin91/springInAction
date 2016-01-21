package spring.in.action.aspect;

public class DefaultEncoreable implements Encoreable {

	@Override
	public void performEncore() {
		System.out.println("PERFORMING ENCORE!");
	}

}
