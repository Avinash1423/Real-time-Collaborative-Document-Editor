package com.docx.Back;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ExposeDocx {

    @Autowired
    DocxObjectRepository docxObjectRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/expose/{docxId}")
    public JsonNode returnDocx(@PathVariable String docxId) throws JsonProcessingException {


      JsonNode docx= docxObjectRepository.findDeltaById(docxId);

      if(docx==null||docx.isEmpty()){

          return new ObjectMapper().readTree("{}");
      }
      else return docx;


    }
}
