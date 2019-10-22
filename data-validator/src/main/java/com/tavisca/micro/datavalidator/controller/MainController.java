package com.tavisca.micro.datavalidator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavisca.micro.datavalidator.model.Employee;
import com.tavisca.micro.datavalidator.model.RequestTransaction;
import com.tavisca.micro.datavalidator.repository.RequestTransactionRepository;
import com.tavisca.micro.datavalidator.util.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class MainController {

    @Autowired
    RequestTransactionRepository transactionRepository;

//    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    EmployeeValidator employeeValidator = new EmployeeValidator();
    @PostMapping
    public ResponseEntity<?> businessValidation(HttpEntity<String> requestEntity, @RequestHeader("transactionId") String transactionId) throws JsonProcessingException {
        // expecting username, password, designation, salary,  as a string
        // search for transaction id in header

        System.out.println(requestEntity.getBody());
        System.out.println(transactionId);

        // save this request to db
        RequestTransaction requestTransaction = new RequestTransaction();
        requestTransaction.setRequestBody(requestEntity.getBody());
        requestTransaction.setTransactionId(transactionId);


        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = objectMapper.readValue(requestEntity.getBody(),Employee.class);
        requestTransaction.setValid(false);
        RequestTransaction savedRequest = transactionRepository.save(requestTransaction);
        // validate employee
        if(employeeValidator.isValid(employee)){
            //send to db-service
            savedRequest.setValid(true);
            transactionRepository.save(savedRequest);
            String url = "http://localhost:8090/";
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("transactionId",transactionId);
            HttpEntity<Employee> entity = new HttpEntity<Employee>(employee,headers);

            return restTemplate.exchange(url,HttpMethod.POST,entity,String.class);

        }

        return  new ResponseEntity<>("unexpected behaviour", HttpStatus.BAD_REQUEST);
    }
}
