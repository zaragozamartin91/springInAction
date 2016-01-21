package spring.in.action.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EncoreableIntroducer {
	@DeclareParents(value = "spring.in.action.aspect.Performance", defaultImpl = DefaultEncoreable.class)
	public static Encoreable encoreable;
}
