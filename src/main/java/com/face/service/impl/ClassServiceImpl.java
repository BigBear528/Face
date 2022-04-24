package com.face.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.face.common.Constants;
import com.face.controller.dto.GetClassDTO;
import com.face.exception.ServiceException;
import com.face.mapper.ClassMapper;
import com.face.pojo.Class;
import com.face.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements IClassService {

    @Autowired
    private ClassMapper classMapper;


    @Override
    public Boolean createClass(Class aClass) {
        int insert = classMapper.insert(aClass);
        if (insert > 0) {
            return true;
        } else {
            throw new ServiceException(Constants.CODE_600, "创建失败");
        }

    }

    @Override
    public Boolean endClass(Class aClass) {
        QueryWrapper<Class> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", aClass.getCid());
        Class bClass;
        try {
            bClass = getOne(queryWrapper); // 从数据库查询用户信息
            if (bClass != null) {
                UpdateWrapper<Class> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("cid", aClass.getCid()).set("end", 1);
                boolean isUpdate = update(updateWrapper);
                return isUpdate;
            } else {
                throw new ServiceException(Constants.CODE_400, "课程不存在");
            }
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
    }

    @Override
    public Integer getClassIdByCode(String code) {
        QueryWrapper<Class> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", code);
        Class aClass;
        try {
            aClass = getOne(queryWrapper); // 从数据库查询用户信息
            if (aClass != null) {
                return aClass.getCid();
            } else {
                throw new ServiceException(Constants.CODE_400, "课程不存在");
            }
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_400, "课程不存在");
        }
    }

    @Override
    public List<Class> getClasses(GetClassDTO getClassDTO) {
        QueryWrapper<Class> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tid", getClassDTO.getTid());
        queryWrapper.eq("end", getClassDTO.getEnd());
        queryWrapper.eq("type", getClassDTO.getType());
        queryWrapper.like(StringUtils.isNotBlank(getClassDTO.getName()), "name", getClassDTO.getName());

        List<Class> classes = classMapper.selectList(queryWrapper);
        return classes;


    }
}
