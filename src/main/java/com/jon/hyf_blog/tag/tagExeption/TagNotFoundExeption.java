package com.jon.hyf_blog.tag.tagExeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TagNotFoundExeption extends RuntimeException {
    public TagNotFoundExeption(Long id){
        super("No tag found at id : " + id);
    }
}
