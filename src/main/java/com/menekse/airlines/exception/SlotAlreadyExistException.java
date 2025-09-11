package com.menekse.airlines.exception;

import com.menekse.airlines.common.exceptions.AirlinesAlreadyExistException;

import java.io.Serial;

public class SlotAlreadyExistException extends AirlinesAlreadyExistException {

    @Serial
    private static final long serialVersionUID = -3341485714472927456L;

    public SlotAlreadyExistException(Long cityId) {
        super("Airport slot conflict: Another flight is already scheduled at the specified time for airport " + cityId + ". Please try a different departure/arrival time.");
    }
}
