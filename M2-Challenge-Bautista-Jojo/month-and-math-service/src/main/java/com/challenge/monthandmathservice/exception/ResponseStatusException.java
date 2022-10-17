package com.challenge.monthandmathservice.exception;

public class ResponseStatusException extends RuntimeException{
    public ResponseStatusException() {
        super();
    }

    public ResponseStatusException(String message) {
        super(message);
    }
}
