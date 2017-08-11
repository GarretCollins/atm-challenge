package com.atmservice.exceptions;

public class AccountException extends RuntimeException {

    public AccountException(String error) {
        super(error);
    }
}
