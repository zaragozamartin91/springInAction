package spring.in.action.dessert;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Dessert.class)
public class DessertConfig {
}
