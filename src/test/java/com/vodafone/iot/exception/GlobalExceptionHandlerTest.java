package com.vodafone.iot.exception;


import com.vodafone.iot.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should handle ResponseStatusException with internal server error status")
    public void shouldHandleResponseStatusExceptionWithInternalServerErrorStatus() {
        ResponseStatusException ex = new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Test exception");

        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleResponseStatusException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().get(Constants.DESCRIPTION).contains(Constants.INTERNAL_SERVER_ERROR));
    }

    @Test
    @DisplayName("Should handle ResponseStatusException with non-internal server error status")
    public void shouldHandleResponseStatusExceptionWithNonInternalServerErrorStatus() {
        ResponseStatusException ex = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Test exception");

        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleResponseStatusException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().get(Constants.DESCRIPTION).contains(ex.getReason()));
    }

    @Test
    @DisplayName("Should Handle Generic Exception")
    public void shouldHandleException() {
        Exception ex = new Exception("Test exception");

        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleResponseStatusException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().get(Constants.DESCRIPTION).contains("A technical exception occurred"));
    }

}
