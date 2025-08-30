package com.example.dream_stadium.global.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    public final String error;
    public final String message;
    private final int status;
    private final int errorNumber;
    public final LocalDateTime time;

    public ErrorResponse (String error, String message, int status, int errorNumber, LocalDateTime time) {
        this.error = error;
        this.message = message;
        this.status = status;
        this.errorNumber = errorNumber;
        this.time = time;
    }
}
