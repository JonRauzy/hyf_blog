package com.jon.hyf_blog.util.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Class<?> entityClass, Long id){
        super("No " + entityClass.getSimpleName() + " found at id : " + id);
    }

    public ResourceNotFoundException(Class<?> entityClass){
        super("This " + entityClass.getSimpleName() + " is not found");
    }
}