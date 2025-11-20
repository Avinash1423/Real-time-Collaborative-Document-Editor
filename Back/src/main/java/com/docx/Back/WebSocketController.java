package com.docx.Back;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.SQLOutput;
import java.util.concurrent.CompletableFuture;

@Controller
public class WebSocketController {

     @Autowired
     SimpMessagingTemplate simpMessagingTemplate;

      @Autowired
      RedisClass redisClass;

    @MessageMapping("/server")
    public void handleDocx(String deltaJson ,@Header("docxId") String docxId){

        simpMessagingTemplate.convertAndSend("/queue/"+docxId,deltaJson);

        CompletableFuture.runAsync(()->redisClass.updateRedis(docxId,deltaJson));

    }



}
