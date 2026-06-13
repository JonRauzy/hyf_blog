package com.jon.hyf_blog.util.exceptionHandler;

public class RessourceExist extends RuntimeException {
    public RessourceExist(Class<?> entityClass) {
        super("This " + entityClass.getSimpleName() + " already exist");
    }
}
