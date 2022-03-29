package com.face.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.face.controller.dto.LoginDTO;
import com.face.pojo.Student;
import com.face.pojo.User;
import org.springframework.stereotype.Service;


public interface ILoginService extends IService<Student> {

  Student login(LoginDTO loginDTO);

}
