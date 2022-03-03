package com.example.restapi.exceptions;

public class UserNameNotFoundException extends Exception{
    private static final long serialVersionUID = 1l;

    public UserNameNotFoundException(String message) {
        super(message);
    }
}
