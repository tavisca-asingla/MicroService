package com.tavisca.micro.inputgateway.controller;

import com.tavisca.micro.inputgateway.model.RequestTransaction;
import com.tavisca.micro.inputgateway.repository.RequestRepository;
import com.tavisca.micro.inputgateway.util.RequestValidatior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class MainController {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    RestTemplate restTemplate;

    RequestValidatior requestValidatior = new RequestValidatior();


    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> postUser(@RequestBody(required = false) String requestBody){

        // will transfer this code to another class
        RequestTransaction requestTransaction = new RequestTransaction();
        requestTransaction.setRequestBody(requestBody);
        requestTransaction.setTransactionId(UUID.randomUUID().toString());
        requestTransaction.setValid(true);
        RequestTransaction savedRequest = requestRepository.save(requestTransaction);
        System.out.println(savedRequest.getTransactionId());

        // if request is valid , send to server 2
        if(requestValidatior.isValid(requestBody)){
            System.out.println(requestBody);
            String url = "http://localhost:8089/";
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("transactionId",savedRequest.getTransactionId());
            HttpEntity<String> entity = new HttpEntity<String>(requestBody,headers);
            return restTemplate.exchange(url,HttpMethod.POST,entity,String.class);
        }


        // else dump to exceptions db
        else{
            savedRequest.setValid(false);
            requestRepository.save(savedRequest);
            throw new HttpMessageNotReadableException("request body cannot be null");
        }

    }
}
