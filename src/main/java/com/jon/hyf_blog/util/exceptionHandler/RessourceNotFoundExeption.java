package com.jon.hyf_blog.util.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RessourceNotFoundExeption extends RuntimeException {
    public RessourceNotFoundExeption(Class<?> entityClass, Long id){
        super("No " + entityClass.getSimpleName() + " found at id : " + id);
    }
}