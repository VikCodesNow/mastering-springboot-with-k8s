package com.master.springboot.series.accounts.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExists extends RuntimeException {

    public CustomerAlreadyExists(String message) {
        super(message);
    }
}
