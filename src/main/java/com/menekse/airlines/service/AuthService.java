package com.menekse.airlines.service;

import com.menekse.airlines.controller.request.LoginRequest;
import com.menekse.airlines.controller.request.SignupRequest;
import com.menekse.airlines.controller.response.AuthenticationResponse;

public interface AuthService {

    AuthenticationResponse register(SignupRequest signupRequest);

    AuthenticationResponse login(LoginRequest loginRequest);

}