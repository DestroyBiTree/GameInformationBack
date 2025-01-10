package com.promise.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.promise.mapper.UserDao;
import com.promise.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> {
    @Autowired
    UserDao userDao;

    public List<User> getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    public List<User> getDeleted() {
        return userDao.getDeleted();
    }
}
