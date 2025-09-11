package com.menekse.airlines.service;

import com.menekse.airlines.controller.request.CreateFlightRequest;
import com.menekse.airlines.model.domain.Flight;

public interface FlightService {

    Flight createFlight(CreateFlightRequest request);
}
