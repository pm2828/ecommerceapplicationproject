package com.ecommerce.customer.api.exception;



public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}

