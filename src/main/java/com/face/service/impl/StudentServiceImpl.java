package com.face.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.face.common.Constants;
import com.face.controller.dto.ChangePasswordDTO;
import com.face.controller.dto.LoginDTO;
import com.face.controller.dto.StudentDTO;
import com.face.exception.ServiceException;
import com.face.mapper.StudentMapper;
import com.face.pojo.Student;
import com.face.service.IStudentService;
import com.face.utils.TokenUtils;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
  private static final Log LOG = Log.get();

  @Override
  public StudentDTO login(LoginDTO loginDTO) {
    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("id", loginDTO.getId());
    queryWrapper.eq("password", loginDTO.getPassword());
    Student student;
    try {
      student = getOne(queryWrapper); // 从数据库查询用户信息
      if(student != null){
        StudentDTO studentDTO = new StudentDTO();
        BeanUtil.copyProperties(student, studentDTO, true);
        String token = TokenUtils.genToken(student.getId(),student.getPassword());
        studentDTO.setToken(token);
        return studentDTO;
      }else {
        throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
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
      if(student != null){
//        StudentDTO studentDTO = new StudentDTO();
//        BeanUtil.copyProperties(student, studentDTO, true);
//        String token = TokenUtils.genToken(student.getId(),student.getPassword());
//        studentDTO.setToken(token);
//        return studentDTO;

        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",changePasswordDTO.getId()).set("password",changePasswordDTO.getNewPassword());
        boolean isUpdate = update(updateWrapper);
        return isUpdate;


      }else {
        throw new ServiceException(Constants.CODE_600,"密码错误");
      }
    } catch (Exception e) {
      LOG.error(e);
      throw new ServiceException(Constants.CODE_600,"密码错误");
    }
  }

  @Override
  public Boolean uploadFace(Student student) {
    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("id", student.getId());
    Student s;
    try {
      s = getOne(queryWrapper); // 从数据库查询用户信息
      if(s != null){
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",student.getId()).set("face",student.getFace());
        boolean isUpdate = update(updateWrapper);
        return isUpdate;
      }else {
        throw new ServiceException(Constants.CODE_600,"上传失败");
      }
    } catch (Exception e) {
      LOG.error(e);
      throw new ServiceException(Constants.CODE_600,"上传失败");
    }
  }

}
