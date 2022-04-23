package com.face.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.face.controller.dto.createClassDTO;
import com.face.pojo.Class;

public interface IClassService extends IService<Class> {
    Boolean createClass(Class aClass);

    Boolean endClass(Class aClass);

    Integer getClassIdByCode(String code);
}
