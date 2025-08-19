package com.example.dream_stadium.owner.team.service;

import com.example.dream_stadium.common.auth.repository.AuthRepository;
import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.global.exception.BaseException;
import com.example.dream_stadium.global.exception.ErrorCode;
import com.example.dream_stadium.global.spring_security.CustomUserPrincipal;
import com.example.dream_stadium.owner.team.dto.OwnerTeamRequestDto;
import com.example.dream_stadium.owner.team.dto.OwnerTeamResponseDto;
import com.example.dream_stadium.owner.team.entity.Team;
import com.example.dream_stadium.owner.team.repository.TeamRepository;
import com.example.dream_stadium.owner.user.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final AuthRepository authRepository;

    public void createTeam(Long userId, OwnerTeamRequestDto ownerTeamRequestDto) {
        User user = authRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

       Team team = new Team(ownerTeamRequestDto.getName(), user);

       teamRepository.save(team);
    }

    public OwnerTeamResponseDto patchTeam(Long userId ,Long teamId, OwnerTeamRequestDto ownerTeamRequestDto) {
       User user = authRepository.findById(userId)
               .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

       Team team = teamRepository.findById(teamId).
                orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        if(!team.getUser().getId().equals(user.getId())) { // 해당 팀을 만든유저(team의 userid)가 저장된 userId와 같아야 함, 그래야 만든사람이 수정이 가능
            throw new BaseException(ErrorCode.USER_NOT_FOUND);
        }

        if (team.getName().equals(ownerTeamRequestDto.getName())) {
            throw new BaseException(ErrorCode.USER_NOT_FOUND);
        } // 이름 변경이 같으면 안됨.

        team.setName(ownerTeamRequestDto.getName());
        teamRepository.save(team);

        return new OwnerTeamResponseDto(user.getId(), team.getId(), team.getName());

    }

    public List<OwnerTeamResponseDto> listTeam() {
        List<Team> teams = teamRepository.findAll();

        return teams.stream()
                .map(team -> new OwnerTeamResponseDto(team.getUser().getId(), team.getId(), team.getName()))
                .toList();
//        List<OwnerTeamResponseDto> xteamsss = new ArrayList<>();
//
//        for(Team team : teams) {
//            xteamsss.add(new OwnerTeamResponseDto(team.getUser().getId(),team.getId(), team.getName()));
//            return xteamsss;
//        }
//        return xteamsss;
    }

    @Transactional
    public void deleteTeam(Long userId, OwnerTeamRequestDto ownerTeamRequestDto) {
        User user = authRepository.findById(userId)
                .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));

        Team team = teamRepository.findByName(ownerTeamRequestDto.getName())
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        if(!user.getId().equals(team.getUser().getId())) {
            throw new BaseException(ErrorCode.USER_NOT_FOUND);
        }

        teamRepository.delete(team);
    }
}
