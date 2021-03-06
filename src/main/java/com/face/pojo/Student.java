package com.face.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("students")
public class Student {
  private String id;
  private String name;
  private String password;
  private int gender;
  private String face;
  private int img;
}
