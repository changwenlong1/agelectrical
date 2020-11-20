package com.example.order.exception;

public class AddUserException extends RuntimeException{
    public AddUserException() {
    }

    public AddUserException(String message) {
        super(message);
    }

    public AddUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddUserException(Throwable cause) {
        super(cause);
    }

    public AddUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
