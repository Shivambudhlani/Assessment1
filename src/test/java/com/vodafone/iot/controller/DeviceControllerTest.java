package com.vodafone.iot.controller;

import com.vodafone.iot.model.Device;
import com.vodafone.iot.model.DeviceOutput;
import com.vodafone.iot.model.FilePath;
import com.vodafone.iot.service.GetDeviceService;
import com.vodafone.iot.service.LoadDeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class DeviceControllerTest {

    @Mock
    private GetDeviceService deviceService;

    @Mock
    private LoadDeviceService loadDeviceService;

    @InjectMocks
    private DeviceController deviceController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should load data and return success message")
    public void shouldLoadDataAndReturnSuccessMessage() {
        FilePath filePath = new FilePath();
        filePath.setFilepath("C:\\Workspace\\Temp\\data1.csv");
        ResponseEntity<DeviceOutput> response = deviceController.loadData(filePath);
        assertEquals("200 OK", response.getStatusCode().toString());
    }

    @Test
    @DisplayName("Should return device details when device exists")
    public void shouldReturnDeviceDetailsWhenDeviceExists() {
        String productId = "testProductId";
        String timestamp = "testTimestamp";
        Device device = new Device();
        when(deviceService.getDeviceDetails(productId, timestamp)).thenReturn(device);

        ResponseEntity<Device> response = deviceController.getDeviceDetails(productId, timestamp);

        assertEquals(device, response.getBody());
    }

    @Test
    @DisplayName("Should throw exception when device does not exist")
    public void shouldThrowExceptionWhenDeviceDoesNotExist() {
        String productId = "testProductId";
        String timestamp = "testTimestamp";
        when(deviceService.getDeviceDetails(productId, timestamp)).thenReturn(null);

        assertThrows(ResponseStatusException.class, () -> deviceController.getDeviceDetails(productId, timestamp));
    }
}