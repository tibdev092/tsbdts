package com.cloudpoc.departments.exception;


import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler({DepartmentNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handleNotFoundExceptions(RuntimeException e) {
        Map<String, String> responseParams = new LinkedHashMap<>();
        responseParams.put("Reason", e.getMessage());
        responseParams.put("When", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseParams);
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity handle(BindException exception) {
        Map<String, String> responseParams = new LinkedHashMap<>();
        String message = exception.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(" , "));
        responseParams.put("Reason", message);
        responseParams.put("When", LocalDateTime.now().toString());

        return ResponseEntity.badRequest().body(responseParams);
    }
}
