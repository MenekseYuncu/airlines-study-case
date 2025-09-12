package com.menekse.airlines.controller;

import com.menekse.airlines.common.response.BaseResponse;
import com.menekse.airlines.model.domain.User;
import com.menekse.airlines.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/user/me")
    @PreAuthorize("isAuthenticated()")
    public BaseResponse<User> getCurrentUser() {
        User user = userService.getCurrentUser();
        return BaseResponse.successOf(user);
    }
}
