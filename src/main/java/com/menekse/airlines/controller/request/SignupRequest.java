package com.menekse.airlines.controller.request;

import jakarta.validation.constraints.NotNull;

public record SignupRequest(
        @NotNull
        String username,
        @NotNull
        String password,
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        Long cityId
) {
}
