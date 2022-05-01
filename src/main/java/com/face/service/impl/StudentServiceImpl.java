package com.face.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.face.common.Constants;
import com.face.controller.dto.*;
import com.face.exception.ServiceException;
import com.face.mapper.*;
import com.face.pojo.*;
import com.face.pojo.Class;
import com.face.service.IStudentService;
import com.face.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    private static final Log LOG = Log.get();

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private AttendanceMapper attendanceMapper;


    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private TeacherMapper teacherMapper;


    @Override
    public StudentDTO login(LoginDTO loginDTO) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", loginDTO.getId());
        queryWrapper.eq("password", loginDTO.getPassword());
        Student student;
        try {
            student = getOne(queryWrapper); // 从数据库查询用户信息
            if (student != null) {
                StudentDTO studentDTO = new StudentDTO();
                BeanUtil.copyProperties(student, studentDTO, true);
                String token = TokenUtils.genToken(student.getId(), student.getPassword());
                studentDTO.setToken(token);
                return studentDTO;
            } else {
                throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
            }
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }

    }

    @Override
    public Boolean changePassword(ChangePasswordDTO changePasswordDTO) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", changePasswordDTO.getId());
        queryWrapper.eq("password", changePasswordDTO.getCurrentPassword());
        Student student;
        try {
            student = getOne(queryWrapper); // 从数据库查询用户信息
            if (student != null) {
//        StudentDTO studentDTO = new StudentDTO();
//        BeanUtil.copyProperties(student, studentDTO, true);
//        String token = TokenUtils.genToken(student.getId(),student.getPassword());
//        studentDTO.setToken(token);
//        return studentDTO;

                UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id", changePasswordDTO.getId()).set("password", changePasswordDTO.getNewPassword());
                boolean isUpdate = update(updateWrapper);
                return isUpdate;


            } else {
                throw new ServiceException(Constants.CODE_600, "密码错误");
            }
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_600, "密码错误");
        }
    }

    @Override
    public Boolean uploadFace(Student student) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", student.getId());
        Student s;
        try {
            s = getOne(queryWrapper); // 从数据库查询用户信息
            if (s != null) {
                UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id", student.getId()).set("face", student.getFace());
                boolean isUpdate = update(updateWrapper);
                return isUpdate;
            } else {
                throw new ServiceException(Constants.CODE_600, "上传失败");
            }
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_600, "上传失败");
        }
    }

    @Override
    public Boolean faceUploadSuccess(Student student) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", student.getId());
        Student s;
        try {
            s = getOne(queryWrapper); // 从数据库查询用户信息
            if (s != null) {
                UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id", student.getId()).set("img", student.getImg());
                boolean isUpdate = update(updateWrapper);
                return isUpdate;
            } else {
                throw new ServiceException(Constants.CODE_600, "上传失败");
            }
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_600, "上传失败");
        }
    }

    @Override
    public List<StudentAttendanceDTO> getAttendanceById(String sid) {
        QueryWrapper<Record> recordQueryWrapper = new QueryWrapper<>();
        recordQueryWrapper.eq("sid", sid);
        recordQueryWrapper.eq("status", 0);
        List<Record> recordList = recordMapper.selectList(recordQueryWrapper);

        List<StudentAttendanceDTO> studentAttendanceDTOList = new ArrayList<>();

        for (int i = 0; i < recordList.size(); i++) {
            Record record = recordList.get(i);

            // 创建一个对象
            StudentAttendanceDTO studentAttendanceDTO = new StudentAttendanceDTO();

            // 保存aid
            studentAttendanceDTO.setAid(record.getAid());

            // 根据aid获取attendance对象
            QueryWrapper<Attendance> attendanceQueryWrapper = new QueryWrapper<>();
            attendanceQueryWrapper.eq("aid", record.getAid());
            Attendance attendance = attendanceMapper.selectOne(attendanceQueryWrapper);

            // 保存数据到对象中
            studentAttendanceDTO.setType(attendance.getType());
            studentAttendanceDTO.setStartTime(attendance.getStartTime());
            studentAttendanceDTO.setEndTime(attendance.getEndTime());
            studentAttendanceDTO.setLat(attendance.getLat());
            studentAttendanceDTO.setLon(attendance.getLon());
            studentAttendanceDTO.setLocation(attendance.getLocation());
            studentAttendanceDTO.setRadius(attendance.getRadius());

            // 根据cid获取class对象
            QueryWrapper<Class> classQueryWrapper = new QueryWrapper<>();
            classQueryWrapper.eq("cid", attendance.getCid());
            Class aClass = classMapper.selectOne(classQueryWrapper);

            // 保存数据到对象中
            studentAttendanceDTO.setClassName(aClass.getName());
            studentAttendanceDTO.setCode(aClass.getCode());

            // 根据tid获取teacher对象
            QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
            teacherQueryWrapper.eq("id", aClass.getTid());
            Teacher teacher = teacherMapper.selectOne(teacherQueryWrapper);

            // 保存数据到对象中
            studentAttendanceDTO.setTeacherName(teacher.getName());

            studentAttendanceDTOList.add(studentAttendanceDTO);
        }

        return studentAttendanceDTOList;
    }

    @Override
    public Boolean faceSuccess(Record record) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("aid", record.getAid());
        queryWrapper.eq("sid", record.getSid());
        Record record1 = recordMapper.selectOne(queryWrapper);

        if (record1 != null) {
//            record1.setTime(record.getTime());
//            record1.setStatus(1);

            UpdateWrapper<Record> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("aid", record.getAid())
                    .eq("sid", record.getSid())
                    .set("status", 1)
                    .set("time", record.getTime());
//            int i = recordMapper.updateById(record1);
            int i = recordMapper.update(null, updateWrapper);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    @Override
    public List<StudentAttendanceDTO> getExpiredList(String sid) {
        QueryWrapper<Record> recordQueryWrapper = new QueryWrapper<>();
        recordQueryWrapper.eq("sid", sid);
        recordQueryWrapper.eq("status", 1);
        List<Record> recordList = recordMapper.selectList(recordQueryWrapper);

        List<StudentAttendanceDTO> studentAttendanceDTOList = new ArrayList<>();

        for (int i = 0; i < recordList.size(); i++) {
            Record record = recordList.get(i);

            // 创建一个对象
            StudentAttendanceDTO studentAttendanceDTO = new StudentAttendanceDTO();

            // 保存aid
            studentAttendanceDTO.setAid(record.getAid());

            // 根据aid获取attendance对象
            QueryWrapper<Attendance> attendanceQueryWrapper = new QueryWrapper<>();
            attendanceQueryWrapper.eq("aid", record.getAid());
            Attendance attendance = attendanceMapper.selectOne(attendanceQueryWrapper);

            // 保存数据到对象中
            studentAttendanceDTO.setType(attendance.getType());
            studentAttendanceDTO.setStartTime(attendance.getStartTime());
            studentAttendanceDTO.setEndTime(attendance.getEndTime());
            studentAttendanceDTO.setLat(attendance.getLat());
            studentAttendanceDTO.setLon(attendance.getLon());
            studentAttendanceDTO.setLocation(attendance.getLocation());
            studentAttendanceDTO.setRadius(attendance.getRadius());

            // 根据cid获取class对象
            QueryWrapper<Class> classQueryWrapper = new QueryWrapper<>();
            classQueryWrapper.eq("cid", attendance.getCid());
            Class aClass = classMapper.selectOne(classQueryWrapper);

            // 保存数据到对象中
            studentAttendanceDTO.setClassName(aClass.getName());
            studentAttendanceDTO.setCode(aClass.getCode());

            // 根据tid获取teacher对象
            QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
            teacherQueryWrapper.eq("id", aClass.getTid());
            Teacher teacher = teacherMapper.selectOne(teacherQueryWrapper);

            // 保存数据到对象中
            studentAttendanceDTO.setTeacherName(teacher.getName());

            studentAttendanceDTOList.add(studentAttendanceDTO);
        }

        return studentAttendanceDTOList;
    }

    @Override
    public Boolean leaveApplication(Record record) {


        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("aid", record.getAid());
        queryWrapper.eq("sid", record.getSid());
        Record record1 = recordMapper.selectOne(queryWrapper);


        if (record1 != null) {
//            record1.setTime(record.getTime());
//            record1.setStatus(2);
//            record1.setReason(record.getReason());

            UpdateWrapper<Record> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("aid", record.getAid())
                    .eq("sid", record.getSid())
                    .set("status", 2)
                    .set("time", record.getTime())
                    .set("reason", record.getReason());
//            int i = recordMapper.updateById(record1);
            int i = recordMapper.update(null, updateWrapper);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public  List<String> getSidListBy(int aid) {
        QueryWrapper<Record> recordQueryWrapper = new QueryWrapper<>();
        recordQueryWrapper.eq("aid", aid);
        List<Record> recordList = recordMapper.selectList(recordQueryWrapper);

        List<String> sidList = new ArrayList<>();

        for (int i = 0; i < recordList.size(); i++) {
            Record record = recordList.get(i);
            sidList.add(record.getSid());
        }

        return sidList;
    }


}
