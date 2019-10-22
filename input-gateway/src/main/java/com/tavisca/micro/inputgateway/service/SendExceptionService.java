package com.tavisca.micro.inputgateway.service;

import com.tavisca.micro.inputgateway.model.ExceptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class SendExceptionService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<?> sendRequestToExceptionAPI(Exception exception){
        String url = "http://localhost:8092/exceptions";
        ExceptionModel exModel = new ExceptionModel();
        exModel.setMessage(exception.getMessage());
        exModel.setAppName("Input_Gateway");
        exModel.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
//        exModel.setStackTrace(exception.getStackTrace());
        HttpEntity<ExceptionModel> entity = new HttpEntity<>(exModel);
        return restTemplate.exchange(url, HttpMethod.POST,entity,String.class);
    }

}
