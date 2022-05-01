package com.face.controller;


import com.face.common.Constants;
import com.face.common.Result;
import com.face.pojo.Course;
import com.face.service.ICourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    ICourseService iCourseService;

    @PostMapping("/addCourse")
    public Result addCourse(@RequestBody Course course){
        if(course!=null){
            Boolean isAdd = iCourseService.addCourse(course);
            return Result.success(isAdd);
        }else {
            return Result.error(Constants.CODE_400,"参数错误");
        }
    }
}
