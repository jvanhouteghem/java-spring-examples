package com.byteslounge.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

// LogginAspect vient par dessus ExempleService

@Aspect
public class LoggingAspect {

	// @before : le code de l'advice est exécuté avant l'exécution de la méthode (il n'est pas possible d'inhiber l'invocation de la méthode sauf si une exception est levée dans l'advice). 
	// Cette annotation pointe vers une méthode spécifique, en l'occurence ici simpleMethod()
	@Before("execution(* com.byteslounge.spring.service.ExampleService.simpleMethod(..))")
	public void beforeExecution(JoinPoint jp) {
		System.out.println("-----------------------@Before------------------------------");
		System.out.println(jp.getSourceLocation() + "Before method: " + jp.getSignature().getName() + ". Class: " + jp.getTarget().getClass().getSimpleName());
	}
	
	// Un before qui se lance avant chaque méthodes 
	@Before("execution(* com.byteslounge.spring.service.ExampleService.*(..))")
	public void beforeExecution2(JoinPoint jp) {
		System.out.println("-----------------------@Before2------------------------------");
		System.out.println("Before method: " + jp.getSignature().getName() + ". Class: " + jp.getTarget().getClass().getSimpleName());
	}

	// @After : le code de l'aspect est exécuté après l'exécution de la méthode, même si une exception est levée.
	@After("execution(* com.byteslounge.spring.service.ExampleService.simpleMethod(..))")
	public void afterExecution2(JoinPoint jp) {
		System.out.println("-----------------------@After 1 ------------------------------");		
		System.out.println("After method: " + jp.getSignature().getName() + ". Class: " + jp.getTarget().getClass().getSimpleName());
	}
	
	// Il est possible d'en faire plusieur à la suite. ATTENTION : le dernier sera executé en premier (exemple ici le after2 avant le after1).
	@After("execution(* com.byteslounge.spring.service.ExampleService.simpleMethod(..))")
	public void afterExecution1(JoinPoint jp) {
		System.out.println("-----------------------@After 2 ------------------------------");		
		System.out.println("After method: " + jp.getSignature().getName() + ". Class: " + jp.getTarget().getClass().getSimpleName());
	}
	
	// Il est possible d'en faire plusieur à la suite. ATTENTION : le dernier sera executé en premier (exemple ici le after2 avant le after1 puis le after3).
	@After("execution(* com.byteslounge.spring.service.ExampleService.*(..))")
	public void afterExecution3(JoinPoint jp) {
		System.out.println("-----------------------@After 3 ------------------------------");		
		System.out.println("After method: " + jp.getSignature().getName() + ". Class: " + jp.getTarget().getClass().getSimpleName());
	}
	
	// @AfterReturning : le code de l'aspect est exécuté après l'exécution de la méthode qui renvoie une valeur de retour (aucune exception n'est levée).
	@AfterReturning(pointcut = "execution(* com.byteslounge.spring.service.ExampleService.methodReturnsValue(..))", returning = "result")
	public void afterReturningExecution(JoinPoint jp, Object result) {
		System.out.println("-----------------------@AfterReturning------------------------------");
		System.out.println("After returning method: " + jp.getSignature().getName() + ". Class: " + jp.getTarget().getClass().getSimpleName());
		System.out.println("Result returned: " + result);
	}

	// @AfterThrowing : le code de l'aspect est exécuté lorsqu'une exception est levée suite à l'invocation de la méthode
	@AfterThrowing(pointcut = "execution(* com.byteslounge.spring.service.ExampleService.methodThrowsException(..))", throwing = "ex")
	public void afterThrowingExecution(JoinPoint jp, Exception ex) {
		System.out.println("-----------------------@AfterThrowing------------------------------");
		System.out.println("After throwing method: " + jp.getSignature().getName() + ". Class: " + jp.getTarget().getClass().getSimpleName());
		System.out.println("Exception: " + ex.getMessage());
	}

	// @Around : le code de l'aspect permet de lancer l'exécution de la méthode et ainsi de réaliser des traitements avant, pour par exemple conditionner l'invocation de la méthode et des traitements après
	@Around("execution(* com.byteslounge.spring.service.ExampleService.testAround*(..))")
	public Object aroundExecution(ProceedingJoinPoint jp) throws Exception {
		System.out.println("-----------------------@Around------------------------------");	
		System.out.println("Before method: " + jp.getSignature().getName() + ". Class: " + jp.getTarget().getClass().getSimpleName());

		try {
			Object result = jp.proceed();
			System.out.println("Returning: " + result);
			return result;
		} catch (Throwable e) {
			System.out.println("Error: " + e.getMessage());
			throw new Exception("Error", e);
		}
	}
}
