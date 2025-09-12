package com.menekse.airlines.service;

import com.menekse.airlines.controller.request.CreateFlightRequest;
import com.menekse.airlines.controller.request.FlightSearchRequest;
import com.menekse.airlines.model.domain.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlightService {

    Page<Flight> getAllFlights(Pageable pageable);

    List<Flight> getFlightsByCity(Long cityId);

    Page<Flight> getFlightsByDepartureCity(Long cityId, Pageable pageable);

    Page<Flight> searchFlights(FlightSearchRequest request,  Pageable pageable);

    Flight createFlight(CreateFlightRequest request);
}
