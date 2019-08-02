package com.discovery.voyager.aplication.exception;

public class InternalApplicationException extends RuntimeException  {

    private static final long serialVersionUID = 1L;

    public InternalApplicationException(String message) {
        super(message);
    }

    public InternalApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalApplicationException() {

    }
}