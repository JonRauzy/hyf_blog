package com.jon.hyf_blog.user.userExeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoUserExeption extends RuntimeException{
    public NoUserExeption() {
        super("No user to show");
    }
}
