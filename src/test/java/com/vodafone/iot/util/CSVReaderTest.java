package com.vodafone.iot.util;

import com.vodafone.iot.model.Device;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CSVReaderTest {

    @Test
    @DisplayName("Should read devices from CSV file")
    public void shouldReadDevicesFromCSVFile() {
        String fileName = "C:\\Workspace\\Temp\\data1.csv";

        List<Device> devices = CSVReader.readDevicesFromCSV(fileName);

        assertFalse(devices.isEmpty());
        assertEquals("WG11155638", devices.get(0).getId());
    }

    @Test
    @DisplayName("Should throw exception when file does not exist")
    public void shouldThrowExceptionWhenFileDoesNotExist() {
        String fileName = "nonexistent.csv";

        assertThrows(ResponseStatusException.class, () -> CSVReader.readDevicesFromCSV(fileName));
    }




}
