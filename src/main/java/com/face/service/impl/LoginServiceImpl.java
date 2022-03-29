package com.face.service.impl;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.face.controller.dto.LoginDTO;
import com.face.mapper.LoginMapper;
import com.face.mapper.UserMapper;
import com.face.pojo.Student;
import com.face.pojo.User;
import com.face.service.ILoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Student> implements ILoginService {

  private static final Log LOG = Log.get();

  @Resource
  private LoginMapper loginMapper;
  
  @Override
  public Student login(LoginDTO loginDTO) {

    QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("id", loginDTO.getId());
    queryWrapper.eq("password", loginDTO.getPassword());
    Student one;
    try {
      one = getOne(queryWrapper); // 从数据库查询用户信息
    } catch (Exception e) {
      LOG.error(e);
//      throw new ServiceException(Constants.CODE_500, "系统错误");
      return null;
    }
    return one;
  }
}
