package com.face.controller.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChartDataDTO {

//    private List<Long> totalNumberList;
    private List<Integer> countNumberList;
    private List<Long> completedNumberList;
    private List<Long> leaveNumberList;
    private List<Long> immatureNumberList;
    private  List<Double> attendanceRateList;

}
