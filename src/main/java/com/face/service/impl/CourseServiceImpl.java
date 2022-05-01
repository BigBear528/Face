package com.face.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.face.common.Constants;
import com.face.exception.ServiceException;
import com.face.mapper.CourseMapper;
import com.face.pojo.Course;
import com.face.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {


    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Boolean addCourse(Course course) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", course.getCid());
        queryWrapper.eq("sid", course.getSid());

        Course c;

        c = getOne(queryWrapper); // 从数据库查询用户信息
        if (c == null) {

            int insert = courseMapper.insert(course);
            if(insert>0){
                return true;
            }else {
                throw new ServiceException(Constants.CODE_600, "加入课程失败");
            }
        } else {
            throw new ServiceException(Constants.CODE_400, "课程已存在");
        }


    }
}
