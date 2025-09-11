package com.menekse.airlines.common.exceptions;

import java.io.Serial;

public class AirlinesAlreadyExistException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 114440190392085474L;

    public AirlinesAlreadyExistException(String message) {
        super(message);
    }
}
