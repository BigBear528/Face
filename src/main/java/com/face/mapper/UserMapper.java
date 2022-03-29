package com.face.mapper;

import com.face.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
//@Repository
public interface UserMapper {

    // 查询所有的用户
//    @Select("select * from face_test")
    List<User> queryUserList();

    // 根据用户id查询
    @Select("select * from face_test where id = #{id}")
    User queryUserById(@Param("id") Integer id);

    Integer addUser(User user);

    Integer deleteUserById(Integer id);

    Integer updateUser(User user);
}
