Source : http://www.byteslounge.com/tutorials/spring-aop-example
Modifications par JVanhouteghem le 06/01/2016

Exemple d'utilisation de Spring AOP

Sortie console : 
janv. 06, 2016 5:06:37 PM org.springframework.context.support.AbstractApplicationContext prepareRefresh
INFOS: Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@2e817b38: startup date [Wed Jan 06 17:06:37 CET 2016]; root of context hierarchy
janv. 06, 2016 5:06:37 PM org.springframework.beans.factory.xml.XmlBeanDefinitionReader loadBeanDefinitions
INFOS: Loading XML bean definitions from class path resource [spring-config.xml]
janv. 06, 2016 5:06:37 PM org.springframework.beans.factory.support.DefaultListableBeanFactory preInstantiateSingletons
INFOS: Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@42e26948: defining beans [exampleService,org.springframework.context.annotation.internalConfigurationAnnotationProcessor,org.springframework.context.annotation.internalAutowiredAnnotationProcessor,org.springframework.context.annotation.internalRequiredAnnotationProcessor,org.springframework.context.annotation.internalCommonAnnotationProcessor,org.springframework.aop.config.internalAutoProxyCreator,loggingAspect,org.springframework.context.annotation.ConfigurationClassPostProcessor.importAwareProcessor]; root of factory hierarchy

Call exampleBean.simpleMethod() : 
-----------------------@Before------------------------------
org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@5158b42fBefore method: simpleMethod. Class: ExampleService
-----------------------@Before2------------------------------
Before method: simpleMethod. Class: ExampleService
-----------------------@After 2 ------------------------------
After method: simpleMethod. Class: ExampleService
-----------------------@After 1 ------------------------------
After method: simpleMethod. Class: ExampleService
-----------------------@After 3 ------------------------------
After method: simpleMethod. Class: ExampleService


Call exampleBean.methodReturnsValue() : 
-----------------------@Before2------------------------------
Before method: methodReturnsValue. Class: ExampleService
-----------------------@After 3 ------------------------------
After method: methodReturnsValue. Class: ExampleService
-----------------------@AfterReturning------------------------------
After returning method: methodReturnsValue. Class: ExampleService
Result returned: Hello from methodReturnsValue


Call exampleBean.methodThrowsException() 
-----------------------@Before2------------------------------
Before method: methodThrowsException. Class: ExampleService
-----------------------@After 3 ------------------------------
After method: methodThrowsException. Class: ExampleService
-----------------------@AfterThrowing------------------------------
After throwing method: methodThrowsException. Class: ExampleService
Exception: Exception from methodThrowsException
Exception caught in Main: Exception from methodThrowsException


Call exampleBean.testAroundReturningResult() 
-----------------------@Around------------------------------
Before method: testAroundReturningResult. Class: ExampleService
-----------------------@Before2------------------------------
Before method: testAroundReturningResult. Class: ExampleService
Returning: Hello from aroundReturningResult
-----------------------@After 3 ------------------------------
After method: testAroundReturningResult. Class: ExampleService


Call exampleBean.testAroundThrowingException() 
-----------------------@Around------------------------------
Before method: testAroundThrowingException. Class: ExampleService
-----------------------@Before2------------------------------
Before method: testAroundThrowingException. Class: ExampleService
Error: Exception from testAroundThrowingException
-----------------------@After 3 ------------------------------
After method: testAroundThrowingException. Class: ExampleService
Exception caught in Main: Error


