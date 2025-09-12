package com.menekse.airlines.service;

import com.menekse.airlines.controller.request.CreateFlightRequest;
import com.menekse.airlines.controller.request.FlightSearchRequest;
import com.menekse.airlines.model.domain.Flight;

import java.util.List;

public interface FlightService {

    List<Flight> getAllFlights();

    List<Flight> getFlightsByCity(Long cityId);

    List<Flight> getFlightsByDepartureCity(Long cityId);

    List<Flight> searchFlights(FlightSearchRequest request);

    Flight createFlight(CreateFlightRequest request);
}
