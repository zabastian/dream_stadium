package com.example.dream_stadium.customer.reservation.repository;

import com.example.dream_stadium.customer.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
