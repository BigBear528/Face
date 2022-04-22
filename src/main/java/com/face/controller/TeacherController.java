package com.face.controller;

import cn.hutool.core.util.StrUtil;
import com.face.common.Constants;
import com.face.common.Result;
import com.face.controller.dto.LoginDTO;
import com.face.controller.dto.TeacherDTO;
import com.face.service.ITeacherService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    ITeacherService iTeacherService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        String id = loginDTO.getId();
        String password = loginDTO.getPassword();
        if (StrUtil.isEmptyIfStr(password) || StrUtil.isEmptyIfStr(id)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }

        TeacherDTO teacherDTO = iTeacherService.login(loginDTO);

        return Result.success(teacherDTO);
    }


}
