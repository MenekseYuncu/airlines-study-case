package com.menekse.airlines.service.impl;

import com.menekse.airlines.controller.request.LoginRequest;
import com.menekse.airlines.controller.request.SignupRequest;
import com.menekse.airlines.controller.response.UserCreateResponse;
import com.menekse.airlines.exception.AuthenticationFailedException;
import com.menekse.airlines.exception.CityNotFoundException;
import com.menekse.airlines.exception.RoleNotFoundException;
import com.menekse.airlines.exception.UserNameAlreadyExistException;
import com.menekse.airlines.model.entity.CityEntity;
import com.menekse.airlines.model.entity.RoleEntity;
import com.menekse.airlines.model.entity.UserEntity;
import com.menekse.airlines.model.enums.UserStatus;
import com.menekse.airlines.repository.CityRepository;
import com.menekse.airlines.repository.RoleRepository;
import com.menekse.airlines.repository.UserRepository;
import com.menekse.airlines.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CityRepository cityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserCreateResponse register(SignupRequest signupRequest) {
        if (userRepository.findByUsername(signupRequest.username()).isPresent()) {
            throw new UserNameAlreadyExistException();
        }

        RoleEntity userRole = roleRepository.findByName("USER")
                .orElseThrow(RoleNotFoundException::new);

        CityEntity city = cityRepository.findById(signupRequest.cityId())
                .orElseThrow(CityNotFoundException::new);

        UserEntity user = UserEntity.builder()
                .username(signupRequest.username())
                .password(passwordEncoder.encode(signupRequest.password()))
                .firstName(signupRequest.firstName())
                .lastName(signupRequest.lastName())
                .city(city)
                .role(userRole)
                .status(UserStatus.ACTIVE)
                .build();

        UserEntity savedUser = userRepository.save(user);

        return UserCreateResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .roles(List.of(savedUser.getRole().getName()))
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .status(savedUser.getStatus())
                .build();
    }

    @Override
    public void login(LoginRequest loginRequest) {
        String username = loginRequest.username();
        String password = loginRequest.password();

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(AuthenticationFailedException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationFailedException();
        }
    }
}