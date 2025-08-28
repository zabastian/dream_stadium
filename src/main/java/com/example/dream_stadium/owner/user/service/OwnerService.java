package com.example.dream_stadium.owner.user.service;

import com.example.dream_stadium.common.auth.repository.AuthRepository;
import com.example.dream_stadium.common.user.dto.UserResponse;
import com.example.dream_stadium.common.user.entity.User;
import com.example.dream_stadium.common.user.entity.UserRole;
import com.example.dream_stadium.global.exception.BaseException;
import com.example.dream_stadium.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final AuthRepository authRepository;

    @Transactional(readOnly = true)
    public UserResponse getUserInfo(Long userId) {
        User user = authRepository.findById(userId).
                orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        return new UserResponse(user.getNickname(), user.getUserRole());
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUserList() {
        List<User> users = authRepository.findAll();

        return users.stream()
                .map(userLists -> new UserResponse(userLists.getNickname(), userLists.getUserRole()) )
                .toList();
    }

}

/*    public static void userRoleInfo(UserRole userRole) {
        if (userRole != UserRole.OWNER) {
            throw new BaseException(ErrorCode.USER_NOT_FOUND);
        }
    }*/

//            if(userRole != UserRole.OWNER) {
//                throw new BaseException(ErrorCode.USER_NOT_FOUND);
//            }
