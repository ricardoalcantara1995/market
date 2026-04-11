package com.rlaal.minimarket.exception;

import jakarta.persistence.EntityNotFoundException;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
