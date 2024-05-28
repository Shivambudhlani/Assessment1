package com.vodafone.iot.util;

import com.vodafone.iot.model.Device;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CSVReader {
    public static List<Device> readDevicesFromCSV(String fileName) {
        List<Device> devices = new LinkedList<>();
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(Constants.SEPERATOR);
                Device device = createDevice(attributes);

                devices.add(device);
                line = br.readLine();
            }
        }catch (NoSuchFileException nsfe){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.NO_DATA_FILE_FOUND, nsfe);
        }
        catch (IOException ioe) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ioe.getCause().toString());
        }

        return devices;
    }

    private static Device createDevice(String[] metadata) {

        Device device = new Device();
        device.setDatetime(Utility.convertTimeStampToLocalDateTime(metadata[0]));
        device.setEventId(metadata[1]);
        device.setId(metadata[2]);
        device.setLatitude(metadata[3]);
        device.setLongitude(metadata[4]);
        device.setBattery(metadata[5]);
        device.setLight(metadata[6]);
        device.setAirplaneMode(metadata[7]);




        return device;
    }


}
