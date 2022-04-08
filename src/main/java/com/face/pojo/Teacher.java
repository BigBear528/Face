package com.face.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("teachers")
public class Teacher {
    private String id;
    private String name;
    private String password;
    private int gender;
    private String face;
}
