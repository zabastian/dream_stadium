package com.example.dream_stadium.global.alarm;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    public static final String RESERVATION_ALARM_QUEUE = "reservation-alarm-queue";
    public static final String RESERVATION_ALARM_EXCHANGE = "reservation-alarm-exchange";

    @Bean
    public DirectExchange alarmExchange() {
        return new DirectExchange(RESERVATION_ALARM_EXCHANGE);
    }

    @Bean
    public Queue reservationAlarmQueue() {
        return new Queue(RESERVATION_ALARM_QUEUE, true);
    }

    @Bean
    public Binding binding(Queue reservationAlarmQueue, DirectExchange alarmExchange) {
        return BindingBuilder.bind(reservationAlarmQueue)
                .to(alarmExchange)
                .with(RESERVATION_ALARM_QUEUE);
    }
}