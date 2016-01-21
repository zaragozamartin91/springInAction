package spring.in.action.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience {
	@Pointcut("execution(** spring.in.action.aspect.Performance.perform(..))")
	public void performance() {
	};

	@Before("execution(** spring.in.action.aspect.Performance.perform(..))")
	public void silenceCellPhones() {
		System.out.println("silencing cell phones");
	}

	@Before("execution(** spring.in.action.aspect.Performance.perform(..))")
	public void takeSeats() {
		System.out.println("sitting down");
	}

	@AfterReturning("execution(** spring.in.action.aspect.Performance.perform(..))")
	public void applause() {
		System.out.println("CLAP CLAP!");
	}

	/*
	 * the @Pointcut annotation defines a reusable pointcut within an @AspectJ aspect
	 */
	@AfterThrowing("performance()")
	public void demandRefund() {
		System.out.println("it sucked!");
	}
}
