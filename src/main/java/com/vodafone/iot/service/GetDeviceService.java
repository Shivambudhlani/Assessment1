package com.vodafone.iot.service;

import com.vodafone.iot.model.Device;
import com.vodafone.iot.repository.LocalRepository;
import com.vodafone.iot.util.Constants;
import com.vodafone.iot.util.Utility;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Scope("prototype")
public class GetDeviceService {

    public List<Device> getLocalRepository(){
        return LocalRepository.getDevices();
    }

    public Device getDeviceDetails(String productId, String timestampStr) {
        LocalDateTime dateTime = timestampStr != null ? Utility.convertTimeStampToLocalDateTime(timestampStr) : LocalDateTime.now(ZoneId.of(Constants.UTC));

        List<Device> devices = getLocalRepository().stream()
                .filter(d -> d.getId().equals(productId) && !d.getDatetime().isAfter(dateTime))
                .collect(Collectors.toList());

        IntStream.range(0, devices.size() - 2)
                .filter(i ->
                        devices.get(i).getLatitude().equals(devices.get(i + 1).getLatitude()) &&
                                devices.get(i).getLongitude().equals(devices.get(i + 1).getLongitude()) &&
                                devices.get(i).getLatitude().equals(devices.get(i + 2).getLatitude()) &&
                                devices.get(i).getLongitude().equals(devices.get(i + 2).getLongitude())
                )
                .forEach(i -> devices.get(i + 2).setStatus(Constants.INACTIVE));
        Optional<Device> device =  devices.stream().max(Comparator.comparing(Device::getDatetime));

        if(!device.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id "+ productId +" not found");
        }
        if(device.get().getAirplaneMode().equalsIgnoreCase(Constants.OFF) && device.get().getLatitude().isEmpty()&& device.get().getLongitude().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.DEVICE_NOT_LOCATED);
        }
        addAdditionalAttributes(device.get());
        return device.get();
    }

    private void addAdditionalAttributes(Device device) {
        if(device.getId().startsWith(Constants.WG))
            device.setName(Constants.CYCLE_PLUS_TRACKER);
        else if(device.getId().startsWith(Constants.INT_69))
            device.setName(Constants.GENERAL_TRACKER);
        device.setStatus(device.getName().equalsIgnoreCase(Constants.GENERAL_TRACKER)
                && device.getLatitude().isEmpty()
                && device.getLongitude().isEmpty() ? Constants.INACTIVE : Constants.ACTIVE);

        device.setBattery(Utility.getBatteryStatus(device.getBattery()));
        device.setDescription(Constants.LOCATION_IDENTIFIED);
        if (Constants.ON.equalsIgnoreCase(device.getAirplaneMode())) {
            device.setLongitude("");
            device.setLatitude("");
            device.setStatus(Constants.INACTIVE);
            device.setDescription(Constants.LOCATION_NOT_AVAILABLE);
        }
    }
}

