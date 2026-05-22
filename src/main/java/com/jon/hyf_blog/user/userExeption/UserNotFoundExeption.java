package com.jon.hyf_blog.user.userExeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundExeption extends RuntimeException {
    public UserNotFoundExeption(Long id){
        super("No user found at id : " + id);
    }
}