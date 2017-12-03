package com.yarachkin.dock.exception;

public class IoDockException extends Exception {
    public IoDockException() {
    }

    public IoDockException(String message) {
        super(message);
    }

    public IoDockException(String message, Throwable cause) {
        super(message, cause);
    }

    public IoDockException(Throwable cause) {
        super(cause);
    }
}
