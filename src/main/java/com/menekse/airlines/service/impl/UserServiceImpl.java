package com.menekse.airlines.service.impl;

import com.menekse.airlines.exception.UserNotFoundException;
import com.menekse.airlines.mapper.UserEntityToUserMapper;
import com.menekse.airlines.model.domain.User;
import com.menekse.airlines.model.entity.UserEntity;
import com.menekse.airlines.repository.UserRepository;
import com.menekse.airlines.security.CustomUserDetails;
import com.menekse.airlines.security.JwtUtils;
import com.menekse.airlines.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityToUserMapper userEntityToUserMapper = UserEntityToUserMapper.INSTANCE;
    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        try {
            CustomUserDetails userDetails = JwtUtils.getCurrentUserDetails();
            UserEntity user = userRepository.findById(userDetails.getUserId())
                    .orElseThrow(UserNotFoundException::new);

            return userEntityToUserMapper.map(user);
        } catch (SecurityException e) {
            return null;
        }
    }
}
