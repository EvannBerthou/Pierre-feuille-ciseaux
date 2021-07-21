package com.example.demo.model;

public class ErrorResponse {
    private String error;
    public ErrorResponse(String _error) {
        this.error = _error;
    }

    public String getError() {
        return error;
    }
}
