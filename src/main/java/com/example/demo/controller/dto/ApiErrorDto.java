package com.example.demo.controller.dto;

import org.springframework.http.HttpStatus;

public class ApiErrorDto {
    private String error;
    private String message;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ApiErrorDto(HttpStatus error, String message) {
        this.error = error.getReasonPhrase();
        this.message = message;
    }
}
