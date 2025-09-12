package com.menekse.airlines.controller.response;

import com.menekse.airlines.model.domain.City;
import com.menekse.airlines.model.enums.UserStatus;
import lombok.Builder;

import java.util.List;

@Builder
public record AuthenticationResponse(
        Long id,
        String username,
        List<String> roles,
        String firstName,
        String lastName,
        UserStatus status,
        City city,
        String accessToken
) {
}
