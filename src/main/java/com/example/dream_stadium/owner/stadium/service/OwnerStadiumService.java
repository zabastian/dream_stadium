package com.example.dream_stadium.owner.stadium.service;

import com.example.dream_stadium.common.auth.repository.AuthRepository;
import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.global.exception.BaseException;
import com.example.dream_stadium.global.exception.ErrorCode;
import com.example.dream_stadium.owner.stadium.dto.OwnerStadiumRequestDto;
import com.example.dream_stadium.owner.stadium.dto.OwnerStadiumResponseDto;
import com.example.dream_stadium.owner.stadium.entity.Stadium;
import com.example.dream_stadium.owner.stadium.repository.OwnerStadiumRepository;
import com.example.dream_stadium.owner.team.dto.OwnerTeamResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerStadiumService {

    private final AuthRepository authRepository;
    private final OwnerStadiumRepository ownerStadiumRepository;

    public void createStadium (Long id, OwnerStadiumRequestDto ownerStadiumRequestDto) {
        User user = authRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        Stadium stadium = new Stadium(ownerStadiumRequestDto.getName(), user);

        ownerStadiumRepository.save(stadium);

    }

    public List<OwnerStadiumResponseDto> stadiumList() {

        List<Stadium> listStadium = ownerStadiumRepository.findAll();

        return listStadium
                .stream()
                .map(stadium -> new OwnerStadiumResponseDto(stadium.getId(), stadium.getName()))
                .toList();

    }

    public void deleteStadium(Long id, Long stadiumId) {
        User user = authRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        Stadium stadium = ownerStadiumRepository.findById(stadiumId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        if(!user.getId().equals(stadium.getUser().getId())) {
            throw new BaseException(ErrorCode.USER_NOT_FOUND);
        }

        ownerStadiumRepository.delete(stadium);

    }

}
