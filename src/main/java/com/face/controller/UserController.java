package com.face.controller;

import com.face.common.Constants;
import com.face.exception.ServiceException;
import com.face.mapper.UserMapper;
import com.face.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryUserList")
    public List<User> queryUserList() {
        List<User> userList = userMapper.queryUserList();
        return userList;
    }

    @PostMapping("/queryUserById")
    public User queryUserById(@RequestParam("id") Integer id) {
        User user = userMapper.queryUserById(id);
        return user;
    }

    @PostMapping("/addUser")
    public Integer addUser(@RequestBody User user) {
        return userMapper.addUser(user);
    }

    @PostMapping("/deleteUserById")
    public Integer deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }

    @PostMapping("/updateUser")
    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
