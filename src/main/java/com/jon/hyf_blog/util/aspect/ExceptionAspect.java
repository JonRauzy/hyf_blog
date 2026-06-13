//package com.jon.hyf_blog.aspect;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.sql.SQLException;
//
//@Slf4j
//@Aspect
//@Component
//public class ExceptionAspect extends Throwable {
//
//    @AfterThrowing(pointcut = "execution(* com.jon.hyf_blog.*.*(..))", throwing = "exception")
//    public void handleExceptions(Exception exception) {
//        logErrorBasedOnExceptionType(exception);
//    }
//
//    private void logErrorBasedOnExceptionType(Exception exception) {
//        if (exception instanceof IOException ioEx) {
//            log.error("IOException occurred: {}", ioEx.getMessage());
//        } else if (exception instanceof SQLException sqlEx) {
//            log.error("SQLException occurred: {}", sqlEx.getMessage());
//        } else {
//            log.error("Exception occurred: {}", exception.getMessage());
//        }
//    }
//}