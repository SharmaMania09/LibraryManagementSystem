package com.project.LibraryManagementSystem.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
public class TransactionAspect {

    @AfterThrowing(pointcut = "execution(* com.project.LibraryManagementSystem.controller.*.*(..)) || execution(* com.project.LibraryManagementSystem.service.*.*(..))", throwing = "ex")
    @Transactional(rollbackFor = Exception.class)
    public void rollbackTransaction(Exception ex) {
        System.out.println("Transaction rolled back due to: " + ex.getMessage());
    }
}
