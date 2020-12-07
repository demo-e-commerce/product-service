package com.example.demo.controller.exception;

import com.example.demo.controller.dto.ApiErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private ResponseEntity<ApiErrorDto> buildErrorResponse(HttpStatus status, Throwable ex) {
        return ResponseEntity.status(status).body(new ApiErrorDto(status, ex.getMessage()));
    }

    @ExceptionHandler(value = {Throwable.class})
    public ResponseEntity<ApiErrorDto> handUnExpectedException(Throwable ex, WebRequest request) {
        LOGGER.error(request.getContextPath(), ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ApiErrorDto> handleNotFoundException(NotFoundException ex, WebRequest request) {
        LOGGER.error(request.getContextPath(), ex);
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ResponseEntity<ApiErrorDto> handleNotFoundException(MissingServletRequestParameterException ex, WebRequest request) {
        LOGGER.error(request.getContextPath(), ex);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
