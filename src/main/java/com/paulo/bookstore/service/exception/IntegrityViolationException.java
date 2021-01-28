package com.paulo.bookstore.service.exception;

public class IntegrityViolationException extends RuntimeException {
    public IntegrityViolationException(String message) {
        super(message);
    }

    public IntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
