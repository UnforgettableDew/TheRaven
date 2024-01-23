package com.unforgettable.theraven.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse { //Basic exception response class
    private String message;
    private HttpStatus httpStatus;
    private Timestamp timestamp;
    private String path;
}