package com.face.controller.dto;

import lombok.Data;

@Data
public class AddAttendanceDTO {

    private int cid;
    private int startTime;
    private int endTime;
    private double lat;
    private double lon;
    private int type;
    private double radius;
    private String location;
}
