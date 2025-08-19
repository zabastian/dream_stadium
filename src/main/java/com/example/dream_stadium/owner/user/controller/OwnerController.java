package com.example.dream_stadium.owner.user.controller;

import com.example.dream_stadium.common.user.dto.UserResponse;
import com.example.dream_stadium.common.user.entity.UserRole;
import com.example.dream_stadium.owner.user.service.OwnerService;
import com.example.dream_stadium.global.spring_security.CustomUserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService userService;

    @GetMapping("/userInfo/{id}")
    public ResponseEntity<UserResponse> userInfo(@PathVariable Long id) { //owner라는 조건만 만족하면 조회가능 하게 만듬
        UserResponse userResponse = userService.getUserInfo(id);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/userLists")
    public ResponseEntity<List<UserResponse>> userList() {
        return ResponseEntity.ok(userService.getUserList());
    }
}
