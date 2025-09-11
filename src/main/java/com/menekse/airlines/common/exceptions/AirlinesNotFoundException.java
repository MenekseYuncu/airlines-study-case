package com.menekse.airlines.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AirlinesNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2519267781117976117L;

    public AirlinesNotFoundException(String message) {
        super(message);
    }
}