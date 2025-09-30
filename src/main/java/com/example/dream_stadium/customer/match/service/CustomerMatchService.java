package com.example.dream_stadium.customer.match.service;

import com.example.dream_stadium.common.user.entity.BaseEntity;
import com.example.dream_stadium.global.exception.BaseException;
import com.example.dream_stadium.global.exception.ErrorCode;
import com.example.dream_stadium.owner.match.dto.OwnerMatchResponseDto;
import com.example.dream_stadium.owner.match.entity.Match;
import com.example.dream_stadium.owner.match.repository.OwnerMatchRepository;
import com.example.dream_stadium.owner.match_seat.dto.OwnerMatchSeatResponseDto;
import com.example.dream_stadium.owner.match_seat.entity.MatchSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerMatchService {

    private final OwnerMatchRepository ownerMatchRepository;

    @Transactional(readOnly = true)
    public List<OwnerMatchResponseDto> matchList() {
        List<Match> matches = ownerMatchRepository.findAll();

        return matches
                .stream()
                .map(OwnerMatchResponseDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public OwnerMatchResponseDto matchGet(Long id) {
        Match match = ownerMatchRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.MATCH_NOT_FOUND));

        return OwnerMatchResponseDto.from(match);

    }

}
