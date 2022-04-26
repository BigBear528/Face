package com.face.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("attendance")
public class Attendance {
    @TableId(value = "aid", type = IdType.AUTO)
    private int aid;
    private int cid;
    private int startTime;
    private int endTime;
    private double lat;     // 维度
    private double lon;     // 精度
    private int type;
    private int count;
    private double radius;
    private String location;
}
