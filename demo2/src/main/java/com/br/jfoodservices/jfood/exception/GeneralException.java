package com.br.jfoodservices.jfood.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.cloud.firestore.FirestoreException;

@JsonSerialize
public class GeneralException extends Exception {
    public GeneralException(String message) {
        super(message);
    }
}
