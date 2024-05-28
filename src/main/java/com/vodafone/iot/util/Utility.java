package com.vodafone.iot.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Utility {


    public static LocalDateTime convertTimeStampToLocalDateTime(String timestampStr) {
        Instant instant = Instant.ofEpochMilli(Long.parseLong(timestampStr));
        return LocalDateTime.ofInstant(instant, ZoneId.of(Constants.UTC));
    }

    public static String getBatteryStatus(String batteryPercentage) {
        if(batteryPercentage == null || batteryPercentage.isEmpty())
            return "";
        double percentage = Double.parseDouble(batteryPercentage);
        if (percentage >= 98) {
            return Constants.FULL;
        } else if (percentage >= 60) {
            return Constants.HIGH;
        } else if (percentage >= 40) {
            return Constants.MEDIUM;
        } else if (percentage >= 10) {
            return Constants.LOW;
        } else {
            return Constants.CRITICAL;
        }
    }


}
