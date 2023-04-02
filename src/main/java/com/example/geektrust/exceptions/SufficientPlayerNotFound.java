package com.example.geektrust.exceptions;

public class SufficientPlayerNotFound extends RuntimeException {
    public SufficientPlayerNotFound() {
        super();
    }

    public SufficientPlayerNotFound(String msg) {
        super(msg);
    }
}
