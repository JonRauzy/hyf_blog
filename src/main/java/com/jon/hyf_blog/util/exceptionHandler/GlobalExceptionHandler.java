package com.jon.hyf_blog.util.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception ex, HttpStatus status) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), status.value(), ex.getMessage());
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(RessourceNotFoundExeption.class)
    public ResponseEntity<ErrorResponse> handleArticleNotFound(RessourceNotFoundExeption ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoRessourceExeption.class)
    public ResponseEntity<ErrorResponse> handleNoArticle(NoRessourceExeption ex) {
        return buildErrorResponse(ex, HttpStatus.NO_CONTENT);
    }
}
