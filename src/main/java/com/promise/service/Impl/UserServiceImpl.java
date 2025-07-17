package com.promise.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.promise.mapper.UserDao;
import com.promise.entity.User;
import com.promise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public List<User> getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public List<User> getDeleted() {
        return userDao.getDeleted();
    }
}
