package com.jon.hyf_blog.tag.tagExeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoTagExeption extends RuntimeException{
    public NoTagExeption() {
        super("No tag to show");
    }
}
