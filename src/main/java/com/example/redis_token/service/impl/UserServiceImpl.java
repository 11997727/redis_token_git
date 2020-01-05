package com.example.redis_token.service.impl;

import com.example.redis_token.entity.User;
import com.example.redis_token.mapper.UserMapper;
import com.example.redis_token.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: redis_token
 * @Package com.example.redis_token.service.impl
 * @Description:
 * @date 2020/1/4 星期六 15:47
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User queryUserByAccountAndPassword(User user) {
        return userMapper.selectUserByAccountAndPassword(user);
    }
}
