package com.example.demo.controller.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidOrderException extends Exception {

    private List<String> notFoundProductCodes;
    private List<String> outOfStockProductCodes;

    public InvalidOrderException(List<String> notFoundProductCodes, List<String> outOfStockProductCodes) {
        this.notFoundProductCodes = notFoundProductCodes;
        this.outOfStockProductCodes = outOfStockProductCodes;
    }
}
