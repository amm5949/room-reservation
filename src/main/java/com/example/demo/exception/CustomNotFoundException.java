package com.example.demo.exception;

public class CustomNotFoundException extends RuntimeException{
    public CustomNotFoundException(String message) {
        super(message);
    }

    public CustomNotFoundException(String getClass, long id) {
        super(getClass+" with Id '"+id+"' not found");
    }

    public CustomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomNotFoundException(Throwable cause) {
        super(cause);
    }
}
