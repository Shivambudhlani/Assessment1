package com.vodafone.iot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@AllArgsConstructor
@Setter
@Getter
public class DeviceOutput implements Serializable {
    private String description;

}
