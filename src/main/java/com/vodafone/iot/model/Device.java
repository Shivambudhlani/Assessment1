package com.vodafone.iot.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class Device implements Serializable {
    private LocalDateTime datetime;
    private String eventId;
    private String id;
    private String latitude;
    private String longitude;
    private String battery;
    private String light;
    private String airplaneMode;
    private String name;
    private String status;
    private String description;

    public String toString() {
        return "Device [datetime=" + datetime + ", eventId=" + eventId + ", id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", battery=" + battery + ", light=" + light + ", airplaneMode=" + airplaneMode + ", name=" + name + ", status=" + status + ", description=" + description + "]";

    }


}
