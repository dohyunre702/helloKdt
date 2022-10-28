package com.springboot.hello.controller;


import com.springboot.hello.dao.UserDao;
import com.springboot.hello.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/user/")
    public User addAndGet() throws SQLException {
        userDao.add(new User("1","dd","1212"));
        //return userDao.findById("1");
    }
}
