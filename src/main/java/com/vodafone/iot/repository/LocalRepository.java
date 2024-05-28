package com.vodafone.iot.repository;

import com.vodafone.iot.model.Device;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class LocalRepository {
    private static List<Device> devices = new LinkedList<>();

    public static List<Device> getDevices() {
        return devices;
    }

    public static void setDevices(List<Device> devices) {
        LocalRepository.devices = devices;
    }
}
