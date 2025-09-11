package com.menekse.airlines.controller;


import com.menekse.airlines.common.response.BaseResponse;
import com.menekse.airlines.controller.request.LoginRequest;
import com.menekse.airlines.controller.request.SignupRequest;
import com.menekse.airlines.controller.response.AuthenticationResponse;
import com.menekse.airlines.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public BaseResponse<AuthenticationResponse> register(
            @Valid @RequestBody final SignupRequest signupRequest
    ) {
        AuthenticationResponse response = authService.register(signupRequest);
        return BaseResponse.successOf(response);
    }

    @PostMapping("/login")
    public BaseResponse<AuthenticationResponse> login(
            @Valid @RequestBody final LoginRequest loginRequest
    ) {
        AuthenticationResponse authenticationResponse = authService.login(loginRequest);
        return BaseResponse.successOf(authenticationResponse);
    }

}
