package com.example.redis_token.mapper;

import com.example.redis_token.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: redis_token
 * @Package com.example.redis_token.mapper
 * @Description:
 * @date 2020/1/4 星期六 11:50
 */
@Mapper
public interface UserMapper {
    /**
     * 登录
     * @param user
     * @return
     */
    User selectUserByAccountAndPassword(User user);
}
