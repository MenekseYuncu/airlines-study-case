package com.menekse.airlines.repository;

import com.menekse.airlines.model.entity.FlightEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, String> {


    @Query("SELECT f FROM FlightEntity f WHERE " +
            "(:departureCityId IS NULL OR f.departureCity.id = :departureCityId) AND " +
            "(:arrivalCityId IS NULL OR f.arrivalCity.id = :arrivalCityId) AND " +
            "(:flightDate IS NULL OR CAST(f.departureTime AS localdate) = :flightDate) AND " +
            "f.departureTime >= CURRENT_TIMESTAMP " +
            "ORDER BY f.departureTime ASC")
    Page<FlightEntity> searchFlights(
            Long departureCityId,
            Long arrivalCityId,
            LocalDate flightDate,
            Pageable pageable);

    Page<FlightEntity> findAllByOrderByDepartureTimeDesc(Pageable pageable);

    List<FlightEntity> findByDepartureCityIdOrArrivalCityIdOrderByDepartureTimeAsc(Long departureCityId, Long arrivalCityId);

    Page<FlightEntity> findByDepartureCityIdAndDepartureTimeAfterOrderByDepartureTimeAsc(
            Long departureCityId,
            LocalDateTime now,
            Pageable pageable
    );
}
