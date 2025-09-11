package com.menekse.airlines.controller.response;

import com.menekse.airlines.model.enums.UserStatus;
import lombok.Builder;

import java.util.List;

@Builder
public record UserCreateResponse(
        Long id,
        String username,
        List<String> roles,
        String firstName,
        String lastName,
        UserStatus status
) {
}
