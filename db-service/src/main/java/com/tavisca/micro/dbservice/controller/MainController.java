package com.tavisca.micro.dbservice.controller;

import com.tavisca.micro.dbservice.model.Employee;
import com.tavisca.micro.dbservice.model.RequestTransaction;
import com.tavisca.micro.dbservice.repository.EmployeeRepository;
import com.tavisca.micro.dbservice.repository.RequestTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RequestTransactionRepository requestRepository;

    @PostMapping
    public ResponseEntity<?> saveToDb(@RequestHeader("transactionId") String transactionId, @RequestBody Employee employee){

        System.out.println(transactionId + "is the transactionId");
        RequestTransaction requestTransaction = new RequestTransaction();
        requestTransaction.setRequestBody(employee.toString());
        requestTransaction.setTransactionId(transactionId);
        requestTransaction.setValid(true);

        requestRepository.save(requestTransaction);

        employeeRepository.save(employee);


        return new ResponseEntity<>("Saved Employee", HttpStatus.ACCEPTED);

    }
}
