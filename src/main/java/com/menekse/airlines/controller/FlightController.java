package com.menekse.airlines.controller;


import com.menekse.airlines.common.response.BaseResponse;
import com.menekse.airlines.controller.request.CreateFlightRequest;
import com.menekse.airlines.model.domain.Flight;
import com.menekse.airlines.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class FlightController {

    private final FlightService flightService;

    @PostMapping("/flight")
    @PreAuthorize("hasAuthority('ADMIN')")
    public BaseResponse<Flight> createFlight(@RequestBody final CreateFlightRequest request) {
        Flight flight = flightService.createFlight(request);
        return BaseResponse.successOf(flight);
    }

}
