package com.docx.Back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;

import java.time.Duration;

@Configuration
public class RedisClass {

     @Autowired
     StringRedisTemplate redisTemplate;

     @Async
    public void updateRedis(String docxId,String deltaJson){

        redisTemplate.opsForValue().set(docxId,deltaJson, Duration.ofMinutes(15));

         System.out.println( "⭕updateRedis"+ redisTemplate.opsForValue().get(docxId));

    }


}


