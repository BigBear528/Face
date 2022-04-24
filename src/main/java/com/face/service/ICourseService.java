package com.face.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.face.pojo.Course;

public interface ICourseService extends IService<Course> {
    Boolean addCourse(Course course);
}
