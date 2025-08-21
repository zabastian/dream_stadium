package com.example.dream_stadium.owner.match.service;

import com.example.dream_stadium.common.auth.repository.AuthRepository;
import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.global.exception.BaseException;
import com.example.dream_stadium.global.exception.ErrorCode;
import com.example.dream_stadium.owner.match.dto.OwnerMatchRequestDto;
import com.example.dream_stadium.owner.match.dto.OwnerMatchResponseDto;
import com.example.dream_stadium.owner.match.entity.Match;
import com.example.dream_stadium.owner.match.repository.OwnerMatchRepository;
import com.example.dream_stadium.owner.stadium.entity.Stadium;
import com.example.dream_stadium.owner.stadium.repository.OwnerStadiumRepository;
import com.example.dream_stadium.owner.team.entity.Team;
import com.example.dream_stadium.owner.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerMatchService {

    private final AuthRepository authRepository;
    private final OwnerMatchRepository ownerMatchRepository;
    private final TeamRepository teamRepository;
    private final OwnerStadiumRepository ownerStadiumRepository;

    public OwnerMatchResponseDto createMatch(Long id, OwnerMatchRequestDto ownerMatchRequestDto){
       User user = authRepository.findById(id)
               .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

       Team homeTeam = teamRepository.findById(ownerMatchRequestDto.getHomeTeamId())
               .orElseThrow(()->new BaseException(ErrorCode.USER_NOT_FOUND));

       Team awayTeam = teamRepository.findById(ownerMatchRequestDto.getAwayTeamId())
                .orElseThrow(()->new BaseException(ErrorCode.USER_NOT_FOUND));

       Stadium stadium = ownerStadiumRepository.findById(ownerMatchRequestDto.getStadiumId())
               .orElseThrow(()-> new BaseException(ErrorCode.USER_NOT_FOUND));



 /*      Match match = new Match(
               null,
               ownerMatchRequestDto.getName(),
               ownerMatchRequestDto.getCost(),
               ownerMatchRequestDto.getMatchDate(),
               homeTeam,
               awayTeam,
               stadium,
               user
               );*/

       Match match = Match.toEntity(
               ownerMatchRequestDto.getName(),
               ownerMatchRequestDto.getCost(),
               ownerMatchRequestDto.getMatchDate(),
               homeTeam,
               awayTeam,
               stadium,
               user
       );

       Match savedMatch = ownerMatchRepository.save(match);

     /*   return new OwnerMatchResponseDto(
                savedMatch.getId(),
                savedMatch.getName(),
                savedMatch.getCost(),
                savedMatch.getMatchDate(),
                savedMatch.getHomeTeam().getId(),
                savedMatch.getAwayTeam().getId(),
                savedMatch.getStadium().getId()
        );*/
        return OwnerMatchResponseDto.from(savedMatch);
    }
}
