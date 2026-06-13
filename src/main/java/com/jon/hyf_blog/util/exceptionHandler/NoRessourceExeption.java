package com.jon.hyf_blog.util.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoRessourceExeption extends RuntimeException{
    public NoRessourceExeption(Class<?> entityClass) {
        super("No " + entityClass.getSimpleName() + " to show");
    }
}
