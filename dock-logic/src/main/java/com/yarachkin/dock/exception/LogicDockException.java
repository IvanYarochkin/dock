package com.yarachkin.dock.exception;

public class LogicDockException extends Exception {
    public LogicDockException() {
    }

    public LogicDockException(String message) {
        super(message);
    }

    public LogicDockException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogicDockException(Throwable cause) {
        super(cause);
    }
}
