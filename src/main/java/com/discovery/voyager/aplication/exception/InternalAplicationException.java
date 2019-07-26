package com.discovery.voyager.aplication.exception;

public class InternalAplicationException extends RuntimeException  {

    private static final long serialVersionUID = 1L;

    public InternalAplicationException(String message) {
        super(message);
    }

    public InternalAplicationException(String message, Throwable cause) {
        super(message, cause);
    }

}