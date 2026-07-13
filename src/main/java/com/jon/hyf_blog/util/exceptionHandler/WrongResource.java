package com.jon.hyf_blog.util.exceptionHandler;

public class WrongResource extends RuntimeException {
    public WrongResource(Class<?> entityClass) {
        super("This " + entityClass.getSimpleName() + " doesn't belong to this ressource");
    }
}
