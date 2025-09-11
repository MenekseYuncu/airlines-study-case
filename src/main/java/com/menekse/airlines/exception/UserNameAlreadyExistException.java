package com.menekse.airlines.exception;

import com.menekse.airlines.common.exceptions.AirlinesAlreadyExistException;

import java.io.Serial;

public class UserNameAlreadyExistException extends AirlinesAlreadyExistException {

    @Serial
    private static final long serialVersionUID = 2112346532533215120L;

    public UserNameAlreadyExistException() {
        super("Username already exists");
    }
}
