package com.face.pojo;

import lombok.Data;

@Data
public class Message {
    private int aid;
    private String sid;
    private int status;
    private String reason;
    private int time;
}
