package com.example.dream_stadium.global.alarm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationAlarmMessage {
    private Long reservationId;
    private String message;
}