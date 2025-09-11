package com.menekse.airlines.exception;


import com.menekse.airlines.common.exceptions.AirlinesNotFoundException;

import java.io.Serial;

public class RoleNotFoundException extends AirlinesNotFoundException {

    @Serial
    private static final long serialVersionUID = 2486871171390322669L;


    public RoleNotFoundException() {
        super("Role not found");
    }
}
