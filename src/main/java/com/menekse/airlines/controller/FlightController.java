package com.menekse.airlines.controller;


import com.menekse.airlines.common.response.BaseResponse;
import com.menekse.airlines.controller.request.CreateFlightRequest;
import com.menekse.airlines.controller.request.FlightSearchRequest;
import com.menekse.airlines.model.domain.Flight;
import com.menekse.airlines.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FlightController {

    private final FlightService flightService;


    @GetMapping("/flights")
    @PreAuthorize("isAuthenticated()")
    public BaseResponse<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return BaseResponse.successOf(flights);
    }

    @GetMapping("/flights/city/{cityId}")
    @PreAuthorize("isAuthenticated()")
    public BaseResponse<List<Flight>> getFlightsByCity(@PathVariable Long cityId) {
        List<Flight> flights = flightService.getFlightsByCity(cityId);
        return BaseResponse.successOf(flights);
    }

    @GetMapping("/flights/departure/{cityId}")
    @PreAuthorize("isAuthenticated()")
    public BaseResponse<List<Flight>> getFlightsByDepartureCity(@PathVariable Long cityId) {
        List<Flight> flights = flightService.getFlightsByDepartureCity(cityId);
        return BaseResponse.successOf(flights);
    }

    @GetMapping("/search")
    @PreAuthorize("isAuthenticated()")
    public BaseResponse<List<Flight>> searchFlights(
            @RequestParam(required = false) Long departureCityId,
            @RequestParam(required = false) Long arrivalCityId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate flightDate) {

        FlightSearchRequest request = new FlightSearchRequest(departureCityId, arrivalCityId, flightDate);
        List<Flight> flights = flightService.searchFlights(request);
        return BaseResponse.successOf(flights);
    }

    @PostMapping("/admin/flight")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Flight> createFlight(@RequestBody final CreateFlightRequest request) {
        Flight flight = flightService.createFlight(request);
        return BaseResponse.successOf(flight);
    }

}
