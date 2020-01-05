package com.example.redis_token.service;

import com.example.redis_token.entity.User;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: redis_token
 * @Package com.example.redis_token.service
 * @Description:
 * @date 2020/1/4 星期六 13:46
 */
public interface UserService {
    /**
     * 登录
     * @param user
     * @return
     */
    User queryUserByAccountAndPassword(User user);
}
