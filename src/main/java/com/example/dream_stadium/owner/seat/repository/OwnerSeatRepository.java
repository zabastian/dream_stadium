package com.example.dream_stadium.owner.seat.repository;

import com.example.dream_stadium.owner.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerSeatRepository extends JpaRepository<Seat, Long> {
}
