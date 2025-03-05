package com.project.LibraryManagementSystem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.project.LibraryManagementSystem.controller.*.*(..)) || execution(* com.project.LibraryManagementSystem.service.*.*(..))") // Apply to all service methods
    public void logBeforeMethod(JoinPoint joinPoint) {
        System.out.println("Executing method: " + joinPoint.getSignature().getName());
        System.out.println("Arguments: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* com.project.LibraryManagementSystem.controller.*.*(..)) || execution(* com.project.LibraryManagementSystem.service.*.*(..))", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        System.out.println("Method executed: " + joinPoint.getSignature().getName());
        System.out.println("Return value: " + result);
    }
}
