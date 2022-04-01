package com.face.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.face.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
