package com.menekse.airlines.service;

import com.menekse.airlines.controller.request.LoginRequest;
import com.menekse.airlines.controller.request.SignupRequest;
import com.menekse.airlines.controller.response.UserCreateResponse;

public interface AuthService {

    UserCreateResponse register(SignupRequest signupRequest);

    void login(LoginRequest loginRequest);

}