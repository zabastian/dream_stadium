package com.example.dream_stadium.global.alarm;

import com.example.dream_stadium.customer.reservation.repository.ReservationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class ReservationAlarmConsumer {

    private final ReservationRepository reservationRepository;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitConfig.RESERVATION_ALARM_QUEUE)
    @Transactional
    public void handleReservationAlarm(String messageBody) {
        try {
            ReservationAlarmMessage alarmMessage =
                    objectMapper.readValue(messageBody, ReservationAlarmMessage.class);

                reservationRepository.findById(alarmMessage.getReservationId())
                        .ifPresent(reservation -> {
                            reservation.setAlarmSent(true);
                            reservationRepository.save(reservation);
                            System.out.println("예약 알람 처리 완료: reservationId=" + reservation.getId());
                            System.out.println("알람 메시지: " + alarmMessage.getMessage());
                        });

        } catch (JsonProcessingException e) {
            throw new RuntimeException("RabbitMQ 메시지 변환 실패", e);
        }
    }
}

