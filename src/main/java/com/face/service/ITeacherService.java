package com.face.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.face.controller.dto.LoginDTO;
import com.face.controller.dto.TeacherDTO;
import com.face.pojo.Teacher;

public interface ITeacherService extends IService<Teacher> {
    TeacherDTO login(LoginDTO loginDTO);
}
