package com.example.dream_stadium.owner.match.controller;

import com.example.dream_stadium.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium.owner.match.dto.OwnerMatchRequestDto;
import com.example.dream_stadium.owner.match.dto.OwnerMatchResponseDto;
import com.example.dream_stadium.owner.match.service.OwnerMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerMatchController {

    private final OwnerMatchService ownerMatchService;

    @PostMapping("/createMatch")
    public ResponseEntity<OwnerMatchResponseDto> createdMatch(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @RequestBody OwnerMatchRequestDto ownerMatchRequestDto
            ) {

        OwnerMatchResponseDto ownerMatchResponseDto = ownerMatchService.createMatch(customUserPrincipal.getUserId(), ownerMatchRequestDto);
        return ResponseEntity.ok(ownerMatchResponseDto);

    }
}
