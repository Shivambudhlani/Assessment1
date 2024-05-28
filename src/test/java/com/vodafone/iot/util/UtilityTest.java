package com.vodafone.iot.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilityTest {

    @Test
    @DisplayName("Should convert timestamp to LocalDateTime correctly")
    public void shouldConvertTimestampToLocalDateTimeCorrectly() {
        String timestampStr = "1716813661927";
        LocalDateTime expectedDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(timestampStr)), ZoneOffset.UTC);

        LocalDateTime resultDateTime = Utility.convertTimeStampToLocalDateTime(timestampStr);

        assertEquals(expectedDateTime, resultDateTime);
    }

    @Test
    @DisplayName("Should throw NumberFormatException for non-numeric timestamp")
    public void shouldThrowNumberFormatExceptionForNonNumericTimestamp() {
        String timestampStr = "notANumber";

        assertThrows(NumberFormatException.class, () -> Utility.convertTimeStampToLocalDateTime(timestampStr));
    }


    @DisplayName("Should return battery status as FULL when percentage is 98 or more")
    @Test
    public void shouldReturnBatteryStatusAsFullWhenPercentageIs98OrMore() {
        String batteryStatus = Utility.getBatteryStatus("98");

        assertEquals(Constants.FULL, batteryStatus);
    }

    @DisplayName("Should return battery status as HIGH when percentage is between 60 and 97")
    @Test
    public void shouldReturnBatteryStatusAsHighWhenPercentageIsBetween60And97() {
        String batteryStatus = Utility.getBatteryStatus("60");

        assertEquals(Constants.HIGH, batteryStatus);
    }

    @DisplayName("Should return battery status as MEDIUM when percentage is between 40 and 59")
    @Test
    public void shouldReturnBatteryStatusAsMediumWhenPercentageIsBetween40And59() {
        String batteryStatus = Utility.getBatteryStatus("40");

        assertEquals(Constants.MEDIUM, batteryStatus);
    }

    @DisplayName("Should return battery status as LOW when percentage is between 10 and 39")
    @Test
    public void shouldReturnBatteryStatusAsLowWhenPercentageIsBetween10And39() {
        String batteryStatus = Utility.getBatteryStatus("10");

        assertEquals(Constants.LOW, batteryStatus);
    }

    @DisplayName("Should return battery status as CRITICAL when percentage is less than 10")
    @Test
    public void shouldReturnBatteryStatusAsCriticalWhenPercentageIsLessThan10() {
        String batteryStatus = Utility.getBatteryStatus("9");

        assertEquals(Constants.CRITICAL, batteryStatus);
    }

    @DisplayName("Should return empty string when battery percentage is null")
    @Test
    public void shouldReturnEmptyStringWhenBatteryPercentageIsNull() {
        String batteryStatus = Utility.getBatteryStatus(null);

        assertEquals("", batteryStatus);
    }

    @DisplayName("Should return empty string when battery percentage is empty")
    @Test
    public void shouldReturnEmptyStringWhenBatteryPercentageIsEmpty() {
        String batteryStatus = Utility.getBatteryStatus("");

        assertEquals("", batteryStatus);
    }

    @DisplayName("Should throw NumberFormatException when battery percentage is not a number")
    @Test
    public void shouldThrowNumberFormatExceptionWhenBatteryPercentageIsNotANumber() {
        assertThrows(NumberFormatException.class, () -> Utility.getBatteryStatus("notANumber"));
    }


}
