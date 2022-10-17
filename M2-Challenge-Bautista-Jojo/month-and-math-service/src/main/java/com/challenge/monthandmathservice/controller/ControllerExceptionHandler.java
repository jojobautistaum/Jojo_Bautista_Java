package com.challenge.monthandmathservice.controller;

import com.challenge.monthandmathservice.model.CustomErrorResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {ArithmeticException.class})
    public ResponseEntity<CustomErrorResponse> handleArithmeticException(ArithmeticException ex) {
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        String customMessage = ex.getMessage();;
        if(customMessage.contains("/ by zero")) {
            customMessage = "The given number in 'operand1' cannot be divided by zero";
        }

        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, customMessage);
        return new ResponseEntity<>(error, returnHttpStatus);
    }

    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResponseEntity<CustomErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        // Extract the important message between '\'
        String customMessage = ex.getMessage();
        if (customMessage != null) {
            customMessage = customMessage.substring(customMessage.indexOf('\"') + 1, customMessage.lastIndexOf('\"'));
        }

        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, customMessage);
        return new ResponseEntity<>(error, returnHttpStatus);
    }

    @ExceptionHandler(value = {InvalidFormatException.class})
    public ResponseEntity<CustomErrorResponse> handleInvalidFormatException(InvalidFormatException ex) {
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        String customMessage = ex.getMessage();
        if(customMessage.indexOf("operand") > 0) {
            customMessage = "Both operands should be an integer or number";
        }

        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, customMessage);
        return new ResponseEntity<>(error, returnHttpStatus);
    }

    @ExceptionHandler(value = {NumberFormatException.class})
    public ResponseEntity<CustomErrorResponse> handleNumberFormatException(NumberFormatException ex) {
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        String customMessage = ex.getMessage();
        if(customMessage.indexOf("input string") > 0) {
            customMessage = "Both operands should be an integer or a number";
        }

        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, customMessage);
        return new ResponseEntity<>(error, returnHttpStatus);
    }

    @ExceptionHandler(value = {MismatchedInputException.class})
    public ResponseEntity<CustomErrorResponse> handleMismatchedInputException(MismatchedInputException ex) {
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        String customMessage = ex.getMessage();
        if(customMessage != null && customMessage.indexOf("Boolean value") > 0) {
            customMessage = "Boolean is an invalid value for 'operands'. Please assign a number or integer";
        }

        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, customMessage);
        return new ResponseEntity<>(error, returnHttpStatus);
    }

    @ExceptionHandler(value = {JsonParseException.class})
    public ResponseEntity<CustomErrorResponse> handleResponseStatusException(JsonParseException ex) {
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, "Malformed JSON body. " + ex.getMessage());
        return new ResponseEntity<>(error, returnHttpStatus);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<CustomErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        String customMessage = ex.getMessage();
        if (customMessage != null) {
            // "HttpMessageNotReadableException" is often nested to other exceptions
            // We will check if this case is more specific to a particular Exception for a more meaningful message
            if (customMessage.indexOf("body is missing") > 0) {
                customMessage = "Required request JSON body is missing";
            }
            if (customMessage.indexOf("from String") > 0 && customMessage.indexOf("InvalidFormatException") > 0) {
                customMessage = "Both operands should be an integer or number";
            }
            if (customMessage.indexOf("from Boolean") > 0 && customMessage.indexOf("MismatchedInputException") > 0) {
                customMessage = "Boolean is an invalid value for 'operands'. Please assign a number or integer";
            }
        }

        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, customMessage);
        return new ResponseEntity<>(error, returnHttpStatus);
    }
}
