package com.face.controller;

import cn.hutool.core.util.StrUtil;
import com.face.common.Constants;
import com.face.common.Result;
import com.face.controller.dto.*;
import com.face.pojo.Teacher;
import com.face.service.ITeacherService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    @PostMapping("/changePassword")
    public Result changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        String id = changePasswordDTO.getId();
        String currentPassword = changePasswordDTO.getCurrentPassword();
        String newPassword = changePasswordDTO.getNewPassword();
        if (StrUtil.isEmptyIfStr(currentPassword) || StrUtil.isEmptyIfStr(id) || StrUtil.isEmptyIfStr(newPassword)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }

        Boolean ismodifySuccessfully = iTeacherService.changePassword(changePasswordDTO);

        return Result.success(ismodifySuccessfully);
    }


    @PostMapping("/faceUpload")
    public Result faceUpload(@RequestBody faceUploadDTO faceUploadDTO) {
        if (faceUploadDTO != null) {
            Teacher teacher = new Teacher();
            teacher.setId(faceUploadDTO.getId());
            teacher.setFace(faceUploadDTO.getFace());
            Boolean isUpload = iTeacherService.uploadFace(teacher);
            return Result.success(isUpload);
        }

        return Result.error(Constants.CODE_600, "请选择图片");
    }

    @PostMapping("/getLeaveListById")
    public Result getLeaveListById(@RequestBody int cid) {
        List<LeaveRecordDTO> list = iTeacherService.getLeaveListById(cid);
        return Result.success(list);
    }

    @PostMapping("/approvalApplication")
    public Result approvalApplication(@RequestBody ApplicationDTO applicationDTO) {
        if (applicationDTO != null) {
            Boolean aBoolean = iTeacherService.approvalApplication(applicationDTO);
            return Result.success(aBoolean);
        } else {
            return Result.error(Constants.CODE_400, "参数错误");
        }
    }

    @PostMapping("/getChartData")
    public Result getChartData(@RequestBody int cid) {
        ChartDataDTO chartData = iTeacherService.getChartData(cid);
        return Result.success(chartData);
    }

    @PostMapping("/getAttendanceSheet")
    public Result getAttendanceSheet(@RequestBody int cid) {
        List<AttendanceSheetDTO> attendanceSheet = iTeacherService.getAttendanceSheet(cid);
        return Result.success(attendanceSheet);
    }
}
