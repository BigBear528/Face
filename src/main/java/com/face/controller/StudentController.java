package com.face.controller;

import cn.hutool.core.util.StrUtil;
import com.face.common.Constants;
import com.face.common.Result;
import com.face.controller.dto.LoginDTO;
import com.face.controller.dto.StudentDTO;
import com.face.pojo.Student;
import com.face.service.IStudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student")
public class StudentController {

  @Resource
  IStudentService iStudentService;

  @PostMapping("/login")
  public Result login(@RequestBody LoginDTO loginDTO){
    String id = loginDTO.getId();
    String password = loginDTO.getPassword();
    if(StrUtil.isEmptyIfStr(password) || StrUtil.isEmptyIfStr(id)){
      return Result.error(Constants.CODE_400,"参数错误");
    }

    StudentDTO studentDTO  = iStudentService.login(loginDTO);

    return Result.success(studentDTO);
  }
}
