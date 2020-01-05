package com.example.redis_token;

import com.example.redis_token.entity.User;
import com.example.redis_token.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTokenApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setAccount("admin");
        user.setPassword("123456");
        User user1 = userMapper.selectUserByAccountAndPassword(user);
        System.out.println(user1);
    }


}
