package com.face.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.face.controller.dto.*;
import com.face.pojo.Record;
import com.face.pojo.Teacher;

import java.util.List;

public interface ITeacherService extends IService<Teacher> {
    TeacherDTO login(LoginDTO loginDTO);

    Boolean changePassword(ChangePasswordDTO changePasswordDTO);

    Boolean uploadFace(Teacher teacher);

    List<LeaveRecordDTO>getLeaveListById(int cid);

    Boolean approvalApplication(ApplicationDTO applicationDTO);
}
