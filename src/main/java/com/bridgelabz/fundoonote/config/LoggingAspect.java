package com.bridgelabz.fundoonote.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // 👇 Trigger when exception occurs
    @AfterThrowing(
            pointcut = "execution(* com.bridgelabz.fundoonote..*(..))",
            throwing = "ex"
    )
    public void logException(JoinPoint joinPoint, Exception ex) {

        String methodName = joinPoint.getSignature().toShortString();

        System.out.println("❌ Exception in method: " + methodName);
        System.out.println("⚠️ Error message: " + ex.getMessage());
    }
}