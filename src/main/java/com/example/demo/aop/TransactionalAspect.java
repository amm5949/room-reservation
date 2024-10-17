package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Configuration
public class TransactionalAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    private
    // Pointcut for any method annotated with @Transactional
    @Pointcut("@annotation(transactional)")
    public void transactionalMethods(Transactional transactional) {
        // Pointcut method, no code required here.
    }

    // Log before the transactional method starts
    @Before(value = "transactionalMethods(transactional)", argNames = "joinPoint,transactional")
    public void beforeTransactionalMethod(JoinPoint joinPoint, Transactional transactional) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Transaction about to start on method: " + methodName + " with arguments: " + java.util.Arrays.toString(args));
    }

    // Log after the transactional method completes
    @After(value = "transactionalMethods(transactional)", argNames = "joinPoint,transactional")
    public void afterTransactionalMethod(JoinPoint joinPoint, Transactional transactional) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Transaction completed on method: " + methodName);
    }
}
