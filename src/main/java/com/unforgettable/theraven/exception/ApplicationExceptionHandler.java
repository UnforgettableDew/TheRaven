package com.unforgettable.theraven.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {CustomerNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(Exception exception,
                                                                  HttpServletRequest request) {
        HttpStatus httpStatus = NOT_FOUND;
        ExceptionResponse response = getExceptionResponse(exception.getMessage(), request.getRequestURI(), httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {jakarta.validation.ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception,
                                                                     HttpServletRequest request) {
        HttpStatus httpStatus = BAD_REQUEST;
        List<String> violationsList = exception.getConstraintViolations()
                .stream().map(ConstraintViolation::getMessage).toList();

        ExceptionResponse response = getExceptionResponse(violationsList.toString(), request.getRequestURI(), httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                        HttpServletRequest request) {
        HttpStatus httpStatus = BAD_REQUEST;

        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).toList();

        ExceptionResponse response = getExceptionResponse(errors.toString(), request.getRequestURI(), httpStatus);


        return new ResponseEntity<>(response, httpStatus);
    }

    private ExceptionResponse getExceptionResponse(String exceptionMessage, String requestURI, HttpStatus httpStatus) {

        return ExceptionResponse.builder()
                .message(exceptionMessage)
                .httpStatus(httpStatus)
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .path(requestURI)
                .build();
    }

}
