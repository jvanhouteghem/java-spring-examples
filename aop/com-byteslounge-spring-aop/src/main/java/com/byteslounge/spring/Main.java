package com.byteslounge.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.byteslounge.spring.service.ExampleService;

public class Main {
	
	// source : http://www.byteslounge.com/tutorials/spring-aop-example
	// Modifications par JVanhouteghem le 06/01/2016

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		ExampleService exampleBean = (ExampleService) ctx.getBean("exampleService"); // eq ExampleService es = new exampleService();

		// -----------------------------------------------------
		System.out.println("==================================================");
		System.out.println("Call exampleBean.simpleMethod() : ");
		System.out.println("==================================================");
		exampleBean.simpleMethod();
		
		// -----------------------------------------------------
		System.out.println("");
		System.out.println("");
		System.out.println("==================================================");
		System.out.println("Call exampleBean.methodReturnsValue() : ");
		System.out.println("==================================================");
		
		Object result = exampleBean.methodReturnsValue(); // AOP
		
		// -----------------------------------------------------
		System.out.println("");
		System.out.println("");
		System.out.println("==================================================");
		System.out.println("Call exampleBean.methodThrowsException() ");
		System.out.println("==================================================");
		
		try {
			exampleBean.methodThrowsException();
		} catch (Exception e) {
			System.out.println("Exception caught in Main: " + e.getMessage());
		}
		
		// -----------------------------------------------------
		System.out.println("");
		System.out.println("");
		System.out.println("==================================================");
		System.out.println("Call exampleBean.testAroundReturningResult() ");
		System.out.println("==================================================");
		
		result = exampleBean.testAroundReturningResult();
		
		// -----------------------------------------------------
		System.out.println("");
		System.out.println("");
		System.out.println("==================================================");
		System.out.println("Call exampleBean.testAroundThrowingException() ");
		System.out.println("==================================================");
		
		try {
			exampleBean.testAroundThrowingException();
		} catch (Exception e) {
			System.out.println("Exception caught in Main: " + e.getMessage());
		}

	}
}
