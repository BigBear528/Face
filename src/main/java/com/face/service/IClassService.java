package com.face.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.face.controller.dto.GetClassDTO;
import com.face.controller.dto.createClassDTO;
import com.face.pojo.Class;

import java.util.List;

public interface IClassService extends IService<Class> {
    Boolean createClass(Class aClass);

    Boolean endClass(Class aClass);

    Integer getClassIdByCode(String code);

    List<Class> getClasses(GetClassDTO getClassDTO);
}
