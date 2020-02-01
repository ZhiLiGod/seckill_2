package com.seckill2.util;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RedisUtil {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  public Object get(@NonNull final String key) {
    return redisTemplate.opsForValue().get(key);
  }

  public boolean set(@NonNull final String key, Object value) {
    boolean result = false;

    try {
      redisTemplate.opsForValue().set(key, value, 10, TimeUnit.SECONDS);
      result = true;
    } catch (Exception e) {
      log.error("ERROR: ", e);
    }

    return result;
  }

  public boolean delete(final String key) {
    boolean result = false;

    try {
      redisTemplate.delete(key);
      result = true;
    } catch (Exception e) {
      log.error("ERROR: ", e);
    }

    return result;
  }

}
