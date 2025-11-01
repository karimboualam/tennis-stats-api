package com.atelier.tennis.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(Long id) {
        super("Player not found with ID: " + id);
    }
}
