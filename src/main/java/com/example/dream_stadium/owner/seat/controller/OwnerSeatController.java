package com.example.dream_stadium.owner.seat.controller;

import com.example.dream_stadium.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium.owner.seat.dto.OwnerSeatRequestDto;
import com.example.dream_stadium.owner.seat.dto.OwnerSeatResponseDto;
import com.example.dream_stadium.owner.seat.service.OwnerSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerSeatController {

    private final OwnerSeatService ownerSeatService;

    @PostMapping("/createSeats")
    public ResponseEntity<Void> createdSeats() {
        ownerSeatService.createSeats();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getSeats")
    public ResponseEntity<List<OwnerSeatResponseDto>> ListSeats() {
        List<OwnerSeatResponseDto> ownerSeatResponseDto = ownerSeatService.seatList();
        return ResponseEntity.ok(ownerSeatResponseDto);

    }

    @DeleteMapping("/deleteSeats/{seatsId}")
    public ResponseEntity<Void> deletedSeats(@PathVariable Long seatsId) {
        ownerSeatService.deleteSeats(seatsId);
        return ResponseEntity.noContent().build();
    }
}
