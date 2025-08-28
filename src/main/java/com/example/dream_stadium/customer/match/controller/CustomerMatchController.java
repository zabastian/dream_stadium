package com.example.dream_stadium.customer.match.controller;

import com.example.dream_stadium.customer.match.service.CustomerMatchService;
import com.example.dream_stadium.owner.match.dto.OwnerMatchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerMatchController {

    private final CustomerMatchService customerMatchService;

    @GetMapping("/getMatch")
    public ResponseEntity<List<OwnerMatchResponseDto>> listMatch() {
        List<OwnerMatchResponseDto> ownerMatchResponseDto = customerMatchService.matchList();
        return ResponseEntity.ok(ownerMatchResponseDto);
    }

    @GetMapping("/getMatch/{matchId}")
    public ResponseEntity<OwnerMatchResponseDto> getMatch(
            @PathVariable Long matchId
    ) {
        OwnerMatchResponseDto ownerMatchResponseDto = customerMatchService.matchGet(matchId);
        return ResponseEntity.ok(ownerMatchResponseDto);

    }

}
