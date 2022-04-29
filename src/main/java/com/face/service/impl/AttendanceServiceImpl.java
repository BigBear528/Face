package com.face.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.face.common.Constants;
import com.face.exception.ServiceException;
import com.face.mapper.AttendanceMapper;
import com.face.mapper.CourseMapper;
import com.face.mapper.RecordMapper;
import com.face.pojo.Attendance;
import com.face.pojo.Course;
import com.face.pojo.Record;
import com.face.service.IAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements IAttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public Boolean addAttendance(Attendance attendance) {
        QueryWrapper<Attendance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", attendance.getCid());
        int count = attendanceMapper.selectCount(queryWrapper).intValue();


        attendance.setCount(count + 1);

        System.out.println(attendance.getCount());

        int insert = attendanceMapper.insert(attendance);

        // 获取到新建考勤的id
        int aid = attendance.getAid();
//        System.out.println("++++++"+aid);

        // 根据课程id(cid)获取学生id(sid)
        QueryWrapper<Course> queryWrapperCourse = new QueryWrapper<>();
        queryWrapperCourse.eq("cid", attendance.getCid());

        List<Course> courseList = courseMapper.selectList(queryWrapperCourse);


        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            Record record = new Record();
            record.setAid(attendance.getAid());
            record.setSid(course.getSid());
            recordMapper.insert(record);
        }

        if (insert > 0) {
            return true;
        } else {
            throw new ServiceException(Constants.CODE_600, "发布失败");
        }
    }
}
