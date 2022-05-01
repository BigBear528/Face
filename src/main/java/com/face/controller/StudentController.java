package com.face.controller;

import cn.hutool.core.util.StrUtil;
import com.face.common.Constants;
import com.face.common.Result;
import com.face.controller.dto.*;
import com.face.pojo.Record;
import com.face.pojo.Student;
import com.face.service.IStudentService;
import com.face.utils.DistanceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

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

    @PostMapping("/changePassword")
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
    public Result distance(@RequestBody DistanceDTO distanceDTO) {
        double distance = DistanceUtils.isInCircle(distanceDTO.getLon1(), distanceDTO.getLat1(), distanceDTO.getLon2(), distanceDTO.getLat2());
        return Result.success(distance);
    }

    @PostMapping("/faceUploadSuccess")
    public Result faceUploadSuccess(@RequestBody faceUploadSuccessDTO faceUploadSuccessDTO) {

        if (faceUploadSuccessDTO != null) {
            Student student = new Student();
            student.setId(faceUploadSuccessDTO.getId());
            student.setImg(faceUploadSuccessDTO.getStatus());
            Boolean isUpload = iStudentService.faceUploadSuccess(student);
            return Result.success(isUpload);
        }

        return Result.error(Constants.CODE_600, "请上传人脸");
    }

    @PostMapping("/getAttendanceById")
    public Result getAttendanceById(@RequestBody String sid) {
        if (!StrUtil.isEmptyIfStr(sid)) {
            List<StudentAttendanceDTO> list = iStudentService.getAttendanceById(sid);
            return Result.success(list);
        } else {
            return Result.error(Constants.CODE_400, "参数错误");
        }
    }


    @PostMapping("/getExpiredList")
    public Result getExpiredList(@RequestBody String sid) {
        if (!StrUtil.isEmptyIfStr(sid)) {
            List<StudentAttendanceDTO> list = iStudentService.getExpiredList(sid);
            return Result.success(list);
        } else {
            return Result.error(Constants.CODE_400, "参数错误");
        }
    }

    @PostMapping("/faceSuccess")
    public Result faceSuccess(@RequestBody faceSuccessDTO faceSuccessDTO) {
        if (faceSuccessDTO != null) {
            Record record = new Record();
            record.setAid(faceSuccessDTO.getAid());
            record.setSid(faceSuccessDTO.getSid());
            record.setTime(faceSuccessDTO.getTime());
            Boolean isSuccess = iStudentService.faceSuccess(record);

            return Result.success(isSuccess);
        } else {
            return Result.error(Constants.CODE_400, "参数错误");
        }

    }

    @PostMapping("/leaveApplication")
    public Result leaveApplication(@RequestBody LeaveApplicationDTO leaveApplicationDTO) {

        if (leaveApplicationDTO != null) {
            Record record = new Record();
            record.setAid(leaveApplicationDTO.getAid());
            record.setSid(leaveApplicationDTO.getSid());
            record.setTime(leaveApplicationDTO.getTime());
            record.setReason(leaveApplicationDTO.getReason());
            Boolean isSuccess = iStudentService.leaveApplication(record);
            return Result.success(isSuccess);
        } else {
            return Result.error(Constants.CODE_400, "参数错误");
        }
    }

    @PostMapping("/getSidList")
    public Result getSidList(@RequestBody int aid) {

        List<String> sidList = iStudentService.getSidListBy(aid);

        return Result.success(sidList);

    }

    @PostMapping("/faceSuccessMulti")
    public Result faceSuccessMulti(@RequestBody faceSuccessMultiDTO faceSuccessMultiDTO){
        if (faceSuccessMultiDTO != null) {
            Boolean isSuccess = iStudentService.faceSuccessMulti(faceSuccessMultiDTO);

            return Result.success(isSuccess);
        } else {
            return Result.error(Constants.CODE_400, "参数错误");
        }
    }

    @PostMapping("/getAllCourse")
    public Result getAllCourse(@RequestBody String sid){
        List<CourseDTO> allCourse = iStudentService.getAllCourse(sid);

        return Result.success(allCourse);
    }

    @PostMapping("/getAllMessage")
    public Result getAllMessage(@RequestBody String sid){
        List<MessageDTO> allMessage = iStudentService.getAllMessage(sid);
        return Result.success(allMessage);

    }
}
