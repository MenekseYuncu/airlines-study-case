package com.menekse.airlines.exception;

import com.menekse.airlines.common.exceptions.AirlinesNotFoundException;

import java.io.Serial;

public class UserNotFoundException extends AirlinesNotFoundException {

    @Serial
    private static final long serialVersionUID = -1142497022968120703L;

    public UserNotFoundException() {
        super("User Not Found");
    }
}
