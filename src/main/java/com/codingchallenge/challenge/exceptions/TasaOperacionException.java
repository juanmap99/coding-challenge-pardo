package com.codingchallenge.challenge.exceptions;

import org.springframework.http.HttpStatus;

public class TasaOperacionException extends Exception{
    public TasaOperacionException(String msg){
        super(msg);
    }

    public HttpStatus getStatus(){
        return HttpStatus.BAD_REQUEST;
    }
}
