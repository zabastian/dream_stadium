package com.example.dream_stadium.owner.match_seat.service;

import com.example.dream_stadium.common.auth.repository.AuthRepository;
import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.global.exception.BaseException;
import com.example.dream_stadium.global.exception.ErrorCode;
import com.example.dream_stadium.owner.match.entity.Match;
import com.example.dream_stadium.owner.match.repository.OwnerMatchRepository;
import com.example.dream_stadium.owner.match_seat.dto.OwnerMatchSeatRequestDto;
import com.example.dream_stadium.owner.match_seat.dto.OwnerMatchSeatResponseDto;
import com.example.dream_stadium.owner.match_seat.entity.MatchSeat;
import com.example.dream_stadium.owner.match_seat.repository.OwnerMatchSeatRepository;
import com.example.dream_stadium.owner.seat.entity.Seat;
import com.example.dream_stadium.owner.seat.repository.OwnerSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerMatchSeatService {

    private final AuthRepository authRepository;
    private final OwnerSeatRepository ownerSeatRepository;
    private final OwnerMatchRepository ownerMatchRepository;
    private final OwnerMatchSeatRepository ownerMatchSeatRepository;

    @Transactional
    public OwnerMatchSeatResponseDto createMatchSeat(Long userId, OwnerMatchSeatRequestDto ownerMatchSeatRequestDto) {
        User user = authRepository.findById(userId)
                .orElseThrow(()->new BaseException(ErrorCode.USER_NOT_FOUND));

        Seat seat = ownerSeatRepository.findById(ownerMatchSeatRequestDto.getSeatId())
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        Match match = ownerMatchRepository.findById(ownerMatchSeatRequestDto.getMatchId())
                .orElseThrow(() -> new RuntimeException("Match not found"));

        if(!match.getUser().getId().equals(user.getId())) {
            throw new BaseException(ErrorCode.USER_NOT_FOUND);
        }

        MatchSeat matchSeat = MatchSeat.from(
                ownerMatchSeatRequestDto.getCapacity(),
                ownerMatchSeatRequestDto.getMatchSeatRole(),
                seat,
                match
        );

        match.getMatchSeats().add(matchSeat);
        seat.getMatchSeats().add(matchSeat);
//        matchSeat.setMatch(match); 팩토리메소드 통 match와 seat을 set해줌
//        matchSeat.setSeat(seat);

        MatchSeat matchSeat1 = ownerMatchSeatRepository.save(matchSeat);

        return OwnerMatchSeatResponseDto.to(matchSeat1);

    }

    @Transactional(readOnly = true)
    public List<OwnerMatchSeatResponseDto> matchSeatList() {
        List<MatchSeat> matchSeat = ownerMatchSeatRepository.findAll();

        return matchSeat
                .stream()
                .map(OwnerMatchSeatResponseDto::to)
               /* //여기서 헷갈리는 이유는 Match에 대한 값을 response객체에 넘겨주는 로직이 보이지 않기 때문이다. 항상 인식하자
                //.map(match -> OwnerMatchSeatResponseDto.to(match))
                //.map(match -> new OwnerMatchSeatResponse(match.getUsername,~~)
                //.map(match -> new OwnerMatchSeatResponse(match)
                //.map(OwnerMatchSeatResponseDto::new)*/
                .toList();
    }

    @Transactional
    public void deleteMatchSeat(Long userId, Long matchSeatId) {
        User user = authRepository.findById(userId)
                .orElseThrow(()->new BaseException(ErrorCode.USER_NOT_FOUND));

        MatchSeat matchSeat = ownerMatchSeatRepository.findById(matchSeatId)
                .orElseThrow(()->new BaseException(ErrorCode.USER_NOT_FOUND));

        if (!user.getId().equals(matchSeat.getMatch().getUser().getId())) {
            throw new BaseException(ErrorCode.USER_NOT_FOUND);
        }

        matchSeat.getMatch().getMatchSeats().remove(matchSeat);
        matchSeat.setMatch(null);

        matchSeat.getSeat().getMatchSeats().remove(matchSeat);
        matchSeat.setSeat(null);

        ownerMatchSeatRepository.delete(matchSeat);
    }
}
