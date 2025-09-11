package com.menekse.airlines.controller;

import com.menekse.airlines.common.response.BaseResponse;
import com.menekse.airlines.model.domain.City;
import com.menekse.airlines.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CityController {

    private final CityService cityService;

    @GetMapping("/city")
    public BaseResponse<List<City>> cities(
    ) {
        List<City> response = cityService.getAllCities();
        return BaseResponse.successOf(response);
    }
}
