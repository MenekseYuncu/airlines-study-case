package com.menekse.airlines.service.impl;

import com.menekse.airlines.mapper.CityEntityToCityMapper;
import com.menekse.airlines.model.domain.City;
import com.menekse.airlines.model.entity.CityEntity;
import com.menekse.airlines.repository.CityRepository;
import com.menekse.airlines.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityEntityToCityMapper cityEntityToCityMapper = CityEntityToCityMapper.INSTANCE;
    private final CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        List<CityEntity> cityEntities = cityRepository.findAll();
        return cityEntityToCityMapper.map(cityEntities);
    }
}
