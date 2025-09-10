package com.menekse.airlines.model.domain;

import com.menekse.airlines.model.enums.FlightStatus;

import java.time.LocalDateTime;

public record Flight(
        String id,
        String flightNumber,
        City departureCity,
        City arrivalCity,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        FlightStatus status
) {
    public Flight {
        if (status == null) {
            status = FlightStatus.SCHEDULED;
        }
    }
}
