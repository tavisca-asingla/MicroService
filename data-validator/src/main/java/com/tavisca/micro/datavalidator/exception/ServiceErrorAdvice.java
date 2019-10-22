package com.tavisca.micro.datavalidator.exception;

import com.tavisca.micro.datavalidator.util.SendExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ServiceErrorAdvice {

    SendExceptionUtil sendExceptionUtil = new SendExceptionUtil();

    @ExceptionHandler(IllegalArgumentException.class)
    public void constraintViolationException(HttpServletResponse response,IllegalArgumentException ex) throws IOException {
        System.out.println(ex.getMessage());


        sendExceptionUtil.sendRequestToExceptionAPI(ex);
        response.sendError(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleAllExceptions(HttpServletResponse response,HttpMessageNotReadableException ex) throws IOException{
        System.out.println(ex.getMessage());

        sendExceptionUtil.sendRequestToExceptionAPI(ex);
        response.sendError(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }


}