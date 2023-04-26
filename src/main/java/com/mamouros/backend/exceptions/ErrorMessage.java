package com.mamouros.backend.exceptions;

import java.util.Date;

public class ErrorMessage {
    private int statusCode;
    private String message;
    private String desc;
    private final Date date;

    public ErrorMessage(int statusCode, String message, String desc) {
        this.statusCode = statusCode;
        this.message = message;
        this.desc = desc;
        this.date = new Date();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDate() {
        return date;
    }
}
