package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.example.demo.model.PlayResponse;

@ControllerAdvice
class InvalidPlayAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidPlayException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    PlayResponse employeeNotFoundHandler(InvalidPlayException ex) {
        return new PlayResponse(ex.getMessage());
    }
}
