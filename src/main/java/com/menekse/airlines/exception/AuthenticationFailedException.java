package com.menekse.airlines.exception;


import com.menekse.airlines.common.exceptions.AirlinesInvalidException;

import java.io.Serial;

public class AuthenticationFailedException extends AirlinesInvalidException {

    @Serial
    private static final long serialVersionUID = 2084284377599862258L;

    public AuthenticationFailedException() {
        super("Invalid username or password");
    }
}
