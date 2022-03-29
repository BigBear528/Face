package com.face.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.face.pojo.Student;
import com.face.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper extends BaseMapper<Student> {
}
