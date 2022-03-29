package com.face.controller;

import cn.hutool.core.util.StrUtil;
import com.face.controller.dto.LoginDTO;
import com.face.pojo.Student;
import com.face.pojo.User;
import com.face.service.ILoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
public class LoginController {
  @Resource
  private ILoginService iLoginService;

  @PostMapping("/studentLogin")
  public Student studentLogin(@RequestBody LoginDTO loginDTO){
    String id = loginDTO.getId();
    String password = loginDTO.getPassword();
    if(StrUtil.isEmptyIfStr(password) || StrUtil.isEmptyIfStr(id)){
      return null;
    }

    Student student  = iLoginService.login(loginDTO);


    return student;

  }

}
