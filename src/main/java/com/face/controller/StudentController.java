package com.face.controller;

import cn.hutool.core.util.StrUtil;
import com.face.common.Constants;
import com.face.common.Result;
import com.face.controller.dto.*;
import com.face.pojo.Student;
import com.face.service.IStudentService;
import com.face.utils.DistanceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

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

  @PostMapping("/faceUpload")
  public Result faceUpload(@RequestBody faceUploadDTO faceUploadDTO) throws IOException {
    if (faceUploadDTO != null) {
      Student student = new Student();
      student.setId(faceUploadDTO.getId());
      student.setFace(faceUploadDTO.getFace());
      Boolean isUpload = iStudentService.uploadFace(student);
      return Result.success(isUpload);
    }

    return Result.error(Constants.CODE_600, "请选择图片");
  }

  @PostMapping("/distance")
  public Result distance(@RequestBody DistanceDTO distanceDTO){
    double distance = DistanceUtils.isInCircle(distanceDTO.getLon1(), distanceDTO.getLat1(), distanceDTO.getLon2(), distanceDTO.getLat2());
    return Result.success(distance);
  }
}
