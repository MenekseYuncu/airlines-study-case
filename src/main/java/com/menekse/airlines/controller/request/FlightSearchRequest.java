package com.menekse.airlines.controller.request;

import java.time.LocalDate;

public record FlightSearchRequest(
        Long departureCityId,
        Long arrivalCityId,
        LocalDate flightDate
) {
    public FlightSearchRequest {
        if (departureCityId != null && departureCityId.equals(arrivalCityId)) {
            throw new IllegalArgumentException("Departure and arrival cities cannot be the same");
        }
    }
}