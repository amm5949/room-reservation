package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class Monitoring {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private long startTime;

    @Before("execution(* com.example.demo.*.*.*(..))")  // PointCut
    public void BeforeExecution(JoinPoint joinPoint)  // Advice
    {

        logger.info("Start of {}", getMethodAndClass(joinPoint));
        startTime = System.currentTimeMillis();
    }

    @After(value = "execution(* com.example.demo.*.*.*(..))")
    public void afterExecution(JoinPoint joinPoint)
    {
        long timeSpent = System.currentTimeMillis() - startTime;
        logger.info("Execution of {}, elapsed time: {} ms", getMethodAndClass(joinPoint), timeSpent);
    }

    public String getMethodAndClass(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String className = signature.getClass().getName();
        String methodName = signature.getDeclaringType().getSimpleName();
        logger.debug("methodName: {}, className: {}", methodName, className);
        return "the method of" + methodName +" in the class of " + className;
    }
}
