package com.menekse.airlines.service.impl;

import com.menekse.airlines.controller.request.LoginRequest;
import com.menekse.airlines.controller.request.SignupRequest;
import com.menekse.airlines.controller.response.AuthenticationResponse;
import com.menekse.airlines.exception.AuthenticationFailedException;
import com.menekse.airlines.exception.CityNotFoundException;
import com.menekse.airlines.exception.RoleNotFoundException;
import com.menekse.airlines.exception.UserNameAlreadyExistException;
import com.menekse.airlines.mapper.UserEntityToUserMapper;
import com.menekse.airlines.model.domain.User;
import com.menekse.airlines.model.entity.CityEntity;
import com.menekse.airlines.model.entity.RoleEntity;
import com.menekse.airlines.model.entity.UserEntity;
import com.menekse.airlines.model.enums.UserStatus;
import com.menekse.airlines.repository.CityRepository;
import com.menekse.airlines.repository.RoleRepository;
import com.menekse.airlines.repository.UserRepository;
import com.menekse.airlines.security.CustomUserDetails;
import com.menekse.airlines.service.AuthService;
import com.menekse.airlines.security.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserEntityToUserMapper userEntityToUserMapper = UserEntityToUserMapper.INSTANCE;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CityRepository cityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    @Transactional
    public AuthenticationResponse register(SignupRequest signupRequest) {
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

        return AuthenticationResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .roles(List.of(savedUser.getRole().getName()))
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .status(savedUser.getStatus())
                .build();
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        UserEntity userEntity = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(AuthenticationFailedException::new);

        if (!passwordEncoder.matches(loginRequest.password(), userEntity.getPassword())) {
            throw new AuthenticationFailedException();
        }

        CustomUserDetails userDetails = new CustomUserDetails(
                userEntity,
                userEntity.getPassword(),
                List.of(new SimpleGrantedAuthority(userEntity.getRole().getName()))
        );

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        String token = jwtUtils.generateJwtToken(authentication);

        User user = userEntityToUserMapper.map(userEntity);
        return AuthenticationResponse.builder()
                .id(user.id())
                .username(user.username())
                .roles(List.of(user.role().name()))
                .firstName(user.firstName())
                .lastName(user.lastName())
                .status(user.status())
                .city(user.city())
                .accessToken(token)
                .build();
    }
}