package com.jon.hyf_blog.util.exceptionHandler;

import com.jon.hyf_blog.article.articleExeption.ArticleNotFoundExeption;
import com.jon.hyf_blog.article.articleExeption.NoArticleExeption;
import com.jon.hyf_blog.tag.tagExeption.NoTagExeption;
import com.jon.hyf_blog.tag.tagExeption.TagNotFoundExeption;
import com.jon.hyf_blog.user.userExeption.NoUserExeption;
import com.jon.hyf_blog.user.userExeption.UserExist;
import com.jon.hyf_blog.user.userExeption.UserNotFoundExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ArticleNotFoundExeption.class)
    public ResponseEntity<ErrorResponse> handleArticleNotFound(ArticleNotFoundExeption ex) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), 404, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoArticleExeption.class)
    public ResponseEntity<ErrorResponse> handleNoArticle(NoArticleExeption ex) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NO_CONTENT.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(TagNotFoundExeption.class)
    public ResponseEntity<ErrorResponse> handleTagNotFound(TagNotFoundExeption ex) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), 404, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoTagExeption.class)
    public ResponseEntity<ErrorResponse> handleNoTag(NoTagExeption ex) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NO_CONTENT.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(UserNotFoundExeption.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundExeption ex) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), 404, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoUserExeption.class)
    public ResponseEntity<ErrorResponse> handleNoUser(NoUserExeption ex) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NO_CONTENT.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(UserExist.class)
    public ResponseEntity<ErrorResponse> HandleUserExist(UserExist ex) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), 404, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
