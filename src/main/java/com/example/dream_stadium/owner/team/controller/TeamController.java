package com.example.dream_stadium.owner.team.controller;

import com.example.dream_stadium.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium.owner.team.dto.OwnerTeamRequestDto;
import com.example.dream_stadium.owner.team.dto.OwnerTeamResponseDto;
import com.example.dream_stadium.owner.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/createTeam")
    public ResponseEntity<Void> createdTeam(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @RequestBody OwnerTeamRequestDto ownerTeamRequestDto) {
        teamService.createTeam(customUserPrincipal.getUserId(), ownerTeamRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/patchTeam/{teamId}")
    public ResponseEntity<OwnerTeamResponseDto> patchedTeam(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @PathVariable Long teamId,
            @RequestBody OwnerTeamRequestDto ownerTeamRequestDto
    ) {
        OwnerTeamResponseDto ownerTeamResponseDto = teamService.patchTeam(customUserPrincipal.getUserId(), teamId, ownerTeamRequestDto);
        return ResponseEntity.ok(ownerTeamResponseDto);
    }

    @GetMapping("/teamList")
    public ResponseEntity<List<OwnerTeamResponseDto>> TeamList() {
        List<OwnerTeamResponseDto> list = teamService.listTeam();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/deleteTeam")
    public ResponseEntity<Void> deletedTeam(
            @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
            @RequestBody OwnerTeamRequestDto ownerTeamRequestDto
    ) {
        teamService.deleteTeam(customUserPrincipal.getUserId(), ownerTeamRequestDto);
        return ResponseEntity.noContent().build();


    }
}
