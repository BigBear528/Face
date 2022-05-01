package com.face.controller;


import cn.hutool.core.util.StrUtil;
import com.face.common.Constants;
import com.face.common.Result;
import com.face.controller.dto.GetClassDTO;
import com.face.controller.dto.createClassDTO;
import com.face.pojo.Class;
import com.face.service.IClassService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Resource
    IClassService iClassService;

    @PostMapping("/createClass")
    public Result createClass(@RequestBody createClassDTO createClassDTO) {
        if (createClassDTO != null) {
            Class aClass = new Class();
            aClass.setName(createClassDTO.getName());
            aClass.setTid(createClassDTO.getTid());
            aClass.setCode(createClassDTO.getCode());
            aClass.setType(createClassDTO.getType());
            Boolean isCreate = iClassService.createClass(aClass);

            return Result.success(isCreate);
        }

        return Result.error(Constants.CODE_400, "参数错误");
    }

    @PostMapping("/endClass")
    public Result endClass(@RequestBody Integer cid) {
        Class aClass = new Class();
        aClass.setCid(cid);
        Boolean isEnd = iClassService.endClass(aClass);
        return Result.success(isEnd);
    }

    @PostMapping("/getClassIdByCode")
    public Result getClassIdByCode(@RequestBody String code) {

        if (!StrUtil.isEmptyIfStr(code)) {
            Integer cid = iClassService.getClassIdByCode(code);
            return Result.success(cid);
        } else {
            return Result.error(Constants.CODE_400, "参数错误");
        }
    }

    @PostMapping("/getClasses")
    public Result getClasses(@RequestBody GetClassDTO getClassDTO) {

        if (getClassDTO != null) {
            List<Class> classes = iClassService.getClasses(getClassDTO);
            return Result.success(classes);
        } else {
            return Result.error(Constants.CODE_400, "参数错误");
        }

    }

//    @PostMapping("/test")
//    public Result test(){
//        List<Class> classes = new ArrayList<>();
//
//        classes.add(new Class(1,"c++","t1001",0,"123",1));
//        classes.add(new Class(2,"java","t1001",0,"1234",1));
//
//        return Result.success(classes);
//    }

}
