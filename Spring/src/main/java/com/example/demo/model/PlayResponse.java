package com.example.demo.model;

public class PlayResponse {
    private String error;
    public PlayResponse(String _error) {
        this.error = _error;
    }

    public String getError() {
        return error;
    }
}
