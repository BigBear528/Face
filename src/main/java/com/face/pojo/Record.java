package com.face.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("record")
public class Record {

    @TableId(value = "aid", type = IdType.AUTO)
    private Integer aid;
    private String sid;
    private int status;
    private int time;
}
