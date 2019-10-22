package com.tavisca.micro.inputgateway.model;

import java.sql.Timestamp;
import java.util.Date;

public class ExceptionModel {
    private String message;
//    private StackTraceElement[] stackTrace;
//    private String path;
    private Timestamp timestamp;

    private String appName;


    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

//    public StackTraceElement[] getStackTrace() {
//        return stackTrace;
//    }
//
//    public void setStackTrace(StackTraceElement[] stackTrace) {
//        this.stackTrace = stackTrace;
//    }
}
