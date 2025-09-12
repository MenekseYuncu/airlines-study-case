package com.menekse.airlines.controller.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateFlightRequest(

        @NotNull
        Long departureCityId,

        @NotNull
        Long arrivalCityId,

        @NotNull
        LocalDateTime departureTime,

        @NotNull
        LocalDateTime arrivalTime
) {
}
