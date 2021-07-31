package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.model.ErrorResponse;

@ControllerAdvice
class GameEndedAdvice {
    @ResponseBody
    @ExceptionHandler(GameEndedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorResponse gameEndedHandler(GameEndedException ex) {
        return new ErrorResponse(ex.getMessage());
    }
}
