package com.vodafone.iot.service;

import com.vodafone.iot.model.DeviceOutput;
import com.vodafone.iot.util.CSVReader;
import com.vodafone.iot.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadDeviceServiceTest {

    @InjectMocks
    private LoadDeviceService loadDeviceService;

    @Mock
    private CSVReader csvReader;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        MockitoAnnotations.openMocks(CSVReader.class);
    }

    @DisplayName("Should load data and return refreshed message")
    @Test
    public void shouldLoadDataAndReturnRefreshedMessage() {
        //when(CSVReader.readDevicesFromCSV(anyString())).thenAnswer((Answer<LinkedList<Device>>) invocation -> new LinkedList<>());


        DeviceOutput response = loadDeviceService.loadData("C:\\Workspace\\Temp\\data1.csv");

        assertEquals(Constants.DATA_REFRESHED, response.getDescription());
    }
}