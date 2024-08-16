package com.br.jfoodservices.jfood.exception;


import com.br.jfoodservices.jfood.model.data.response.ApiResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiResponseError> handleResourceNotFoundException(ResourceNotFoundException e) {
        ApiResponseError errorResponse = new ApiResponseError(e.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(GeneralException.class)
    @ResponseBody
    public ResponseEntity<ApiResponseError> handleGeneralException(GeneralException e) {
        ApiResponseError errorResponse = new ApiResponseError(e.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
