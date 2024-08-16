package com.br.jfoodservices.jfood.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
