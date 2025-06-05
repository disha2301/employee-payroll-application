package com.gevernova.employee.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

    @RestControllerAdvice // @ControllerAdvice + @ResponseBody
    public class GlobalExceptionHandler {
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException exception) {
            Map<String, String> errors = new HashMap<>();
            exception.getBindingResult()
                    .getFieldErrors()
                    .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
