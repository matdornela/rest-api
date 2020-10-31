package com.randoli.restapi.exception;

public class EntityException extends RuntimeException {

    public EntityException() {
    }

    public EntityException(String message) {
        super(message);
    }

    public EntityException(Throwable throwable) {
        super(throwable);
    }

    public EntityException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
