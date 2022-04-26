package com.face.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.face.pojo.Attendance;

public interface IAttendanceService  extends IService<Attendance> {
    Boolean addAttendance(Attendance attendance);
}
