package com.face.controller.dto;

import lombok.Data;

@Data
public class LeaveRecordDTO {

    private int aid;
    private String sid;
    private int status;
    private int time;
    private String reason;
    private String studentName;
}
