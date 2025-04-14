package com.estsoft.demo.controller;

import com.estsoft.demo.exception.NotExistsIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotExistsIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorHandle> handlerNotExistsIdException(NotExistsIdException e) {
        return ResponseEntity.badRequest()
                .body(new ErrorHandle("400", e.getMessage()));
    }
}
