package com.tiddev.sample.service.common.exception;

public class NoResourceException extends RuntimeException {

    public NoResourceException(String message) {
        super(message);
    }
}
