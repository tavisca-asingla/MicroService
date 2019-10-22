package com.tavisca.micro.dbservice.exception;

import com.tavisca.micro.dbservice.util.SendExceptionUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ServiceErrorAdvice {

    SendExceptionUtil sendExceptionUtil = new SendExceptionUtil();

    @ExceptionHandler(Exception.class)
    public void constraintViolationException(HttpServletResponse response,Exception ex) throws IOException {
        System.out.println(ex.getMessage());


        response.sendError(HttpStatus.BAD_REQUEST.value());
        sendExceptionUtil.sendRequestToExceptionAPI(ex);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleAllExceptions(HttpServletResponse response,HttpMessageNotReadableException ex) throws IOException{
        System.out.println(ex.getMessage());

        response.sendError(HttpStatus.BAD_REQUEST.value());
        sendExceptionUtil.sendRequestToExceptionAPI(ex);
    }


}