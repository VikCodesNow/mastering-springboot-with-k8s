package com.master.springboot.series.accounts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFound extends RuntimeException {

    public AccountNotFound(String errorMessage) {
        super(errorMessage);
    }
}
