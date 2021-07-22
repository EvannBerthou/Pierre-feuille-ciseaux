package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.model.ErrorResponse;

@ControllerAdvice
class SamePlayerAdvice {
    @ResponseBody
    @ExceptionHandler(SamePlayerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    ErrorResponse samePlayerHandler(SamePlayerException ex) {
        return new ErrorResponse(ex.getMessage());
    }
}
