package com.menekse.airlines.model.domain;

import com.menekse.airlines.model.enums.UserStatus;

public record User (
        Long id,
        String username,
        String password,
        String firstName,
        String lastName,
        City city,
        Role role,
        UserStatus status
){
    public User {
        if (status == null) {
            status = UserStatus.ACTIVE;
        }
    }
}
