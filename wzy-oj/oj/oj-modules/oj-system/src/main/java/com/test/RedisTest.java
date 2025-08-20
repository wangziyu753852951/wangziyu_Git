package com.test;

/**
 * ClassName: RedisTest
 * Description
 *
 * @Author wzy
 * @Create 2025/8/2 11:51
 * @Version 1.0
 */
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RedisTest implements CommandLineRunner {
    private final StringRedisTemplate redisTemplate;

    public RedisTest(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void run(String... args) {
        try {
            redisTemplate.opsForValue().set("test", "success");
            System.out.println("Redis连接成功！");
        } catch (Exception e) {
            System.err.println("Redis连接失败: " + e.getMessage());
        }
    }
}