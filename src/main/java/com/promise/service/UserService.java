package com.promise.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.promise.entity.User;

import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService extends IService<User> {
    
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户列表
     */
    List<User> getByUsername(String username);
    
    /**
     * 查询逻辑删除的用户
     * @return 被删除的用户列表
     */
    List<User> getDeleted();
} 