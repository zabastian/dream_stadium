package com.example.dream_stadium.global.exception;


import org.springframework.http.HttpStatus;

public enum ErrorCode {
    USER_NOT_FOUND("해당 유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND, 1),
    LOGIN_FAILED("로그인에 실패했습니다.", HttpStatus.UNAUTHORIZED, 2);

    private final String message;
    private final HttpStatus httpStatus;
    private final Integer ErrorNumber;

    ErrorCode(String message, HttpStatus httpStatus, Integer ErrorNumber) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.ErrorNumber = ErrorNumber;
    }

    public String getMessage(){
        return this.message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Integer ErrorNumber() { return ErrorNumber; }
}
