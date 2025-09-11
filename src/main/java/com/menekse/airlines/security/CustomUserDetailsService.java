package com.menekse.airlines.security;

import com.menekse.airlines.exception.UserNotFoundException;
import com.menekse.airlines.model.entity.UserEntity;
import com.menekse.airlines.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        String roleWithPrefix = "ROLE_" + user.getRole().getName();

        return new CustomUserDetails(
                user,
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(roleWithPrefix))
        );
    }
}