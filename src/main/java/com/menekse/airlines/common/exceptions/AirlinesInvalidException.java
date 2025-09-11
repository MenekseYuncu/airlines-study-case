package com.menekse.airlines.common.exceptions;

import java.io.Serial;

public class AirlinesInvalidException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 8727916176180667334L;

    public AirlinesInvalidException(String message) {
        super(message);
    }
}
