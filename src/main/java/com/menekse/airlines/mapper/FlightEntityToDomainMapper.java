package com.menekse.airlines.mapper;

import com.menekse.airlines.common.BaseMapper;
import com.menekse.airlines.model.domain.Flight;
import com.menekse.airlines.model.entity.FlightEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FlightEntityToDomainMapper extends BaseMapper<Flight, FlightEntity> {

    FlightEntityToDomainMapper INSTANCE = Mappers.getMapper(FlightEntityToDomainMapper.class);

    @Override
    List<Flight> map(List<FlightEntity> source);
}
