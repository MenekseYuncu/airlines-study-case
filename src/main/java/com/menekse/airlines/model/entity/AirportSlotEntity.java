package com.menekse.airlines.model.entity;


import com.menekse.airlines.common.BaseEntity;
import com.menekse.airlines.model.enums.SlotType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "od_airport_slot")
public class AirportSlotEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private CityEntity city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private FlightEntity flight;

    @Column(name = "slot_time", nullable = false)
    private LocalDateTime slotTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "slot_type")
    private SlotType slotType;
}
