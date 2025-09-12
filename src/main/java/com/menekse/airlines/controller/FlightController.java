package com.menekse.airlines.controller;


import com.menekse.airlines.common.response.BaseResponse;
import com.menekse.airlines.controller.request.CreateFlightRequest;
import com.menekse.airlines.controller.request.FlightSearchRequest;
import com.menekse.airlines.model.domain.Flight;
import com.menekse.airlines.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public BaseResponse<Page<Flight>> getAllFlights(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Flight> flights = flightService.getAllFlights(pageable);
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
    public BaseResponse<Page<Flight>> getFlightsByDepartureCity(
            @PathVariable Long cityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("departureTime").ascending());
        Page<Flight> flights = flightService.getFlightsByDepartureCity(cityId, pageable);
        return BaseResponse.successOf(flights);
    }

    @GetMapping("/flights/search")
    @PreAuthorize("isAuthenticated()")
    public BaseResponse<Page<Flight>> searchFlights(
            @RequestParam(required = false) Long departureCityId,
            @RequestParam(required = false) Long arrivalCityId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate flightDate,
            Pageable pageable) {
        FlightSearchRequest request = new FlightSearchRequest(departureCityId, arrivalCityId, flightDate);
        Page<Flight> flights = flightService.searchFlights(request, pageable);
        return BaseResponse.successOf(flights);
    }

    @PostMapping("/admin/flight")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Flight> createFlight(@Valid @RequestBody final CreateFlightRequest request) {
        Flight flight = flightService.createFlight(request);
        return BaseResponse.successOf(flight);
    }

}
