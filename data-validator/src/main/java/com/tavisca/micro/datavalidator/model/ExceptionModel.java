package com.tavisca.micro.datavalidator.model;

import java.sql.Timestamp;

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
}
