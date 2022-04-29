package com.face.controller.dto;

import lombok.Data;

@Data
public class LeaveApplicationDTO {

    private int aid;
    private String sid;
    private int time;
    private  String reason;
}
