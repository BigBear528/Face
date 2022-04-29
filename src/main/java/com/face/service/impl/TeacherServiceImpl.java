package com.face.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.face.common.Constants;
import com.face.controller.dto.*;
import com.face.exception.ServiceException;
import com.face.mapper.*;
import com.face.pojo.*;
import com.face.pojo.Class;
import com.face.service.ITeacherService;
import com.face.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private RecordMapper recordMapper;


    @Autowired
    private StudentMapper studentMapper;


    @Override
    public TeacherDTO login(LoginDTO loginDTO) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", loginDTO.getId());
        queryWrapper.eq("password", loginDTO.getPassword());

        Teacher teacher;

        try {
            teacher = getOne(queryWrapper);

            if (teacher != null) {
                TeacherDTO teacherDTO = new TeacherDTO();
                BeanUtil.copyProperties(teacher, teacherDTO, true);
                String token = TokenUtils.genToken(teacher.getId(), teacher.getPassword());
                teacherDTO.setToken(token);
                return teacherDTO;
            } else {
                throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
            }

        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
    }

    @Override
    public Boolean changePassword(ChangePasswordDTO changePasswordDTO) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", changePasswordDTO.getId());
        queryWrapper.eq("password", changePasswordDTO.getCurrentPassword());

        Teacher teacher;

        try {
            teacher = getOne(queryWrapper); // 从数据库查询用户信息

            if (teacher != null) {
                UpdateWrapper<Teacher> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id", changePasswordDTO.getId()).set("password", changePasswordDTO.getNewPassword());
                boolean isUpdate = update(updateWrapper);
                return isUpdate;
            } else {
                throw new ServiceException(Constants.CODE_600, "密码错误");
            }

        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_600, "密码错误");
        }
    }

    @Override
    public Boolean uploadFace(Teacher teacher) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", teacher.getId());

        Teacher t;

        try {
            t = getOne(queryWrapper); // 从数据库查询用户信息
            if (t != null) {
                UpdateWrapper<Teacher> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id", teacher.getId()).set("face", teacher.getFace());
                boolean isUpdate = update(updateWrapper);
                return isUpdate;
            } else {
                throw new ServiceException(Constants.CODE_600, "上传失败");
            }
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_600, "上传失败");
        }
    }

    @Override
    public List<LeaveRecordDTO> getLeaveListById(int cid) {
        QueryWrapper<Attendance> attendanceQueryWrapper = new QueryWrapper<>();
        attendanceQueryWrapper.eq("cid", cid);
        List<Attendance> attendanceList = attendanceMapper.selectList(attendanceQueryWrapper);

        List<LeaveRecordDTO> list = new ArrayList<>();

        for (int i = 0; i < attendanceList.size(); i++) {
            Attendance attendance = attendanceList.get(i);

            QueryWrapper<Record> recordQueryWrapper = new QueryWrapper<>();
            recordQueryWrapper.eq("aid", attendance.getAid());
            recordQueryWrapper.eq("status", 2);
            List<Record> recordList = recordMapper.selectList(recordQueryWrapper);

            for (int j = 0; j < recordList.size(); j++) {
                Record record = recordList.get(j);
                LeaveRecordDTO leaveRecordDTO = new LeaveRecordDTO();
                leaveRecordDTO.setAid(record.getAid());
                leaveRecordDTO.setSid(record.getSid());
                leaveRecordDTO.setReason(record.getReason());
                leaveRecordDTO.setStatus(record.getStatus());
                leaveRecordDTO.setTime(record.getTime());

                QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
                studentQueryWrapper.eq("id",record.getSid());

                Student student = studentMapper.selectOne(studentQueryWrapper);
                leaveRecordDTO.setStudentName(student.getName());

                list.add(leaveRecordDTO);
            }

        }

        return list;


    }

    @Override
    public Boolean approveApplication(ApplicationDTO applicationDTO) {
        QueryWrapper<Record> recordQueryWrapper = new QueryWrapper<>();
        recordQueryWrapper.eq("aid",applicationDTO.getAid());
        recordQueryWrapper.eq("sid",applicationDTO.getSid());

        Record record = recordMapper.selectOne(recordQueryWrapper);
        if(record!=null){
           record.setTeacherReason(applicationDTO.getReason());
            UpdateWrapper<Record> recordUpdateWrapper = new UpdateWrapper<>();
            recordUpdateWrapper.eq("aid", applicationDTO.getAid()).eq("sid",applicationDTO.getSid()).set("status", applicationDTO.getStatus()).set("teacherReason",applicationDTO.getReason());
//            boolean update = update(recordUpdateWrapper);
            int i = recordMapper.update(null, recordUpdateWrapper);
            if(i>0){
                return true;
            }

        }

        return false;
    }
}

