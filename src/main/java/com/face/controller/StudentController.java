package com.face.controller;

import cn.hutool.core.util.StrUtil;
import com.face.common.Constants;
import com.face.common.Result;
import com.face.controller.dto.ChangePasswordDTO;
import com.face.controller.dto.LoginDTO;
import com.face.controller.dto.StudentDTO;
import com.face.pojo.Student;
import com.face.service.IStudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    IStudentService iStudentService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        String id = loginDTO.getId();
        String password = loginDTO.getPassword();
        if (StrUtil.isEmptyIfStr(password) || StrUtil.isEmptyIfStr(id)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }

        StudentDTO studentDTO = iStudentService.login(loginDTO);

        return Result.success(studentDTO);
    }

    @PostMapping("changePassword")
    public Result changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        String id = changePasswordDTO.getId();
        String currentPassword = changePasswordDTO.getCurrentPassword();
        String newPassword = changePasswordDTO.getNewPassword();
        if (StrUtil.isEmptyIfStr(currentPassword) || StrUtil.isEmptyIfStr(id) || StrUtil.isEmptyIfStr(newPassword)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }

        Boolean ismodifySuccessfully = iStudentService.changePassword(changePasswordDTO);

        return Result.success(ismodifySuccessfully);
    }

    @PostMapping("faceUpload")
    public Result faceUpload(@RequestParam("file") MultipartFile file, @RequestParam String id) throws IOException {
//    if(file != null){
////      System.out.println(file);
////      BASE64Encoder base64Encoder =new BASE64Encoder();//base64
////      // 将该字符串保存到数据库即可
////      String baseimg = "data:" + file.getContentType()+";base64," + base64Encoder.encode(file.getBytes());
////
////      return Result.success(baseimg);
//
//    }
//
//    return Result.error();


        InputStream ins = file.getInputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = ins.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.flush();
        byte data[] = bos.toByteArray();
        Student student = new Student();
        student.setId(id);
        student.setFace(data);
        Boolean isUpload = iStudentService.uploadFace(student);

        return Result.success(isUpload);

    }
}
