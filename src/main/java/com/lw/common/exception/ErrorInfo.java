package com.lw.common.exception;

public class ErrorInfo<T> {
    private String statusCode;
    private String message;

    public ErrorInfo() {
    }

    public ErrorInfo(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "[statusCode='" + statusCode + '\'' +
                ", message='" + message + "\']";
    }
}
