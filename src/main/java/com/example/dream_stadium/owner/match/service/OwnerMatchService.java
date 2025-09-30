package com.example.dream_stadium.owner.match.service;

import com.example.dream_stadium.common.auth.repository.AuthRepository;
import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.global.exception.BaseException;
import com.example.dream_stadium.global.exception.ErrorCode;
import com.example.dream_stadium.owner.match.dto.OwnerMatchRequestDto;
import com.example.dream_stadium.owner.match.dto.OwnerMatchResponseDto;
import com.example.dream_stadium.owner.match.entity.Match;
import com.example.dream_stadium.owner.match.repository.OwnerMatchRepository;
import com.example.dream_stadium.owner.match_seat.repository.OwnerMatchSeatRepository;
import com.example.dream_stadium.owner.stadium.entity.Stadium;
import com.example.dream_stadium.owner.stadium.repository.OwnerStadiumRepository;
import com.example.dream_stadium.owner.team.entity.Team;
import com.example.dream_stadium.owner.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerMatchService {

    private final AuthRepository authRepository;
    private final OwnerMatchRepository ownerMatchRepository;
    private final TeamRepository teamRepository;
    private final OwnerStadiumRepository ownerStadiumRepository;
    private final OwnerMatchSeatRepository ownerMatchSeatRepository;

    @Transactional
    public OwnerMatchResponseDto createMatch(Long id, OwnerMatchRequestDto ownerMatchRequestDto){
       User user = authRepository.findById(id)
               .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

       Team homeTeam = teamRepository.findById(ownerMatchRequestDto.getHomeTeamId())
               .orElseThrow(()->new BaseException(ErrorCode.TEAM_NOT_FOUND));

       Team awayTeam = teamRepository.findById(ownerMatchRequestDto.getAwayTeamId())
                .orElseThrow(()->new BaseException(ErrorCode.TEAM_NOT_FOUND));

       Stadium stadium = ownerStadiumRepository.findById(ownerMatchRequestDto.getStadiumId())
               .orElseThrow(()-> new BaseException(ErrorCode.STADIUM_NOT_FOUND));



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

    @Transactional
    public void deleteMatch(Long id, OwnerMatchRequestDto ownerMatchRequestDto) {
        User user = authRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        Match match = ownerMatchRepository.findById(ownerMatchRequestDto.getId())
                .orElseThrow(()-> new BaseException(ErrorCode.MATCH_NOT_FOUND));

        if (!match.getUser().getId().equals(user.getId())) {
            throw new BaseException(ErrorCode.UNAUTHORIZED_USER);
        }

        ownerMatchSeatRepository.deleteByMatchId(match.getId());
        ownerMatchRepository.delete(match);

    }

    @Transactional(readOnly = true)
    public List<OwnerMatchResponseDto> matchList() {
        List<Match> matches = ownerMatchRepository.findAll();

        return matches
                .stream()
//                .map(OwnerMatchResponseDto::from)
                .map(match -> OwnerMatchResponseDto.from(match))
                //이렇게 생각하자 기존에는 match -> new OwnerMatchResponseDto(getter속성들) 이렇게 들어가는게 match의 속성들을 response에 할당하는 작어이니,
                // 지금 이 로직도 어떻게 보면 response에 할당하는 작업을 하기위해 전달해주는 로직이라 생각하자 어쨋든 .from(match)처럼 match를 전달해야 하니 저렇게 전달된게 최종 new로 만드는 생성자와 같다.
                // 메서드 참조(::)는 클래스의 메서드명이나 생성자(new) 만 올 수 있습니다.
                .toList();

    }
}
