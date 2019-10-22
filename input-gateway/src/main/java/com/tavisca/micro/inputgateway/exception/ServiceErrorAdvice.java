package com.tavisca.micro.inputgateway.exception;

import com.tavisca.micro.inputgateway.model.ExceptionModel;
import com.tavisca.micro.inputgateway.service.SendExceptionService;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@ControllerAdvice
public class ServiceErrorAdvice {

    @Autowired
    SendExceptionService sendExceptionService;

    @ExceptionHandler(IllegalArgumentException.class)
    public void constraintViolationException(HttpServletResponse response,IllegalArgumentException ex) throws IOException {
        System.out.println(ex.getMessage());


        sendExceptionService.sendRequestToExceptionAPI(ex);
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }


    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public void handleBadRequestRestTemplate(HttpServletResponse response,HttpClientErrorException.BadRequest ex) throws IOException {
        System.out.println(ex.getMessage());


        sendExceptionService.sendRequestToExceptionAPI(ex);
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleAllExceptions(HttpServletResponse response,HttpMessageNotReadableException ex) throws IOException{
        System.out.println(ex.getMessage());

        sendExceptionService.sendRequestToExceptionAPI(ex);
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }


}