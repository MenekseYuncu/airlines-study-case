package com.menekse.airlines.repository;

import com.menekse.airlines.model.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, String> {

    @Query("SELECT f FROM FlightEntity f WHERE " +
            "(:departureCityId IS NULL OR f.departureCity.id = :departureCityId) AND " +
            "(:arrivalCityId IS NULL OR f.arrivalCity.id = :arrivalCityId) AND " +
            "(:flightDate IS NULL OR CAST(f.departureTime AS localdate) = :flightDate) " +
            "ORDER BY f.departureTime ASC")
    List<FlightEntity> searchFlights(
            Long departureCityId,
            Long arrivalCityId,
            LocalDate flightDate);

    List<FlightEntity> findAllByOrderByDepartureTimeAsc();

    List<FlightEntity> findByDepartureCityIdOrArrivalCityIdOrderByDepartureTimeAsc(Long departureCityId, Long arrivalCityId);

    List<FlightEntity> findByDepartureCityIdOrderByDepartureTimeAsc(Long departureCityId);
}
