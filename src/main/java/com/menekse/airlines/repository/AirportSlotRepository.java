package com.menekse.airlines.repository;

import com.menekse.airlines.model.entity.AirportSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportSlotRepository extends JpaRepository<AirportSlotEntity, Long> {
}
