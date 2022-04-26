package com.face.controller.dto;

import lombok.Data;

@Data
public class StudentAttendanceDTO {

    private int aid;
    private String className;
    private String code;
    private String teacherName;
    private int type;
    private int startTime;
    private int endTime;
    private double lat;
    private  double lon;
    private String location;
    private double radius;
}
