package com.face.controller.dto;

import lombok.Data;

@Data
public class AttendanceSheetDTO {

    private String name;
    private String sid;
    private int startTime;
    private int endTime;
    private int status;
}
