package com.springproject.blogger.exception.handler;

import com.springproject.blogger.exception.NoElementFoundException;
import com.springproject.blogger.exception.NoPermissionException;
import com.springproject.blogger.exception.RegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoPermissionException.class)
    public ResponseEntity<String> handleNoPermissionException(NoPermissionException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN); // HTTP 403 Forbidden
    }

    @ExceptionHandler(NoElementFoundException.class)
    public ResponseEntity<String> handleNoElementFoundException(NoElementFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // HTTP 404 Not Found
    }

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleRegistrationException(RegistrationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST); // HTTP 404 Not Found
    }
}
