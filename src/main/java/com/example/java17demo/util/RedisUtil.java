package com.example.java17demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisUtil {

    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public String get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public Boolean set(final String key, String value) {
        Boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            log.error("写入缓存出错 ===> " + e.toString());
        }
        return result;
    }

    /**
     * 更新缓存
     * @param key
     * @param value
     * @return
     */
    public String update(final String key, String value) {
        String result = "";
        try {
            result =  redisTemplate.opsForValue().getAndSet(key, value);
        } catch (Exception e) {
            log.error("更新缓存出错 ===> " + e.toString());
        }
        return result;
    }

    /**
     * 删除缓存
     * @param key
     * @return
     */
    public Boolean delete(final String key) {
        Boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            log.error("删除缓存出错 ===> " + e.toString());
        }
        return result;
    }

    /**
     * 是否存在某个key
     * @param key
     * @return
     */
    public Boolean has(final String key) {
        Boolean result = false;
        try {
            redisTemplate.hasKey(key);
            result = true;
        } catch (Exception e) {
            log.error("判断是否存在此缓存出错 ===> " + e.toString());
        }
        return result;
    }

    /**
     * 序列化key
     *
     * @param key
     * @return
     */
    public byte[] dump(String key) {
        return redisTemplate.dump(key);
    }

    /**
     * 返回 key 所储存的值的类型
     *
     * @param key
     * @return
     */
    public DataType type(String key) {
        return redisTemplate.type(key);
    }
}
