package com.vodafone.iot.service;

import com.vodafone.iot.model.DeviceOutput;
import com.vodafone.iot.repository.LocalRepository;
import com.vodafone.iot.util.CSVReader;
import com.vodafone.iot.util.Constants;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class LoadDeviceService {

    public DeviceOutput loadData(String filePath) {
        LocalRepository.setDevices(CSVReader.readDevicesFromCSV(filePath));
        return new DeviceOutput(Constants.DATA_REFRESHED);
    }
}
