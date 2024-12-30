package com.tat1roliv.crudspring.controller;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import org.springframework.http.HttpStatus;

import com.tat1roliv.crudspring.exception.RecordNotFoundException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    
    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public String handleNotFoundException(RecordNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        return ex.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage()).reduce("", (acc, message) -> acc + message + "\n");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException ex) {
      
        return ex.getConstraintViolations().stream().map(ConstraintViolation -> ConstraintViolation.getMessage()).reduce("", (acc, message) -> acc + message + "\n");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
   
        if( ex != null && ex.getRequiredType() != null) {

            String type = ex.getRequiredType().getName();
            // java.lang.Long
            String[] typeParts = type.split("\\.");
            // Long
            String typeName = typeParts[typeParts.length - 1];
            return ex.getName() + " should be of type " + typeName;
        }

        return " Argmuent type not valid ";
    }
    
}
