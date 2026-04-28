package com.ihor.artist_portfolio_server.controller;

import com.ihor.artist_portfolio_server.exception.ConflictException;
import com.ihor.artist_portfolio_server.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handleConflict(ConflictException e) {
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        String message = e.getMessage();

        if (message.equals("Email already exists")) {
            return ResponseEntity.status(400).body(message);
        }
        if (message.equals("Invalid password") || message.equals("User not found")) {
            return ResponseEntity.status(401).body(message);
        }
        if (message.equals("Email not verified")) {
            return ResponseEntity.status(403).body(message);
        }

        return ResponseEntity.status(500).body(message);
    }
}
