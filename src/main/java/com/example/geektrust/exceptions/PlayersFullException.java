package com.example.geektrust.exceptions;

public class PlayersFullException extends RuntimeException {
    public PlayersFullException() {
        super();
    }

    public PlayersFullException(String msg) {
        super(msg);
    }
}
