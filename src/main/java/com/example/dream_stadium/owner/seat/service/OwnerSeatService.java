package com.example.dream_stadium.owner.seat.service;


import com.example.dream_stadium.global.exception.BaseException;
import com.example.dream_stadium.global.exception.ErrorCode;
import com.example.dream_stadium.owner.match_seat.repository.OwnerMatchSeatRepository;
import com.example.dream_stadium.owner.seat.dto.OwnerSeatResponseDto;
import com.example.dream_stadium.owner.seat.entity.Seat;
import com.example.dream_stadium.owner.seat.repository.OwnerSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerSeatService {

    private final OwnerSeatRepository ownerSeatRepository;
    private final OwnerMatchSeatRepository ownerMatchSeatRepository;

    @Transactional
    public void createSeats() {

        Seat seat = new Seat();

        ownerSeatRepository.save(seat);

    }

    @Transactional(readOnly = true)
    public List<OwnerSeatResponseDto> seatList() {
        List<Seat> seat = ownerSeatRepository.findAll();
        return seat
                .stream()
                .map(seats -> new OwnerSeatResponseDto(seats.getId()))
                .toList();

    }

    @Transactional
    public void deleteSeats(Long seatsId) {
        Seat seat = ownerSeatRepository.findById(seatsId)
                        .orElseThrow(()->new BaseException(ErrorCode.USER_NOT_FOUND));
        ownerMatchSeatRepository.deleteBySeatId(seat.getId());

        ownerSeatRepository.delete(seat);

    }
}
