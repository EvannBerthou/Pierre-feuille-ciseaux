package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.model.ErrorResponse;

@ControllerAdvice
class InvalidGameIdAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidGameIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    ErrorResponse invalidGameIdHandler(InvalidGameIdException ex) {
        return new ErrorResponse(ex.getMessage());
    }
}
