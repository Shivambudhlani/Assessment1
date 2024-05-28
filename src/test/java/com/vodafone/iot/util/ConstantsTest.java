package com.vodafone.iot.util;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstantsTest {

    @Test
    @DisplayName("Should have correct constant values")
    public void shouldHaveCorrectConstantValues() {
        assertEquals("description", Constants.DESCRIPTION);
        assertEquals("ERROR: A technical exception occurred", Constants.INTERNAL_SERVER_ERROR);
        assertEquals("Error: ", Constants.ERROR);
        assertEquals("Device could not be located", Constants.DEVICE_NOT_LOCATED);
        assertEquals("WG", Constants.WG);
        assertEquals("CyclePlusTracker", Constants.CYCLE_PLUS_TRACKER);
        assertEquals("69", Constants.INT_69);
        assertEquals("GeneralTracker", Constants.GENERAL_TRACKER);
        assertEquals("Inactive", Constants.INACTIVE);
        assertEquals("Active", Constants.ACTIVE);
        assertEquals("SUCCESS: Location identified.", Constants.LOCATION_IDENTIFIED);
        assertEquals("SUCCESS: Location not available: Please turn off airplane mode.", Constants.LOCATION_NOT_AVAILABLE);
        assertEquals("Full", Constants.FULL);
        assertEquals("High", Constants.HIGH);
        assertEquals("Medium", Constants.MEDIUM);
        assertEquals("Low", Constants.LOW);
        assertEquals("Critical", Constants.CRITICAL);
        assertEquals("No data file found", Constants.NO_DATA_FILE_FOUND);
        assertEquals(",", Constants.SEPERATOR);
        assertEquals("ON", Constants.ON);
        assertEquals("OFF", Constants.OFF);
    }
}