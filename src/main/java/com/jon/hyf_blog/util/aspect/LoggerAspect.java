package com.jon.hyf_blog.util.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggerAspect {
    // @Around must return Object so the actual controller return value is not lost
    @Around(value = "execution(* com.jon.hyf_blog.*.*.*(..))")
    public Object logControllerMethodCalls(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().toShortString();
        long start = System.currentTimeMillis();
        log.warn("[LOG] Calling: " + method);
        Object result = joinPoint.proceed(); // execute the actual method

        long duration = System.currentTimeMillis() - start;
        log.warn("[LOG] Finished: " + method + " (" + duration + " ms)");
        return result;
    }
}
