package spittr.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import spittr.data.DuplicateSpitterException;

/*
 A controller advice is
 any class that’s annotated with @ControllerAdvice and has one or more of the following
 kinds of methods:
 > @ExceptionHandler-annotated
 > @InitBinder-annotated
 > @ModelAttribute-annotated
 Those methods in an @ControllerAdvice-annotated class are applied globally across
 all @RequestMapping-annotated methods on all controllers in an application.
 */

/*
 * The @ControllerAdvice annotation is itself annotated with @Component. Therefore,
 an @ControllerAdvice-annotated class will be picked up by component-scanning, just
 like an @Controller-annotated class
 * */
@ControllerAdvice
public class AppWideExceptionHandler {
	@ExceptionHandler(DuplicateSpittleException.class)
	public String duplicateSpittleHandler() {
		return "error/duplicate";
	}
	
	@ExceptionHandler(DuplicateSpitterException.class)
	public String duplicateSpitterHandler(){
		return "error/duplicateSpitter";
	}
}
