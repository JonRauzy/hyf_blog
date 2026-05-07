package com.jon.hyf_blog.article.articleExeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoArticleExeption extends RuntimeException{
    public NoArticleExeption() {
        super("No article to show");
    }
}
