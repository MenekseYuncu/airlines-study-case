package com.menekse.airlines.model.domain;

import com.menekse.airlines.model.enums.SlotType;

import java.time.LocalDateTime;

public record AirportSlot(
        Long id,
        City city,
        Flight flight,
        LocalDateTime slotTime,
        SlotType slotType
) {
}
