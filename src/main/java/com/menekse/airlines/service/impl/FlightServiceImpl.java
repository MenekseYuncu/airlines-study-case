package com.menekse.airlines.service.impl;

import com.menekse.airlines.controller.request.CreateFlightRequest;
import com.menekse.airlines.controller.request.FlightSearchRequest;
import com.menekse.airlines.exception.ArrivalCityNotFoundException;
import com.menekse.airlines.exception.DepartureCityNotFoundException;
import com.menekse.airlines.exception.SlotAlreadyExistException;
import com.menekse.airlines.mapper.FlightEntityToDomainMapper;
import com.menekse.airlines.model.domain.Flight;
import com.menekse.airlines.model.entity.AirportSlotEntity;
import com.menekse.airlines.model.entity.CityEntity;
import com.menekse.airlines.model.entity.FlightEntity;
import com.menekse.airlines.model.enums.FlightStatus;
import com.menekse.airlines.model.enums.SlotType;
import com.menekse.airlines.repository.AirportSlotRepository;
import com.menekse.airlines.repository.CityRepository;
import com.menekse.airlines.repository.FlightRepository;
import com.menekse.airlines.security.JwtUtils;
import com.menekse.airlines.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private static final int SLOT_BUFFER_MINUTES = 30;

    private final FlightEntityToDomainMapper flightEntityToDomainMapper = FlightEntityToDomainMapper.INSTANCE;
    private final FlightRepository flightRepository;
    private final CityRepository cityRepository;
    private final AirportSlotRepository slotRepository;


    @Override
    public Page<Flight> getAllFlights(Pageable pageable) {
        Page<FlightEntity> flightEntities = flightRepository.findAllByOrderByDepartureTimeDesc(pageable);
        return flightEntities.map(flightEntityToDomainMapper::map);
    }

    @Override
    public List<Flight> getFlightsByCity(Long cityId) {
        List<FlightEntity> flightEntities = flightRepository.findByDepartureCityIdOrArrivalCityIdOrderByDepartureTimeAsc(
                cityId, cityId);
        return flightEntityToDomainMapper.map(flightEntities);
    }

    @Override
    public Page<Flight> getFlightsByDepartureCity(Long cityId, Pageable pageable) {
        Page<FlightEntity> flightEntities = flightRepository.findByDepartureCityIdAndDepartureTimeAfterOrderByDepartureTimeAsc(
                cityId,
                LocalDateTime.now(),
                pageable
        );
        return flightEntities.map(flightEntityToDomainMapper::map);
    }


    @Override
    public Page<Flight> searchFlights(FlightSearchRequest request, Pageable pageable) {
        if (request.departureCityId() == null &&
                request.arrivalCityId() == null &&
                request.flightDate() == null) {
            return Page.empty();
        }
        Page<FlightEntity> flightEntities = flightRepository.searchFlights(
                request.departureCityId(),
                request.arrivalCityId(),
                request.flightDate(),
                pageable
        );
        return flightEntities.map(flightEntityToDomainMapper::map);
    }


    @Override
    @Transactional
    public Flight createFlight(CreateFlightRequest request) {
        validateFlightRequest(request);

        CityEntity departureCity = cityRepository.findById(request.departureCityId())
                .orElseThrow(DepartureCityNotFoundException::new);

        CityEntity arrivalCity = cityRepository.findById(request.arrivalCityId())
                .orElseThrow(ArrivalCityNotFoundException::new);

        validateSlotAvailability(departureCity.getId(), request.departureTime());
        validateSlotAvailability(arrivalCity.getId(), request.arrivalTime());

        Long currentUserId = JwtUtils.getCurrentUserId();

        FlightEntity flight = buildFlightEntity(request, departureCity, arrivalCity, currentUserId);
        flightRepository.save(flight);

        createAndSaveSlots(flight, departureCity, arrivalCity, request.departureTime(), request.arrivalTime());

        return flightEntityToDomainMapper.map(flight);
    }

    private void validateFlightRequest(CreateFlightRequest request) {
        if (request.departureTime().isAfter(request.arrivalTime())) {
            throw new IllegalArgumentException("Departure time cannot be after arrival time");
        }

        if (request.departureTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Departure time cannot be in the past");
        }

        if (request.departureCityId().equals(request.arrivalCityId())) {
            throw new IllegalArgumentException("Departure and arrival cities cannot be the same");
        }
    }

    private void validateSlotAvailability(Long cityId, LocalDateTime slotTime) {
        LocalDateTime start = slotTime.minusMinutes(SLOT_BUFFER_MINUTES);
        LocalDateTime end = slotTime.plusMinutes(SLOT_BUFFER_MINUTES);

        boolean hasConflicts = slotRepository.existsByCityIdAndSlotTimeBetween(cityId, start, end);
        if (hasConflicts) {
            throw new SlotAlreadyExistException(cityId);
        }
    }

    private FlightEntity buildFlightEntity(CreateFlightRequest request,
                                           CityEntity departureCity,
                                           CityEntity arrivalCity,
                                           Long userId) {
        return FlightEntity.builder()
                .flightNumber(generateFlightNumber())
                .departureCity(departureCity)
                .arrivalCity(arrivalCity)
                .departureTime(request.departureTime())
                .arrivalTime(request.arrivalTime())
                .status(FlightStatus.SCHEDULED)
                .createdBy(userId)
                .build();
    }

    private String generateFlightNumber() {
        return "TK-" + (1000 + (int) (Math.random() * 9000));
    }

    private void createAndSaveSlots(FlightEntity flight,
                                    CityEntity departureCity,
                                    CityEntity arrivalCity,
                                    LocalDateTime departureTime,
                                    LocalDateTime arrivalTime) {
        AirportSlotEntity departureSlot = buildSlot(departureCity, flight, departureTime, SlotType.DEPARTURE);

        AirportSlotEntity arrivalSlot = buildSlot(arrivalCity, flight, arrivalTime, SlotType.ARRIVAL);

        slotRepository.saveAll(List.of(departureSlot, arrivalSlot));
    }

    private AirportSlotEntity buildSlot(CityEntity city,
                                        FlightEntity flight,
                                        LocalDateTime slotTime,
                                        SlotType slotType) {
        return AirportSlotEntity.builder()
                .city(city)
                .flight(flight)
                .slotTime(slotTime)
                .slotType(slotType)
                .build();
    }

}