package com.face.controller.dto;

import lombok.Data;

@Data
public class MessageDTO {

    private String name;
    private int status;
    private String reason;
    private int time;
}
