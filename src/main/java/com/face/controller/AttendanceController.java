package com.face.controller;


import com.face.common.Constants;
import com.face.common.Result;
import com.face.controller.dto.AddAttendanceDTO;
import com.face.pojo.Attendance;
import com.face.service.IAttendanceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Resource
    IAttendanceService iAttendanceService;

    @PostMapping("/addAttendance")
    public Result addAttendance(@RequestBody AddAttendanceDTO addAttendanceDTO) {

        if (addAttendanceDTO != null) {
            Attendance attendance = new Attendance();
            attendance.setCid(addAttendanceDTO.getCid());
            attendance.setStartTime(addAttendanceDTO.getStartTime());
            attendance.setEndTime(addAttendanceDTO.getEndTime());
            attendance.setType(addAttendanceDTO.getType());

            if (addAttendanceDTO.getType() == 0) {
                attendance.setLat(addAttendanceDTO.getLat());
                attendance.setLon(addAttendanceDTO.getLon());
                attendance.setRadius(addAttendanceDTO.getRadius());
                attendance.setLocation(addAttendanceDTO.getLocation());
            }

            Boolean isAdd = iAttendanceService.addAttendance(attendance);

            return Result.success(isAdd);
        } else {
            return Result.error(Constants.CODE_400, "参数错误");
        }


    }
}
