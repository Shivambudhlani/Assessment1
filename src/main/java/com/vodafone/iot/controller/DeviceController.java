package com.vodafone.iot.controller;

import com.vodafone.iot.model.Device;
import com.vodafone.iot.model.DeviceOutput;
import com.vodafone.iot.model.FilePath;
import com.vodafone.iot.service.GetDeviceService;
import com.vodafone.iot.service.LoadDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/iot/event/v1")
public class DeviceController {
    @Autowired
    private GetDeviceService deviceService;

    @Autowired
    private LoadDeviceService loadDeviceService;


    @PostMapping
    public ResponseEntity<DeviceOutput> loadData(@RequestBody FilePath filePath) {
        return ResponseEntity.ok(loadDeviceService.loadData(filePath.getFilepath()));
    }

    @GetMapping
    public ResponseEntity<Device> getDeviceDetails(@RequestParam String productId, @RequestParam(required = false) String timestamp) {
        Device device = deviceService.getDeviceDetails(productId, timestamp);
        if (device == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id " + productId + " not found");
        }
        return ResponseEntity.ok(device);
    }

}
