package com.docx.Back;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AutoSaveService {

    @Autowired
    DocxObjectRepository docxObjectRepository;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Scheduled(fixedRate= 15000)
    public void update(){

     Set<String> Keys=stringRedisTemplate.keys("*");
     if(Keys == null)return;

     for(String docxId:Keys){

         String delta=stringRedisTemplate.opsForValue().get(docxId);


         docxObjectRepository.UpdateDelta(delta,docxId);


     }


    }


}
