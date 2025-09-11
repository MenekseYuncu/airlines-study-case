package com.menekse.airlines.model.entity;

import com.menekse.airlines.common.BaseEntity;
import com.menekse.airlines.model.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "od_flight")
public class FlightEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "flight_number", nullable = false, unique = true)
    private String flightNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_city_id", nullable = false)
    private CityEntity departureCity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_city_id", nullable = false)
    private CityEntity arrivalCity;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightStatus status = FlightStatus.SCHEDULED;

    @CreatedBy
    @Column(name = "created_by", nullable = false)
    private Long createdBy;
}
