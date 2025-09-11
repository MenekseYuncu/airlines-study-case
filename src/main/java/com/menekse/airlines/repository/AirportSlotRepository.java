package com.menekse.airlines.repository;

import com.menekse.airlines.model.entity.AirportSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AirportSlotRepository extends JpaRepository<AirportSlotEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END " +
            "FROM AirportSlotEntity s " +
            "WHERE s.city.id = :cityId " +
            "AND s.slotTime BETWEEN :startTime AND :endTime")
    boolean existsByCityIdAndSlotTimeBetween(Long cityId,
                                             LocalDateTime startTime,
                                             LocalDateTime endTime);

}
