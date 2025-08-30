package com.example.dream_stadium.owner.stadium.controller;

import com.example.dream_stadium.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium.owner.stadium.dto.OwnerStadiumRequestDto;
import com.example.dream_stadium.owner.stadium.dto.OwnerStadiumResponseDto;
import com.example.dream_stadium.owner.stadium.service.OwnerStadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerStadiumController {

    private final OwnerStadiumService ownerStadiumService;

    @PostMapping("/createdStadium")
    public ResponseEntity<Void> createdStadium(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @RequestBody OwnerStadiumRequestDto ownerStadiumRequestDto
            ) {
        ownerStadiumService.createStadium(customUserPrincipal.getUserId(), ownerStadiumRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/listStadium")
    public ResponseEntity<List<OwnerStadiumResponseDto>> listStadium() {
        return ResponseEntity.ok(ownerStadiumService.stadiumList());
    }

    @DeleteMapping("/deleteStadium/{stadiumId}")
    public ResponseEntity<Void> deletedStadium(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @PathVariable Long stadiumId
    ) {
        ownerStadiumService.deleteStadium(customUserPrincipal.getUserId(), stadiumId);
        return ResponseEntity.noContent().build();
    }

}
