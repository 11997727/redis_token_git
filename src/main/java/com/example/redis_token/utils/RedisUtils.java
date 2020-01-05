package com.example.redis_token.utils;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: redis_token
 * @Package com.example.redis_token.utils
 * @Description:
 * @date 2020/1/5 星期日 11:16
 */
@Component
public class RedisUtils {
    @Resource
    private RedisTemplate<String,Object>redisTemplate;

    /**
     * 获取
     * @param key
     * @return
     */
    public Object get(String key){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }

    /**
     * 删除
     * @param key
     */
    public void delete(String key){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.delete(key);
    }

    /**
     * 给一个key设置过期时间
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key,long time){
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                boolean flag=false;
                try{
                    redisTemplate.setKeySerializer(new StringRedisSerializer());
                    redisTemplate.setValueSerializer(new StringRedisSerializer());
                    byte[] serialize = new StringRedisSerializer().serialize(key);
                    flag=redisConnection.expire(serialize,time);
                }catch (Exception e){
                  e.printStackTrace();
                }
                return flag;
            }
        });
    }

    /**
     * 设置
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean set(String key,String value,long time){
        try{
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            ValueOperations<String, Object> vo = redisTemplate.opsForValue();
            vo.set(key,value);
            expire(key, time);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
}
