package com.face.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.face.controller.dto.LoginDTO;
import com.face.controller.dto.StudentDTO;
import com.face.pojo.Student;

public interface IStudentService extends IService<Student> {

  StudentDTO login(LoginDTO loginDTO);
}
