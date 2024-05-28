package com.vodafone.iot.exception;

import com.vodafone.iot.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, String> errorBody = new HashMap<>();
        if(ex.getStatus().value() == HttpStatus.INTERNAL_SERVER_ERROR.value()){
            errorBody.put(Constants.DESCRIPTION, Constants.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        errorBody.put(Constants.DESCRIPTION, Constants.ERROR + ex.getReason());
        return new ResponseEntity<>(errorBody, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(Exception ex) {
        Map<String, String> errorBody = new HashMap<>();
        errorBody.put(Constants.DESCRIPTION, Constants.ERROR + "A technical exception occurred");
        return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
