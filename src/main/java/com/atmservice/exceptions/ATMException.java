package com.atmservice.exceptions;

public class ATMException extends RuntimeException {

    public ATMException(String error) {
        super(error);
    }
}
