package com.example.semana8.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(-1)
public class CustomExceptionHandler {

    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public ResponseEntity<Object> handleErrorCustomException(ExceptionNotFoundEntity e) {
        System.out.println("Exception handled in CustomExceptionHandler");
        System.out.println(e.getMessage());
        CustomErrorResponse errorResponse = new CustomErrorResponse("500", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }
}
