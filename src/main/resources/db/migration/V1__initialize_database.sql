
CREATE TABLE IF NOT EXISTS od_role
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(20) NOT NULL UNIQUE,
    description VARCHAR(100),
    created_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP            DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS od_city
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS od_user
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    first_name VARCHAR(50)  NOT NULL,
    last_name  VARCHAR(50)  NOT NULL,
    city_id    BIGINT REFERENCES od_city (id),
    role_id    BIGINT NOT NULL REFERENCES od_role (id) DEFAULT 2,
    status     VARCHAR(20)  NOT NULL DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'INACTIVE', 'DELETED')),
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS od_flight
(
    id                VARCHAR(36) PRIMARY KEY DEFAULT gen_random_uuid()::VARCHAR,
    flight_number     VARCHAR(10)  NOT NULL UNIQUE,
    departure_city_id BIGINT       NOT NULL REFERENCES od_city (id),
    arrival_city_id   BIGINT       NOT NULL REFERENCES od_city (id),
    departure_time    TIMESTAMP(0) NOT NULL,
    arrival_time      TIMESTAMP(0) NOT NULL,
    status            VARCHAR(20)  NOT NULL DEFAULT 'SCHEDULED'
    CHECK (status IN ('SCHEDULED', 'BOARDING', 'DEPARTED', 'ARRIVED', 'DELAYED', 'CANCELED', 'DELETED')),
    created_by        BIGINT       NOT NULL REFERENCES od_user (id),
    created_at        TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,

    CHECK (departure_city_id != arrival_city_id),
    CHECK (arrival_time > departure_time)
    );

CREATE TABLE IF NOT EXISTS od_airport_slot
(
    id         BIGSERIAL PRIMARY KEY,
    city_id    BIGINT      NOT NULL REFERENCES od_city (id),
    flight_id  VARCHAR(36) NOT NULL REFERENCES od_flight (id),
    slot_time  TIMESTAMP   NOT NULL,
    slot_type  VARCHAR(10) CHECK (slot_type IN ('DEPARTURE', 'ARRIVAL')),
    created_at TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

CREATE INDEX idx_od_user_username ON od_user(username);
CREATE INDEX idx_od_user_role_id ON od_user(role_id);
CREATE INDEX idx_od_flight_departure_city ON od_flight(departure_city_id);
CREATE INDEX idx_od_flight_arrival_city ON od_flight(arrival_city_id);
CREATE INDEX idx_od_flight_departure_time ON od_flight(departure_time);
CREATE INDEX idx_od_airport_slot_city_time ON od_airport_slot(city_id, slot_time);