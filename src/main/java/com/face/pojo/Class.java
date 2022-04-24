package com.face.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("class")
public class Class {
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;
    private String name;
    private String tid;
    private int end;
    private String code;
    private int type;
}
