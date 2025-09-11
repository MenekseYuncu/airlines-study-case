package com.menekse.airlines.exception;

import com.menekse.airlines.common.exceptions.AirlinesNotFoundException;

import java.io.Serial;

public class ArrivalCityNotFoundException extends AirlinesNotFoundException {

    @Serial
    private static final long serialVersionUID = -1329200497781027607L;

    public ArrivalCityNotFoundException() {
        super("Arrival city not found");
    }
}
