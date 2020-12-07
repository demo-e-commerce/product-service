package com.example.demo.controller.exception;

import lombok.Getter;

@Getter
public class InvalidOrderException extends Exception {

    public InvalidOrderException(String message) {
        super(message);
    }
}
