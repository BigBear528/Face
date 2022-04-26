package com.face.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.face.controller.dto.ChangePasswordDTO;
import com.face.controller.dto.LoginDTO;
import com.face.controller.dto.StudentAttendanceDTO;
import com.face.controller.dto.StudentDTO;
import com.face.pojo.Student;

import java.util.List;

public interface IStudentService extends IService<Student> {

    StudentDTO login(LoginDTO loginDTO);

    Boolean changePassword(ChangePasswordDTO changePasswordDTO);

    Boolean uploadFace(Student student);

    Boolean faceUploadSuccess(Student student);

    List<StudentAttendanceDTO> getAttendanceById(String sid);
}
