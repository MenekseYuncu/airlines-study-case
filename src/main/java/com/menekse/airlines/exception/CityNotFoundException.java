package com.menekse.airlines.exception;


import com.menekse.airlines.common.exceptions.AirlinesNotFoundException;

import java.io.Serial;

public class CityNotFoundException extends AirlinesNotFoundException {

    @Serial
    private static final long serialVersionUID = 2598406406779271223L;


    public CityNotFoundException() {
        super("City not found");
    }
}
