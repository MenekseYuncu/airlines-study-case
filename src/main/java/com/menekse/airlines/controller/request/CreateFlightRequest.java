package com.menekse.airlines.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateFlightRequest(

        @NotNull
        @Size(min = 3, max = 10)
        String flightNumber,

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
