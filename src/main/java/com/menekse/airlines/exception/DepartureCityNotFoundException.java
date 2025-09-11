package com.menekse.airlines.exception;

import com.menekse.airlines.common.exceptions.AirlinesNotFoundException;

import java.io.Serial;

public class DepartureCityNotFoundException extends AirlinesNotFoundException {

    @Serial
    private static final long serialVersionUID = 8304368105758129094L;

    public DepartureCityNotFoundException() {
        super("Departure city not found");
    }
}
