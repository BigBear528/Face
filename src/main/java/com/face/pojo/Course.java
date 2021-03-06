package com.face.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("course")
public class Course {

    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;
    private String sid;
}
