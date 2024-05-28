package com.vodafone.iot.service;


import com.vodafone.iot.model.Device;
import com.vodafone.iot.repository.LocalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetDeviceServiceTest {

    @InjectMocks
    private GetDeviceService getDeviceService;

    @InjectMocks
    private LoadDeviceService loadDeviceService;

    @Mock
    private LocalRepository localRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        loadDeviceService.loadData("C:\\Workspace\\Temp\\data1.csv");
    }

    @DisplayName("Should return device details when device exists")
    @Test
    public void shouldReturnDeviceDetailsWhenDeviceExists() {


        Device result = getDeviceService.getDeviceDetails("WG11155638", null);

        assertEquals("WG11155638", result.getId());
    }

    @DisplayName("Should throw ResponseStatusException when device not found")
    @Test
    public void shouldThrowResponseStatusExceptionWhenDeviceNotFound() {


        assertThrows(ResponseStatusException.class, () -> getDeviceService.getDeviceDetails("WG11155444", null));
    }

    @DisplayName("Should throw ResponseStatusException when device not located")
    @Test
    public void shouldThrowResponseStatusExceptionWhenDeviceNotLocated() {
        assertThrows(ResponseStatusException.class, () -> getDeviceService.getDeviceDetails("6900233112", null));
    }
}
