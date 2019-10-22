package com.tavisca.micro.exceptionservice.controller;

import com.tavisca.micro.exceptionservice.model.ExceptionModel;
import com.tavisca.micro.exceptionservice.repository.ExceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    ExceptionRepository exceptionRepository;

    @PostMapping("/exceptions")
    public ResponseEntity<?> postException(@RequestBody ExceptionModel exceptionModel){
        ExceptionModel saved = exceptionRepository.save(exceptionModel);
        if(saved != null)
            return new ResponseEntity<>("Saved", HttpStatus.CREATED);
        return new ResponseEntity<>("unable to save",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
