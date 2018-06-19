package com.test.exceptions;

public class InvalidUserException extends RuntimeException {

    InvalidUserException(String message){
        super(message);
    }

}
