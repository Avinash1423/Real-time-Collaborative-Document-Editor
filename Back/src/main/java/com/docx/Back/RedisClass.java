package com.docx.Back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisClass {

     @Autowired
     StringRedisTemplate redisTemplate;


    public void updateRedis(String docxId,String deltaJson){

        redisTemplate.opsForValue().set(docxId,deltaJson);


    }


}


