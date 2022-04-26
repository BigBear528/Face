package com.face.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.face.controller.dto.*;
import com.face.pojo.Record;
import com.face.pojo.Student;

import java.util.List;

public interface IStudentService extends IService<Student> {

    StudentDTO login(LoginDTO loginDTO);

    Boolean changePassword(ChangePasswordDTO changePasswordDTO);

    Boolean uploadFace(Student student);

    Boolean faceUploadSuccess(Student student);

    List<StudentAttendanceDTO> getAttendanceById(String sid);

    Boolean faceSuccess(Record record);
}
