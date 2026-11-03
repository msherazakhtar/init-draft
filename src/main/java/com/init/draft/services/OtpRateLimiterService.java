package com.expense.tracker.services;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class OtpRateLimiterService {

    private final StringRedisTemplate redisTemplate;
    private static final int MAX_ATTEMPTS = 3;
    private static final long BLOCK_DURATION_MINUTES = 15;

    public OtpRateLimiterService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Checks if the user is allowed to generate an OTP.
     * @param identifier Can be phoneNumber, email, or username.
     * @return true if allowed, false if blocked.
     */
    public boolean isAllowed(String identifier) {
        String key = "otp:count:" + identifier;

        // Fetch current count
        String currentCountStr = redisTemplate.opsForValue().get(key);

        if (currentCountStr == null) {
            // First attempt: Set count to 1 and set 15-minute expiration
            redisTemplate.opsForValue().set(key, "1", BLOCK_DURATION_MINUTES, TimeUnit.MINUTES);
            return true;
        }

        int currentCount = Integer.parseInt(currentCountStr);

        if (currentCount >= MAX_ATTEMPTS) {
            // User exceeded 3 attempts within the window
            return false;
        }

        // Increment the count, maintaining the original TTL
        redisTemplate.opsForValue().increment(key);
        return true;
    }

    /**
     * Optional: Get remaining block time to show a nice message to the user
     */
    public long getRemainingTimeMinutes(String identifier) {
        String key = "otp:count:" + identifier;
        Long expire = redisTemplate.getExpire(key, TimeUnit.MINUTES);
        return (expire != null && expire > 0) ? expire : BLOCK_DURATION_MINUTES;
    }
}