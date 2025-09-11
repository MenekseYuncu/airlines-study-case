package com.menekse.airlines.mapper;

import com.menekse.airlines.common.BaseMapper;
import com.menekse.airlines.model.domain.City;
import com.menekse.airlines.model.entity.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityEntityToCityMapper extends BaseMapper<City, CityEntity> {

    CityEntityToCityMapper INSTANCE = Mappers.getMapper(CityEntityToCityMapper.class);
}
