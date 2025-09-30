package com.example.dream_stadium.owner.match_seat.controller;

import com.example.dream_stadium.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium.owner.match_seat.dto.OwnerMatchSeatRequestDto;
import com.example.dream_stadium.owner.match_seat.dto.OwnerMatchSeatResponseDto;
import com.example.dream_stadium.owner.match_seat.service.OwnerMatchSeatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerMatchSeatController {

    private final OwnerMatchSeatService ownerMatchSeatService;

    @PostMapping("/createMatchSeat")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<OwnerMatchSeatResponseDto> createdMatchSeat(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @Valid @RequestBody OwnerMatchSeatRequestDto ownerMatchSeatRequestDto
            ) {

        OwnerMatchSeatResponseDto ownerMatchSeatResponseDto = ownerMatchSeatService.createMatchSeat(customUserPrincipal.getUserId(), ownerMatchSeatRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(ownerMatchSeatResponseDto);

    }

    @GetMapping("/listMatchSeat")
    public ResponseEntity<List<OwnerMatchSeatResponseDto>> listMatchSeat() {
        List<OwnerMatchSeatResponseDto> ownerMatchSeatResponseDto = ownerMatchSeatService.matchSeatList();
        return ResponseEntity.ok(ownerMatchSeatResponseDto);
    }

    @DeleteMapping("/deleteMatchSeat/{matchSeatId}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Void> deletedMatchSeat(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @PathVariable Long matchSeatId
    ) {
        ownerMatchSeatService.deleteMatchSeat(customUserPrincipal.getUserId(), matchSeatId);
        return ResponseEntity.noContent().build();

    }
}
