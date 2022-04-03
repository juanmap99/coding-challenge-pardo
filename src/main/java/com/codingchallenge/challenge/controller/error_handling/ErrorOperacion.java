package com.codingchallenge.challenge.controller.error_handling;

import org.springframework.http.HttpStatus;

public class ErrorOperacion {
    public HttpStatus status;
    public String message;
    public String time;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
