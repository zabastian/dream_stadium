package com.example.dream_stadium.owner.match.controller;

import com.example.dream_stadium.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium.owner.match.dto.OwnerMatchRequestDto;
import com.example.dream_stadium.owner.match.dto.OwnerMatchResponseDto;
import com.example.dream_stadium.owner.match.service.OwnerMatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<OwnerMatchResponseDto> createdMatch(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @Valid @RequestBody OwnerMatchRequestDto ownerMatchRequestDto
            ) {

        OwnerMatchResponseDto ownerMatchResponseDto = ownerMatchService.createMatch(customUserPrincipal.getUserId(), ownerMatchRequestDto);
        return ResponseEntity.ok(ownerMatchResponseDto);

    }
}
