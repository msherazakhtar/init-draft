package com.init.draft.repositories;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.concurrent.TimeUnit;

@Repository
public class OtpCacheRepository {
    private final StringRedisTemplate redisTemplate;
    private static final String PREFIX = "otp:";

    public OtpCacheRepository(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String username, String otp, long timeoutInMinutes) {
        redisTemplate.opsForValue().set(PREFIX + username, otp, timeoutInMinutes, TimeUnit.MINUTES);
    }

    public String get(String username) {
        return redisTemplate.opsForValue().get(PREFIX + username);
    }

    public void delete(String username) {
        redisTemplate.delete(PREFIX + username);
    }
}