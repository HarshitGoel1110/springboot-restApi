package com.example.restapi.exceptions;

public class UserAlreadyExistsException extends Exception{
    public static final long serialVersionUID = 1l;

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
