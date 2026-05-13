package com.jon.hyf_blog.article.articleExeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArticleNotFoundExeption extends RuntimeException {
    public ArticleNotFoundExeption(Long id){
        super("No article found at id : " + id);
    }
}
