package com.madeeasy.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Aspect
@Configuration
public class UserServiceAspect {

    /**
     * use case for creating aspect :
     * #1 : Logging
     * #2 : Validation
     * #3 : Caching
     * #4 : Security
     * #5 : Transactions
     * #6 : Monitoring
     * #7 : Error Handling
     * etc ...
     */

    // this will run just before execution of any method
    @Before(value = "execution(* com.madeeasy.controller.UsersController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("beforeAdvice in UserServiceAspect || " +
                joinPoint.getSignature().getName() + " || " +
                Arrays.toString(joinPoint.getArgs()) + " || " +
                joinPoint.getTarget().getClass().getName());
    }

    // this will run just after execution of the method
    @After(value = "execution(* com.madeeasy.controller.UsersController.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("afterAdvice in UserServiceAspect || " +
                joinPoint.getSignature().getName() + " || " +
                Arrays.toString(joinPoint.getArgs()) + " || " +
                joinPoint.getTarget().getClass().getName());
    }

    // this will run as soon as method returns its value
    @AfterReturning(value = "execution(* com.madeeasy.controller.UsersController.*(..))")
    public void afterReturningAdvice(JoinPoint joinPoint) {
        System.out.println("afterReturningAdvice in UserServiceAspect || " +
                joinPoint.getSignature().getName() + " || " +
                Arrays.toString(joinPoint.getArgs()) + " || " +
                joinPoint.getTarget().getClass().getName());
    }

    // this will run as soon as method throws an exception
    @AfterThrowing(pointcut = "execution(* com.madeeasy.controller.UsersController.*(..))", throwing = "exception")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable exception) {
        System.out.println("afterThrowingAdvice in UserServiceAspect || " +
                joinPoint.getSignature().getName() + " || " +
                Arrays.toString(joinPoint.getArgs()) + " || " +
                joinPoint.getTarget().getClass().getName());
        System.out.println("Exception message: " + exception.getMessage());
    }

    // this executes before and after joint point
    @Around("execution(* com.madeeasy.controller.UsersController.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("aroundAdvice executes before joint point in UserServiceAspect || " +
                proceedingJoinPoint.getSignature().getName() + " || " +
                Arrays.toString(proceedingJoinPoint.getArgs()) + " || " +
                proceedingJoinPoint.getTarget().getClass().getName());
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            /**
             * By adding a finally block after the try block, you ensure that the code inside the finally block is executed every
             * time the intercepted method is called. In this case, it will print the advice message again.
             */
            System.out.println("aroundAdvice executes after joint point in UserServiceAspect || " +
                    proceedingJoinPoint.getSignature().getName() + " || " +
                    Arrays.toString(proceedingJoinPoint.getArgs()) + " || " +
                    proceedingJoinPoint.getTarget().getClass().getName());
        }
        return result;
    }

    /**
     * Here's an overview of the standard syntax for each pointcut designator and annotation in AspectJ:
     *
     * 1. `within`: Targets all methods within a specific class or package.
     *    - Syntax: `within(package.ClassName)` or `within(package.*)`
     *
     * 2. `this`: Targets any proxy implementing a specific interface.
     *    - Syntax: `this(package.InterfaceName)`
     *
     * 3. `target`: Targets the class itself, excluding subclasses.
     *    - Syntax: `target(package.ClassName)`
     *
     * 4. `args`: Targets methods with specific arguments.
     *    - Syntax: `args(type)` or `args(type1, type2, ...)`
     *
     * 5. `@args`: Targets methods with parameters annotated with a specific annotation.
     *    - Syntax: `@args(package.AnnotationName)`
     *
     * 6. `@within`: Targets any bean within a class annotated with a specific annotation.
     *    - Syntax: `@within(package.AnnotationName)`
     *
     * 7. `@target`: Targets any bean of a type annotated with a specific annotation.
     *    - Syntax: `@target(package.AnnotationName)`
     *
     * 8. `@annotation`: Targets methods annotated with a specific annotation.
     *    - Syntax: `@annotation(package.AnnotationName)`
     *
     * Here's an example code snippet demonstrating these pointcut designators and annotations:
     *
     *     // within
     *     @Before("within(com.madeeasy.controller.UsersController)")
     *     public void withinAdvice(JoinPoint joinPoint) {
     *         // Advice logic
     *     }
     *
     *     // this
     *     @AfterReturning("this(com.madeeasy.controller.UsersController)")
     *     public void thisAdvice(JoinPoint joinPoint) {
     *         // Advice logic
     *     }
     *
     *     // target
     *     @AfterThrowing("target(com.madeeasy.controller.UsersController)")
     *     public void targetAdvice(JoinPoint joinPoint) {
     *         // Advice logic
     *     }
     *
     *     // args
     *     @AfterReturning("args(String)")
     *     public void argsAdvice(JoinPoint joinPoint) {
     *         // Advice logic
     *     }
     *
     *     // @args
     *     @Before("@args(com.madeeasy.MyAnnotation)")
     *     public void argsAnnotationAdvice(JoinPoint joinPoint) {
     *         // Advice logic
     *     }
     *
     *     // @within
     *     @Before("@within(com.madeeasy.MyControllerAnnotation)")
     *     public void withinAnnotationAdvice(JoinPoint joinPoint) {
     *         // Advice logic
     *     }
     *
     *     // @target
     *     @Before("@target(com.madeeasy.MyServiceAnnotation)")
     *     public void targetAnnotationAdvice(JoinPoint joinPoint) {
     *         // Advice logic
     *     }
     *
     *     // @annotation
     *     @Before("@annotation(com.madeeasy.MyMethodAnnotation)")
     *     public void annotationAdvice(JoinPoint joinPoint) {
     *         // Advice logic
     *     }
     *
     * In this example, each advice method is annotated with the appropriate pointcut designator or annotation,
     * following the standard syntax mentioned above. You can customize the advice logic within each method to suit
     * your requirements.
     */
}
