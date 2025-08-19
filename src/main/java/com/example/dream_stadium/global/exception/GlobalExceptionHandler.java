package com.example.dream_stadium.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getErrorCode().name(),
                e.getErrorCode().getMessage(),
                e.getErrorCode().getHttpStatus().value(),
                e.getErrorCode().ErrorNumber(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, e.getErrorCode().getHttpStatus());
    }
}
