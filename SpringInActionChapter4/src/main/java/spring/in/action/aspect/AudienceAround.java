package spring.in.action.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AudienceAround {
	@Pointcut("execution(** spring.in.action.aspect.Performance.perform(..))")
	public void performance() {
	};

	@Around("performance()")
	public void watchPerformance(ProceedingJoinPoint jp) {
		try {
			System.out.println("silencing cell phones");
			System.out.println("sitting down");
			jp.proceed();
			System.out.println("clap clap!");
		}
		catch (Throwable e) {
			System.out.println("demanding refund!");
		}

	}
}
