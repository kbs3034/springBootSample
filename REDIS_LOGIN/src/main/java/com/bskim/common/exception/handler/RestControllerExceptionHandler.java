package com.bskim.common.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bskim.common.dto.Result;

@RestControllerAdvice
public class RestControllerExceptionHandler {
	
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(Exception e) {
        Result result = new Result(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}