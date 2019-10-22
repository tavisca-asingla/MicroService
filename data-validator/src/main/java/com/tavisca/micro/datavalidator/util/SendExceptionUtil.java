package com.tavisca.micro.datavalidator.util;

import com.tavisca.micro.datavalidator.model.ExceptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SendExceptionUtil {


    private RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<?> sendRequestToExceptionAPI(Exception exception){
        String url = "http://localhost:8092/exceptions";
        ExceptionModel exModel = new ExceptionModel();
        exModel.setMessage(exception.getMessage());
        exModel.setAppName("Data_Validator");
        exModel.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
//        exModel.setStackTrace(exception.getStackTrace());
        HttpEntity<ExceptionModel> entity = new HttpEntity<>(exModel);
        return restTemplate.exchange(url, HttpMethod.POST,entity,String.class);
    }

}
