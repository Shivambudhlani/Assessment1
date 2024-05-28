package com.vodafone.iot.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FilePath implements Serializable {
    private String filepath;
}