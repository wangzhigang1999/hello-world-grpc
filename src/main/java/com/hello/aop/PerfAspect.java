package com.hello.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;

@Aspect
public class PerfAspect {

    Logger logger = org.slf4j.LoggerFactory.getLogger(PerfAspect.class);

    @Pointcut("execution(* com.hello.service.HelloServiceImpl.*(..))")
    public void serviceMethod() {
    }

    @Around("serviceMethod()")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
        // get method name
        String methodName = pjp.getSignature().getName();
        logger.info("start - " + methodName + " at " + System.currentTimeMillis());
        long start = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        long end = System.currentTimeMillis();
        logger.info("end - " + methodName + " at " + end + " took " + (end - start) + " ms");
        return retVal;
    }
}
