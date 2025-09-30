package com.example.dream_stadium.global.exception;


import org.springframework.http.HttpStatus;

public enum ErrorCode {
    USER_NOT_FOUND("해당 유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND, 1),
    LOGIN_FAILED("로그인에 실패했습니다.", HttpStatus.UNAUTHORIZED, 2),
    UNAUTHORIZED_USER("로그인 권한 인증에 실패했습니다.", HttpStatus.UNAUTHORIZED, 3),
    COUPON_NOT_FOUND("쿠폰을 찾는데 실패했습니다.", HttpStatus.UNAUTHORIZED, 4),
    COUPON_NOT_DOWNLOADED("쿠폰이 다운되지 않았습니다.", HttpStatus.UNAUTHORIZED, 5),
    COUPON_ALREADY_USED("쿠폰이 이미 사용되었습니다..", HttpStatus.UNAUTHORIZED, 6),
    MATCH_NOT_FOUND("경기를 찾는데 실패했습니다.", HttpStatus.UNAUTHORIZED, 7),
    MATCH_COUPON_NOT_FOUND("경기쿠폰을 찾는데 실패했습니다.", HttpStatus.UNAUTHORIZED, 8),
    SEAT_NOT_FOUND("좌석을 찾는데 실패했습니다.", HttpStatus.UNAUTHORIZED, 9),
    MATCH_SEAT_NOT_FOUND("경기좌성을 찾는데 실패했습니다.", HttpStatus.UNAUTHORIZED, 10),
    USER_COUPON_NOT_FOUND("유저쿠폰을 찾는데 실패했습니다.", HttpStatus.UNAUTHORIZED, 11),
    RESERVATION_NOT_FOUND("예약을 찾는데 실패했습니다.", HttpStatus.UNAUTHORIZED, 12),
    TEAM_NOT_FOUND("팀을 찾는데 실패했습니다.", HttpStatus.UNAUTHORIZED, 13),
    STADIUM_NOT_FOUND("경기장을 찾는데 실패했습니다.", HttpStatus.UNAUTHORIZED, 14),
    TOKEN_IS_EXPIRED("토큰이 이미 만료되었습니다..", HttpStatus.UNAUTHORIZED, 15);

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
