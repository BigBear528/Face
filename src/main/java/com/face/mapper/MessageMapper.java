package com.face.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.face.pojo.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
